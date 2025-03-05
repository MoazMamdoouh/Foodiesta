package com.example.foodiesta.Calender;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodiesta.Calender.Calender.CalenderEntity;
import com.example.foodiesta.R;

import java.util.List;

public class CalenderAdapter extends RecyclerView.Adapter<CalenderViewHolder> {
    private Context context ;
    private List<CalenderEntity> calenderEntities ;
    private OnCalenderIconClicked onCalenderIconClicked ;
    public CalenderAdapter(Context context, List<CalenderEntity> calenderEntities , OnCalenderIconClicked onCalenderIconClicked) {
        this.context = context;
        this.calenderEntities = calenderEntities;
        this.onCalenderIconClicked = onCalenderIconClicked ;
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
        holder.mealName.setText(calenderEntities.get(position).getMealName());
        holder.calenderIcon.setOnClickListener(clicked ->{
            int id = calenderEntities.get(position).getMealId() ;
            int year = calenderEntities.get(position).getYear() ;
            int month = calenderEntities.get(position).getMonth();
            int day = calenderEntities.get(position).getDay() ;
            String imageImage = calenderEntities.get(position).getMealImage() ;
            String imageName = calenderEntities.get(position).getMealName();
            onCalenderIconClicked.onCalenderIconClicked(id , year , month , day , imageImage , imageName);
        });

        holder.calenderCardView.setOnClickListener(
                clicked ->{
                    onCalenderIconClicked.calenderCardClicked(calenderEntities.get(position).getMealId());
                }
        );
    }

    @Override
    public int getItemCount() {
        return calenderEntities.size();
    }
}
