package com.mapvina.android.plugins.annotation;

import androidx.annotation.Nullable;
import com.mapvina.android.style.layers.Layer;
import com.mapvina.android.style.sources.GeoJsonOptions;
import com.mapvina.android.style.sources.GeoJsonSource;

interface CoreElementProvider<L extends Layer> {

    String getLayerId();

    String getSourceId();

    L getLayer();

    GeoJsonSource getSource(@Nullable GeoJsonOptions geoJsonOptions);
}
