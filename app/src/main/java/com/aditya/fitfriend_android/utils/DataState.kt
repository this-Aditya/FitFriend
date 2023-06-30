package com.aditya.fitfriend_android.utils

sealed class DataState<out R> {

    data class Success<out T>(val data: T): DataState<T>()
    data class Error(val ex: Exception): DataState<Nothing>()
    object Loading : DataState<Nothing>()

}