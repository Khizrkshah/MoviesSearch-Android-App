package com.example.movies;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.Viewholder> {

    private Context context;
    private ArrayList<Movie> movieArrayList;
    // i3 create interface variable & add in constructor & solve main activity error by pass this in new CourseAdapter
    AdapterView.OnItemClickListener onItemClickListener;
    Movie item;

    public Adapter(Context context, ArrayList<Movie> movieArrayList) {
        this.context = context;
        this.movieArrayList = movieArrayList;
        //this.onItemClickListener = onItemClickListener; AdapterView.OnItemClickListener onItemClickListener
    }

    @NonNull
    @Override
    public Adapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.Viewholder holder, int position) {

        Movie model = movieArrayList.get(position);

        holder.titleTextView.setText(model.title);
        holder.yearTextView.setText(model.year);
        Picasso.get().load(model.posterUrl).into(holder.posterView);
        // can also set click event from Adapter class
        holder.cardView.setOnClickListener(v -> {
            //Toast.makeText(context, " -> "+model.imdbId, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(context, SecondActivity.class);
            intent.putExtra("imdbId",model.imdbId);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private TextView titleTextView, directorTextView, yearTextView, actorTextView;
        private ImageView posterView;



        public Viewholder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            yearTextView = itemView.findViewById(R.id.yearTextView);
            posterView = itemView.findViewById(R.id.posterView);

        }
    }
}