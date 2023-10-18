package com.yashendra.firebasequizapp.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import com.google.gson.Gson
import com.yashendra.firebasequizapp.R
import com.yashendra.firebasequizapp.models.Quiz
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    lateinit var quiz:Quiz
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        okey.setOnClickListener {
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        setupviews()
    }

    private fun setupviews() {
        val quizdata=intent.getStringExtra("QUIZ")
        quiz= Gson().fromJson<Quiz>(quizdata,Quiz::class.java)
        calculateScore()
        setAnswerview()
    }

    private fun setAnswerview() {
        val builder = StringBuilder("")
        for (entry in quiz.questions.entries) {
            val question = entry.value
            builder.append("<font color'#18206F'><b>Question: ${question.description}</b></font><br/><br/>")
            builder.append("<font color='#009688'>Answer: ${question.answer}</font><br/><br/>")
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            txtAnswer.text = Html.fromHtml(builder.toString(), Html.FROM_HTML_MODE_COMPACT);
        } else {
            txtAnswer.text = Html.fromHtml(builder.toString());
        }    }

    private fun calculateScore() {
        var score=0
        for(entry in quiz.questions.entries)
        {
            val question=entry.value
            if(question.userAnswer==question.answer)
            {
                score+=10
            }
        }
        txtScore.text="Your Score is $score"
    }

}