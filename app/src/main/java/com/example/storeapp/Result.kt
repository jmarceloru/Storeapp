package com.example.storeapp


sealed interface Result <out T>{
    data class Success<T>(val data: T):Result<T>
    data class Error(val exception: Throwable) : Result<Nothing>
    data object Loading: Result<Nothing>
}

fun <T> Result<T>.ifSuccess(block: (T) -> Unit){
    if (this is Result.Success){
        block(data)
    }
}