package com.example.asdadvance.ui.main.home.choice.info

import android.view.View
import com.bagicode.bagicodebaseutils.utils.Helpers.getErrorBodyMessage
import com.example.asdadvance.model.BusRequest
import com.example.asdadvance.model.request.CheckoutRequest
import com.example.asdadvance.network.HttpClient
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PersonalInfoPresenter (private val view : PersonalInfoContract.View) : PersonalInfoContract.Presenter {

    private val mCompositeDispossable : CompositeDisposable?

    init {
        this.mCompositeDispossable = CompositeDisposable()
    }

    override fun setCheckoutBooking(checkoutRequest: CheckoutRequest, viewParms : View) {
        view.showLoading()

        val disposable = HttpClient.getInstance().getApi()!!.setBooking(
            checkoutRequest
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()

                    if(it.codeStatus.equals("200")) {
                        it.data?.let { it1 -> view.onCheckoutBookingSuccess(it1, viewParms) }
                    } else {
                        view.onCheckoutBookingFailed(it.codeMessage.toString())
                    }
                },
                {
                    view.dismissLoading()
                    view.onCheckoutBookingFailed(it.getErrorBodyMessage())
                }
            )
        mCompositeDispossable?.add(disposable)
    }

    override fun setCheckoutUpdate(idTiket : String, statusPembayaran : String, viewParms : View) {
        view.showLoading()

        val disposable = HttpClient.getInstance().getApi()!!.setBookingUpdate(
            idTiket, statusPembayaran
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()

                    if(it.codeStatus.equals("200")) {
                        view.onCheckoutupdateSuccess(it.data.toString(), viewParms)
                    } else {
                        view.onCheckoutupdateFailed(it.codeMessage.toString())
                    }
                },
                {
                    view.dismissLoading()
                    view.onCheckoutupdateFailed(it.getErrorBodyMessage())
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