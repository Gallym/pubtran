package org.jguniverse.pidapplicationgm.view

import org.jguniverse.pidapplicationgm.utils.StopRenderer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.data.geojson.GeoJsonLayer
import org.jguniverse.pidapplicationgm.R
import org.jguniverse.pidapplicationgm.repo.model.Stop
import org.jguniverse.pidapplicationgm.utils.StopLoader
import org.jguniverse.pidapplicationgm.utils.BitmapHelper


class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnPolylineClickListener {

    private lateinit var mMap: GoogleMap
    private var layer: GeoJsonLayer? = null
    private val stops: List<Stop> by lazy {
        StopLoader(this).load()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        prepareMap(googleMap)
    }

    private fun prepareMap(map: GoogleMap) {
        mMap = map
        mMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                        this, R.raw.map_style))


        // Add a marker and move the camera
        val prague = LatLng(50.073658, 14.418540)
        //loadMarkers(mMap)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(prague, 15f))


//        val bounds = LatLngBounds(
//                LatLng((49.974726), 14.709795), // NE bounds
//                LatLng((50.147639), 14.233386)  // SW bounds
//        )
//        map.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 0))
        addClusteredMarkers(mMap)
    }

    private fun loadMarkers(map: GoogleMap) {
        val prague = LatLng(50.073658, 14.418540)
        map.addMarker(MarkerOptions().position(prague).title("Prague"))
        loadStops(map)
        map.setOnMarkerClickListener { marker -> // on marker click we are getting the title of our marker
            loadRoute(map)
            false
        }
    }

    /**
     * Adds markers to the map with clustering support.
     */
    private fun addClusteredMarkers(map: GoogleMap) {
        // Create the ClusterManager class and set the custom renderer
        val clusterManager = ClusterManager<Stop>(this, map)
        clusterManager.renderer =
                StopRenderer(
                        this,
                        map,
                        clusterManager
                )

        // Set custom info window adapter
        //clusterManager.markerCollection.setInfoWindowAdapter(MarkerInfoWindowAdapter(this))

        // Add the places to the ClusterManager
        val stop1 = Stop("Václavské náměstí", LatLng(50.08167, 14.42528))
        val stop2 = Stop("Václavské náměstí", LatLng(50.08196, 14.42572))
        clusterManager.addItems(listOf(stop1, stop2))
        clusterManager.cluster()

        // Show polygon
        clusterManager.setOnClusterItemClickListener { item ->
            loadRoute(map)
            return@setOnClusterItemClickListener false
        }

        // When the camera starts moving, change the alpha value of the marker to translucent
        map.setOnCameraMoveStartedListener {
            clusterManager.markerCollection.markers.forEach { it.alpha = 0.3f }
            clusterManager.clusterMarkerCollection.markers.forEach { it.alpha = 0.3f }
        }

        map.setOnCameraIdleListener {
            // When the camera stops moving, change the alpha value back to opaque
            clusterManager.markerCollection.markers.forEach { it.alpha = 1.0f }
            clusterManager.clusterMarkerCollection.markers.forEach { it.alpha = 1.0f }

            // Call clusterManager.onCameraIdle() when the camera stops moving so that re-clustering
            // can be performed when the camera stops moving
            clusterManager.onCameraIdle()
        }
    }

    private fun loadStops(map: GoogleMap) {
        // call backend endpoint
        // parse response
        // load stops

        val stop1 = LatLng(50.08167, 14.42528)
        val stop2 = LatLng(50.08196, 14.42572)

        map.addMarker(
                MarkerOptions()
                        .position(stop1)
                        .title("Václavské náměstí")
                        .icon(busIcon))
        map.addMarker(
                MarkerOptions()
                        .position(stop2)
                        .title("Václavské náměstí 2")
                        .icon(busIcon))
    }

    private fun loadRoute(map: GoogleMap) {
        val polyline1: Polyline = map.addPolyline(PolylineOptions()
                .clickable(true)
                .add(
                        LatLng(50.085165410265276, 14.42500591278076),
                        LatLng(50.08704474330281, 14.428052902221678),
                        LatLng(50.08861423970004, 14.428600072860718),
                        LatLng(50.09129190218363, 14.427666664123535),
                        LatLng(50.09514637209521, 14.426765441894531),
                        LatLng(50.09806455021389, 14.437322616577147)))
        polyline1.tag = "route"
        polyline1.color = -0x1000000
        polyline1.width = 12.toFloat()
        map.setOnPolylineClickListener(this)



//        if (layer == null || !layer!!.isLayerOnMap) {
//            layer = GeoJsonLayer(map, R.raw.route, this)
//
//            layer!!.addLayerToMap()
//        } else {
//            layer!!.removeLayerFromMap()
//        }
    }

    private val busIcon: BitmapDescriptor by lazy {
        val color = ContextCompat.getColor(this, R.color.black)
        BitmapHelper.vectorToBitmap(this, R.drawable.ic_baseline_directions_bus_24, color)
    }

    override fun onPolylineClick(p: Polyline?) {
        p?.remove()
    }
}