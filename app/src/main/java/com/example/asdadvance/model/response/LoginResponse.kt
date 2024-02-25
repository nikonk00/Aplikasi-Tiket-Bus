package com.example.asdadvance.model.response


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @Expose
    @SerializedName("email")
    var email: String?,
    @Expose
    @SerializedName("id_user")
    var idUser: String?,
    @Expose
    @SerializedName("key")
    var key: String?,
    @Expose
    @SerializedName("username")
    var username: String?
)