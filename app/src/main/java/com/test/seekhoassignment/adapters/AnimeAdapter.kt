package com.test.seekhoassignment.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.seekhoassignment.R
import com.test.seekhoassignment.models.Anime

class AnimeAdapter(private val onClick: (Anime) -> Unit) : RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>() {

    private val animeList = mutableListOf<Anime>()

    val currentList: List<Anime>
        get() = animeList

    fun submitList(list: List<Anime>) {
        val startPosition = animeList.size
        animeList.addAll(list)
        notifyItemRangeInserted(startPosition, list.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_anime, parent, false)
        return AnimeViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        holder.bind(animeList[position])
    }

    override fun getItemCount() = animeList.size

    inner class AnimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)
        private val titleView: TextView = itemView.findViewById(R.id.titleView)
        private val episodesView: TextView = itemView.findViewById(R.id.episodesView)
        private val ratingView: TextView = itemView.findViewById(R.id.ratingView)

        fun bind(anime: Anime) {
            imageView.load(anime.images.jpg.image_url)
            titleView.text = anime.title
            episodesView.text = "Episodes: ${anime.episodes ?: "N/A"}"
            ratingView.text = "Rating: ${anime.score ?: "N/A"}"

            itemView.setOnClickListener { onClick(anime) }
        }
    }
}