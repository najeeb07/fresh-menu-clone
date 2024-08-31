package com.najeebappdev.freshmenu.ui.main.adapter

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.najeebappdev.freshmenu.R
import com.najeebappdev.freshmenu.data.model.OfItems

class ItemsAdapter(
    private val context: Context,
    private var items: List<OfItems>
) :
    RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ItemViewHolder(private val view: View) :
        RecyclerView.ViewHolder(view) {

        fun bind(item: OfItems) {
            view.findViewById<TextView>(R.id.productname).text = item.name
            view.findViewById<TextView>(R.id.price).text = "₹"+item.price
            view.findViewById<TextView>(R.id.regular_price).paintFlags = Paint.STRIKE_THRU_TEXT_FLAG


            Glide.with(view)
                .load(item.image)
                .into(view.findViewById<ImageView>(R.id.imageView))
        }
    }
}