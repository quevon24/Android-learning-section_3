package com.seccion3.seccion_03.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.seccion3.seccion_03.Models.Movie;
import com.seccion3.seccion_03.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by quevon24 on 24/11/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Movie> movies;
    private int layout;
    private OnItemClickListener itemClicklistener;

    private Context context;

    public MyAdapter(List<Movie> movies, int layout, OnItemClickListener listener) {
        this.movies = movies;
        this.layout = layout;
        this.itemClicklistener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        ViewHolder vh = new ViewHolder(v);

        // almacenar contexto
        context = parent.getContext();

        return vh;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // llamamos al metodo bind del viewholder pasandole objeto y listener
        holder.bind(movies.get(position), itemClicklistener);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;
        public ImageView imageViewPoster;

        public ViewHolder(View itemView) {
            super(itemView);
            // this.textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            textViewName = (TextView) itemView.findViewById(R.id.textViewTitle);
            imageViewPoster = (ImageView) itemView.findViewById(R.id.imageViewPoster);

        }

        public void bind(final Movie movie, final OnItemClickListener listener) {

            // this.textViewName.setText(name);
            textViewName.setText(movie.getName());

            // usar picasso para cargar imagen
            Picasso.with(context).load(movie.getPoster()).fit().into(imageViewPoster);

            // imageViewPoster.setImageResource(movie.getPoster());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(movie, getAdapterPosition());
                }
            });

        }

    }

    // Recycler views requieren de definir manualmente los metodos
    public interface OnItemClickListener {
        void onItemClick(Movie movie, int position);
    }


}
