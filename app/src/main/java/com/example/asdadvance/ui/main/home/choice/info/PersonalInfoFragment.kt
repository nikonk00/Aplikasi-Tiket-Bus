package com.example.asdadvance.ui.main.home.choice.info

import android.view.View
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.bagicode.bagicodebaseutils.basewithbinding.BaseBindingFragment
import com.bagicode.bagicodebaseutils.utils.Const
import com.bagicode.bagicodebaseutils.utils.formatPrice
import com.bagicode.bagicodebaseutils.utils.loadRoundedImage

import com.example.asdadvance.R
import com.example.asdadvance.databinding.FragmentPersonalInfoBinding
import com.example.asdadvance.model.BusRequest
import com.example.asdadvance.model.request.CheckoutRequest
import com.example.asdadvance.model.request.Penumpang
import com.example.asdadvance.model.response.BusResponse
import com.example.asdadvance.model.response.KursiResponse
import com.example.asdadvance.model.response.LoginResponse
import com.example.asdadvance.ui.BagicodeTravel
import com.example.asdadvance.ui.dialog.bottomsheet.InputEmailBottomSheet
import com.google.gson.Gson

class PersonalInfoFragment : BaseBindingFragment(), PersonalInfoAdapter.ItemListPenumpangAdapterCallBack,
    PersonalInfoContract.View {

    private lateinit var binding: FragmentPersonalInfoBinding

    private var busParms : BusResponse?=null
    private var dataPick : BusRequest?=null
    private var dataKursi : ArrayList<KursiResponse>?=null

    private var dataPassenger = ArrayList<String>()
    private var emailContactParms : String = ""
    private var totalParms : Int = 0

    lateinit var adapterPassenger : PersonalInfoAdapter
    lateinit var presenter: PersonalInfoPresenter
    lateinit var userResponse : LoginResponse


    override fun getFragmentView(): ViewBinding {
        binding = FragmentPersonalInfoBinding.inflate(layoutInflater)
        return binding
    }

    override fun onBindView() {
        busParms = arguments?.getParcelable("data")
        dataPick = arguments?.getParcelable("dataPick")
        dataKursi = arguments?.getParcelableArrayList("dataKursi")

        presenter = PersonalInfoPresenter(this)

        initViewData()
        initListener()
    }

    private fun initListener() {

        binding.tvAddPassenger.setOnClickListener {
            if (dataPassenger.size != dataPick?.penumpang) {
                InputEmailBottomSheet.newInstance(object : InputEmailBottomSheet.Listener {
                    override fun onClick(data: String) {
                        dataPassenger.add(data)
                        adapterPassenger.notifyDataSetChanged()

                    }
                }, "Penumpang", "Silahkan isis nama lengkap").show(parentFragmentManager, "")
            } else {
                showSnackbarMessage(binding.btnLanjutkan, " Total kursi yang kamu pesan ${dataPick?.penumpang} Slot", Const.ToastType.Error )
            }
        }
        binding.ivEmail.setOnClickListener{
            var emailParms = binding.tvEmail.text.toString()

            InputEmailBottomSheet.newInstance(object : InputEmailBottomSheet.Listener {
                override fun onClick(data: String) {
                    emailContactParms = data
                    binding.tvEmail.text = data

                }
            }, "Email", "Silahkan masukkan email", emailParms).show(parentFragmentManager, "")
        }

        binding.btnLanjutkan.setOnClickListener {
            if (dataPassenger.size == dataPick?.penumpang) {

//                viewPay = it

//                initMidtransSDK()
//                CustomerPayModel().userDetails(
//                    "Niko Fernanda",
//                    "niko@gmail.com",
//                    "082139827765",
//                    "jalan mlati no 20",
//                    "Yogyakarta",
//                    "38512",
//                    "IDN"
//                )

                setCheckout(
                    dataPassenger,
                    dataKursi!!,
                    busParms!!,
                    dataPick!!,
                    it,
                    "progress",
                    "transfer"
                )
            } else {
                showSnackbarMessage(binding.btnLanjutkan,
                    "Total kursi yang kamu pesan ${dataPick?.penumpang} Slot",
                    Const.ToastType.Error)
            }
        }
    }

    private fun initViewData() {
        binding.ivLogo.loadRoundedImage(busParms?.logo, 4)

        totalParms = dataPick?.penumpang!! * busParms?.price?.toInt()!!
        binding.tvPrice.formatPrice(totalParms.toString())

        binding.tvTime.text = "${busParms?.jam} WIB"

        var user = BagicodeTravel.getApp().getUser()
        userResponse = Gson().fromJson(user, LoginResponse::class.java)
        binding.tvEmail.text = userResponse.email
        emailContactParms = userResponse.email.toString()

        dataPassenger.add(userResponse?.username!!)
        adapterPassenger = PersonalInfoAdapter(dataPassenger, this)
        val layoutManager = LinearLayoutManager(activity)
        binding.rvPassenger.layoutManager = layoutManager
        binding.rvPassenger.adapter = adapterPassenger
    }

    override fun onitemPenumpangAdapterCallBack(data: String, position: Int) {
        InputEmailBottomSheet.newInstance(object : InputEmailBottomSheet.Listener {
            override fun onClick(data: String) {
                dataPassenger.set(position, data)
                adapterPassenger.notifyDataSetChanged()
            }
        }, "Penumpang", "Silahkan masukkan nama lengkap", data).show(parentFragmentManager, "")
    }

    override fun onCheckoutBookingSuccess(id: String, view: View) {
        Navigation.findNavController(view).navigate(R.id.action_success, null)

    }

    override fun onCheckoutupdateSuccess(message: String, view: View) {

    }

    override fun onCheckoutupdateFailed(message: String) {
        showSnackbarMessage(binding.btnLanjutkan, message, Const.ToastType.Error)
    }

    override fun onCheckoutBookingFailed(message: String) {
        showSnackbarMessage(binding.btnLanjutkan, message, Const.ToastType.Error)
    }

    private fun setCheckout(dataPassengerParms : ArrayList<String>,
                            dataKursiParms : ArrayList<KursiResponse>,
                            busParms : BusResponse,
                            dataPickParms : BusRequest,
                            view : View,
                            statusBayar : String,
                            jenisBayar : String) {

        var penumpangTemp = ArrayList<Penumpang>()
        var checkoutRequest : CheckoutRequest

        for (i in dataPassengerParms.indices) {
            penumpangTemp.add(
                Penumpang(
                    "",
                    dataKursiParms.get(i).nameKursi,
                    dataPassengerParms.get(i))
            )
        }

        checkoutRequest = CheckoutRequest(
            busParms.logo,
            busParms.title,
            busParms.plat,
            dataPickParms.daricode,
            dataPickParms.tujuancode,
            dataPickParms.date,
            emailContactParms,
            "",
            userResponse.idUser,
            busParms.jam,
            jenisBayar,
            penumpangTemp,
            statusBayar,
            dataPickParms.dari,
            dataPickParms.tujuan,
            busParms.classBus,
            totalParms.toString()
        )

        presenter.setCheckoutBooking(checkoutRequest, view)
    }

//    private fun initMidtransSDK() {
//        val uisetting = UIKitCustomSetting()
//        uisetting.isShowPaymentStatus = true
//        uisetting.isSkipCustomerDetailsPages = true
//
//        SdkUIFlowBuilder.init()
//            .setContext(requireContext())
//            .setMerchantBaseUrl(BuildConfig.BASE_URL_PAY)
//            .setClientKey(BuildConfig.CLIENT_KEY)
//            .setTransactionFinishedCallback(this)
//            .enableLog(true)
//            .setColorTheme(
//                CustomColorTheme("#AA55FF","#1A45BC","#AA55FF")
//            )
//            .buildSDK()
//    }

//    override fun onTransactionFinished(result: TransactionResult) {
//        if (result.response != null) {
//            when (result.status) {
//                TransactionResult.STATUS_SUCCESS -> {
//                    presenter.setCheckoutUpdate(idTiketParms,
//                        "done",
//                        viewPay
//                    )
//                }
//                TransactionResult.STATUS_PENDING -> {
//                    presenter.setCheckoutUpdate(idTiketParms,
//                        "pending",
//                        viewPay
//                    )
//                }
//                TransactionResult.STATUS_FAILED -> {
//                    presenter.setCheckoutUpdate(idTiketParms,
//                        "failed",
//                        viewPay
//                    )
//                }
//            }
//            result.response.validationMessages
//        } else if (result.isTransactionCanceled) {
//            showSnackbarMessage(binding.btnLanjutkan, "Transaksi ini dibatalkan", Const.ToastType.Error)
//        } else {
//            if (result.status.equals(TransactionResult.STATUS_INVALID, true)) {
//                showSnackbarMessage(binding.btnLanjutkan, "Transaksi ini invalid", Const.ToastType.Error)
//            } else {
//                showSnackbarMessage(binding.btnLanjutkan, "Transaksi ini finish with failed", Const.ToastType.Error)
//
//            }
//        }
//    }
}