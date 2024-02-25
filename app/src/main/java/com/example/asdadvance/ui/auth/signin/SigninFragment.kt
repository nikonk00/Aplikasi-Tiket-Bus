package com.example.asdadvance.ui.auth.signin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.viewbinding.ViewBinding
import com.bagicode.bagicodebaseutils.basewithbinding.BaseBindingFragment
import com.bagicode.bagicodebaseutils.utils.Const
import com.bagicode.bagicodebaseutils.utils.changePage
import com.example.asdadvance.R
import com.example.asdadvance.databinding.FragmentSigninBinding
import com.example.asdadvance.model.response.LoginResponse
import com.example.asdadvance.ui.BagicodeTravel
import com.example.asdadvance.ui.auth.AuthActivity
import com.example.asdadvance.ui.main.MainActivity
import com.google.gson.Gson

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SigninFragment : BaseBindingFragment(), SigninContract.View {

    private lateinit var binding : FragmentSigninBinding
    private lateinit var presenter: SigninPresenter

    override fun getFragmentView(): ViewBinding {
        binding = FragmentSigninBinding.inflate(layoutInflater)
        return binding
    }


    override fun onBindView() {
        presenter = SigninPresenter(this)


        binding.btnSignin.setOnClickListener(View.OnClickListener {

            var emailParms = binding.etEmail.text.toString()
            var passwordParms = binding.etPassword.text.toString()


            if (emailParms.isNullOrEmpty()) {
                showSnackbarMessage(binding.btnSignin, "Silahkan isi email", Const.ToastType.Error)
            } else if (passwordParms.isNullOrEmpty()) {
                showSnackbarMessage(binding.btnSignin, "Silahkan isi password", Const.ToastType.Error)
            } else {
                presenter.setSignin(
                    emailParms,
                    passwordParms
                )
            }

        })
    }

    override fun onSigninSuccess(loginResponse: LoginResponse) {

        loginResponse.key?.let { BagicodeTravel.getApp().setToken(it) }

        val gson = Gson()
        val json = gson.toJson(loginResponse)

        BagicodeTravel.getApp().setUser(json)

        changePage(MainActivity::class.java,null, activity, true)

    }

    override fun onSigninFailed(message: String) {
        showSnackbarMessage(binding.btnSignin, message, Const.ToastType.Error)
    }


}