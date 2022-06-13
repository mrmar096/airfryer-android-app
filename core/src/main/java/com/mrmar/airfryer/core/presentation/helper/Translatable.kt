package com.mrmar.airfryer.core.presentation.helper

import android.content.res.Resources

interface Translatable {
    fun getStringResource(resources: Resources) : String
}