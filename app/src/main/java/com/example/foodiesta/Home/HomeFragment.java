package com.example.foodiesta.Home;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.foodiesta.Data.Remore_data.MealsRemoteDataSource;
import com.example.foodiesta.MainActivity.MainActivity;
import com.example.foodiesta.Utilities.FoodObjectResponse;
import com.example.foodiesta.R;
import com.example.foodiesta.Utilities.LoadingDialog;
import com.example.foodiesta.Utilities.OnItemClickListener;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;


public class HomeFragment extends Fragment implements  OnItemClickListener  {

    private RecyclerView recyclerView ;
    private HomeAdapter homeAdapter ;
    private ImageView dailyMealImage ;
    private HomePresenter homePresenter ;
    private CardView dailyMealCardView ;
    private View view ;
    private int mealId = 0 ;
    private LoadingDialog loadingDialog ;
    private Date calendar ;
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
        loadingDialog = new LoadingDialog(getContext());
        initUI(view);
        initHomePresenter();
        requestMeals() ;
        ((MainActivity) requireActivity()).showBottomNav(true);
         dailyMealCardView.setOnClickListener(click ->
                 navigateThroughDailyMeal()
              );

         getCurrentDate();
         loadingDialog.showLoadingAnimation();


    }
    private void getCurrentDate() {
        String  format = DateFormat.getDateInstance().format(calendar);
        SharedPreferences timeSharedPreference = getActivity().getSharedPreferences("timeSharedPreference " , Context.MODE_PRIVATE);
        SharedPreferences imageSharedPreference =  getActivity().getSharedPreferences("imageSharedPreference " , Context.MODE_PRIVATE);
        SharedPreferences idSharedPreference =  getActivity().getSharedPreferences("idSharedPreference " , Context.MODE_PRIVATE);
               mealId = idSharedPreference.getInt("mealId" , 0) ;
        String savedDate = timeSharedPreference.getString("CurrentDate" , null);
        String imageSp =  imageSharedPreference.getString("imageUrl" , null);
        if(savedDate == null){
            SharedPreferences.Editor editor = timeSharedPreference.edit();
            editor.putString("CurrentDate", format);
            editor.apply();
            requestRandomMeal();
        } else if(savedDate.equals(format) && imageSp != null){
                Glide.with(getContext()).load(imageSp)
                        .placeholder(R.drawable.food)
                        .error(R.drawable.fooderror)
                        .into(dailyMealImage);
        }else {
            SharedPreferences.Editor editor = timeSharedPreference.edit();
            editor.putString("CurrentDate", format);
            editor.apply();
            requestRandomMeal() ;
        }
    }
    private void navigateThroughDailyMeal() {

       com.example.foodiesta.Home.HomeFragmentDirections.ActionHomeFragmentToDetailsFragment homeFragmentDirections =
                com.example.foodiesta.Home.HomeFragmentDirections.actionHomeFragmentToDetailsFragment(mealId) ;
        Navigation.findNavController(view).navigate(homeFragmentDirections);
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
        dailyMealCardView = view.findViewById(R.id.home_card_daily_meal) ;
        recyclerView = view.findViewById(R.id.home_rv_meals);
        dailyMealImage = view.findViewById(R.id.home_iv_daily_meal_image);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager linearLayoutManager = new GridLayoutManager(getContext() , 2) ;
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        homeAdapter = new HomeAdapter(getContext() , new FoodObjectResponse(), this) ;
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(homeAdapter);
        calendar = Calendar.getInstance().getTime();
    }
    @Override
    public void onItemClicked(int id) {
        com.example.foodiesta.Home.HomeFragmentDirections.ActionHomeFragmentToDetailsFragment homeFragmentDirections =
                com.example.foodiesta.Home.HomeFragmentDirections.actionHomeFragmentToDetailsFragment(id) ;
        Navigation.findNavController(view).navigate(homeFragmentDirections);
    }

    public void showRandomMeals(FoodObjectResponse foodObjectResponse){
        loadingDialog.hideDialog();
        dailyMealCardView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
        homeAdapter.setRandomMealsResponse(foodObjectResponse) ;
        homeAdapter.notifyDataSetChanged();
    }

    void showRandomDailyMeal(FoodObjectResponse foodObjectResponse){
        Glide.with(requireActivity()).load(foodObjectResponse.getListOfRandomMeals().get(0).getMealImage())
                .placeholder(R.drawable.food)
                .error(R.drawable.fooderror)
                .into(dailyMealImage) ;
        mealId = foodObjectResponse.getListOfRandomMeals().get(0).getId() ;
        mealIdSharedPreference(foodObjectResponse);
        imageUrlSharedPreference(foodObjectResponse);
    }
    private void imageUrlSharedPreference(FoodObjectResponse randomDailyMealResponse){
        SharedPreferences imageSharedPreference =  getActivity().getSharedPreferences("imageSharedPreference " , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = imageSharedPreference.edit() ;
        editor.putString("imageUrl" ,randomDailyMealResponse.getListOfRandomMeals().get(0).getMealImage());
        editor.apply();
    }

    void showError(String error){
        Toast.makeText(getContext(), "Failed" + error, Toast.LENGTH_SHORT).show();
    }

    private void mealIdSharedPreference(FoodObjectResponse randomDailyMealResponse){
        SharedPreferences idSharedPreference =  getActivity().getSharedPreferences("idSharedPreference " , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = idSharedPreference.edit() ;
        editor.putInt("mealId" ,randomDailyMealResponse.getListOfRandomMeals().get(0).getId());
        editor.apply();
    }
}