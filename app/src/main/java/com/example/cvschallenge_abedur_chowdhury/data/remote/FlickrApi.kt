package com.example.cvschallenge_abedur_chowdhury.data.remote

import com.example.cvschallenge_abedur_chowdhury.data.remote.dto.FlickrPayloadDto
import com.example.cvschallenge_abedur_chowdhury.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApi {
    @GET(Constants.SEARCH_IMAGE_PATH)
    suspend fun getSearchedImages(@Query(Constants.QUERY_TAGS) tags: String): FlickrPayloadDto
}