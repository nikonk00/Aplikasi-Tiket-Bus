package com.example.asdadvance.ui.auth.signin

import android.telephony.SmsMessage
import com.bagicode.bagicodebaseutils.base.BasePresenter
import com.bagicode.bagicodebaseutils.base.BaseView
import com.example.asdadvance.model.response.LoginResponse

interface SigninContract {

    interface View : BaseView {
        fun onSigninSuccess(loginResponse: LoginResponse)
        fun onSigninFailed(message: String)
    }

    interface  Presenter : SigninContract, BasePresenter {
        fun setSignin (email:String, pass:String)
    }

}