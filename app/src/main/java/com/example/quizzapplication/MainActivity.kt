package com.example.quizzapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.quizzapplication.adapter.QuizListAdapter
import com.example.quizzapplication.databinding.ActivityMainBinding

//Questions lors de la soutenance : temps ,difficultés, technologies  , le design et qualité de code
class MainActivity : AppCompatActivity() {
    //to enable the binding
    private lateinit var binding :ActivityMainBinding
    private lateinit var quizList : MutableList<QuizzModel>
    private lateinit var adapter: QuizListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        //initialize the list
        quizList = mutableListOf()
        getDataFromFirebase()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun getDataFromFirebase(){

        //dummy data
        val listQuestionModel = mutableListOf<QuestionModel>()
        listQuestionModel.add(QuestionModel("q1", mutableListOf("1","2","3","4"),"1"))
        listQuestionModel.add(QuestionModel("q2", mutableListOf("1","2","3","4"),"1"))
        listQuestionModel.add(QuestionModel("q3", mutableListOf("1","2","3","4"),"1"))

        quizList.add(QuizzModel("1","Programming","All the basic programming","10",listQuestionModel))
       // quizList.add(QuizzModel("2","Computer","All the computer questions","10"))
        //quizList.add(QuizzModel("3","Geogrophy","Boost your geogrophic knowledge","15"))

        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = QuizListAdapter(quizList)
        binding.recyclerView.layoutManager = GridLayoutManager(this,2)
        binding.recyclerView.adapter = adapter

    }


}