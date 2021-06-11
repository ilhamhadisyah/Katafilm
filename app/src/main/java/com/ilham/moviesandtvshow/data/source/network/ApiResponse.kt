package com.ilham.moviesandtvshow.data.source.network

import androidx.annotation.Nullable

class ApiResponse<T>(
    val status: StatusResponse, @param:Nullable @field:Nullable
    val body: T, @param:Nullable @field:Nullable
    val message: String?
) {
    companion object {
        fun <T> success(@Nullable body: T): ApiResponse<T> {
            return ApiResponse(StatusResponse.SUCCESS, body, null)
        }

        fun <T> empty(message: String, @Nullable body: T): ApiResponse<T> {
            return ApiResponse(StatusResponse.EMPTY, body, message)
        }

        fun <T> error(message: String, @Nullable body: T): ApiResponse<T> {
            return ApiResponse(StatusResponse.ERROR, body, message)
        }
    }
}

