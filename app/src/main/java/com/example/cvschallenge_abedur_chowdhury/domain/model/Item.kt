package com.example.cvschallenge_abedur_chowdhury.domain.model

import java.util.UUID

data class Item(
    val itemId: UUID = UUID.randomUUID(),
    val author: String,
    val description: String,
    val media: String,
    val published: String,
    val title: String
)
