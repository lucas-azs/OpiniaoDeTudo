package com.example.opiniaodetudo

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.PopupMenu
import android.widget.TextView
import com.example.opiniaodetudo.Model.Review
import com.example.opiniaodetudo.Repository.ReviewRepository


class ListActivity : AppCompatActivity() {

    private lateinit var reviews: MutableList<Review>

    private fun initList(listView: ListView) {
        object : AsyncTask<Void, Void, ArrayAdapter<Review>>() {
            override fun doInBackground(vararg params: Void?): ArrayAdapter<Review> {
                val reviews = ReviewRepository(this@ListActivity.applicationContext)
                    .listAll()
                val adapter = object : ArrayAdapter<Review>(this@ListActivity, -1, reviews) {
                    override fun getView(
                        position: Int,
                        convertView: View?,
                        parent: ViewGroup): View {

                        val itemView =
                            layoutInflater.inflate(R.layout.activity_review_list_item_layout, null)
                        val item = reviews[position]
                        val textViewName = itemView
                            .findViewById<TextView>(R.id.itemName)
                        val textViewReview = itemView
                            .findViewById<TextView>(R.id.itemReview)
                        textViewName.text = item.name
                        textViewReview.text = item.review
                        return itemView
                    }
                }
                return adapter
            }

            override fun onPostExecute(adapter: ArrayAdapter<Review>) {
                listView.adapter = adapter
            }
        }.execute()
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //verificar qual activity chamar, pode ser a item
        setContentView(R.layout.activity_list_review_layout)

        val repo = ReviewRepository(this@ListActivity.applicationContext)
        reviews = repo.listAll().toMutableList()

        this.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val listView = findViewById<ListView>(R.id.list_recyclerview)
        initList(listView)
        configureOnLongClick(listView)



    }

    override fun onRestart() {
        super.onRestart()
        object : AsyncTask<Unit, Void, Unit>() {
            override fun doInBackground(vararg params: Unit?) {
                this@ListActivity.reviews =
                    ReviewRepository(this@ListActivity.applicationContext).listAll().toMutableList()
            }

            override fun onPostExecute(result: Unit?) {
                val listView = findViewById<ListView>(R.id.list_recyclerview)
                val adapter = listView.adapter as ArrayAdapter<Review>
                adapter.notifyDataSetChanged()
            }
        }.execute()
    }


    private fun delete(item: Review) {
        object : AsyncTask<Unit, Void, Unit>() {
            override fun doInBackground(vararg params: Unit?) {
                ReviewRepository(this@ListActivity.applicationContext).delete(item)
                reviews.remove(item)
            }

            override fun onPostExecute(result: Unit?) {
                val listView = findViewById<ListView>(R.id.list_recyclerview)
                val adapter = listView.adapter as ArrayAdapter<Review>
                adapter.notifyDataSetChanged()
            }
        }.execute()
    }

    private fun configureOnLongClick(listView: ListView?) {
        listView?.setOnItemLongClickListener { parent, view, position, id ->
            val popupMenu = PopupMenu(this@ListActivity, view)
            popupMenu.inflate(R.menu.list_review_item_menu)
            popupMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.item_list_delete -> this@ListActivity.delete(reviews[position])
                    R.id.item_list_edit -> this@ListActivity.openItemForEdition(reviews[position])
                }
                true
            }
            popupMenu.show()
            true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun openItemForEdition(item: Review) {
        val intent = Intent(this, MainActivity::class.java)
        val itemPut= item as String
        intent.putExtra("item", itemPut)
        startActivity(intent)

    }

}

