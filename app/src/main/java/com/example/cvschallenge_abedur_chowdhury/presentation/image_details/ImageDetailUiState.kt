package com.example.cvschallenge_abedur_chowdhury.presentation.image_details

import com.example.cvschallenge_abedur_chowdhury.domain.model.Item

data class ImageDetailUiState(
    val isLoading: Boolean = false,
    val data: Item? = null,
    val error: String = ""
)