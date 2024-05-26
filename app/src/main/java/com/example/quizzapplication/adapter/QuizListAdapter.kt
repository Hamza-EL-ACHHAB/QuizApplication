package com.example.quizzapplication.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzapplication.QuizActivity
import com.example.quizzapplication.QuizzModel
import com.example.quizzapplication.databinding.QuizCategoryItemBinding

class QuizListAdapter(private val quizModelList : List<QuizzModel>) :
    RecyclerView.Adapter<QuizListAdapter.MyViewHolder>() {


    // to pass the view we have to give the binding
    class MyViewHolder(private val binding: QuizCategoryItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(model : QuizzModel){
            // bind all the views
            //binding contains the items
            binding.apply {
                quizTitle.text = model.title
                quizDescription.text = model.description
                quizTime.text = model.time + " min"
                root.setOnClickListener{
                    val intent = Intent(root.context, QuizActivity::class.java)
                    root.context.startActivity(intent)

                }




            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        //create the view from binding
        val binding = QuizCategoryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)


    }

    override fun getItemCount(): Int {
        return quizModelList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(quizModelList[position])
    }
}