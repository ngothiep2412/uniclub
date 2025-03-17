package com.example.unihub.uniclub.domain;


import kotlinx.serialization.Serializable;

@Serializable
data class CategoryModel(
    val id: Int,
    val name: String,
)