package com.example.cvsassesement.data.converter

import com.example.cvsassesement.data.dto.GetPhotoResponse
import com.example.cvsassesement.domain.model.Image

object PhotoConverterImpl : PhotoConverter<GetPhotoResponse?, List<Image>?> {
    override fun convertToDomain(getPhotoResponse: GetPhotoResponse?): List<Image>? {
        val imageList = mutableListOf<Image>()

        getPhotoResponse?.items?.let { items ->
            items.forEach {
                val title = it.title
                val media = it.media
                val published = it.published
                val author = it.author
                val tags = it.tags
                val image = Image(title, media, published, author, tags)
                imageList.add(image)
            }
        }

        return imageList
    }
}