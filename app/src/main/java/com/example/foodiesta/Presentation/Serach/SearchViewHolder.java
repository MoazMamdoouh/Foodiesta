package com.example.foodiesta.Presentation.Serach;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodiesta.R;

public class SearchViewHolder extends RecyclerView.ViewHolder {
    ImageView searchMealImage;
    CardView  searchCardView ;

    public SearchViewHolder(@NonNull View itemView) {
        super(itemView);
        searchMealImage = itemView.findViewById(R.id.row_search_iv_show_list) ;
        searchCardView = itemView.findViewById(R.id.row_search_card_view_show) ;
    }
}
