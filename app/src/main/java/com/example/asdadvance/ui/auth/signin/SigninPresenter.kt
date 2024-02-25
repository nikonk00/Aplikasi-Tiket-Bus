package com.example.asdadvance.ui.auth.signin

import com.bagicode.bagicodebaseutils.utils.Helpers.getErrorBodyMessage
import com.example.asdadvance.network.HttpClient
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SigninPresenter (private val view : SigninContract.View) : SigninContract.Presenter {

    private val mCompositeDispossable : CompositeDisposable?

    init {
        this.mCompositeDispossable = CompositeDisposable()
    }

    override fun setSignin(email: String, pass: String) {
        view.showLoading()

        val disposable = HttpClient.getInstance().getApi()!!.setLogin(
            email,
            pass
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()

                    if(it.codeStatus.equals("200")) {
                        it.data?.let { it1 -> view.onSigninSuccess(it1) }
                    } else {
                        view.onSigninFailed(it.codeMessage.toString())
                    }
                },
                {
                    view.dismissLoading()
                    view.onSigninFailed(it.getErrorBodyMessage())
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