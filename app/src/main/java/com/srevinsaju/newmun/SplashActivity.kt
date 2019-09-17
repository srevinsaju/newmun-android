package com.srevinsaju.newmun

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.transition.Fade
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(window){
            requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
            exitTransition= Fade()

        }
        setContentView(R.layout.activity_splash)
        val viewme = findViewById<View>(R.id.imageView2)
        supportActionBar!!.hide()
        val uid = FirebaseAuth.getInstance().uid
        if (uid == null){
            val intent = Intent(this, LoginActivity::class.java)

            Handler().postDelayed(Runnable {
                kotlin.run {

                    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        this, viewme,"sth"
                    )
                    startActivity(intent, options.toBundle())

                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                    finish()
                    Toast.makeText(this, "Welcome new user!", Toast.LENGTH_SHORT).show()
                }
            }, 4000)
        }
        else {
            val intent = Intent(this, MainActivity::class.java)

            Handler().postDelayed(Runnable {
                kotlin.run {
                    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        this, viewme,"sth"
                    )
                    startActivity(intent, options.toBundle())
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)

                    finish()
                    Toast.makeText(this, "Welcome back!", Toast.LENGTH_SHORT).show()
                }
            }, 4000)
        }

    }
}
