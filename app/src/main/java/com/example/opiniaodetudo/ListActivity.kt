package com.example.opiniaodetudo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.example.opiniaodetudo.Model.Review
import com.example.opiniaodetudo.Repository.ReviewRepository


class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_list_item_layout)

        val listView = findViewById<ListView>(R.id.list_recyclerview)
        val reviews = ReviewRepository.instance.listAll()
        //val stringList = reviews.map { "${it.name} - ${it.review}" }

        val adapter = object : ArrayAdapter<Review>(this, -1, reviews) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val itemView =
                    layoutInflater.inflate(R.layout.activity_review_list_item_layout, null)
                val item = reviews[position]
                val textViewName = itemView.findViewById<TextView>(R.id.itemName)
                val textViewReview = itemView.findViewById<TextView>(R.id.itemReview)
                textViewName.text = item.name
                textViewReview.text = item.review

                return itemView

            }
        }

        listView.adapter = adapter


        this.supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
