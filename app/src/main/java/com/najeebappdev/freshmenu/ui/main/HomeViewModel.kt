package com.najeebappdev.freshmenu.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.najeebappdev.freshmenu.data.model.SliderImageData
import com.najeebappdev.freshmenu.data.model.FoodCategory
import com.najeebappdev.freshmenu.data.repository.HomeRepository

import kotlinx.coroutines.launch

class HomeViewModel(private val repository: HomeRepository) : ViewModel() {

    private val _sliderImages = MutableLiveData<List<SliderImageData>>()
    val sliderImages: LiveData<List<SliderImageData>> get() = _sliderImages



    private val _foodCategories = MutableLiveData<List<FoodCategory>>()
    val foodCategories: LiveData<List<FoodCategory>> get() = _foodCategories

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    init {
        getSliderImages()
        getFoodCategories()  // Fetch food categories on initialization
    }

    fun getSliderImages() {
        viewModelScope.launch {
            try {
                val response = repository.getSliderImages()
                if (response.isSuccessful) {
                    val sliderData = response.body()?.response?.data
                    if (sliderData != null) {
                        _sliderImages.value = sliderData.map { SliderImageData(it) }
                    } else {
                        _error.value = "Slider data is null"
                    }
                } else {
                    _error.value = "Error fetching slider images: ${response.message()}"
                }
            } catch (e: Exception) {
                _error.value = "Exception: ${e.message}"
            }
        }
    }

     private fun getFoodCategories() {
        viewModelScope.launch {
            try {
                val response = repository.getCategories()
                if (response.isSuccessful) {
                    _foodCategories.value = response.body()
                } else {
                    _error.value = "Error fetching food categories: ${response.message()}"
                }
            } catch (e: Exception) {
                _error.value = "Exception: ${e.message}"
            }
        }
    }


}