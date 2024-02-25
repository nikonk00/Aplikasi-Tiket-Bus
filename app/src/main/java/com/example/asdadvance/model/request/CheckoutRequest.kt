package com.example.asdadvance.model.request


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CheckoutRequest(
    @Expose
    @SerializedName("bus_logo")
    var busLogo: String?,
    @Expose
    @SerializedName("bus_nama")
    var busNama: String?,
    @Expose
    @SerializedName("bus_plat")
    var busPlat: String?,
    @Expose
    @SerializedName("code_terminal_dari")
    var codeTerminalDari: String?,
    @Expose
    @SerializedName("code_terminal_tujuan")
    var codeTerminalTujuan: String?,
    @Expose
    @SerializedName("date_keberangkatan")
    var dateKeberangkatan: String?,
    @Expose
    @SerializedName("email_contact")
    var emailContact: String?,
    @Expose
    @SerializedName("id_tiket")
    var idTiket: String?,
    @Expose
    @SerializedName("id_user")
    var idUser: String?,
    @Expose
    @SerializedName("jam_keberangkatan")
    var jamKeberangkatan: String?,
    @Expose
    @SerializedName("pembayaran")
    var pembayaran: String?,
    @Expose
    @SerializedName("penumpang")
    var penumpang: List<Penumpang?>?,
    @Expose
    @SerializedName("status_bayar")
    var statusBayar: String?,
    @Expose
    @SerializedName("terminal_dari")
    var terminalDari: String?,
    @Expose
    @SerializedName("terminal_tujuan")
    var terminalTujuan: String?,
    @Expose
    @SerializedName("tipe_bus")
    var tipeBus: String?,
    @Expose
    @SerializedName("total_price")
    var totalPrice: String?
)