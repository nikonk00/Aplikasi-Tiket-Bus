package com.example.asdadvance.ui.main.home.choice.tiket

import com.bagicode.bagicodebaseutils.utils.Helpers.getErrorBodyMessage
import com.example.asdadvance.model.BusRequest
import com.example.asdadvance.network.HttpClient
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PilihKursiPresenter (private val view : PilihKursiContract.View) : PilihKursiContract.Presenter {

    private val mCompositeDispossable : CompositeDisposable?

    init {
        this.mCompositeDispossable = CompositeDisposable()
    }

    override fun getKursiList(id: String) {
        view.showLoading()

        val disposable = HttpClient.getInstance().getApi()!!.getKursiList(
            id
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()

                    if(it.codeStatus.equals("200")) {
                        it.data?.let { it1 -> view.onKursiSuccess(it1) }
                    } else {
                        view.onKursiFailed(it.codeMessage.toString())
                    }
                },
                {
                    view.dismissLoading()
                    view.onKursiFailed(it.getErrorBodyMessage())
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