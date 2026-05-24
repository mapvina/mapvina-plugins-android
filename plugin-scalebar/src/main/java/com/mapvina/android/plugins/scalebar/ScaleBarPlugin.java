package com.mapvina.android.plugins.scalebar;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.annotation.VisibleForTesting;
import com.mapvina.android.camera.CameraPosition;
import com.mapvina.android.log.Logger;
import com.mapvina.android.maps.MapVinaMap;
import com.mapvina.android.maps.MapView;
import com.mapvina.android.maps.Projection;

/**
 * Plugin class that shows a scale bar on MapView and changes the scale corresponding to the MapView's scale.
 */
public class ScaleBarPlugin {
    private static final String TAG = "Mbgl-ScaleBarPlugin";

    private final MapView mapView;
    private final MapVinaMap mapVinaMap;
    private final Projection projection;
    private boolean enabled = true;
    private ScaleBarWidget scaleBarWidget;

    @VisibleForTesting
    final MapVinaMap.OnCameraMoveListener cameraMoveListener = new MapVinaMap.OnCameraMoveListener() {
        @Override
        public void onCameraMove() {
            invalidateScaleBar();
        }
    };

    @VisibleForTesting
    final MapVinaMap.OnCameraIdleListener cameraIdleListener = new MapVinaMap.OnCameraIdleListener() {
        @Override
        public void onCameraIdle() {
            invalidateScaleBar();
        }
    };

    public ScaleBarPlugin(@NonNull MapView mapView, @NonNull MapVinaMap mapvinaMap) {
        this.mapView = mapView;
        this.mapVinaMap = mapvinaMap;
        this.projection = mapvinaMap.getProjection();
    }

    /**
     * Create a scale bar widget on mapView.
     *
     * @param option The scale bar widget options that used to build scale bar widget.
     * @return The created ScaleBarWidget instance.
     */
    public ScaleBarWidget create(@NonNull ScaleBarOptions option) {
        if (scaleBarWidget != null) {
            mapView.removeView(scaleBarWidget);
        }
        scaleBarWidget = option.build();
        scaleBarWidget.setMapViewWidth(mapView.getWidth());
        mapView.addView(scaleBarWidget);

        scaleBarWidget.setVisibility(enabled ? View.VISIBLE : View.GONE);
        if (enabled) {
            addCameraListeners();
            invalidateScaleBar();
        }
        return scaleBarWidget;
    }

    /**
     * Returns true if the scale plugin is currently enabled and visible.
     *
     * @return true if enabled, false otherwise
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Toggles the scale plugin state.
     * <p>
     * If the scale plugin wasn enabled, a {@link com.mapvina.android.maps.MapVinaMap.OnCameraMoveListener}
     * will be added to the {@link MapView} to listen to scale change events to update the state of this plugin. If the
     * plugin was disabled the {@link com.mapvina.android.maps.MapVinaMap.OnCameraMoveListener}
     * will be removed from the map.
     * </p>
     */
    @UiThread
    public void setEnabled(boolean enabled) {
        if (scaleBarWidget == null) {
            Logger.w(TAG, "Create a widget before changing ScalebBarPlugin's state. Ignoring.");
            return;
        }
        if (this.enabled == enabled) {
            // already in correct state
            return;
        }
        this.enabled = enabled;
        scaleBarWidget.setVisibility(enabled ? View.VISIBLE : View.GONE);
        if (enabled) {
            addCameraListeners();
            invalidateScaleBar();
        } else {
            removeCameraListeners();
        }
    }

    private void invalidateScaleBar() {
        CameraPosition cameraPosition = mapVinaMap.getCameraPosition();
        scaleBarWidget.setDistancePerPixel((projection.getMetersPerPixelAtLatitude(cameraPosition.target.getLatitude()))
            / mapView.getPixelRatio());
    }

    private void addCameraListeners() {
        mapVinaMap.addOnCameraMoveListener(cameraMoveListener);
        mapVinaMap.addOnCameraIdleListener(cameraIdleListener);
    }

    private void removeCameraListeners() {
        mapVinaMap.removeOnCameraMoveListener(cameraMoveListener);
        mapVinaMap.removeOnCameraIdleListener(cameraIdleListener);
    }
}
