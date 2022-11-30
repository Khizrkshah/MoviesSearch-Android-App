package com.example.movies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
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

public class MainActivity extends AppCompatActivity {


    private ProgressBar progressBar;

    private EditText movieTitleField;
    private Button searchButton;
    private RecyclerView recyclerView;

    private static String movieTitle;
    private static final String API_KEY = "bb0e00c9";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progress);

        //directorTextView = findViewById(R.id.directorTextView);

        //actorTextView = findViewById(R.id.actorsTextView);
        movieTitleField = findViewById(R.id.movieTitleField);
        searchButton = findViewById(R.id.searchButton);
        recyclerView = findViewById(R.id.recyclerView);

    }

    public void searchButtonPressed(View view) {
        movieTitle = movieTitleField.getText().toString();
        String url = "https://www.omdbapi.com/?s=" + movieTitle + "&apikey=" + API_KEY;
        progressBar.setVisibility(View.VISIBLE);

        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                progressBar.setVisibility(View.GONE);

                try {
                    String jsonString = response.getString("Search");
                    JSONArray jsonArray = new JSONArray(jsonString);
                    ArrayList<Movie> movies = new ArrayList<Movie>();
                    for (int i = 0; i < jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String title = jsonObject.getString("Title");
                        String year = jsonObject.getString("Year");
                        String posterUrl = jsonObject.getString("Poster");
                        String imdbId = jsonObject.getString("imdbID");
                        Movie movie = new Movie(title,year,posterUrl,imdbId);
                        movies.add(movie);
                    }

                    Adapter adapter = new Adapter(MainActivity.this,movies);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


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