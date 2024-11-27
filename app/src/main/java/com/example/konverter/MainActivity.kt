package com.example.konverter

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView = findViewById<ImageView>(R.id.imageView)
        val titleTextView = findViewById<TextView>(R.id.titleTextView)
        val explanationTextView = findViewById<TextView>(R.id.explanationTextView)

        val apiKey = "LYa6ChoKMcbtwVH4VommPKkLfDM4NJOXTPvfeeL3"

        RetrofitInstance.api.getPhotoOfTheDay(apiKey).enqueue(object : Callback<NasaPhotoResponse> {
            override fun onResponse(
                call: Call<NasaPhotoResponse>,
                response: Response<NasaPhotoResponse>
            ) {
                if (response.isSuccessful) {
                    val photo = response.body()
                    if (photo != null) {
                        titleTextView.text = photo.title
                        explanationTextView.text = photo.explanation
                        Picasso.get().load(photo.url).into(imageView)
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Ошибка загрузки", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<NasaPhotoResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Ошибка: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}