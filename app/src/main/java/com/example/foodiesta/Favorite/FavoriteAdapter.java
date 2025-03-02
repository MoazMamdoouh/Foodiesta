package com.example.foodiesta.Favorite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodiesta.Favorite.Favorite.FavoriteEntity;
import com.example.foodiesta.R;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteViewHolder> {
    private Context context ;
    private List<FavoriteEntity> favoriteEntities ;
    private OnFavoriteItemClicked onFavoriteItemClicked ;

    public FavoriteAdapter(Context context, List<FavoriteEntity> favoriteEntities , OnFavoriteItemClicked onFavoriteItemClicked) {
        this.context = context;
        this.favoriteEntities = favoriteEntities;
        this.onFavoriteItemClicked = onFavoriteItemClicked ;
    }
    public void  setList(List<FavoriteEntity> favoriteEntities){
        this.favoriteEntities = favoriteEntities ;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context) ;
        View view = layoutInflater.inflate(R.layout.row_favorite_cards , parent , false) ;
        FavoriteViewHolder favoriteViewHolder = new FavoriteViewHolder(view) ;
        return favoriteViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        Glide.with(context).load(favoriteEntities.get(position).getMealUrl())
                .placeholder(R.drawable.food)
                .error(R.drawable.fooderror)
                .into(holder.mealImage) ;
        holder.mealName.setText(favoriteEntities.get(position).getMealName());
        int id = favoriteEntities.get(position).getMealId();
        holder.favIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = favoriteEntities.get(position).getMealUrl() ;
                String name = favoriteEntities.get(position).getMealName() ;
                onFavoriteItemClicked.onFavoriteItemClickListener(id ,url , name );
            }
        });
        holder.favoriteCard.setOnClickListener(clicked ->{
            onFavoriteItemClicked.onFavoriteCardClicked(id);
        });

    }

    @Override
    public int getItemCount() {
        return favoriteEntities.size();
    }
}
