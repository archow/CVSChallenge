package com.example.cvschallenge_abedur_chowdhury.domain.repository

import com.example.cvschallenge_abedur_chowdhury.domain.model.FlickrPayload
import com.example.cvschallenge_abedur_chowdhury.util.Resource
import kotlinx.coroutines.flow.Flow

interface FlickrRepository {
    fun getSearchedImages(tag: String, limit: Int?): Flow<Resource<FlickrPayload>>
}