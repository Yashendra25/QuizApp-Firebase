package com.yashendra.firebasequizapp.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yashendra.firebasequizapp.R
import com.yashendra.firebasequizapp.models.Question

class OptionAdapter(val context: Context,val question: Question):RecyclerView.Adapter<OptionAdapter.OptionViewHolder>() {

    private var options:List<String> =listOf(question.option1,question.option2,question.option3,question.option4)
    inner class OptionViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        var optionview=itemView.findViewById<TextView>(R.id.quizoption)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        val view=View.inflate(context, R.layout.option_item,null)
        return OptionViewHolder(view)
    }

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
        holder.optionview.text=options[position]
        holder.itemView.setOnClickListener {
            question.userAnswer=options[position]
            notifyDataSetChanged()
        }
        if(question.userAnswer==options[position])
        {
            holder.itemView.setBackgroundResource(R.drawable.option_item_selected_bg)
        }
        else
        {
            holder.itemView.setBackgroundResource(R.drawable.option_item_bg)
        }
    }

    override fun getItemCount(): Int {
        return options.size
    }
}