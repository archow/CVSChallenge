package com.example.cvschallenge_abedur_chowdhury.data.remote.dto

data class FlickrPayloadDto(
    val description: String,
    val generator: String,
    val items: List<ItemDto>,
    val link: String,
    val modified: String,
    val title: String
)