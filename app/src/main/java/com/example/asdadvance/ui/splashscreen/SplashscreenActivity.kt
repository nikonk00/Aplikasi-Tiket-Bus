package com.example.asdadvance.ui.splashscreen

import android.content.Intent
import android.os.Handler
import com.bagicode.bagicodebaseutils.base.BaseActivity
import com.bagicode.bagicodebaseutils.utils.changePage
import com.example.asdadvance.R
import com.example.asdadvance.ui.auth.AuthActivity

class SplashscreenActivity : BaseActivity() {
    override fun getActivityView(): Int = R.layout.activity_splashscreen

    override fun onBindView() {
        Handler().postDelayed({
            changePage(AuthActivity::class.java, null, true)

//            startActivity(Intent(this, AuthActivity::class.java))
//            finish()
        }, 3000)
    }

}