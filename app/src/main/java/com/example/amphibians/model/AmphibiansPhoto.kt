package com.example.amphibians.model

import kotlinx.serialization.SerialName

data class AmphibiansPhoto (
    val id: String,
    @SerialName(value = "img_src")
    val imgSrc: String
)