package com.example.asdadvance.ui.main.mybooking

import com.bagicode.bagicodebaseutils.utils.Helpers.getErrorBodyMessage
import com.example.asdadvance.network.HttpClient
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MyBookingPresenter(private val view: MybookingFragment) : MyBookingContract.Presenter {

    private val mCompositeDispossable : CompositeDisposable?

    init {
        this.mCompositeDispossable = CompositeDisposable()
    }

    override fun getMyBookingList(idUser:String) {
        view.showLoading()

        val disposable = HttpClient.getInstance().getApi()!!.getMyBookingList(
            idUser
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()

                    if(it.codeStatus.equals("200")) {
                        it.data?.let { it1 -> view.onMyBookingSuccess(it1) }
                    } else {
                        view.onMyBookingFailed(it.codeMessage.toString())
                    }
                },
                {
                    view.dismissLoading()
                    view.onMyBookingFailed(it.getErrorBodyMessage())
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