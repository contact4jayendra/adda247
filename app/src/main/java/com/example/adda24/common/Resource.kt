package com.example.adda24.common

import com.example.adda24.data.api.model.Status
import java.net.ConnectException
import java.net.SocketTimeoutException


data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(
                status = Status.SUCCESS,
                data = data,
                message = null
            )
        }


        fun <T> error(data: T?, message: String, exception: Exception?): Resource<T> {
            var msgUpdate = message
            try {
                if (exception != null) {
                    if (exception is SocketTimeoutException) {
                        msgUpdate = Constants.SOCKET_TIME_OUT
                    } else if (exception is ConnectException) {
                        msgUpdate = Constants.NO_INTERNET
                    } else {
                        msgUpdate = Constants.ERROR
                    }
                }
            } catch (e: Exception) {
                msgUpdate = Constants.ERROR
            }
            return Resource(
                status = Status.ERROR,
                data = data,
                message = msgUpdate
            )
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(
                status = Status.LOADING,
                data = data,
                message = null
            )
        }
    }
}