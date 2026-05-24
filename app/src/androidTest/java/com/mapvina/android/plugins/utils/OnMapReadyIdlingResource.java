package com.mapvina.android.plugins.utils;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.test.espresso.IdlingResource;
import com.mapvina.android.maps.MapVinaMap;
import com.mapvina.android.maps.MapView;
import com.mapvina.android.maps.OnMapReadyCallback;
import com.mapvina.android.maps.Style;
import com.mapvina.android.plugins.testapp.R;

public class OnMapReadyIdlingResource implements IdlingResource, OnMapReadyCallback {

    private MapVinaMap mapVinaMap;
    private MapView mapView;
    private IdlingResource.ResourceCallback resourceCallback;

    public OnMapReadyIdlingResource(Activity activity) {
        new Handler(Looper.getMainLooper()).post(() -> {
            mapView = activity.findViewById(R.id.mapView);
            if (mapView != null) {
                mapView.getMapAsync(OnMapReadyIdlingResource.this);
            }
        });
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }

    @Override
    public boolean isIdleNow() {
        return mapVinaMap != null && mapVinaMap.getStyle() != null && mapVinaMap.getStyle().isFullyLoaded();
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
        this.resourceCallback = resourceCallback;
    }

    public MapView getMapView() {
        return mapView;
    }

    public MapVinaMap getMapVinaMap() {
        return mapVinaMap;
    }

    @Override
    public void onMapReady(@NonNull MapVinaMap mapVinaMap) {
        this.mapVinaMap = mapVinaMap;
        mapVinaMap.setStyle(Style.getPredefinedStyle("Streets"), style -> {
            if (resourceCallback != null) {
                resourceCallback.onTransitionToIdle();
            }
        });
    }
}