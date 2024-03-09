// Main activity file for the app
package com.example.wishlist_recyclerview

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val wishlists = mutableListOf<Wishlist>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: WishlistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Wishlist"

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = WishlistAdapter(wishlists)
        recyclerView.adapter = adapter

        val submitButton: Button = findViewById(R.id.submitButton)
        submitButton.setOnClickListener {
            val name = findViewById<EditText>(R.id.nameTextView).text.toString()
            val price = findViewById<EditText>(R.id.priceTextView).text.toString()
            val url = findViewById<EditText>(R.id.urlTextView).text.toString()

            addItemToWishlist(name, price, url)
        }

        adapter.setOnItemClickListener { item ->
            try {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(item.url))
                ContextCompat.startActivity(this@MainActivity, browserIntent, null)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this@MainActivity, "Invalid URL for ${item.name}", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun addItemToWishlist(name: String, price: String, url: String) {
        val priceValue = price.toDoubleOrNull()

        if (priceValue != null) {
            val newItem = Wishlist(name, priceValue, url)
            wishlists.add(newItem)
            adapter.notifyItemInserted(wishlists.size - 1)
        } else {
            Toast.makeText(this, "Invalid price format", Toast.LENGTH_SHORT).show()
        }
    }
}
