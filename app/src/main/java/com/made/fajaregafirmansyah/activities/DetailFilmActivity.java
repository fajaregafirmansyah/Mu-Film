package com.made.fajaregafirmansyah.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.made.fajaregafirmansyah.R;

public class DetailFilmActivity extends AppCompatActivity {

    private ImageView gambar;
    private TextView tvJudul;
    private TextView tvDeskripsi;
    private final String url_image = "https://image.tmdb.org/t/p/w500/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_film);

        gambar = findViewById(R.id.gambar);
        tvJudul = findViewById(R.id.txt_judul);
        tvDeskripsi = findViewById(R.id.txt_deskripsi);

        getSupportActionBar().setElevation(2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("DETAIL FILM");

        Intent movie = getIntent();
        final String titleOri = movie.getStringExtra("orititle");
        final String overview = movie.getStringExtra("overview");
        final String poster_path = movie.getStringExtra("poster");
        final String imageLoad = url_image + poster_path;

        Glide
                .with(this)
                .load(imageLoad)
                .placeholder(R.drawable.bg_blank_film)
                .into(gambar);

        tvJudul.setText(titleOri);
        tvDeskripsi.setText(overview);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
