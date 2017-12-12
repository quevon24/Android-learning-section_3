package com.seccion3.seccion_03.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.seccion3.seccion_03.Models.Movie;
import com.seccion3.seccion_03.Adapters.MyAdapter;
import com.seccion3.seccion_03.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Movie> movies;

    private RecyclerView mRecyclerView;

    private RecyclerView.Adapter mAdapter;

    private RecyclerView.LayoutManager mLayoutManager;

    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movies = this.getAllMovies();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // List view
        // mLayoutManager = new LinearLayoutManager(this);
        // Grid view
        // mLayoutManager = new GridLayoutManager(this, 2);
        // Staggered Grid view, similar a grid view, pero permite aplicar los items de diferentes tamaños
        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        mAdapter = new MyAdapter(movies, R.layout.recycler_view_item, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Movie movie, int position) {
                // Toast.makeText(getApplicationContext(), name + " - " + position, Toast.LENGTH_LONG).show();
                deleteMovie(position);
            }
        });

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.add_name:
                this.addMovie(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void addMovie(int position) {
        movies.add(position, new Movie("New movie " + (++counter) , R.drawable.newmovie));
        mAdapter.notifyItemInserted(position);
        // Moverse a la posicion donde se agrego el elemento
        mLayoutManager.scrollToPosition(position);
    }

    private void deleteMovie(int position) {
        movies.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    private List<Movie> getAllMovies() {
        return new ArrayList<Movie>() {{
            add(new Movie("Ben Hur", R.drawable.american));
            add(new Movie("Deadpool", R.drawable.detroit));
            add(new Movie("Guardianes", R.drawable.dunkirk));
            add(new Movie("Warcraft", R.drawable.kingsman));
        }};
    }

}
