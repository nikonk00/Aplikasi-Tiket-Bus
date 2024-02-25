package com.example.asdadvance.model.response


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Penumpang(
    @Expose
    @SerializedName("id_penumpang")
    var idPenumpang: String?,
    @Expose
    @SerializedName("kursi")
    var kursi: String?,
    @Expose
    @SerializedName("nama")
    var nama: String?
) : Parcelable