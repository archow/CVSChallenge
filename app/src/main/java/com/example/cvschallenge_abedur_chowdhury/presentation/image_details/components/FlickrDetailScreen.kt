package com.example.cvschallenge_abedur_chowdhury.presentation.image_details.components


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.cvschallenge_abedur_chowdhury.domain.model.Item
import com.example.cvschallenge_abedur_chowdhury.presentation.FlickrViewModel

@Composable
fun FlickrDetailScreen(
    viewModel: FlickrViewModel = hiltViewModel()
) {
    val selectedItem = viewModel.flickrItemSelected.collectAsState().value
    selectedItem?.let {
        Box {
            ShowItemDetails(item = it)
        }
    }
}

@Composable
fun ShowItemDetails(item: Item) {
    Column {
        AsyncImage(
            model = item.media,
            contentDescription = item.description
        )
        Text(
            text = "Title: ${item.title}",
            fontSize = 24.sp,
            fontWeight = FontWeight(10)
        )
        Text(text = "Author: ${item.author}")
        Text(text="Description: ${item.description}")
        Text(text = "Published date: ${item.published}")
    }
}