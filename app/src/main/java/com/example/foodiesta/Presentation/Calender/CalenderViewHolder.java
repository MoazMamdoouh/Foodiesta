package com.example.foodiesta.Presentation.Calender;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodiesta.R;

public class CalenderViewHolder extends RecyclerView.ViewHolder {

    ImageView mealImage , calenderIcon ;
    public CalenderViewHolder(@NonNull View itemView) {
        super(itemView);
        mealImage = itemView.findViewById(R.id.row_calender_iv_meal_image) ;
        calenderIcon = itemView.findViewById(R.id.row_calender_iv_calender_icon) ;
    }
}
