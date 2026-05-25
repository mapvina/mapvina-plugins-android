package io.github.mapvina.android.maps

import android.graphics.RectF
import io.github.mapvina.android.geometry.LatLng
import io.github.mapvina.android.geometry.LatLngBounds
import io.github.mapvina.android.style.expressions.Expression
import io.github.mapvina.geojson.Feature

/**
 * Queries the map for rendered features
 *
 * @param latLng      the location to query
 * @param layerIds    optionally - only query these layers
 * @return the list of feature
 */
inline fun MapVinaMap.queryRenderedFeatures(
    latLng: LatLng,
    vararg layerIds: String,
): List<Feature> {
    return this.queryRenderedFeatures(latLng, null, *layerIds)
}

/**
 * Queries the map for rendered features
 *
 * @param latLng      the location to query
 * @param filter      filters the returned features with an expression
 * @param layerIds    optionally - only query these layers
 * @return the list of feature
 */
inline fun MapVinaMap.queryRenderedFeatures(
    latLng: LatLng,
    filter: Expression?,
    vararg layerIds: String,
): List<Feature> {
    return this.queryRenderedFeatures(this.projection.toScreenLocation(latLng), filter, *layerIds)
}

/**
 * Queries the map for rendered features
 *
 * @param latLngBounds the bounds to query
 * @param layerIds     optionally - only query these layers
 * @return the list of feature
 */
inline fun MapVinaMap.queryRenderedFeatures(
    latLngBounds: LatLngBounds,
    vararg layerIds: String,
): List<Feature> {
    return this.queryRenderedFeatures(latLngBounds, null, *layerIds)
}

/**
 * Queries the map for rendered features
 *
 * @param latLngBounds the bounds to query
 * @param layerIds     optionally - only query these layers
 * @return the list of feature
 */
inline fun MapVinaMap.queryRenderedFeatures(
    latLngBounds: LatLngBounds,
    filter: Expression?,
    vararg layerIds: String,
): List<Feature> {
    val topRight = this.projection.toScreenLocation(latLngBounds.northEast)
    val bottomLeft = this.projection.toScreenLocation(latLngBounds.southWest)
    val rectF = RectF(bottomLeft.x, topRight.y, topRight.x, bottomLeft.y)
    return this.queryRenderedFeatures(rectF, filter, *layerIds)
}
