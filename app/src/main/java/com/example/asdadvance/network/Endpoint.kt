package com.example.asdadvance.network


import com.example.asdadvance.model.Wrapper
import com.example.asdadvance.model.request.CheckoutRequest
import com.example.asdadvance.model.response.BusResponse
import com.example.asdadvance.model.response.KursiResponse
import com.example.asdadvance.model.response.LoginResponse
import com.example.asdadvance.model.response.MyBookingResponse
import io.reactivex.Observable
import retrofit2.http.*

interface Endpoint {

    @FormUrlEncoded
    @POST("register.php")
    fun setRegister(
        @Field("email") email : String,
        @Field("pass") pass : String,
        @Field("username") username : String
    ) : Observable<Wrapper<Any>>

    @FormUrlEncoded
    @POST("login.php")
    fun setLogin(
        @Field("email") email : String,
        @Field("pass") pass : String
    ) : Observable<Wrapper<LoginResponse>>

    @GET("search.php")
    fun getBusList(
        @Query("tipe") tipe : String?,
        @Query("penumpang") penumpang : String?,
        @Query("date") date : String?,
        @Query("dari") dari : String?,
        @Query("tujuan") tujuan : String?
    ) : Observable<Wrapper<ArrayList<BusResponse>>>

    @GET("kursi.php")
    fun getKursiList(
        @Query("id_bus") id_bus : String?
    ) : Observable<Wrapper<ArrayList<KursiResponse>>>


    @POST("checkout.php")
    fun setBooking(
        @Body checkoutRequest: CheckoutRequest
    ) : Observable<Wrapper<String>>

    @FormUrlEncoded
    @POST("checkout_update.php")
    fun setBookingUpdate(
        @Field ("id_tiket") idTiket : String,
        @Field ("status") statusPembayaran : String
    ) : Observable<Wrapper<Any>>

    @GET("booking.php")
    fun getMyBookingList(
        @Query("id_user") id_User : String?
    ) : Observable<Wrapper<ArrayList<MyBookingResponse>>>

}