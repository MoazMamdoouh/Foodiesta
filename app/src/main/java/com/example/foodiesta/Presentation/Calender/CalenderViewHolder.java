package com.example.foodiesta.Presentation.Calender;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodiesta.R;

public class CalenderViewHolder extends RecyclerView.ViewHolder {

    ImageView mealImage , calenderIcon ;
    TextView mealName ;
    CardView calenderCardView ;
    public CalenderViewHolder(@NonNull View itemView) {
        super(itemView);
        mealImage = itemView.findViewById(R.id.row_calender_iv_meal_image) ;
        calenderIcon = itemView.findViewById(R.id.row_calender_iv_calender_icon) ;
        mealName = itemView.findViewById(R.id.row_calender_tv_meal_name);
        calenderCardView = itemView.findViewById(R.id.row_calender_card_view) ;
    }
}
