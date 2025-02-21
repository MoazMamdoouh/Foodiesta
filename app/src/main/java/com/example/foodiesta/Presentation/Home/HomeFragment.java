package com.example.foodiesta.Presentation.Home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.foodiesta.Data.Remore_data.MealsRemoteDataSource;
import com.example.foodiesta.Data.Repository.Home_repo.HomeRepository;
import com.example.foodiesta.Model.Home.List_meals.RandomMealsResponse;
import com.example.foodiesta.Model.Home.Random_meal.RandomDailyMealResponse;
import com.example.foodiesta.R;
import com.example.foodiesta.Utilities.OnItemClickListener;

import java.util.ArrayList;


public class HomeFragment extends Fragment implements  OnItemClickListener , HomeGateWay {

    private RecyclerView recyclerView ;
    private HomeAdapter homeAdapter ;
    private ImageView dailyMealImage ;
    private HomePresenter homePresenter ;
    private View view ;
    public HomeFragment() {
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


        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view ;
        initUI(view);
        initHomePresenter();
        requestMeals() ;
        requestRandomMeal() ;
    }

    private void requestRandomMeal() {
        homePresenter.getRandomMeal() ;
    }

    private void requestMeals() {
        //returns Random meal response
        homePresenter.getAllRemoteMeals() ;
    }
    private void initHomePresenter() {
        HomeRepository homeRepository = new HomeRepository( MealsRemoteDataSource.getInstance());
        homePresenter = new HomePresenter(homeRepository , this);
    }

    private void initUI(View view) {
        recyclerView = view.findViewById(R.id.home_rv_meals);
        dailyMealImage = view.findViewById(R.id.home_iv_daily_meal_image);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager linearLayoutManager = new GridLayoutManager(getContext() , 2) ;
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        homeAdapter = new HomeAdapter(getContext() , new RandomMealsResponse(), this) ;
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(homeAdapter);
    }
    @Override
    public void onItemClicked(int id) {
        Toast.makeText(getContext(), "item id = " + id, Toast.LENGTH_SHORT).show();
        com.example.foodiesta.Presentation.Home.HomeFragmentDirections.ActionHomeFragmentToDetailsFragment homeFragmentDirections =
                HomeFragmentDirections.actionHomeFragmentToDetailsFragment(id) ;
        Navigation.findNavController(view).navigate(homeFragmentDirections);
    }

    @Override
    public void showMeals(RandomMealsResponse randomMealsResponse) {
      homeAdapter.setRandomMealsResponse(randomMealsResponse) ;
      homeAdapter.notifyDataSetChanged();
    }

    @Override
    public void showRandomMeal(RandomDailyMealResponse randomDailyMealResponse) {
        Glide.with(getContext()).load(randomDailyMealResponse.getListOfRandomMeals().get(0).getMealImage())
                .placeholder(R.drawable.food)
                .into(dailyMealImage) ;
    }

    @Override
    public void showError(String msg) {

    }
}