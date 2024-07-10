package com.example.cvschallenge_abedur_chowdhury.domain.use_cases.search_images

import com.example.cvschallenge_abedur_chowdhury.domain.model.FlickrPayload
import com.example.cvschallenge_abedur_chowdhury.domain.repository.FlickrRepository
import com.example.cvschallenge_abedur_chowdhury.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchImagesUseCase @Inject constructor(
    private val flickrRepository: FlickrRepository
) {
    operator fun invoke(tag: String, limit: Int? = 21): Flow<Resource<FlickrPayload>> =
        flickrRepository.getSearchedImages(tag, limit)
}