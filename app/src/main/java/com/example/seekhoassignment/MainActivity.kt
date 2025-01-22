package com.example.seekhoassignment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.seekhoassignment.adapter.AnimeListAdapter
import com.example.seekhoassignment.apiUtils.Client
import com.example.seekhoassignment.models.Anime
import com.example.seekhoassignment.models.ResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var animeListView: RecyclerView
    private val animeList = mutableListOf<Anime>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        animeListView = findViewById(R.id.topAnimeList)

        Client.instance.getTopAnime().enqueue(object : Callback<ResponseData> {
            override fun onResponse(
                call: Call<ResponseData>,
                response: Response<ResponseData>
            ) {
                if (response.isSuccessful) {
                    response.body()?.data?.let {
                        animeList.addAll(it)
                        animeListView.adapter = AnimeListAdapter(this@MainActivity, animeList)

                    }
                }
            }

            override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }


}