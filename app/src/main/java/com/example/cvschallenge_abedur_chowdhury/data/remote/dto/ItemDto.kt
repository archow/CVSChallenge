package com.example.cvschallenge_abedur_chowdhury.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ItemDto(
    val author: String,
    @SerializedName("author_id")
    val authorId: String,
    @SerializedName("date_taken")
    val dateTaken: String,
    val description: String,
    val link: String,
    val media: MediaDto,
    val published: String,
    val tags: String,
    val title: String
)