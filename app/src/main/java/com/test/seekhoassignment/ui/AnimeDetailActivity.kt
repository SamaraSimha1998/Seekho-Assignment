package com.test.seekhoassignment.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import coil.load
import com.example.seekhoassignment.R
import com.test.seekhoassignment.models.AnimeDetailResponse
import com.test.seekhoassignment.services.RetrofitInstance
import kotlinx.coroutines.launch

class AnimeDetailActivity : AppCompatActivity() {
    private lateinit var player: ExoPlayer
    private lateinit var playerView: WebView
    private lateinit var posterImageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var synopsisTextView: TextView
    private lateinit var genreTextView: TextView
    private lateinit var episodesTextView: TextView
    private lateinit var ratingTextView: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anime_detail)

        playerView = findViewById(R.id.player_view)
        posterImageView = findViewById(R.id.posterImageView)
        titleTextView = findViewById(R.id.titleTextView)
        synopsisTextView = findViewById(R.id.synopsisTextView)
        genreTextView = findViewById(R.id.genreTextView)
        episodesTextView = findViewById(R.id.episodesTextView)
        ratingTextView = findViewById(R.id.ratingTextView)

        val animeId = intent.getIntExtra("ANIME_ID", 0)

        lifecycleScope.launch {
            Log.i("response  ", "onCreate: $animeId")
            val response = RetrofitInstance.api.getAnimeDetails(animeId.toString())

            Log.i("response ", "onCreate: "+response.data)

            titleTextView.text = response.data.title ?: "No title available"
            synopsisTextView.text = response.data.synopsis ?: "No synopsis available"
            genreTextView.text = response.data.genres?.joinToString { it.name } ?: "No genres available"
            episodesTextView.text = "Episodes: ${response.data.episodes}"
            ratingTextView.text = "Rating: ${response.data.score}"

            val imageUrl = response.data.images?.jpg?.image_url
            if (imageUrl != null) {
                posterImageView.load(imageUrl)
            } else {
                posterImageView.load(R.drawable.default_image)
            }

            response.data.trailer?.embed_url?.let { trailerUrl ->
                Log.i("response  URL", "onCreate: $trailerUrl")
//                player = ExoPlayer.Builder(this@AnimeDetailActivity).build()
//                playerView.player = player
                playerView.visibility = View.VISIBLE
                posterImageView.visibility = View.GONE
//                player.setMediaItem(MediaItem.fromUri(trailerUrl))
//                player.prepare()
                playerView.settings.javaScriptEnabled = true
                playerView.settings.pluginState = WebSettings.PluginState.ON
                playerView.webViewClient = WebViewClient()
                playerView.loadUrl(trailerUrl)
            } ?: run {
                playerView.visibility = View.GONE
                posterImageView.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::player.isInitialized) player.release()
    }
}