package com.example.cvschallenge_abedur_chowdhury.data.repository

import com.example.cvschallenge_abedur_chowdhury.data.remote.FlickrApi
import com.example.cvschallenge_abedur_chowdhury.data.remote.dto.FlickrPayloadDto
import com.example.cvschallenge_abedur_chowdhury.data.remote.dto.ItemDto
import com.example.cvschallenge_abedur_chowdhury.data.remote.dto.MediaDto
import com.example.cvschallenge_abedur_chowdhury.domain.model.FlickrPayload
import com.example.cvschallenge_abedur_chowdhury.domain.model.Item
import com.example.cvschallenge_abedur_chowdhury.domain.repository.FlickrRepository
import com.example.cvschallenge_abedur_chowdhury.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class FlickrRepositoryImpl @Inject constructor(
    private val flickrApi: FlickrApi
) : FlickrRepository {

    override fun getSearchedImages(tag: String, limit: Int?): Flow<Resource<FlickrPayload>> =
        flow {
            try {
                emit(Resource.Loading())
                val payload =
                    flickrApi.getSearchedImages(tag)
                val limitedItems = limit?.let {
                    payload.items.take(it)
                } ?: payload.items
                emit(
                    Resource.Success(
                        data = dtoToFlickrPayload(payload.copy(items = limitedItems))
                    )
                )
            } catch (e: HttpException) {
                emit(
                    Resource.Error(e.localizedMessage
                        ?: "Http error, unable to get Flickr Payload."))
            } catch (e: IOException) {
                emit(
                    Resource.Error(e.localizedMessage
                        ?: "Could not connect to internet."))
            }
        }

    private fun dtoToFlickrPayload(flickrPayloadDto: FlickrPayloadDto) =
        FlickrPayload(flickrPayloadDto.items.map {
            dtoToItem(it)
        })

    private fun dtoToItem(itemDto: ItemDto) =
        Item(
            author = itemDto.author,
            description = itemDto.description,
            media = itemDto.media.m,
            published = itemDto.published,
            title = itemDto.title
        )

}