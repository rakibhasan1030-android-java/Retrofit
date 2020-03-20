package com.rakib.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ListView flowerListView;
    FlowerAdapter flowerAdapter;
    List<FlowerResponse> responseList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flowerListView = findViewById(R.id.flowerLV);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FlowerService flowerService = retrofit.create(FlowerService.class);

        //this line will hit the URL
        Call<List<FlowerResponse>> flowerListCall = flowerService.getAllFlowers();

        //you can work with synchronous and Asynchronously also. that means you can work in main thread(synchronous) or you can
        //work in background thread(Asynchronous). Here we'll work in background thread(Asynchronous).
        //if you want to work in main thread then you've to call execute or
        //if you want to work in background thread then you've to call enqueue

        //and from this method we'll get the response
        flowerListCall.enqueue(new Callback<List<FlowerResponse>>() {
            @Override
            public void onResponse(Call<List<FlowerResponse>> call, Response<List<FlowerResponse>> response) {
                if (response.code() == 200){
                    responseList = response.body();
                    flowerAdapter =  new FlowerAdapter(MainActivity.this, responseList);
                    flowerListView.setAdapter(flowerAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<FlowerResponse>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });

        flowerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FlowerResponse flowerResponse = responseList.get(position);
                startActivity(new Intent(MainActivity.this, FlowerDetailsActivity.class).putExtra("flowerClicked", flowerResponse));
            }
        });

    }
}
