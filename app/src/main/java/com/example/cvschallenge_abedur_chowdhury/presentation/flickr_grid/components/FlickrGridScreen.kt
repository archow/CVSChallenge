package com.example.cvschallenge_abedur_chowdhury.presentation.flickr_grid.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cvschallenge_abedur_chowdhury.presentation.FlickrScreen
import com.example.cvschallenge_abedur_chowdhury.presentation.FlickrViewModel

@Composable
fun FlickrGridScreen(
    navController: NavController,
    viewModel: FlickrViewModel = hiltViewModel()
) {
    viewModel.getImages("porcupine")

    val imageState = viewModel.flickrResponseState.collectAsState().value

    Box(modifier = Modifier.fillMaxSize()) {
        if (imageState.items.isNotEmpty()) {
            LazyVerticalGrid(columns = GridCells.Fixed(3)) {
                items(imageState.items.size) {position ->
                    FlickrGridItem(imageItem = imageState.items[position]) {item ->
                        viewModel.onItemSelected(item.itemId)
                        navController.navigate(
                            "${FlickrScreen.FlickrDetailScreen.route}/${item.itemId}"
                        )
                    }
                }
            }
        }
        if (imageState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        if (imageState.error.isNotBlank()) {
            Text(
                text=imageState.error,
                color= Color.Red,
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .align(Alignment.Center)

            )
        }
    }
}