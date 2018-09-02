package com.dreamit.currentweatherapp.utils

import android.content.Context
import com.dreamit.currentweatherapp.R

private const val UNABLE_CONNECT_ERROR = "Unable to resolve"
private const val INTERNET_400_ERROR = "400"
private const val INTERNET_500_ERROR = "500"

fun getReadableErrorMessage(context: Context, error: String): String {
    return when {
        error.contains(UNABLE_CONNECT_ERROR) -> {
            context.getString(R.string.error_unable_resolve)
        }
        error.contains(INTERNET_400_ERROR) -> {
            context.getString(R.string.error_400)
        }
        error.contains(INTERNET_500_ERROR) -> {
            context.getString(R.string.error_500)
        }
        else -> error
    }
}