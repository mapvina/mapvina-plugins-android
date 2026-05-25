package io.github.mapvina.android.plugins.annotation;

import androidx.annotation.Nullable;
import io.github.mapvina.android.style.layers.Layer;
import io.github.mapvina.android.style.sources.GeoJsonOptions;
import io.github.mapvina.android.style.sources.GeoJsonSource;

interface CoreElementProvider<L extends Layer> {

    String getLayerId();

    String getSourceId();

    L getLayer();

    GeoJsonSource getSource(@Nullable GeoJsonOptions geoJsonOptions);
}
