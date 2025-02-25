package com.example.foodiesta.Presentation.Serach;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodiesta.Utilities.ListOfFoodResponse;
import com.example.foodiesta.R;

import java.util.List;

public class SearchResponseAdapter extends RecyclerView.Adapter<SearchViewHolder> {
    private Context context ;
    private List<ListOfFoodResponse> listOfRandomMeals ;

    public SearchResponseAdapter(Context context, List<ListOfFoodResponse> listOfRandomMeals) {
        this.context = context;
        this.listOfRandomMeals = listOfRandomMeals;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context) ;
        View view = layoutInflater.inflate(R.layout.row_search_show_list , parent , false) ;
        SearchViewHolder searchViewHolder = new SearchViewHolder(view) ;
        return searchViewHolder ;
    }

    void setListOfSearchResult(List<ListOfFoodResponse> listOfRandomMeals)
    {
        this.listOfRandomMeals = listOfRandomMeals ;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        Glide.with(context).load(listOfRandomMeals.get(position).getMealImage())
                .placeholder(R.drawable.food)
                .error(R.drawable.fooderror)
                .into(holder.searchMealImage) ;
        holder.mealName.setText(listOfRandomMeals.get(position).getMealName());
    }

    @Override
    public int getItemCount() {
        return listOfRandomMeals.size();
    }
}
