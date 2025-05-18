package com.example.growpath.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Subject(
    val id: Int,
    val title: String,
    val description: String,
    val imageUrl: String,
    val category: String, // "top" or "your"
    val content: String? = null,
    val resources: List<Resource> = emptyList()
) : Parcelable

@Parcelize
data class Resource(
    val id: Int,
    val title: String,
    val type: String, // "article", "video", "book"
    val link: String
) : Parcelable
