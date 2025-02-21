package com.example.foodiesta.Presentation.Serach;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.airbnb.lottie.LottieAnimationView;
import com.example.foodiesta.Model.Home.List_meals.RandomMealsResponse;
import com.example.foodiesta.R;
import com.google.android.material.chip.Chip;


public class SearchFragment extends Fragment implements SearchShowResponse  {

    private SearchIngredientAdapter searchIngredientAdapter ;
    private RecyclerView searchIngredientRecyclerView ;
    private SearchPresenter searchPresenter ;
    private EditText searchEditText ;
    private Chip ingredientChip ;
    private boolean ingredientChipFlag = false ;
    private LottieAnimationView lottieAnimationView ;

    public SearchFragment() {
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
        return inflater.inflate(R.layout.fragment_search, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view) ;
        initPresenter() ;
        requestIngredientList();

        ingredientChip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ingredientChipFlag = true ;
                searchEditText.setHint("search with spacific Ingredient ..");
                showLottieFile();
            }
        });

        showLottieFile();

    }

    private void showLottieFile() {
        if(!ingredientChipFlag){
            lottieAnimationView.playAnimation();
        }
        else lottieAnimationView.setVisibility(View.GONE);
    }

    private void initPresenter() {
        searchPresenter = new SearchPresenter(this) ;
    }

     void requestIngredientList() {
        searchPresenter.getListOfIngredient() ;
    }

    private void initUI(View view) {
        searchEditText = view.findViewById(R.id.search_et_search_bar) ;
        initRecyclerView(view) ;
        lottieAnimationView = view.findViewById(R.id.search_lottie_file_search) ;
    }

    private void initRecyclerView(View view) {
        searchIngredientRecyclerView = view.findViewById(R.id.search_rv_list) ;
        searchIngredientRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        searchIngredientRecyclerView.setLayoutManager(linearLayoutManager);
        searchIngredientAdapter = new SearchIngredientAdapter(getContext() , new RandomMealsResponse());
    }

    @Override
    public void onShowResponse(RandomMealsResponse randomMealsResponse) {
        if (ingredientChipFlag) {
            searchIngredientAdapter.setList(randomMealsResponse);
            searchIngredientRecyclerView.setAdapter(searchIngredientAdapter);
            showLottieFile();
        }
    }

}