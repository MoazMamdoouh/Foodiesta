package com.example.foodiesta.Presentation.Calender;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.foodiesta.Data.Local_data.MealsLocalDataSource;
import com.example.foodiesta.Data.Repository.Calender.CalenderRepo;
import com.example.foodiesta.Model.Calender.CalenderEntity;
import com.example.foodiesta.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

public class CalenderFragment extends Fragment  implements OnCalenderIconClicked{

    private CalendarView calendarView ;
    private CalenderPresenter calenderPresenter ;
    private RecyclerView recyclerView ;
    private CalenderAdapter calenderAdapter ;
    public CalenderFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calender, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);
        intiPresenter();

        CalenderClicked();

    }

    private void CalenderClicked() {
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                requestMealsThroughSpacificDate(year , month , dayOfMonth) ;
                Toast.makeText(getContext(), "date is " + year +"/" + month +"/"+dayOfMonth , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void requestMealsThroughSpacificDate(int year , int month , int dayOfMonth) {
         calenderPresenter.getMealThroughSpacificDate(year, month, dayOfMonth) ;
    }
    void getMealsThroughSpacificDate(List<CalenderEntity> calenderEntities){
         calenderAdapter.setCalenderList(calenderEntities);
    }


    private void intiPresenter(){
        MealsLocalDataSource mealsLocalDataSource = new MealsLocalDataSource(getContext());
        CalenderRepo calenderRepo = new CalenderRepo(mealsLocalDataSource);
        calenderPresenter = new CalenderPresenter(calenderRepo , this) ;
    }
    private void initUI(View view) {
        calendarView = view.findViewById(R.id.calender_cl) ;
        initRecyclerView(view);
    }
    private void initRecyclerView(View view){
        recyclerView = view.findViewById(R.id.calender_rv);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        calenderAdapter = new CalenderAdapter(getContext() , new ArrayList<>() , this) ;
        recyclerView.setAdapter(calenderAdapter);
    }


    @Override
    public void onCalenderIconClicked(int id, int year, int month, int day, String mealImage, String mealName) {
        new MaterialAlertDialogBuilder(requireContext())
                .setTitle("Delete "+mealName)
                .setMessage("Are you sure you want to delete "+mealName +" from Calender ? ")
                .setPositiveButton("Delete", (dialog, which) -> {
                    calenderPresenter.deleteMealFromCalender(id , year , month , day , mealImage, mealName);
                })
                .setNegativeButton("Cancel", (dialog, which) -> {

                })
                .show();
    }
}