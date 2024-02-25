package com.example.asdadvance.ui.auth.signup

import android.telephony.SmsMessage
import com.bagicode.bagicodebaseutils.base.BasePresenter
import com.bagicode.bagicodebaseutils.base.BaseView

interface SignupContract {

    interface View : BaseView {
        fun onSignupSuccess(message : String)
        fun onSignupFailed(message: String)
    }

    interface  Presenter : SignupContract, BasePresenter {
        fun setSignup (email:String, pass:String, username:String)
    }

}