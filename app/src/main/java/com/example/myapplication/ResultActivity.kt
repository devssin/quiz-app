package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    private var tvResult: TextView ?= null
    private var tvScore : TextView ?= null
    private var img: ImageView ?= null
    private var btnFinish: Button ?= null
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        tvResult = findViewById(R.id.txtResult)
        tvScore = findViewById(R.id.txtScore)
        img = findViewById(R.id.image)
        btnFinish = findViewById(R.id.btnFinish)

        val userName = intent.getStringExtra(Constants.USER_NAME)
        Log.d("username", userName.toString())
        val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS,0)
        Log.d("Score", correctAnswers.toString())
        val total = intent.getIntExtra(Constants.TOTAL_QUESTIONS,0)
        Log.d("total", total.toString())
        if(correctAnswers < (total * 3 /4)){
            img?.setImageResource(R.drawable.sad)
            tvResult?.text = "Sorry $userName !! You have failed in this Quiz"
            tvScore?.text = "Score: \n$correctAnswers / $total"
        }else{
            img?.setImageResource(R.drawable.trophy)
            tvResult?.text = "Congratulations $userName !! You passed the Quiz successfully"
            tvScore?.text = "Score: \n$correctAnswers / $total"
        }



        btnFinish?.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }



    }

}