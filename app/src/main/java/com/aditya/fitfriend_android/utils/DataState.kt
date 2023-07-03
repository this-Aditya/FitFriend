package com.aditya.fitfriend_android.utils

/**
 * States of data receiving from network request with .
 * Success -> Data is received successfully, data is present as a constructor argument.
 * Error -> Some exception occurred while receiving data. cause is present as a constructor argument.
 * Loading -> Data is loading currently.
 */

sealed class DataState<out R> {

    data class Success<out T>(val data: T): DataState<T>()
    data class Error(val ex: Exception): DataState<Nothing>()
    object Loading : DataState<Nothing>()

}