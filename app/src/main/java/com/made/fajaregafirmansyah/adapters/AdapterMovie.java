package com.made.fajaregafirmansyah.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.made.fajaregafirmansyah.R;
import com.made.fajaregafirmansyah.activities.DetailFilmActivity;
import com.made.fajaregafirmansyah.model.ItemsMovie;
import com.made.fajaregafirmansyah.utils.rvItemClickListener;

import java.util.List;

public class AdapterMovie extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //buat beberapa objek yang penting
    private final List<ItemsMovie.ResultsHasil> itemsMovieList;
    private static final String TAG = AdapterMovie.class.getSimpleName();
    private rvItemClickListener rvItemClickListener;
    private final int tataletakbaris;
    private final Context context;
    private final String URL_IMAGE = "https://image.tmdb.org/t/p/w500";
//    private String URL_IMAGE = "https://image.tmdb.org/t/p/w185/kSBXou5Ac7vEqKd97wotJumyJvU.jpg";

    //kontruktor tips dari fajar setelah membuat kelas adapter pencet alt+insert lalu pilih konstruktor maka akan generate langsung
    public AdapterMovie(List<ItemsMovie.ResultsHasil> itemsMovieList, int tataletakbaris, Context context) {
        this.itemsMovieList = itemsMovieList;
        this.tataletakbaris = tataletakbaris;
        this.context = context;
    }

    //tampilan layout juga mau diatur, masa manusia tidak mau diatur oleh Allah
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(tataletakbaris, parent, false);
        final AdapterMovieHolder adapterMovieHolder = new AdapterMovieHolder(v);
        adapterMovieHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = adapterMovieHolder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    rvItemClickListener.onItemClick(position, adapterMovieHolder.itemView);
                }
            }
        });
        return adapterMovieHolder;
    }

    // setelah menikah(ngambil data dari API) kemudian kawinkan(tampilkan hasil) kepada layout supaya menghasilkan tampilan yang kece
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //ngambil dulu posisi
        final ItemsMovie.ResultsHasil hasil = itemsMovieList.get(position);

        //buat holder
        final AdapterMovie.AdapterMovieHolder movieHolder = (AdapterMovie.AdapterMovieHolder) holder;

        //buat id_poster untuk ngambil background gambar
        final String id_poster = hasil.getPoster_path();
        final String image_poster = URL_IMAGE+id_poster;

        //nampilin gambar
        Glide.with(context)
                .load(image_poster)
                .centerCrop()
                .override(300, 400)
                .placeholder(R.drawable.bg_blank_film)
                .into(movieHolder.imageView);

        //nampilin text
        movieHolder.tvTitle.setText(hasil.getTitle());
        movieHolder.tvDesc.setText(hasil.getOverview());

        //ketika item di klik bawa anak-anak dulu(data)
        movieHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), DetailFilmActivity.class);
                i.putExtra("poster", hasil.getPoster_path());
                i.putExtra("orititle", hasil.getTitle());
                i.putExtra("overview", hasil.getOverview());
                view.getContext().startActivity(i);
            }
        });
    }

    //ngambil posisi
    @Override
    public int getItemCount() {
        return itemsMovieList == null ? 0 : itemsMovieList.size();
    }

    //proses ta'aruf dulu untuk membangun kecocokan setelah itu baru deh nikah
    class AdapterMovieHolder extends RecyclerView.ViewHolder {
        final LinearLayout linearLayout;
        final ImageView imageView;
        final TextView tvTitle;
        final TextView tvDesc;

        AdapterMovieHolder(View itemView) {
            super(itemView);

            linearLayout = itemView.findViewById(R.id.linearItemMovie);
            imageView = itemView.findViewById(R.id.imgItemMovie);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDesc = itemView.findViewById(R.id.tv_desc);
        }
    }
}
