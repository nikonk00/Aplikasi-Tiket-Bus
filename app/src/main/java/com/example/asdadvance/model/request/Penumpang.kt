package com.example.asdadvance.model.request


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

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
)