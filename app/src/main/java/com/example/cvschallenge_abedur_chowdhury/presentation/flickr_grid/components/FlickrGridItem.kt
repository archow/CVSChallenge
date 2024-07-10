package com.example.cvschallenge_abedur_chowdhury.presentation.flickr_grid.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.example.cvschallenge_abedur_chowdhury.R
import com.example.cvschallenge_abedur_chowdhury.domain.model.Item

@Composable
fun FlickrGridItem(
    imageItem: Item,
    onItemClick: (Item) -> Unit
) {
    Card (modifier = Modifier.clickable { onItemClick(imageItem) }) {
        LoadImage(imageItem.media, imageItem.description)
    }
}

@Composable
fun LoadImage(
    media: String? = null,
    description: String? = null
) {
    media?.let {
        AsyncImage(
            model = it,
            contentDescription = description,
            placeholder = painterResource(id = R.drawable.ic_unkown_image)
        )
    }
}