/**
 * Copyright 2023 Aditya Mishra

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

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