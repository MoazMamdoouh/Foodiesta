package com.example.foodiesta.Presentation.Details;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodiesta.R;

public class DetailsViewHolder extends RecyclerView.ViewHolder {
    TextView mealIngredient ;
    TextView mealMeasure ;
    ImageView mealIngredientImage ;
    ImageView mealsFavIcon , mealsCalenderIcon ;
    Context context ;
    public DetailsViewHolder(@NonNull View itemView , Context context) {
        super(itemView);
        mealIngredient = itemView.findViewById(R.id.row_ingredient_tv_ingredient_name);
        mealMeasure = itemView.findViewById(R.id.row_ingredient_tv_ingredient_measure) ;
        mealIngredientImage = itemView.findViewById(R.id.row_ingredient_iv_ingredient_image) ;
        mealsFavIcon = itemView.findViewById(R.id.details_iv_favorite);
        this.context = context ;
    }


}
