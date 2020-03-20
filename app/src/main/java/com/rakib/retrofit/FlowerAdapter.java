package com.rakib.retrofit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class FlowerAdapter extends ArrayAdapter<FlowerResponse> {

    private Context context;
    private List<FlowerResponse> flowerResponses;

    public FlowerAdapter(@NonNull Context context, List<FlowerResponse> flowerResponses) {
        super(context, R.layout.flower_row, flowerResponses);
        this.context = context;
        this.flowerResponses = flowerResponses;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.flower_row, parent, false);
        TextView nameTV = convertView.findViewById(R.id.flowerName);
        TextView priceTV = convertView.findViewById(R.id.flowerPrice);
        nameTV.setText(flowerResponses.get(position).getName());
        priceTV.setText(String.valueOf(flowerResponses.get(position).getPrice()));
        return super.getView(position, convertView, parent);
    }
}
