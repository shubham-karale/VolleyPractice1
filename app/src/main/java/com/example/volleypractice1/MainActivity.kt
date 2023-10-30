package com.example.volleypractice1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    val url = "https://meme-api.com/gimme"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getMemeData()

        var memeImage = findViewById<ImageView>(R.id.memeImage)

        var memeTitle = findViewById<TextView>(R.id.memeTitle)

        var memememeAuthor  = findViewById<TextView>(R.id.memeAuthor)

        var memeButton = findViewById<Button>(R.id.memeButton)

        memeButton.setOnClickListener{
            getMemeData()
        }


    }

    fun getMemeData()
    {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
//        val url = "https://www.google.com"

// Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->

                // Display the first 500 characters of the response string.
//             Convert the response into JSONObject
                var responseObject = JSONObject(response)


                val memeImage = findViewById<ImageView>(R.id.memeImage)

                val memeTitle = findViewById<TextView>(R.id.memeTitle)

                val memememeAuthor  = findViewById<TextView>(R.id.memeAuthor)



                memeTitle.text = responseObject.getString("title")

                memememeAuthor.text = responseObject.getString("author")

                Picasso.get().load(responseObject.getString("url")).into(memeImage)







                Log.d("response is", response.substring(0, 500))

            },
            Response.ErrorListener { error ->

                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()

            })

// Add the request to the RequestQueue.
        queue.add(stringRequest)
    }
}