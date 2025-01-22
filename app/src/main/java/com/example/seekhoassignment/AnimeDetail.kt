package com.example.seekhoassignment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.seekhoassignment.apiUtils.Client
import com.example.seekhoassignment.models.AnimeDetailsResponse
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnimeDetail : AppCompatActivity() {
    private lateinit var trailerVideo: WebView
    private lateinit var posterImage: ImageView
    private lateinit var titleText: TextView
    private lateinit var synopsisText: TextView
    private lateinit var genresText: TextView
    private lateinit var castText: TextView
    private lateinit var episodeText: TextView
    private lateinit var ratingText: TextView
    private lateinit var btnPlay: ImageView
    private lateinit var layoutPlay: FrameLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anime_detail)


        posterImage = findViewById(R.id.poster_image)
        titleText = findViewById(R.id.title_text)
        synopsisText = findViewById(R.id.synopsis_text)
        genresText = findViewById(R.id.genres_text)
        castText = findViewById(R.id.cast_text)
        btnPlay = findViewById(R.id.playBtn)
        layoutPlay = findViewById(R.id.playLayout)
        episodeText = findViewById(R.id.episode_text)
        ratingText = findViewById(R.id.rating_text)
        trailerVideo = findViewById(R.id.trailerWebView)

        trailerVideo.settings.javaScriptEnabled = true
        trailerVideo.settings.domStorageEnabled = true
        trailerVideo.webViewClient = WebViewClient()

        val animeId = intent.getIntExtra("anime_id", -1)
        Log.d("ID", "onCreate : $animeId")

        fetchAnimeDetails(animeId)


    }

    private fun fetchAnimeDetails(animeId: Int) {
        if (animeId != -1) {
            Client.instance.getAnimeDetails(animeId)
                .enqueue(object : Callback<AnimeDetailsResponse> {

                    override fun onResponse(
                        call: Call<AnimeDetailsResponse>,
                        response: Response<AnimeDetailsResponse>
                    ) {
                        if (response.isSuccessful) {
                            val anime = response.body()?.data
                            if (anime != null) {

                                titleText.text = anime.title ?: "Title not available"
                                synopsisText.text = anime.synopsis ?: "Synopsis not available"
                                genresText.text =
                                    "Genres: ${anime.genres?.joinToString { it.name ?: "Unknown" }}"
                                castText.text = if (!anime.characters.isNullOrEmpty()) {
                                    "Main Cast: ${anime.characters.joinToString { it.name ?: "Unknown" }}"
                                } else {
                                    "Main Cast: Not Available"
                                }
                                episodeText.text = "Episodes: ${anime.episodes ?: "N/A"}"
                                ratingText.text = "Rating: ${anime.score ?: "N/A"}"

                                Picasso.get()
                                    .load(anime.images.jpg?.image_url)
                                    .placeholder(android.R.drawable.ic_menu_gallery)
                                    .into(posterImage)

                                if (anime.trailer?.url != null) {
                                    Log.d("URL", "onResponse: ${anime.trailer.url}")
                                    layoutPlay.visibility = View.VISIBLE
                                    btnPlay.setOnClickListener {
                                        btnPlay.visibility = View.GONE
                                        posterImage.visibility = View.GONE
                                        val videoId =
                                            anime.trailer.url.split("v=").getOrNull(1)?.split("&")
                                                ?.get(0)
                                        if (videoId != null) {
                                            val embedUrl = "https://www.youtube.com/embed/$videoId"
                                            trailerVideo.visibility = View.VISIBLE
                                            trailerVideo.loadUrl(embedUrl)
                                        }
                                    }

                                }
                            }
                        } else {
                            Log.e("API Error", "Error: ${response.code()}")
                        }
                    }

                    override fun onFailure(call: Call<AnimeDetailsResponse>, t: Throwable) {
                        Log.e("API Failure", "Failure: ${t.message}")
                    }
                })
        }

    }

}
