package com.example.quizzapplication

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.quizzapplication.adapter.QuizListAdapter
import com.example.quizzapplication.databinding.ActivityMainBinding
import com.google.firebase.database.FirebaseDatabase

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
        binding.progressBar.visibility = View.VISIBLE

        //dummy data
        //returns the reference of the database
        FirebaseDatabase.getInstance().reference
            .get()
            .addOnSuccessListener { dataSnapshot->
                if (dataSnapshot.exists()) {
                    for (snap in dataSnapshot.children){
                        val quizzModel = snap.getValue(QuizzModel::class.java)
                        if (quizzModel != null) {
                            quizList.add(quizzModel)
                        }
                    }
                }
                initRecyclerView()
            }
    }


    private fun initRecyclerView() {
        binding.progressBar.visibility = View.GONE

        adapter = QuizListAdapter(quizList)
        binding.recyclerView.layoutManager = GridLayoutManager(this,2)
        binding.recyclerView.adapter = adapter

    }


}