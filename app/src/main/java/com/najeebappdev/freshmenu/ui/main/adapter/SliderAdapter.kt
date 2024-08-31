package com.najeebappdev.freshmenu.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.najeebappdev.freshmenu.R
import com.najeebappdev.freshmenu.data.model.SliderImageData

class SliderAdapter : PagerAdapter() {

    private var sliderImages: List<SliderImageData> = emptyList()

    fun submitList(images: List<SliderImageData>) {
        this.sliderImages = images
        notifyDataSetChanged()
    }

    override fun getCount(): Int = sliderImages.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val context = container.context
        val inflater = LayoutInflater.from(context)
        val itemView = inflater.inflate(R.layout.slider_item_layout, container, false)

        // Assuming you have an ImageView in your slider_item_layout
        val imageView: ImageView = itemView.findViewById(R.id.imageView)

        // Load image using Coil
        Glide.with(imageView.context)
            .load(sliderImages[position].response.data[0].imagepath)
            .into(imageView)

        container.addView(itemView)

        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}
