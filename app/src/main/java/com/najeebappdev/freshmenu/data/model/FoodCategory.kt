package com.najeebappdev.freshmenu.data.model



data class FoodCategory(
    val listOfItems: List<OfItems>,
    val name: String,
    val cat_id: Int
)



data class OfItems(
    val description: String,
    val id: String,
    val image: String,
    val name: String,

    val price: String,
    val promotionalPrice: String
)