package com.kharismarizqii.moviecatalogueuiux.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDB(
    var title : String,
    var rating : Double,
    var overview : String,
    var releaseDate : String,
    var posterPath : String,
    var backdropPath : String
):Parcelable