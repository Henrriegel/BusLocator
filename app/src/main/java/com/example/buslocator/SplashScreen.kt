package com.example.buslocator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import com.example.buslocator.Credentials.CredentialsActivity

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_DIM_BEHIND,
            WindowManager.LayoutParams.FLAG_BLUR_BEHIND
        )
        setContentView(R.layout.activity_splash_screen)


        // Set the splash to display
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@SplashScreen, CredentialsActivity::class.java))
            finish()
        }, 4000)
    }
}