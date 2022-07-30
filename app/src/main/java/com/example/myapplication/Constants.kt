package com.example.myapplication

import java.util.ArrayList

object Constants {
    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"

    fun getQuestions(): ArrayList<Question> {


        val QuestionsList = ArrayList<Question>()


        val q1 = Question(1, "What is the capital of Morocco",R.drawable.flag_of_morocco, "Rabat", "Marrakech", "Casablanca", "Tangier",1)
        val q2 = Question(2, "What is the capital of USA", R.drawable.flag_of_the_united_states, "Florida", "New York", "Washington", "Miami",3)

        val q3 = Question(3, "What is the capital of United Kingdom",R.drawable.flag_of_the_united_kingdom, "Birmingham", "London", "Manchester", "Liverpool",2)

        val q4 = Question(4, "What is the capital of Italy",R.drawable.flag_of_italy, "Roma", "Milan", "Nepali", "Verona",1)

        val q5 = Question(5, "What is the capital of Netherlands",R.drawable.flag_of_the_netherlands, "Rotterdam", "Breda", "Utrecht", "Amsterdam",4)

        val q6 = Question(1, "What is the capital of Spain", R.drawable.flag_of_spain, "Barcelona", "Seville", "Madrid", "Bilbao",3)

        val q7 = Question(1, "What is the capital of Germany",R.drawable.flag_of_germany, "Munich", "Frankfurt", "Bonn", "Berlin",4)
        val q8 = Question(1, "What is the capital of Norway",R.drawable.flag_of_norway, "Bergen", "Oslo", "Trondheim", "Steinkjer",2)

        val q9 = Question(1, "What is the capital of Sweden",R.drawable.flag_of_sweden, "Malmö", "Stockholm", "Helsingborg", "Gothenburg",2)

        val q10 = Question(1, "What is the capital of canada",R.drawable.flag_of_south_africa, "East London", "Durban", "Johannesburg", "Pietermaritzburg",3)

        QuestionsList.add(q1)
        QuestionsList.add(q2)
        QuestionsList.add(q3)
        QuestionsList.add(q4)
        QuestionsList.add(q5)
        QuestionsList.add(q6)
        QuestionsList.add(q7)
        QuestionsList.add(q8)
        QuestionsList.add(q9)
        QuestionsList.add(q10)

        return QuestionsList

    }
}