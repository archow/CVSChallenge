package com.example.cvschallenge_abedur_chowdhury.presentation.flickr_grid

import com.example.cvschallenge_abedur_chowdhury.domain.model.Item

data class FlickrGridUIState(
    val isLoading: Boolean = false,
    val items: List<Item> = emptyList(),
    val error: String = ""
)