package com.example.ceritamereka.view.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.ceritamereka.view.ViewModelFactory
import com.example.ceritamereka.view.main.MainActivity
import com.example.ceritamereka.view.welcome.WelcomeActivity
import java.util.Timer
import kotlin.concurrent.schedule

class RoutingActivity : AppCompatActivity() {
    private val viewModel by viewModels<RoutingViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private var mIsLogin = false

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition { true }

        viewModel.getSession().observe(this) {
            mIsLogin = it.isLogin
            Timer().schedule(1500){
                routeToNextActivity(mIsLogin)
            }
        }
    }

    private fun routeToNextActivity(isLogin: Boolean) {
        if (isLogin) {
            val intent = Intent(this@RoutingActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            val intent = Intent(this@RoutingActivity, WelcomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}