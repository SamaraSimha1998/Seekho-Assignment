package com.test.seekhoassignment.models

data class AnimeDetailResponse(
    val data: AnimeData
)

data class AnimeData(
    val url: String,
    val images: AnimeImages,
    val trailer: AnimeTrailer,
    val title: String,
    val episodes: Int,
    val rating: String,
    val score: Double,
    val synopsis: String,
    val genres: List<AnimeGenre>,
)

data class AnimeImages(
    val jpg: AnimeImageUrls,
    val webp: AnimeImageUrls
)

data class AnimeImageUrls(
    val image_url: String,
    val small_image_url: String,
    val large_image_url: String
)

data class AnimeTrailer(
    val youtube_id: String,
    val url: String,
    val embed_url: String,
    val images: AnimeTrailerImages
)

data class AnimeTrailerImages(
    val image_url: String,
    val small_image_url: String,
    val medium_image_url: String,
    val large_image_url: String,
    val maximum_image_url: String
)

data class AnimeGenre(
    val mal_id: Int,
    val type: String,
    val name: String,
    val url: String
)
