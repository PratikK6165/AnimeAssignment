package com.example.seekhoassignment.adapter


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.seekhoassignment.AnimeDetail
import com.example.seekhoassignment.R
import com.example.seekhoassignment.models.Anime
import com.squareup.picasso.Picasso

class AnimeListAdapter(
    private val context: Context, private val animeList: List<Anime>
) :
    RecyclerView.Adapter<AnimeListAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.anime_item_box, parent, false);
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        val anime = animeList[position]


        holder.bind(anime)
    }

    override fun getItemCount(): Int {
        return animeList.size
    }


    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.anime_image)
        val titleTextView: TextView = view.findViewById(R.id.anime_title)
        val episodesTextView: TextView = view.findViewById(R.id.anime_episodes)
        val ratingTextView: TextView = view.findViewById(R.id.anime_rating)

        fun bind(anime: Anime) {
            titleTextView.text = anime.title
            episodesTextView.text = "Episodes: ${anime.episodes ?: "N/A"}"
            ratingTextView.text = "Rating: ${anime.score ?: "N/A"}"

            val imageUrl = anime.images?.jpg?.image_url
            if (imageUrl != null) {
                Picasso.get().load(imageUrl).into(imageView)
            } else {
                imageView.setImageResource(R.drawable.ic_launcher_foreground) // Set a default placeholder image
            }

            itemView.setOnClickListener {
                val intent = Intent(context, AnimeDetail::class.java)
                intent.putExtra("anime_id", anime.mal_id)
                context.startActivity(intent)
            }


        }


    }

}