package com.example.foodiesta.Presentation.Calender;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodiesta.Model.Calender.CalenderEntity;
import com.example.foodiesta.Presentation.Favorite.FavoriteViewHolder;
import com.example.foodiesta.R;

import java.util.List;

public class CalenderAdapter extends RecyclerView.Adapter<CalenderViewHolder> {
    private Context context ;
    private List<CalenderEntity> calenderEntities ;

    public CalenderAdapter(Context context, List<CalenderEntity> calenderEntities) {
        this.context = context;
        this.calenderEntities = calenderEntities;
    }

    void setCalenderList(List<CalenderEntity> calenderEntities){
        this.calenderEntities = calenderEntities ;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CalenderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context) ;
        View view = layoutInflater.inflate(R.layout.row_calender_card , parent, false);
        CalenderViewHolder calenderViewHolder = new CalenderViewHolder(view) ;
        return calenderViewHolder ;
    }

    @Override
    public void onBindViewHolder(@NonNull CalenderViewHolder holder, int position) {
        Glide.with(context).load(calenderEntities.get(position).getMealImage())
                .into(holder.mealImage) ;
    }

    @Override
    public int getItemCount() {
        return calenderEntities.size();
    }
}
