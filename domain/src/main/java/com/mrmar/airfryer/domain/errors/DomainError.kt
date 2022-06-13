package com.mrmar.airfryer.domain.errors

import android.content.res.Resources
import com.mrmar.airfryer.core.presentation.helper.Translatable
import com.mrmar.airfryer.domain.R

sealed class DomainError : Translatable {
    object ServiceError : DomainError() {
        override fun getStringResource(resources: Resources): String {
            return resources.getString(R.string.generic_service_error)
        }
    }
    object NoConnectionError : DomainError() {
        override fun getStringResource(resources: Resources): String {
            return resources.getString(R.string.no_internet_error)
        }
    }
}
