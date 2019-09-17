package com.srevinsaju.newmun

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_pre_login.*

class PreLoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pre_login)
        buttonLoginUI.setOnClickListener {
            val email = emailLoginUI.text.toString()
            val password = passLoginUI.text.toString()
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter email or password.", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                .addOnSuccessListener {
                    val uid = FirebaseAuth.getInstance().uid
                    val username = FirebaseDatabase.getInstance().getReference("/user/$uid/fullname").key
                    Toast.makeText(this, "Welcome back $username", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or (Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
                .addOnFailureListener{
                    Toast.makeText(this, "Invalid email or password", Toast.LENGTH_LONG).show()
                }
        }
    }
}
