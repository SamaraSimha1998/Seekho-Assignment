package com.test.seekhoassignment.models

data class Anime(
    val mal_id: Int,
    val title: String,
    val episodes: Int?,
    val score: Double?,
    val images: Images
)

data class Images(val jpg: ImageDetails)
data class ImageDetails(val image_url: String)

data class AnimeListResponse(
    val data: List<Anime>,
    val pagination: Pagination
)

data class Pagination(
    val has_next_page: Boolean
)