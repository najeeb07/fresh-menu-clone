package com.najeebappdev.freshmenu.data.source.remote


import com.najeebappdev.freshmenu.data.model.SliderImageData
import com.najeebappdev.freshmenu.data.model.FoodCategory
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    companion object{

        const val SERVER_BASE_URL = "https://api.npoint.io/"



    }

    @GET("dae2437685135b43ae5f")
    suspend fun getSliderImages(): Response<SliderImageData>


    @GET("cde53c02ea13cbf53efe")
   suspend fun getFoodCategories(): Response<List<FoodCategory>>





}