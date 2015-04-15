package com.ui.rollingscene

import org.scalatest.{BeforeAndAfterAll, FunSuite}
import scala.collection.immutable.Queue
import java.awt.Point

class RollingSceneTest extends ImageCacheDependantTest {

    val display = RollingSceneCoverage(DisplayWindow(200, 100))
    val chopper = new Chopper(new Point, ChopperVelocity(2,0), ChopperImages.imageList)

    test("given a series of vertical bars then a rolling scene can be created") {
        val rollingScene = new RollingScene(Hills(Queue.empty, display), chopper)
    }

    test("given a rolling scene when no bars then call to refresh adds the first bar") {
        val bars = Hills(Queue.empty, display)

        val rollingScene = new RollingScene(bars,chopper)

        assert(1===rollingScene.refresh.verticalBars.count)
    }

    test("given a rolling scene when not enough vertical bars on screen then call to refresh adds more bars") {
        val velocity = ColumnVelocity(-1,-0,1)

        assert(
            new RollingScene(Hills(Queue.empty, display, velocity), chopper)
            .refresh
            .refresh
            .refresh
            .refresh
            .refresh.verticalBars.count > 0
        )
    }
}
