package com.example.quizzapplication

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.quizzapplication.databinding.ActivityQuizBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class QuizActivity : AppCompatActivity(), View.OnClickListener {

    //to pass date from main activity to quiz activity
    companion object{
        var questionModelList : List<QuestionModel> = listOf()
        var time : String = ""
    }

    lateinit var binding: ActivityQuizBinding

    var currentQuestionIndex = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        enableEdgeToEdge()

        setContentView(binding.root)
        binding.apply {
            //it will execute onClick whenever we click the button
            btn0.setOnClickListener(this@QuizActivity)
            btn1.setOnClickListener(this@QuizActivity)
            btn2.setOnClickListener(this@QuizActivity)
            btn3.setOnClickListener(this@QuizActivity)
            nextBtn.setOnClickListener(this@QuizActivity)


        }
        loadQuestions()
        beginCountdown()



        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
    }


    private fun loadQuestions(){
        binding.apply {
            questionIndicatorTextview.text = "Question ${currentQuestionIndex+1}/ ${questionModelList.size}"
            questionProgressIndicator.progress =
                ( currentQuestionIndex.toFloat() / questionModelList.size.toFloat() * 100 ).toInt()
            questionTextview.text = questionModelList[currentQuestionIndex].question
            btn0.text = questionModelList[currentQuestionIndex].options[0]
            btn1.text = questionModelList[currentQuestionIndex].options[1]
            btn2.text = questionModelList[currentQuestionIndex].options[2]
            btn3.text = questionModelList[currentQuestionIndex].options[3]
        }
    }


    private fun beginCountdown() {
        val totalTimeInMillis = time.toInt() * 60 * 1000L
        val totalSeconds = totalTimeInMillis / 1000

        lifecycleScope.launch(Dispatchers.Main) {
            for (secondsRemaining in totalSeconds downTo 0) {
                val minutes = secondsRemaining / 60
                val remainingSeconds = secondsRemaining % 60
                binding.timerIndicatorTextview.text = String.format("%02d:%02d", minutes, remainingSeconds)
                delay(1000L)
            }
            onTimerFinish()
        }
    }

    private fun onTimerFinish() {
        // Finish the quiz
    }

    override fun onClick(v: View?) {
        val clickedbtn = v as Button
        if(clickedbtn.id == R.id.next_btn){
            // next button has been clicked
            currentQuestionIndex++
            loadQuestions()
        }
        else{
            clickedbtn.setBackgroundColor(getColor(R.color.orange))

        }
    }
}
