package com.yashendra.firebasequizapp.adapters

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.yashendra.firebasequizapp.R
import com.yashendra.firebasequizapp.activities.QuestionActivity
import com.yashendra.firebasequizapp.models.Quiz
import com.yashendra.firebasequizapp.utills.ColorPicker
import com.yashendra.firebasequizapp.utills.IconPicker

class QuizAdapter(val context: Context, val quizList: List<Quiz>) :
    RecyclerView.Adapter<QuizAdapter.QuizViewHolder>(){
    inner class QuizViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        var textviewtitle=itemView.findViewById<TextView>(R.id.quiztitle)
        var icon=itemView.findViewById<ImageView>(R.id.quizicon)
        var cardcontainer=itemView.findViewById<CardView>(R.id.cardcontainer)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.quiz_item,parent,false)
        return QuizViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        holder.textviewtitle.text=quizList[position].title
        holder.cardcontainer.setBackgroundColor(Color.parseColor(ColorPicker.getColor()))
        holder.icon.setImageResource(IconPicker.getIcon())
        holder.itemView.setOnClickListener {
            Toast.makeText(context, quizList[position].title, Toast.LENGTH_SHORT).show()
            val intent= Intent(context, QuestionActivity::class.java)
            intent.putExtra("DATE",quizList[position].title)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return quizList.size
    }

}