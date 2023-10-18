package com.yashendra.firebasequizapp.activities

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.yashendra.firebasequizapp.R
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        mAuth = FirebaseAuth.getInstance()

        buttonsignup.setOnClickListener {
            signupUser()
        }
        btnlogin.setOnClickListener {

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    private fun  signupUser(){
        val emial = editTextTextEmailAddress.text.toString()
        val password = editTextTextPassword.text.toString()
        val confirmpassword= editTextconfirmPassword.text.toString()

        if(emial.isEmpty() || password.isEmpty() || confirmpassword.isEmpty()){
            Toast.makeText(this,"Please fill all the fields",Toast.LENGTH_SHORT).show()
            return
        }
        if(password != confirmpassword){
            Toast.makeText(this,"Password did not match",Toast.LENGTH_SHORT).show()
            return
        }


        mAuth.createUserWithEmailAndPassword(emial,password)
            .addOnCompleteListener(this){task ->
                if(task.isSuccessful){
                    // Sign in success, update UI with the signed-in user's information
                    val user = mAuth.currentUser
                    Toast.makeText(this,"Account created successfully",Toast.LENGTH_SHORT).show()
                    Toast.makeText(this,"Login successful",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                    Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }
            }
    }
}