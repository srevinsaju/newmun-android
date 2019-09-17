package com.srevinsaju.newmun

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Fade
import android.view.Window
import android.widget.Toast
import androidx.transition.Explode
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(window){
            requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
            exitTransition=Fade()
        }


        setContentView(R.layout.activity_login)
        reg_button.setOnClickListener {

            var regUserName = username_ui.text.toString()
            var regPass = password_ui.text.toString()
            if (regUserName.isEmpty() || regPass.isEmpty() || fullname_ui.text.toString().isEmpty()) {
                Toast.makeText(this, "Please enter email or password.", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            Toast.makeText(this, "Hello user, $regUserName", Toast.LENGTH_LONG).show()
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(regUserName, regPass)
                .addOnCompleteListener {
                    if (!it.isSuccessful) return@addOnCompleteListener
                    Toast.makeText(
                        this, "User successfully registered with uid ${it.result!!.user.uid}",
                        Toast.LENGTH_LONG
                    ).show()
                    uploadDatabaseDetails() // upload Database details
                    val intent = Intent(this, MainActivity::class.java)

                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or (Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)

                }
                .addOnFailureListener {
                    Toast.makeText(this, "User registration failed ${it.message}", Toast.LENGTH_LONG).show()
                }

        }
        alreadyUser.setOnClickListener {
            val newIntent = Intent(this, PreLoginActivity::class.java)
            startActivity(newIntent)
        }
    }

    private fun uploadDatabaseDetails() {
        val uid = FirebaseAuth.getInstance().uid
        val usernameData = username_ui.text.toString()
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")
        ref.setValue(User("$uid", usernameData, fullname_ui.text.toString()))
            .addOnSuccessListener {
                Toast.makeText(this, "Database updated successfully", Toast.LENGTH_LONG).show()


            }
            .addOnFailureListener{
                Toast.makeText(this, "Database update Failed.", Toast.LENGTH_LONG).show()
            }
    }
    @Override
    public override fun onBackPressed(){
        super.onBackPressed()
        finish()
    }
}

class User(val uid: String, val email: String, val fullname: String){
    constructor() : this("","","")
}