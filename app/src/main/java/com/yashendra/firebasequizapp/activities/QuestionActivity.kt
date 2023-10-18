package com.yashendra.firebasequizapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.yashendra.firebasequizapp.R
import com.yashendra.firebasequizapp.adapters.OptionAdapter
import com.yashendra.firebasequizapp.models.Question
import com.yashendra.firebasequizapp.models.Quiz
import kotlinx.android.synthetic.main.activity_question.*

class QuestionActivity : AppCompatActivity() {
    var quizzes: MutableList<Quiz>? = null
    var questions: MutableMap<String,Question>? = null
    var index=1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        setupfirestore()
        setupeventlisteners()
    }

    private fun setupeventlisteners() {
       btnPrevious.setOnClickListener {
              index--
              bindviews()
       }
        btnNext.setOnClickListener {
            index++
            bindviews()
        }
        btnSubmit.setOnClickListener {
            Log.d("finalquiz",questions.toString())
            val intent=Intent(this,ResultActivity::class.java)
            val json= Gson().toJson(quizzes!![0])
            intent.putExtra("QUIZ",json)
            startActivity(intent)
        }
    }

    private fun setupfirestore() {
        var date=intent.getStringExtra("DATE")
        if (date!=null){
            val firestore= FirebaseFirestore.getInstance()
            firestore.collection("Quizzes").whereEqualTo("title",date)
                .get()
                .addOnSuccessListener {
                    if (it!=null && !it.isEmpty){
                        quizzes=it.toObjects(Quiz::class.java)
                        questions=quizzes!![0].questions
                        bindviews()
                    }
                }
        }

    }

    private fun bindviews() {
        btnPrevious.visibility=View.GONE
        btnSubmit.visibility=View.GONE
        btnNext.visibility=View.GONE
        if (index==1){
            btnNext.visibility=View.VISIBLE
        }
        else if(questions!!.size==index){
            btnSubmit.visibility=View.VISIBLE
            btnPrevious.visibility=View.VISIBLE
        }
        else{
            btnPrevious.visibility=View.VISIBLE
            btnNext.visibility=View.VISIBLE
        }
        val question=questions!!["question$index"]
        question?.let {
            description.text=it.description
            val optionAdapter= OptionAdapter(this,it)
            optionList.layoutManager=LinearLayoutManager(this)
            optionList.adapter=optionAdapter
            optionList.setHasFixedSize(true)
        }




    }
}