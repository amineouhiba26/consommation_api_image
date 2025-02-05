package com.example.consomation_api_image;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PhotosAdapter photosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchPhotos();
    }

    private void fetchPhotos() {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<List<Photo>> call = apiService.getPhotos();

        call.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    photosAdapter = new PhotosAdapter(response.body());
                    recyclerView.setAdapter(photosAdapter);
                } else {
                    showToast("Erreur de chargement");
                }
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                showToast("Erreur : " + t.getMessage());
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
