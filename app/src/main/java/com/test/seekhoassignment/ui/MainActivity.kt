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
    private val animeAdapter = AnimeAdapter { anime ->
        try {
            val intent = Intent(this, AnimeDetailActivity::class.java)
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

        lifecycleScope.launch {
            val response = RetrofitInstance.api.getTopAnime()
            Log.i("response data", "onCreate: $response")
            animeAdapter.submitList(response.data)
        }
    }
}