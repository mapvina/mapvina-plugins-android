package io.github.mapvina.android.plugins.testapp.activity.ktx.maps

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.github.mapvina.android.geometry.LatLng
import io.github.mapvina.android.maps.MapVinaMap
import io.github.mapvina.android.maps.MapView
import io.github.mapvina.android.maps.OnMapReadyCallback
import io.github.mapvina.android.maps.queryRenderedFeatures
import io.github.mapvina.android.plugins.testapp.TestStyles
import io.github.mapvina.android.plugins.testapp.databinding.ActivityMapsKtxBinding

class MapVinaKtxActivity : AppCompatActivity(), OnMapReadyCallback, MapVinaMap.OnMapClickListener {

    private var mapvinaMap: MapVinaMap? = null

    private lateinit var binding: ActivityMapsKtxBinding
    private lateinit var mapView: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsKtxBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mapView = binding.mapView
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
    }

    override fun onMapReady(mapvinaMap: MapVinaMap) {
        this.mapvinaMap = mapvinaMap
        mapvinaMap.setStyle(TestStyles.BRIGHT.url) {
            mapvinaMap.addOnMapClickListener(this)
            Toast.makeText(this, "Click on the map", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onMapClick(point: LatLng): Boolean {
        val features = mapvinaMap?.queryRenderedFeatures(point)
        features?.first().let {
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        }
        return true
    }

    public override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    public override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapvinaMap?.removeOnMapClickListener(this)
        mapView.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }
}
