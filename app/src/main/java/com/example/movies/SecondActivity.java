package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    private static final String API_KEY = "bb0e00c9";
    private TextView movieTitle, movieYear, movieActors, moviePlot;
    private ImageView posterView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        movieTitle = findViewById(R.id.moveTitle);
        movieYear = findViewById(R.id.movieYear);
        movieActors = findViewById(R.id.movieActors);
        moviePlot = findViewById(R.id.moviePlot);
        posterView = findViewById(R.id.posterImageView);


        Intent intent = getIntent();

        String imdbId = intent.getStringExtra("imdbId");

        String url = "https://www.omdbapi.com/?i=" + imdbId + "&apikey=" + API_KEY;

        RequestQueue queue = Volley.newRequestQueue(SecondActivity.this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                        String title = response.getString("Title");
                        String year = response.getString("Year");
                        String posterUrl = response.getString("Poster");
                        String actors = response.getString("Actors");
                        String plot = response.getString("Plot");

                        movieTitle.setText(title);
                        movieActors.setText(actors);
                        moviePlot.setText(plot);
                        movieYear.setText(year);
                        Picasso.get().load(posterUrl).into(posterView);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("MoviesAppError", "Failed to get data.");
            }
        });
        queue.add(jsonObjectRequest);
    }
}