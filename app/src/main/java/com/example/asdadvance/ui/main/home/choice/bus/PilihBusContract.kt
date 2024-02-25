package com.example.asdadvance.ui.main.home.choice.bus

import android.telephony.SmsMessage
import com.bagicode.bagicodebaseutils.base.BasePresenter
import com.bagicode.bagicodebaseutils.base.BaseView
import com.example.asdadvance.model.BusRequest
import com.example.asdadvance.model.response.BusResponse
import com.example.asdadvance.model.response.LoginResponse

interface PilihBusContract {

    interface View : BaseView {
        fun onBusSuccess(BusResponse: ArrayList<BusResponse>)
        fun onBusFailed(message: String)
    }

    interface  Presenter : PilihBusContract, BasePresenter {
        fun getBusList (busRequest: BusRequest)
    }

}