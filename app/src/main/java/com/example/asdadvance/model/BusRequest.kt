package com.example.asdadvance.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BusRequest (

    var tipe : String?="",
    var penumpang : Int?=0,
    var date : String?="",
    var dari : String?="",
    var daricode : String?="",
    var tujuan : String?="",
    var tujuancode : String?="",

        ) : Parcelable


