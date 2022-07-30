package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import java.util.ArrayList
import kotlin.math.log

class QuizQuestionsActivity : AppCompatActivity() , View.OnClickListener {
    private var progressBar : ProgressBar ?= null
    private var tvProgress: TextView ?= null
    private var tvQuestion: TextView ?= null
    private var opt1: TextView ?= null
    private var opt2: TextView ?= null
    private var opt3: TextView ?= null
    private var opt4: TextView ?= null
    private var btnSubmit : Button ?= null
    private var img: ImageView ?= null

    private var userName: String ?= null



    private var questions : ArrayList<Question> ?= null
    private var currentPosition: Int = 1
    var selectedOption: Int = 0
    private var correctAnswers : Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        userName = intent.getStringExtra(Constants.USER_NAME)


        progressBar = findViewById(R.id.progress)
        tvProgress = findViewById(R.id.tvProgress)
        tvQuestion = findViewById(R.id.txtQuestion)
        opt1  = findViewById(R.id.opt1)
        opt2 = findViewById(R.id.opt2)
        opt3 = findViewById(R.id.opt3)
        opt4 = findViewById(R.id.opt4)
        btnSubmit = findViewById(R.id.btnSubmit)
        img = findViewById(R.id.ivImage)

        questions = Constants.getQuestions()
        setQuestions()
        defaultView()

        opt1!!.setOnClickListener(this)
        opt2!!.setOnClickListener(this)
        opt3!!.setOnClickListener(this)
        opt4!!.setOnClickListener(this)
        btnSubmit!!.setOnClickListener(this)





    }

    @SuppressLint("SetTextI18n")
    private fun setQuestions(){

        defaultView()

        if (currentPosition == questions?.size?.plus(1)){
            btnSubmit?.text = "Finish"
        }else{
            btnSubmit?.text = "Submit"
        }

        val question = questions!![currentPosition - 1]
        progressBar?.progress = currentPosition
        tvProgress?.text = "${currentPosition}/${progressBar?.max}"
        img?.setImageResource(question.image)
        tvQuestion?.text = question.question + " ?"
        opt1?.text = question.optionOne
        opt2?.text = question.optionTow
        opt3?.text = question.optionThree
        opt4?.text = question.optionFour
    }

    private fun defaultView(){
        val options = ArrayList<TextView>()

        opt1?.let { options.add(0, it) }
        opt2?.let { options.add(0, it) }
        opt3?.let { options.add(0, it) }
        opt4?.let { options.add(0, it) }

        for (option in options){
            option.setTextColor(Color.parseColor("#434343"))
            option.background = ContextCompat.getDrawable(this, R.drawable.rounded)

        }

    }

    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id){
                R.id.opt1 -> {
                    opt1?.let { selectedOptionView(it,1) }
                }
                R.id.opt2 -> {
                    opt2?.let { selectedOptionView(it,2) }
                }
                R.id.opt3 -> {
                    opt3?.let { selectedOptionView(it,3) }
                }
                R.id.opt4 -> {
                    opt4?.let { selectedOptionView(it,4) }
                }
                R.id.btnSubmit -> {
                    if (selectedOption == 0){
                        currentPosition ++

                        when{
                            currentPosition <= questions!!.size -> {
                                setQuestions()
                            }else ->{
                                val intent = Intent(this, ResultActivity::class.java)
                                intent.putExtra(Constants.USER_NAME, userName)
                                intent.putExtra(Constants.CORRECT_ANSWERS, correctAnswers)
                                intent.putExtra(Constants.TOTAL_QUESTIONS,questions!!.size)
                                startActivity(intent)
                                finish()

                            }

                        }
                    }
                    else{
                        val question = questions?.get(currentPosition - 1)
                        if (question!!.correct != selectedOption){
                            answerView(selectedOption, R.drawable.red)
                        }
                        else{
                            correctAnswers++
                            Log.d("correct" ,correctAnswers.toString())
                        }
                        answerView(question!!.correct, R.drawable.green)
                        if (currentPosition == questions?.size){
                            btnSubmit?.text = "Finish"
                        }else{
                            btnSubmit?.text = "Go To next question"
                        }

                        selectedOption = 0

                    }
                }
            }

        }

    }

    private fun selectedOptionView(v: TextView, i : Int){
        selectedOption = i
        defaultView()
        v.background = ContextCompat.getDrawable(this, R.drawable.selected)
        v.setTextColor(Color.parseColor("#000000"))
    }

    private fun answerView(answer: Int, drawableView : Int){
        when(answer){
            1 -> {
                opt1?.background = ContextCompat.getDrawable(this, drawableView)
            }
            2 -> {
                opt2?.background = ContextCompat.getDrawable(this, drawableView)
            }
            3 -> {
                opt3?.background = ContextCompat.getDrawable(this, drawableView)
            }
            4 -> {
                opt4?.background = ContextCompat.getDrawable(this, drawableView)
            }
        }
    }
}