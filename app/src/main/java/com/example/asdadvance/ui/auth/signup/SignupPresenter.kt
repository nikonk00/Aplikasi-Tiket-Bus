package com.example.asdadvance.ui.auth.signup

import com.bagicode.bagicodebaseutils.utils.Helpers.getErrorBodyMessage
import com.example.asdadvance.network.HttpClient
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SignupPresenter (private val view : SignupContract.View) : SignupContract.Presenter {

    private val mCompositeDispossable : CompositeDisposable?

    init {
        this.mCompositeDispossable = CompositeDisposable()
    }

    override fun setSignup(email: String, pass: String, username: String) {
        view.showLoading()

        val disposable = HttpClient.getInstance().getApi()!!.setRegister(
            email,
            pass,
            username
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()

                    if(it.codeStatus.equals("200")) {
                        view.onSignupSuccess(it.codeMessage.toString())
                    } else {
                        view.onSignupFailed(it.codeMessage.toString())
                    }
                },
                {
                    view.dismissLoading()
                    view.onSignupFailed(it.getErrorBodyMessage())
                }
            )
        mCompositeDispossable?.add(disposable)
    }

    override fun subscribe() {

    }

    override fun unSubscribe() {
        mCompositeDispossable!!.clear()
    }

}