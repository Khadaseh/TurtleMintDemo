package com.turtlemint.turtlemintproject.view.util
// methods for handling response, errors
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T): Resource<T>(data)
    class Error<T>(message: String, data: T? = null): Resource<T>(data, message)
    class Loading<T> : Resource<T>()
}