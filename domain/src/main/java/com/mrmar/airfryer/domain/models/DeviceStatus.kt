package com.mrmar.airfryer.domain.models

import android.content.res.Resources
import com.mrmar.airfryer.core.presentation.helper.Colorable
import com.mrmar.airfryer.core.presentation.helper.Translatable
import com.mrmar.airfryer.domain.R

enum class DeviceStatus : Translatable, Colorable {
    ONLINE {
        override fun getStringResource(resources: Resources): String {
            return resources.getString(R.string.online_device)
        }
        override fun getColorResource(resources: Resources): Int {
            return resources.getColor(android.R.color.holo_green_light, null)
        }
    },
    OFFLINE {
        override fun getStringResource(resources: Resources): String {
            return resources.getString(R.string.offline_device)
        }
        override fun getColorResource(resources: Resources): Int {
            return resources.getColor(android.R.color.holo_red_light, null)
        }
    },
    COOKING {
        override fun getStringResource(resources: Resources): String {
            return resources.getString(R.string.cooking_mode)
        }
        override fun getColorResource(resources: Resources): Int {
            return resources.getColor(android.R.color.holo_orange_light, null)
        }
    },
    HEATING {
        override fun getStringResource(resources: Resources): String {
            return resources.getString(R.string.heating_mode)
        }
        override fun getColorResource(resources: Resources): Int {
            return resources.getColor(android.R.color.holo_purple, null)
        }
    }
}