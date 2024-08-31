package com.najeebappdev.freshmenu.data.repository

import com.najeebappdev.freshmenu.data.model.FoodCategory
import com.najeebappdev.freshmenu.data.model.SliderImageData
import com.najeebappdev.freshmenu.data.source.remote.ApiService
import retrofit2.Response

class HomeRepository(private val apiService: ApiService) {

    suspend fun getSliderImages(): Response<SliderImageData> {
        return apiService.getSliderImages()
    }

    suspend fun getCategories(): Response<List<FoodCategory>> {
        return apiService.getFoodCategories()
    }

}
