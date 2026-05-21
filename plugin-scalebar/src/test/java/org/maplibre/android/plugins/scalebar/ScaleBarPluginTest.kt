package com.mapvina.android.plugins.scalebar

import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics
import android.view.View
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import com.mapvina.android.camera.CameraPosition
import com.mapvina.android.maps.MapVinaMap
import com.mapvina.android.maps.MapView
import com.mapvina.android.maps.Projection

class ScaleBarPluginTest {
    @MockK
    lateinit var mapView: MapView

    @MockK
    lateinit var projection: Projection

    @MockK
    lateinit var mapvinaMap: MapVinaMap

    @MockK
    lateinit var scaleBarOptions: ScaleBarOptions

    @MockK
    lateinit var scaleBarWidget: ScaleBarWidget

    @MockK
    lateinit var context: Context

    @MockK
    lateinit var resources: Resources

    @MockK
    lateinit var displayMetrics: DisplayMetrics

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        displayMetrics.density = 2f
        every { mapView.width } returns 1000
        every { CameraPosition.DEFAULT.target?.let { projection.getMetersPerPixelAtLatitude(it.latitude) } } returns 100_000.0
        every { mapvinaMap.projection } returns projection
        every { mapvinaMap.cameraPosition } returns CameraPosition.DEFAULT
        every { scaleBarOptions.build() } returns scaleBarWidget
        every { mapView.context } returns context
        every { mapView.pixelRatio } returns 2f
        every { context.resources } returns resources
        every { resources.displayMetrics } returns displayMetrics
    }

    @Test
    fun sanity() {
        assertNotNull(mapView)
        assertNotNull(mapvinaMap)
        assertNotNull(scaleBarOptions)
        assertNotNull(scaleBarWidget)
    }

    @Test
    fun create_isEnabled() {
        val scaleBarPlugin =
            ScaleBarPlugin(mapView, mapvinaMap)
        scaleBarPlugin.create(scaleBarOptions)

        assertTrue(scaleBarPlugin.isEnabled)
        verify { scaleBarWidget.visibility = View.VISIBLE }
        verify(exactly = 1) { mapvinaMap.addOnCameraMoveListener(scaleBarPlugin.cameraMoveListener) }
        verify(exactly = 1) { mapvinaMap.addOnCameraIdleListener(scaleBarPlugin.cameraIdleListener) }
    }

    @Test
    fun disable() {
        val scaleBarPlugin =
            ScaleBarPlugin(mapView, mapvinaMap)
        scaleBarPlugin.create(scaleBarOptions)
        scaleBarPlugin.isEnabled = false

        assertFalse(scaleBarPlugin.isEnabled)
        verify { scaleBarWidget.visibility = View.GONE }
        verify { mapvinaMap.removeOnCameraMoveListener(scaleBarPlugin.cameraMoveListener) }
        verify { mapvinaMap.removeOnCameraIdleListener(scaleBarPlugin.cameraIdleListener) }
    }

    @Test
    fun enable() {
        val scaleBarPlugin =
            ScaleBarPlugin(mapView, mapvinaMap)
        scaleBarPlugin.create(scaleBarOptions)
        verify(exactly = 1) { mapvinaMap.addOnCameraMoveListener(scaleBarPlugin.cameraMoveListener) }
        verify(exactly = 1) { mapvinaMap.addOnCameraIdleListener(scaleBarPlugin.cameraIdleListener) }
        verify(exactly = 1) { scaleBarWidget.visibility = View.VISIBLE }
        scaleBarPlugin.isEnabled = false
        scaleBarPlugin.isEnabled = true

        assertTrue(scaleBarPlugin.isEnabled)
        verify(exactly = 2) { scaleBarWidget.visibility = View.VISIBLE }
        verify(exactly = 2) { mapvinaMap.addOnCameraMoveListener(scaleBarPlugin.cameraMoveListener) }
        verify(exactly = 2) { mapvinaMap.addOnCameraIdleListener(scaleBarPlugin.cameraIdleListener) }
    }

    @Test
    fun disable_enable_widgetIsNull() {
        val scaleBarPlugin =
            ScaleBarPlugin(mapView, mapvinaMap)
        scaleBarPlugin.isEnabled = false
        scaleBarPlugin.isEnabled = true

        verify(exactly = 0) { mapvinaMap.addOnCameraMoveListener(scaleBarPlugin.cameraMoveListener) }
        verify(exactly = 0) { mapvinaMap.addOnCameraIdleListener(scaleBarPlugin.cameraIdleListener) }
    }

    @Test
    fun disableBeforeCreate_ignoreResults() {
        val scaleBarPlugin =
            ScaleBarPlugin(mapView, mapvinaMap)
        scaleBarPlugin.isEnabled = false
        scaleBarPlugin.create(scaleBarOptions)

        assertTrue(scaleBarPlugin.isEnabled)
        verify { scaleBarWidget.visibility = View.VISIBLE }
        verify(exactly = 1) { mapvinaMap.addOnCameraMoveListener(scaleBarPlugin.cameraMoveListener) }
        verify(exactly = 1) { mapvinaMap.addOnCameraIdleListener(scaleBarPlugin.cameraIdleListener) }
    }

    @Test
    fun toggled_invalidateWidget() {
        val scaleBarPlugin =
            ScaleBarPlugin(mapView, mapvinaMap)
        scaleBarPlugin.create(scaleBarOptions)
        verify(exactly = 1) { mapvinaMap.cameraPosition }
        verify(exactly = 1) { scaleBarWidget.setDistancePerPixel(50_000.0) }
        scaleBarPlugin.isEnabled = false
        scaleBarPlugin.isEnabled = true

        verify(exactly = 2) { mapvinaMap.cameraPosition }
        verify(exactly = 2) { scaleBarWidget.setDistancePerPixel(50_000.0) }
    }
}
