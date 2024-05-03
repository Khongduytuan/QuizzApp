package com.example.quizzapp.screen.homeScreen.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzapp.R
import com.example.quizzapp.models.QuizSet

class QuizSetAdapter(
    private val context: Context,
    private val onClick: (QuizSet)-> Unit,
    private val onDelete: (QuizSet) -> Unit
):RecyclerView.Adapter<QuizSetAdapter.QuizSetViewModel>() {
    private var quizSets: List<QuizSet> = listOf()

    inner class QuizSetViewModel(itemView: View):RecyclerView.ViewHolder(itemView){
        private val name_quiz_set: TextView = itemView.findViewById(R.id.name_quiz_set)
//        private val circle_view: TextView = itemView.findViewById(R.id.circle_view)
        fun onBind(quizSet: QuizSet){
            name_quiz_set.text = quizSet.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizSetViewModel {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_quiz_set_home_frag, parent, false)
        return QuizSetViewModel(itemView)
    }

    override fun getItemCount(): Int = quizSets.size

    override fun onBindViewHolder(holder: QuizSetViewModel, position: Int) {
        val quizSet = quizSets[position]
        holder.itemView.setOnClickListener { onClick.invoke(quizSet) }
        holder.onBind(quizSets[position])

    }

    fun setQuizSet(quizSets: List<QuizSet>){
        this.quizSets = quizSets
        notifyDataSetChanged()
    }
}