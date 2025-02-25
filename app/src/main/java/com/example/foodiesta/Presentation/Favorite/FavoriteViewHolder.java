package com.example.foodiesta.Presentation.Favorite;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodiesta.R;

public class FavoriteViewHolder extends RecyclerView.ViewHolder {
    public ImageView favIcon ;
    public ImageView mealImage ;
    public FavoriteViewHolder(@NonNull View itemView) {
        super(itemView);
        favIcon = itemView.findViewById(R.id.row_favorite_iv_fav_icon) ;
        mealImage = itemView.findViewById(R.id.row_favorite_iv_meal_image) ;
    }
}
