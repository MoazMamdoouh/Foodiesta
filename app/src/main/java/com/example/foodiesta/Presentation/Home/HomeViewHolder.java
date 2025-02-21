package com.example.foodiesta.Presentation.Home;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodiesta.R;

public class HomeViewHolder extends RecyclerView.ViewHolder {
    ImageView mealImage ;
    TextView mealName ;
    CardView mealCardView ;
    public HomeViewHolder(@NonNull View itemView) {
        super(itemView);
        mealImage = itemView.findViewById(R.id.row_iv_meals_image) ;
        mealName = itemView.findViewById(R.id.row_tv_meals_name) ;
        mealCardView = itemView.findViewById(R.id.row_card_view) ;
    }
}
