package com.pandaq.appcore.api

import com.google.gson.annotations.SerializedName

data class Entity<T>(
    @SerializedName("entity") val entity: T
)