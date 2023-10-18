package com.yashendra.firebasequizapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.yashendra.firebasequizapp.R
import kotlinx.android.synthetic.main.activity_login_intro.*

class LoginIntro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_intro)
        val auth = FirebaseAuth.getInstance()
        if(auth.currentUser != null){
            //user is already logged in
            Toast.makeText(this, "User is already login", Toast.LENGTH_SHORT).show()
            redirect("MAIN")
        }else{
            //user is not logged in
            btngetStarted.setOnClickListener {
                redirect("LOGIN")
            }

        }
    }

    private fun redirect(s: String) {
        val intent=when(s){
            "MAIN"->Intent(this, MainActivity::class.java)
            "LOGIN"->Intent(this, LoginActivity::class.java)
            else->throw java.lang.Exception("No path exist")
        }
        startActivity(intent)
        finish()
    }
}