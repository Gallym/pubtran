package org.jguniverse.pidapplicationgm.repo.model

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

data class Stop(
        val id: Long,
        val name: String,
        val latLng: LatLng,
    ) : ClusterItem {

    override fun getPosition(): LatLng =
            latLng

    override fun getTitle(): String =
            name

    override fun getSnippet(): String =
            name
}
