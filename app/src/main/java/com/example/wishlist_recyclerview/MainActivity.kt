// Main activity file for the app
package com.example.wishlist_recyclerview

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val wishlistItems = mutableListOf<WishlistItem>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: WishlistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = WishlistAdapter(wishlistItems)
        recyclerView.adapter = adapter

        // Handle adding items to the wishlist (you can add a button for this)
        // Example: addItemToWishlist("Item1", "$20.00", "https://example.com/item1")

        // Handle opening the URL in the browser
        adapter.setOnItemClickListener { item ->
            try {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(item.url))
                ContextCompat.startActivity(this@MainActivity, browserIntent, null)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this@MainActivity, "Invalid URL for " + item.name, Toast.LENGTH_LONG).show()
            }
        }
    }

    // Function to add an item to the wishlist
    private fun addItemToWishlist(name: String, price: String, url: String) {
        val newItem = WishlistItem(name, price, url)
        wishlistItems.add(newItem)
        adapter.notifyItemInserted(wishlistItems.size - 1)
    }
}
