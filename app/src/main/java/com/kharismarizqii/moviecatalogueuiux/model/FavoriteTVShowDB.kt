package com.kharismarizqii.moviecatalogueuiux.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FavoriteTVShowDB(
    var id: Int = 0,
    var title: String? = null,
    var rating: Double = 0.0,
    var overview: String? = null,
    var firstAirDate: String? = null,
    var posterPath: String? = null,
    var backdropPath: String? = null
) : Parcelable