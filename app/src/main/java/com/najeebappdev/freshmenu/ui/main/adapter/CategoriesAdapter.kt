package com.najeebappdev.freshmenu.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.najeebappdev.freshmenu.R
import com.najeebappdev.freshmenu.data.model.FoodCategory

class CategoriesAdapter(
    private val context: Context,
    private var listOfCategories: List<FoodCategory>
) : RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_category, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(listOfCategories[position])
    }

    override fun getItemCount(): Int {
        return listOfCategories.size
    }

    fun updateCategories(newCategories: List<FoodCategory>) {
        val diffCallback = CategoriesDiffCallback(listOfCategories, newCategories)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        listOfCategories = newCategories
        diffResult.dispatchUpdatesTo(this)
    }

    class CategoryViewHolder(
        private val view: View
    ) : RecyclerView.ViewHolder(view) {

        fun bind(category: FoodCategory) {
            view.findViewById<TextView>(R.id.categoryName).text = category.name
            view.findViewById<TextView>(R.id.count).text = category.listOfItems.size.toString()
            view.findViewById<RecyclerView>(R.id.recyclerView).adapter =
                ItemsAdapter(view.context, category.listOfItems)
        }
    }
}

class CategoriesDiffCallback(
    private val oldList: List<FoodCategory>,
    private val newList: List<FoodCategory>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name == newList[newItemPosition].name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
