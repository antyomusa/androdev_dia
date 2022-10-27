package com.anthonyyoab.androdev.ui.model

data class BeritaResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)