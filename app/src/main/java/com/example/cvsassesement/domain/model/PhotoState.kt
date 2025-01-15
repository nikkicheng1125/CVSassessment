package com.example.cvsassesement.domain.model

sealed class PhotoState {
    data object Loading : PhotoState()

    data class OnSuccess(
        val images: List<Image>
    ) : PhotoState()

    data class OnError(
        val message:String
    ) : PhotoState()

    data class OnFailure(
        val message: String
    ) : PhotoState()
}