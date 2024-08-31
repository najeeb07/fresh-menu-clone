package com.najeebappdev.freshmenu.data.model



data class SliderImageData(
    val response: Response
) {
    constructor(data: Data) : this(Response(listOf(data), "", "", ""))
}



data class Response(

    val data: List<Data>,

    val error: String,

    val message: String,

    val status: String
)


data class Data(
    val imagepath: String,

    val productDetails: List<ProductDetail>,

    val storeId: Int
)


data class ProductDetail(

    val active: Int,

    val categoryId: Int,

    val color: String,

    val description: String,

    val dietCategory: Int,
    val editEnable: Boolean,
    val id: Int,
    val imagePath: String,

    val isEditEnable: Boolean,
    val name: String,
    val option1: String,
    val option2: String,
    val price: String,
    val productId: Int,
    val promotionalPrice: String,
    val quantity: Int,
    val size: String,
    val specialPrice: String,
    val storeId: Int,
    val userId: Int,

    val weight: String
)
