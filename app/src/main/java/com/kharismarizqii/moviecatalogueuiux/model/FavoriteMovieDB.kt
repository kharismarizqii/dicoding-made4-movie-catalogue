package com.kharismarizqii.moviecatalogueuiux.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FavoriteMovieDB(
    var id: Int = 0,
    var title: String? = null,
    var rating: Double = 0.0,
    var overview: String? = null,
    var releaseDate: String? = null,
    var posterPath: String? = null,
    var backdropPath: String? = null
):Parcelable