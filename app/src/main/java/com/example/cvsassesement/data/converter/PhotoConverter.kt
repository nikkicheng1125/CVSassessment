package com.example.cvsassesement.data.converter

interface PhotoConverter<Entity, Domain> {
    fun convertToDomain(getPhotoResponse: Entity): Domain
}