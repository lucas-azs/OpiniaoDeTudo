package com.example.opiniaodetudo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.opiniaodetudo.Repository.ReviewRepository

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val buttonSave = findViewById<Button>(R.id.button_save)
        val textViewName = findViewById<TextView>(R.id.input_nome)
        val textViewReview = findViewById<TextView>(R.id.input_review)

        buttonSave.setOnClickListener {
            val name = textViewName.text
            val review = textViewReview.text
            Toast.makeText(this, "Nome:$name - Opinião:$review", Toast.LENGTH_LONG).show()
            ReviewRepository.instance.save(name.toString(), review.toString())
        }



    }



}

