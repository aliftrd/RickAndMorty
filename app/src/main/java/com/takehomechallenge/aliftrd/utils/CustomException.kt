package com.takehomechallenge.aliftrd.utils

import android.content.Context
import com.takehomechallenge.aliftrd.R
import com.takehomechallenge.aliftrd.data.lib.ApiResponse


fun Exception.toApiResponse(c: Context): ApiResponse.Error {
    return when (this) {
        else -> {
            this.printStackTrace()
            ApiResponse.Error(c.getString(R.string.unknown_error))
        }
    }
}