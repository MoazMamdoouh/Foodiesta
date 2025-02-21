package com.example.foodiesta.Presentation.Serach;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodiesta.Model.Home.List_meals.RandomMealsResponse;
import com.example.foodiesta.Model.Search.SearchShowListResponse;
import com.example.foodiesta.R;

public class SearchIngredientAdapter  extends RecyclerView.Adapter<SearchViewHolder> {
    private Context context ;
    private RandomMealsResponse randomMealsResponse;

    public SearchIngredientAdapter(Context context, RandomMealsResponse randomMealsResponse) {
        this.context = context;
        this.randomMealsResponse = randomMealsResponse;
    }

    public void setList(RandomMealsResponse randomMealsResponse){
        this.randomMealsResponse = randomMealsResponse ;
    }
    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context) ;
        View view = layoutInflater.inflate(R.layout.row_search_show_list , parent , false) ;
        SearchViewHolder searchViewHolder = new SearchViewHolder(view) ;
        return searchViewHolder ;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        Glide.with(context).load(randomMealsResponse.getListOfRandomMeals().get(position).getMealImage())
                .placeholder(R.drawable.food)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return randomMealsResponse.getListOfRandomMeals().size();
    }
}
