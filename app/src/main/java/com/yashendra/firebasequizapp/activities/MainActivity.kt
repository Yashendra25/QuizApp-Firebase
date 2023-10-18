package com.yashendra.firebasequizapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.firestore.FirebaseFirestore
import com.yashendra.firebasequizapp.R
import com.yashendra.firebasequizapp.adapters.QuizAdapter
import com.yashendra.firebasequizapp.models.Quiz
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var quizAdapter: QuizAdapter
    lateinit var firestore: FirebaseFirestore
    private var quizList= mutableListOf<Quiz>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupviews()

    }



    private fun setupviews() {
        setupFireStore()
        settupdrawerlayout()
        setupRecyclerView()
        setupdatapicker()
    }

    private fun setupdatapicker() {
        btndatepicker.setOnClickListener {
            val datePicker=MaterialDatePicker.Builder.datePicker().build()
            datePicker.show(supportFragmentManager,"Date Picker")
            datePicker.addOnPositiveButtonClickListener {

                val dateformatter= SimpleDateFormat("dd-MM-yyyy")
                val date=dateformatter.format(java.util.Date(it))
                Log.d("DATEofmainactivity",date)
                val intent= Intent(this,QuestionActivity::class.java)
                intent.putExtra("DATE",date)
                startActivity(intent)
            }
            datePicker.addOnNegativeButtonClickListener {
                Log.d("DATE","Date Picker Cancelled")
            }
            datePicker.addOnCancelListener {
                Log.d("DATE","Date Picker Cancelled")
            }
        }
    }

    private fun setupFireStore() {
        firestore= FirebaseFirestore.getInstance()
        val collectionRefrennce=firestore.collection("Quizzes")
        collectionRefrennce.addSnapshotListener { value, error ->
            if(error!=null || value==null){
                Toast.makeText(this, "Error fetching data", Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }
            Log.d("DATA",value.toObjects(Quiz::class.java).toString())
            quizList.clear()
            quizList.addAll(value.toObjects(Quiz::class.java))
            quizAdapter.notifyDataSetChanged()
        }
    }

    private fun setupRecyclerView() {
        quizAdapter= QuizAdapter(this,quizList)
        quizrecyclerview.layoutManager=GridLayoutManager(this,2)
        quizrecyclerview.adapter=quizAdapter
    }

    private fun settupdrawerlayout() {
        setSupportActionBar(AppBar)
        actionBarDrawerToggle = ActionBarDrawerToggle(this,drawer_layout,
            R.string.app_name,
            R.string.app_name
        )
        actionBarDrawerToggle.syncState()
        navigationview.setNavigationItemSelectedListener {
            val intent=Intent(this,ProfileActivity::class.java)
            startActivity(intent)
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}