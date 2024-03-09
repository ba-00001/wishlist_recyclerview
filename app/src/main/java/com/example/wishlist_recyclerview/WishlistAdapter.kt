// WishlistAdapter.kt file for the app
package com.example.wishlist_recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WishlistAdapter(private val mItems: List<Wishlist>) : RecyclerView.Adapter<WishlistAdapter.ViewHolder>()
{
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val priceTextView: TextView = itemView.findViewById(R.id.priceTextView)
        val urlTextView: TextView = itemView.findViewById(R.id.urlTextView)
    }

    private var onItemClickListener: ((Wishlist) -> Unit)? = null

    fun setOnItemClickListener(listener: (Wishlist) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishlistAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val itemView = inflater.inflate(R.layout.wishlist_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: WishlistAdapter.ViewHolder, position: Int) {
        val item: Wishlist = mItems[position]

        viewHolder.nameTextView.text = item.name
        viewHolder.priceTextView.text = item.price.toString()
        viewHolder.urlTextView.text = item.url


    }

    override fun getItemCount(): Int {
        return mItems.size
    }
}
