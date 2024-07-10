package com.example.cvschallenge_abedur_chowdhury.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cvschallenge_abedur_chowdhury.domain.model.Item
import com.example.cvschallenge_abedur_chowdhury.domain.use_cases.search_images.SearchImagesUseCase
import com.example.cvschallenge_abedur_chowdhury.presentation.flickr_grid.FlickrGridUIState
import com.example.cvschallenge_abedur_chowdhury.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class FlickrViewModel @Inject constructor(
    private val searchImagesUseCase: SearchImagesUseCase
) : ViewModel() {
    private val _flickrResponseState = MutableStateFlow(FlickrGridUIState(isLoading = true))
    val flickrResponseState : StateFlow<FlickrGridUIState>
        get() = _flickrResponseState
    private val _flickrItemSelected = MutableStateFlow<Item?>(null)
    val flickrItemSelected : StateFlow<Item?>
        get() = _flickrItemSelected

    fun getImages(tag: String) {
        viewModelScope.launch {
            searchImagesUseCase(tag)
                .collect { result ->
                    when (result) {
                        is Resource.Loading -> {
                            _flickrResponseState.value =
                                FlickrGridUIState(isLoading = true)
                        }
                        is Resource.Success -> {
                            _flickrResponseState.value =
                                FlickrGridUIState(isLoading = false, items = result.data?.items
                                    ?: emptyList())
                        }
                        is Resource.Error -> {
                            _flickrResponseState.value =
                                FlickrGridUIState(isLoading = false, error = result.message)
                        }
                    }
                }
        }
    }

    fun onItemSelected(itemId: UUID) {
        _flickrItemSelected.value = flickrResponseState.value.items.find {
            it.itemId == itemId
        }
    }
}