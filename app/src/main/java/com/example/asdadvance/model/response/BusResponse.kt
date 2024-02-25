package com.example.asdadvance.model.response


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BusResponse(
    @Expose
    @SerializedName("classBus")
    var classBus: String?,
    @Expose
    @SerializedName("code")
    var code: String?,
    @Expose
    @SerializedName("image")
    var image: String?,
    @Expose
    @SerializedName("jam")
    var jam: String?,
    @Expose
    @SerializedName("logo")
    var logo: String?,
    @Expose
    @SerializedName("plat")
    var plat: String?,
    @Expose
    @SerializedName("price")
    var price: String?,
    @Expose
    @SerializedName("title")
    var title: String?
) : Parcelable