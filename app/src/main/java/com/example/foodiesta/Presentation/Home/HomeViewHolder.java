package com.example.foodiesta.Presentation.Home;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodiesta.R;
import com.google.android.material.imageview.ShapeableImageView;

public class HomeViewHolder extends RecyclerView.ViewHolder {
    ShapeableImageView mealImage ;
    CardView mealCardView ;
    TextView mealName ;
    public HomeViewHolder(@NonNull View itemView) {
        super(itemView);
        mealImage = itemView.findViewById(R.id.row_home_iv_meals_image) ;
        mealCardView = itemView.findViewById(R.id.row_card_view) ;
        mealName = itemView.findViewById(R.id.row_home_tv_meal_name) ;
    }
}
