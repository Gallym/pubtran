package org.jguniverse.pidapplicationgm.utils

import org.jguniverse.pidapplicationgm.repo.model.Stop

import android.content.Context
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import org.jguniverse.pidapplicationgm.R

/**
 * A custom cluster renderer for Stop objects.
 */
class StopRenderer(
        private val context: Context,
        map: GoogleMap,
        clusterManager: ClusterManager<Stop>
) : DefaultClusterRenderer<Stop>(context, map, clusterManager) {

    /**
     * The icon to use for each cluster item
     */
    private val bicycleIcon: BitmapDescriptor by lazy {
        val color = ContextCompat.getColor(context,
                R.color.black
        )
        BitmapHelper.vectorToBitmap(
                context,
                R.drawable.ic_baseline_directions_bus_24,
                color
        )
    }

    /**
     * Method called before the cluster item (i.e. the marker) is rendered. This is where marker
     * options should be set
     */
    override fun onBeforeClusterItemRendered(item: Stop, markerOptions: MarkerOptions) {
        markerOptions.title(item.name)
                .position(item.latLng)
                .icon(bicycleIcon)
    }

    /**
     * Method called right after the cluster item (i.e. the marker) is rendered. This is where
     * properties for the Marker object should be set.
     */
    override fun onClusterItemRendered(clusterItem: Stop, marker: Marker) {
        marker.tag = clusterItem
    }
}