package com.example.foodiesta.Presentation.Details;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodiesta.Model.Details.DetailsResponse;
import com.example.foodiesta.R;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsViewHolder> {
    private Context context ;
    private List<String> ingredientList;
    private List<String> measureList ;
    private OnItemListener onItemListener ;

    public DetailsAdapter(Context context, List<String> ingredientList , List<String> measureList , OnItemListener onItemListener) {
        this.context = context;
        this.ingredientList = ingredientList ;
        this.measureList = measureList ;
        this.onItemListener = onItemListener ;
    }
    public void setIngredientList(List<String> ingredientList , List<String> measureList){
        this.ingredientList = ingredientList ;
        this.measureList = measureList ;
    }

    @NonNull
    @Override
    public DetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context) ;
        View view = layoutInflater.inflate(R.layout.row_details_ingrediants , parent , false);
        DetailsViewHolder detailsViewHolder = new DetailsViewHolder(view) ;
        return detailsViewHolder ;
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsViewHolder holder, int position) {
            holder.mealIngredient.setText(ingredientList.get(position));
            holder.mealMeasure.setText(measureList.get(position));
            String encodedIngredient ;
        try {
             encodedIngredient = URLEncoder.encode(ingredientList.get(position), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        Glide.with(context).load("https://www.themealdb.com/images/ingredients/" + encodedIngredient + "-Medium.png")
                            .placeholder(R.drawable.food)
                            .error(R.drawable.fooderror)
                            .into(holder.mealIngredientImage);

    }

    @Override
    public int getItemCount() {
        return ingredientList.size() ;
    }
}
