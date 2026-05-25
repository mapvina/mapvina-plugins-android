package io.github.mapvina.android.maps

import io.github.mapvina.android.geometry.LatLng
import io.github.mapvina.geojson.Point

/**
 * Returns a LatLng representation
 *
 * @return the latitude longitude pair
 */
inline fun LatLng.toPoint(): Point {
    return Point.fromLngLat(this.longitude, this.latitude)
}

/**
 * Returns a Point representation
 *
 * @return the point from the latitude longitude pair
 */
inline fun Point.toLatLng(): LatLng {
    return LatLng(this.latitude(), this.longitude())
}
