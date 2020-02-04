package com.kharismarizqii.moviecatalogueuiux.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TVShowDB(
    var title: String,
    var rating: Double,
    var overview: String,
    var firstAirDate: String,
    var posterPath: String,
    var backdropPath: String
):Parcelable