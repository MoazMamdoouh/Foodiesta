package com.example.foodiesta.Presentation.Serach;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodiesta.Model.Search.Ingredient.ListOfIngredientSearch;
import com.example.foodiesta.R;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<SearchViewHolder> {
    Context context ;
    List<ListOfIngredientSearch> listListOfIngredientSearch;
    OnSearchItemClicked onSearchItemClicked ;

    public IngredientsAdapter(Context context, List<ListOfIngredientSearch> listListOfIngredientSearch, OnSearchItemClicked onSearchItemClicked) {
        this.context = context;
        this.listListOfIngredientSearch = listListOfIngredientSearch;
        this.onSearchItemClicked = onSearchItemClicked ;
    }
    public void setAllIngredientList(List<ListOfIngredientSearch> listOfIngredientSearch){
        this.listListOfIngredientSearch = listOfIngredientSearch;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_search_show_list , parent , false) ;
        SearchViewHolder searchViewHolder = new SearchViewHolder(view) ;

        return searchViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        String encodedIngredient ;
        try {
            encodedIngredient = URLEncoder.encode(listListOfIngredientSearch.get(position).getIngredientName(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        Glide.with(context).load("https://www.themealdb.com/images/ingredients/" + encodedIngredient + "-Medium.png")
                .placeholder(R.drawable.food)
                .error(R.drawable.fooderror)
                .into(holder.searchMealImage);
        holder.mealName.setText(listListOfIngredientSearch.get(position).getIngredientName());
        holder.searchCardView.setOnClickListener(clicked ->{
            onSearchItemClicked.onFilterItemClicked(listListOfIngredientSearch.get(position).getIngredientName() , "ingredient");
        });

    }

    @Override
    public int getItemCount() {
        return listListOfIngredientSearch.size();
    }
}
