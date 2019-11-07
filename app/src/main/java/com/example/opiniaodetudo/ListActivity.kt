package com.example.opiniaodetudo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.opiniaodetudo.Repository.ReviewRepository


class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_review_layout)

            val listView = findViewById<ListView>(R.id.list_recyclerview)
            val reviews = ReviewRepository.instance.listAll()
            val stringList = reviews.map { "${it.name} - ${it.review}" }

            val adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, stringList );
            listView.adapter = adapter



    }
}
