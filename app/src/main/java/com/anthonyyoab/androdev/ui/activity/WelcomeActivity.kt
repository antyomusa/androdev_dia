package com.anthonyyoab.androdev.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.anthonyyoab.androdev.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        splashScreen.setKeepOnScreenCondition{ true }

        val isLogin = true
        if(isLogin){
            openMainActivity()
        } else {
            openLoginActivity()
        }
    }

    private fun openMainActivity(){
        val intent = Intent(this@WelcomeActivity, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun openLoginActivity(){
        val intent = Intent(this@WelcomeActivity, LoginActivity::class.java)
        startActivity(intent)
    }
}