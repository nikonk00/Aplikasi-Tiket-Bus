package com.example.asdadvance.ui.main.home.choice.tiket

import android.telephony.SmsMessage
import com.bagicode.bagicodebaseutils.base.BasePresenter
import com.bagicode.bagicodebaseutils.base.BaseView
import com.example.asdadvance.model.BusRequest
import com.example.asdadvance.model.response.BusResponse
import com.example.asdadvance.model.response.KursiResponse
import com.example.asdadvance.model.response.LoginResponse

interface PilihKursiContract {

    interface View : BaseView {
        fun onKursiSuccess(response: ArrayList<KursiResponse>)
        fun onKursiFailed(message: String)
    }

    interface  Presenter : PilihKursiContract, BasePresenter {
        fun getKursiList (id : String)
    }

}