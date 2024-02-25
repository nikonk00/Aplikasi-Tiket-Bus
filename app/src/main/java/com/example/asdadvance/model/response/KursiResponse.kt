package com.example.asdadvance.model.response


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class KursiResponse(
    @Expose
    @SerializedName("checkKursi")
    var checkKursi: Boolean?,
    @Expose
    @SerializedName("id")
    var id: String?,
    @Expose
    @SerializedName("nameKursi")
    var nameKursi: String?,
    @Expose
    @SerializedName("statusKursi")
    var statusKursi: Boolean?
) : Parcelable