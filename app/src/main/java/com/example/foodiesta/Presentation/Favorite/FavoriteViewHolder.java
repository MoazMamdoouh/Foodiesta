package com.example.foodiesta.Presentation.Favorite;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodiesta.R;

public class FavoriteViewHolder extends RecyclerView.ViewHolder {
    ImageView favIcon ;
    ImageView mealImage ;
    TextView mealName ;
    CardView favoriteCard ;
    public FavoriteViewHolder(@NonNull View itemView) {
        super(itemView);
        favIcon = itemView.findViewById(R.id.row_favorite_iv_fav_icon) ;
        mealImage = itemView.findViewById(R.id.row_favorite_iv_meal_image) ;
        mealName = itemView.findViewById(R.id.row_favorite_tv_meal_name) ;
        favoriteCard = itemView.findViewById(R.id.row_favorite_card_view) ;
    }
}
