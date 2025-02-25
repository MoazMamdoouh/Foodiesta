package com.example.foodiesta.Presentation.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodiesta.Utilities.FoodObjectResponse;
import com.example.foodiesta.R;
import com.example.foodiesta.Utilities.OnItemClickListener;

public class HomeAdapter  extends RecyclerView.Adapter<HomeViewHolder> {
    private Context context ;
    private FoodObjectResponse foodObjectResponse;
    private OnItemClickListener onItemClickListener ;

    public HomeAdapter(Context context, FoodObjectResponse foodObjectResponse, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.foodObjectResponse = foodObjectResponse;
        this.onItemClickListener = onItemClickListener ;
    }

    public void setRandomMealsResponse(FoodObjectResponse foodObjectResponse){
        this.foodObjectResponse = foodObjectResponse;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context) ;
        View v = layoutInflater.inflate(R.layout.row_home_meals , parent , false) ;
        HomeViewHolder homeViewHolder = new HomeViewHolder(v) ;
        return homeViewHolder ;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        holder.mealName.setText(foodObjectResponse.getListOfRandomMeals().get(position).getMealName());
        Glide.with(context).load(foodObjectResponse.getListOfRandomMeals().get(position).getMealImage())
                .placeholder(R.drawable.food)
                .error(R.drawable.fooderror)
                .into(holder.mealImage);
        holder.mealCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClicked(foodObjectResponse.getListOfRandomMeals().get(position).getId());
            }
        });

        holder.mealCardView.startAnimation(AnimationUtils.loadAnimation(context , R.anim.home_card_animation));
    }

    @Override
    public int getItemCount() {
        return foodObjectResponse.getListOfRandomMeals().size();
    }
}
