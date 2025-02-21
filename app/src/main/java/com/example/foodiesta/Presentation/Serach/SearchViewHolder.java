package com.example.foodiesta.Presentation.Serach;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodiesta.R;

public class SearchViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView ;
    public SearchViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.row_search_iv_show_list) ;
    }
}
