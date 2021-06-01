package com.lucascabral.marvelsuperheroes.presenter.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.lucascabral.marvelsuperheroes.R
import com.lucascabral.marvelsuperheroes.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var splashBinding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        splashBinding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(splashBinding.root)
        supportActionBar?.hide()

        val ironMainAnim: Animation = AnimationUtils.loadAnimation(this, R.anim.iron_man_anim)
        splashBinding.splashIronMan.animation = ironMainAnim

        Handler().postDelayed({
            val intent = Intent(applicationContext, AllCharactersActivity::class.java)
            startActivity(intent)
            finish()
        }, 8000)
    }
}