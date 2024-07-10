package com.example.cvschallenge_abedur_chowdhury.util

sealed class Resource<out T> {
    data class Success<T>(val data: T?) : Resource<T>()
    data class Error<T>(val message: String) : Resource<T>()
    data class Loading<T>(val data: T? = null) : Resource<T>()
}