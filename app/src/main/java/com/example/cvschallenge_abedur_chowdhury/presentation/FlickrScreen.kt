package com.example.cvschallenge_abedur_chowdhury.presentation

sealed class FlickrScreen(val route: String) {
    data object FlickrGridScreen : FlickrScreen("flickr_grid_screen")
    data object FlickrDetailScreen: FlickrScreen("flickr_detail_screen")
}