package com.made.fajaregafirmansyah.activities;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.made.fajaregafirmansyah.R;
import com.made.fajaregafirmansyah.adapters.AdapterMovie;
import com.made.fajaregafirmansyah.databinding.ActivityFilmBinding;
import com.made.fajaregafirmansyah.model.ItemsMovie;
import com.made.fajaregafirmansyah.rest.ApiClient;
import com.made.fajaregafirmansyah.rest.ApiInterface;
import com.made.fajaregafirmansyah.utils.AppAlert;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmActivity extends AppCompatActivity {
    private ActivityFilmBinding binding;
    private String TAG = FilmActivity.class.getSimpleName();

    private ProgressBar loading;
    private EditText inputCari;
    private Button prosesCari;
    private static long back_pressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_film);

        setSupportActionBar(binding.homeToolbar);
        getSupportActionBar().setTitle("F I L M");

        loading = binding.progressBarMain;
        inputCari = binding.edtCari;
        prosesCari = binding.btnCari;

        loading.setVisibility(View.VISIBLE);
        ambilFilmPertamakali();

        Glide.with(this).load(R.drawable.banner).into(binding.banner);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        prosesCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prosesCari.onEditorAction(EditorInfo.IME_ACTION_DONE);
                ambilFilm();
            }
        });
    }

    //ketika pertama kali dibuka nampilin daftar film yang mendatang
    public void ambilFilmPertamakali() {
        loading.setVisibility(View.VISIBLE);
        final RecyclerView rv = binding.recyclerFilm;
        rv.setLayoutManager(new LinearLayoutManager(FilmActivity.this));
        ApiInterface apiInterface = ApiClient.getClient(getApplicationContext()).create(ApiInterface.class);
        Call<ItemsMovie> panggil = apiInterface.ambilItemFilmMendatang();
        panggil.enqueue(new Callback<ItemsMovie>() {
            @Override
            public void onResponse(Call<ItemsMovie> call, Response<ItemsMovie> response) {
                ItemsMovie dataFilm = response.body();
                if (dataFilm.getResults().size() == 0) {
                    new AppAlert(FilmActivity.this).authAlert("Ops, film yang kamu cari tidak ditemukan. Cari film lain lagi ya, silahkan coba lagi");
                    loading.setVisibility(View.GONE);
                } else {
                    rv.setAdapter(new AdapterMovie(dataFilm.getResults(), R.layout.list_item_film, getApplicationContext()));
                    Log.e(TAG, "onResponse: hasil setelah manggil doi : " + call);
                    loading.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ItemsMovie> call, Throwable t) {
                new AppAlert(FilmActivity.this).authAlert("Yah, gagal coba nampilin daftar film");
                Log.d(TAG, t.toString());
                loading.setVisibility(View.GONE);
            }
        });
    }

    //fungsi hasil search berdasarkan input dari doi(user)
    public void ambilFilm() {
        String input_film = inputCari.getText().toString();

        loading.setVisibility(View.VISIBLE);
        final RecyclerView rv = binding.recyclerFilm;
        rv.setLayoutManager(new LinearLayoutManager(FilmActivity.this));
        ApiInterface apiInterface = ApiClient.getClient(getApplicationContext()).create(ApiInterface.class);
        Call<ItemsMovie> panggil = apiInterface.ambilItemFilm(input_film);
        panggil.enqueue(new Callback<ItemsMovie>() {
            @Override
            public void onResponse(Call<ItemsMovie> call, Response<ItemsMovie> response) {
                ItemsMovie dataFilm = response.body();
                if (dataFilm.getResults().size() == 0) {
                    new AppAlert(FilmActivity.this).authAlert("Ops, Film yang kamu cari tidak ditemukan. Cari film lain lagi ya, silahkan coba lagi");
                    loading.setVisibility(View.GONE);
                } else {
                    rv.setAdapter(new AdapterMovie(dataFilm.getResults(), R.layout.list_item_film, getApplicationContext()));
                    Log.e(TAG, "onResponse: hasil setelah manggil doi : " + call);
                    loading.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ItemsMovie> call, Throwable t) {
                new AppAlert(FilmActivity.this).authAlert("Yah, gagal coba nampilin daftar film");
                Log.d(TAG, t.toString());
                loading.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis()) moveTaskToBack(true);
        else
            Toast.makeText(getBaseContext(), "Tekan 'kembali' sekali lagi untuk keluar", Toast.LENGTH_SHORT).show();
        back_pressed = System.currentTimeMillis();
    }
}
