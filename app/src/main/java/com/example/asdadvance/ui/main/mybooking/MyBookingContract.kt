package com.example.asdadvance.ui.main.mybooking

import android.telephony.SmsMessage
import com.bagicode.bagicodebaseutils.base.BasePresenter
import com.bagicode.bagicodebaseutils.base.BaseView
import com.example.asdadvance.model.BusRequest
import com.example.asdadvance.model.response.BusResponse
import com.example.asdadvance.model.response.LoginResponse
import com.example.asdadvance.model.response.MyBookingResponse

interface MyBookingContract {

    interface View : BaseView {
        fun onMyBookingSuccess(response: ArrayList<MyBookingResponse>)
        fun onMyBookingFailed(message: String)
    }

    interface  Presenter : MyBookingContract, BasePresenter {
        fun getMyBookingList (idUser: String)
    }

}