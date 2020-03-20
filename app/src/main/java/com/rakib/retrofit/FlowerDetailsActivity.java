package com.rakib.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class FlowerDetailsActivity extends AppCompatActivity {

    private ImageView flowerImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flower_details);
        FlowerResponse response = (FlowerResponse) getIntent().getSerializableExtra("flowerClicked");
        String flowerNameWithEndURL = "photos/" + response.getPhoto();
        flowerImage = findViewById(R.id.flowerImage);
        Picasso.get().load(Constant.BASE_URL+flowerNameWithEndURL).into(flowerImage);
    }
}
