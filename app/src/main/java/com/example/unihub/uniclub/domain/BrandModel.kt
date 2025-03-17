package com.example.unihub.uniclub.domain

import kotlinx.serialization.Serializable

@Serializable
data class BrandModel(
    val id: Int,
    val name: String,
)