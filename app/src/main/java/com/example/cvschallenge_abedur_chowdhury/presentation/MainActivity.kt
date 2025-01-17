package com.example.cvschallenge_abedur_chowdhury.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cvschallenge_abedur_chowdhury.presentation.flickr_grid.components.FlickrGridScreen
import com.example.cvschallenge_abedur_chowdhury.presentation.image_details.components.FlickrDetailScreen
import com.example.cvschallenge_abedur_chowdhury.presentation.ui.theme.CVSChallenge_Abedur_ChowdhuryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CVSChallenge_Abedur_ChowdhuryTheme(darkTheme = false) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = FlickrScreen.FlickrGridScreen.route
                    ) {
                        composable(
                            route = FlickrScreen.FlickrGridScreen.route
                        ) {
                            FlickrGridScreen(navController, hiltViewModel())
                        }
                        composable(
                            route = FlickrScreen.FlickrDetailScreen.route + "/{itemId}"
                        ) {
                            FlickrDetailScreen(hiltViewModel())
                        }
                    }
                }
            }
        }
    }
}