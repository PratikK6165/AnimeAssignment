package com.example.seekhoassignment.models

data class ResponseData(
    val data: List<Anime>
)

data class Anime(
    val mal_id: Int,
    val title: String,
    val episodes: Int?,
    val score: Double?,
    val images: Images
)


data class AnimeDetailsResponse(
    val data: AnimeData
)

data class AnimeData(
    val mal_id: Int,
    val title: String?,
    val synopsis: String?,
    val episodes: Int?,
    val score: Double?,
    val genres: List<Genre>?,
    val trailer: Trailer?,
    val images: Images,
    val characters: List<Character>?
)

data class Images(
    val jpg: Jpg?
)

data class Jpg(
    val image_url: String?
)

data class Genre(val name: String?)

data class Trailer(val url: String?)

data class Character(val name: String?)


