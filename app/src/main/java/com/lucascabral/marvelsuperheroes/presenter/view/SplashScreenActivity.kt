package com.lucascabral.marvelsuperheroes.presenter.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.lucascabral.marvelsuperheroes.R
import com.lucascabral.marvelsuperheroes.databinding.ActivitySplashScreenBinding
import com.lucascabral.marvelsuperheroes.presenter.NavigationActivity

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var splashBinding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        splashBinding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(splashBinding.root)
        supportActionBar?.hide()

        val ironMainAnim: Animation = AnimationUtils.loadAnimation(this, R.anim.iron_man_anim)
        splashBinding.ironManLottieView.animation = ironMainAnim

        splashBinding.splashScreen.animate().setDuration(4000).alpha(1f).withEndAction {
            val intent = Intent(this, NavigationActivity::class.java)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            startActivity(intent)
            finish()
        }
    }
}