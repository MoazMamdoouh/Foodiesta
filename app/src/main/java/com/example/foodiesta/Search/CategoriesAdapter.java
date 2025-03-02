package com.example.foodiesta.Search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodiesta.Search.Category.ListOfCategoriesSearch;
import com.example.foodiesta.R;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<SearchViewHolder> {
    private Context context ;
    private List<ListOfCategoriesSearch> randomMealsResponse;
    private OnSearchItemClicked onSearchItemClicked ;

    public CategoriesAdapter(Context context, List<ListOfCategoriesSearch> randomMealsResponse
            , OnSearchItemClicked onSearchItemClicked) {
        this.context = context;
        this.randomMealsResponse = randomMealsResponse;
        this.onSearchItemClicked = onSearchItemClicked ;
    }

    public void setList(List<ListOfCategoriesSearch> randomMealsResponse){
        this.randomMealsResponse = randomMealsResponse ;
        notifyDataSetChanged();
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
        Glide.with(context).load(randomMealsResponse.get(position).getCategoryImage())
                .placeholder(R.drawable.food)
                .error(R.drawable.fooderror)
                .into(holder.searchMealImage) ;
        holder.mealName.setText(randomMealsResponse.get(position).getCategoryName());
        holder.searchCardView.setOnClickListener(click->{
            String name = randomMealsResponse.get(position).getCategoryName();
            onSearchItemClicked.onFilterItemClicked(name , "category");
        });
    }

    @Override
    public int getItemCount() {
        return randomMealsResponse.size();
    }
}
