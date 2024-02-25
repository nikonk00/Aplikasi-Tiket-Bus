package com.example.asdadvance.ui.main.home.choice.bus

import com.bagicode.bagicodebaseutils.utils.Helpers.getErrorBodyMessage
import com.example.asdadvance.model.BusRequest
import com.example.asdadvance.network.HttpClient
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PilihBusPresenter (private val view : PilihBusContract.View) : PilihBusContract.Presenter {

    private val mCompositeDispossable : CompositeDisposable?

    init {
        this.mCompositeDispossable = CompositeDisposable()
    }

    override fun getBusList(busRequest: BusRequest) {
        view.showLoading()

        val disposable = HttpClient.getInstance().getApi()!!.getBusList(
            busRequest.tipe,
            busRequest.penumpang.toString(),
            busRequest.date,
            busRequest.dari,
            busRequest.tujuan
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()

                    if(it.codeStatus.equals("200")) {
                        it.data?.let { it1 -> view.onBusSuccess(it1) }
                    } else {
                        view.onBusFailed(it.codeMessage.toString())
                    }
                },
                {
                    view.dismissLoading()
                    view.onBusFailed(it.getErrorBodyMessage())
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