package com.example.asdadvance.ui.main.home.choice.info

import com.bagicode.bagicodebaseutils.base.BasePresenter
import com.bagicode.bagicodebaseutils.base.BaseView
import com.example.asdadvance.model.request.CheckoutRequest

interface PersonalInfoContract {

    interface View : BaseView {
        fun onCheckoutBookingSuccess(id : String, view: android.view.View)
        fun onCheckoutupdateSuccess(message: String, view: android.view.View)
        fun onCheckoutupdateFailed(message: String)
        fun onCheckoutBookingFailed(message: String)
    }

    interface  Presenter : PersonalInfoContract, BasePresenter {
        fun setCheckoutBooking (checkoutRequest: CheckoutRequest, view: android.view.View)
        fun setCheckoutUpdate (id_tiket:String, statusPembayaran:String, view: android.view.View)
    }

}