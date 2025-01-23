package com.test.seekhoassignment.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.seekhoassignment.R
import com.test.seekhoassignment.adapters.AnimeAdapter
import com.test.seekhoassignment.services.RetrofitInstance
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private var currentPage = 1
    private var isLoading = false
    private var hasMorePages = true

    private val animeAdapter = AnimeAdapter { anime ->
        try {
            val intent = Intent(this, AnimeDetailsActivity::class.java)
            intent.putExtra("ANIME_ID", anime.mal_id)
            startActivity(intent)
        } catch (ex: Exception) {
            Log.e("package:mine ", ": $ex")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = animeAdapter

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!isLoading && hasMorePages) {
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount &&
                        firstVisibleItemPosition >= 0
                    ) {
                        loadNextPage()
                    }
                }
            }
        })

        loadAnimeList(currentPage)
    }

    private fun loadAnimeList(page: Int) {
        isLoading = true
        lifecycleScope.launch {
            try {
                val response = RetrofitInstance.api.getAnimeList(page)
                Log.i("response data", "Page $page: $response")
                animeAdapter.submitList(animeAdapter.currentList + response.data)
                hasMorePages = response.pagination.has_next_page
            } catch (e: Exception) {
                Log.e("Error", "Failed to load data: $e")
            } finally {
                isLoading = false
            }
        }
    }

    private fun loadNextPage() {
        currentPage++
        loadAnimeList(currentPage)
    }
}
