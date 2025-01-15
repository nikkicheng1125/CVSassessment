package com.example.cvsassesement.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cvsassesement.data.dto.Media
import com.example.cvsassesement.domain.model.Image
import com.example.cvsassesement.domain.model.PhotoState
import com.example.cvsassesement.domain.repository.IRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(private val repository: IRepository) : ViewModel() {
    private val _photoResponse = MutableLiveData<PhotoState>()
    val searchResult: LiveData<PhotoState> = _photoResponse

    private val _selectedImage = MutableLiveData<Image>()
    val selectedImage: LiveData<Image> = _selectedImage

    fun searchPhotos(keyword: String) {
        _photoResponse.postValue(PhotoState.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getPhotos(keyword)
                response?.let { images ->
                    if (images.isEmpty()) {
                        _photoResponse.postValue(PhotoState.OnError(message = "Empty Search Result"))
                    } else {
                        _photoResponse.postValue(PhotoState.OnSuccess(images))
                    }
                }
            } catch (e: Exception) {
                _photoResponse.postValue(PhotoState.OnFailure(e.message.toString()))

            }
        }
    }

    fun selectPhoto(image: Image) {
        _selectedImage.postValue(image)
    }
}