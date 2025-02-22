package com.example.foodiesta.Presentation.Serach;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.foodiesta.Model.Home.List_meals.RandomMealsResponse;
import com.example.foodiesta.R;


public class SearchFragment extends Fragment implements SearchShowResponse , OnSearchItemClicked  {

    private SearchAdapter searchIngredientAdapter ;
    private RecyclerView searchIngredientRecyclerView ;
    private SearchPresenter searchPresenter ;
    private EditText searchEditText ;
    private Button ingredientBtn ;
    private Button countryBtn ;
    private Button categoryBtn ;
    private boolean filterFlag = true ;
    private LottieAnimationView lottieAnimationView ;
    private LottieAnimationView lottieLoadingAnimation ;
    private String categoryName ;
    private View viewAttr ;

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
        viewAttr= view ;
        initUI(view) ;
        initPresenter() ;
        showSearchLottieFile();
        // ingredient filter clicked
        ingredientBtnClicked() ;
        //country filter clicked
        countryBtnClicked();
        //category filter clicked
        categoryBtnClicked();
        //getCategoryNameFromDetails() ;
    }
    private void getCategoryNameFromDetails() {
        categoryName = SearchFragmentArgs.fromBundle(getArguments()).getCategoryName() ;
        Log.i("TAG", "getCategoryNameFromDetails: " + categoryName);
    }
    private void categoryBtnClicked() {
        categoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoadingLottieFile();
                searchEditText.setHint("Search By Category ...");
                categoryBtn.setBackground(getResources().getDrawable(R.drawable.search_btn_clicked_background));
                categoryBtn.setTextColor(getResources().getColor(R.color.white));

                ingredientBtn.setBackground(getResources().getDrawable(R.drawable.search_btn_background));
                ingredientBtn.setTextColor(getResources().getColor(R.color.bright_orange));

                countryBtn.setBackground(getResources().getDrawable(R.drawable.search_btn_background));
                countryBtn.setTextColor(getResources().getColor(R.color.bright_orange));
                  requestRandomListOfCategory("SeaFood");
            }
        });
    }
    private void countryBtnClicked() {
        countryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoadingLottieFile();
                searchEditText.setHint("Search By Country ...");
                countryBtn.setBackground(getResources().getDrawable(R.drawable.search_btn_clicked_background));
                countryBtn.setTextColor(getResources().getColor(R.color.white));

                ingredientBtn.setBackground(getResources().getDrawable(R.drawable.search_btn_background));
                ingredientBtn.setTextColor(getResources().getColor(R.color.bright_orange));

                categoryBtn.setBackground(getResources().getDrawable(R.drawable.search_btn_background));
                categoryBtn.setTextColor(getResources().getColor(R.color.bright_orange));

                requestRandomListOfCounties();
            }
        });
    }
    private void showLoadingLottieFile(){
        lottieAnimationView.setVisibility(View.GONE);
        lottieLoadingAnimation.setVisibility(View.VISIBLE);
        lottieLoadingAnimation.playAnimation();
    }
    private void disableLoadingLottieFile(){
        lottieLoadingAnimation.setVisibility(View.GONE);
    }
    private void ingredientBtnClicked() {
        ingredientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoadingLottieFile();
                searchEditText.setHint("Search By Ingredient ...");
                ingredientBtn.setBackground(getResources().getDrawable(R.drawable.search_btn_clicked_background));
                ingredientBtn.setTextColor(getResources().getColor(R.color.white));

                countryBtn.setBackground(getResources().getDrawable(R.drawable.search_btn_background));
                countryBtn.setTextColor(getResources().getColor(R.color.bright_orange));

                categoryBtn.setBackground(getResources().getDrawable(R.drawable.search_btn_background));
                categoryBtn.setTextColor(getResources().getColor(R.color.bright_orange));

                requestRandomListOfIngredientList();
            }
        });
    }
    private void requestRandomListOfCategory(String category) {
        searchPresenter.requestRandomListOfCategory(category);
    }
    private void requestRandomListOfCounties() {
        searchPresenter.requestRandomListOfCountries();
    }
    void requestRandomListOfIngredientList() {
        searchPresenter.requestRandomListOfIngredient() ;
    }
    private void showSearchLottieFile() {
        if(filterFlag) {
            lottieAnimationView.playAnimation();
        }else lottieAnimationView.cancelAnimation();
    }

    private void initPresenter() {
        searchPresenter = new SearchPresenter(this) ;
    }

    private void initUI(View view) {
        searchEditText = view.findViewById(R.id.search_et_search_bar) ;
        initRecyclerView(view) ;
        lottieAnimationView = view.findViewById(R.id.search_lottie_file_search) ;
        ingredientBtn = view.findViewById(R.id.search_btn_ingredient) ;
        countryBtn = view.findViewById(R.id.search_btn_country) ;
        categoryBtn = view.findViewById(R.id.search_btn_category) ;
        lottieLoadingAnimation = view.findViewById(R.id.search_lottie_file_loading) ;
        lottieLoadingAnimation.setVisibility(View.GONE) ;
    }

    private void initRecyclerView(View view) {
        searchIngredientRecyclerView = view.findViewById(R.id.search_rv_list) ;
        searchIngredientRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        searchIngredientRecyclerView.setLayoutManager(linearLayoutManager);
        searchIngredientAdapter = new SearchAdapter(getContext() , new RandomMealsResponse() , this);
    }


    @Override
    public void onShowResponseOfRandomListByFilter(RandomMealsResponse randomMealsResponse, String query) {
        disableLoadingLottieFile();
        searchIngredientAdapter.setList(randomMealsResponse);
        searchIngredientRecyclerView.setAdapter(searchIngredientAdapter);
        searchIngredientAdapter.notifyDataSetChanged();
    }

    @Override
    public void failureResponse(String msg) {
        Toast.makeText(getContext(), "Error" + msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSearchItemClicked(int id) {
        SearchFragmentDirections.ActionSearchFragmentToDetailsFragment
                actionSearchFragmentToDetailsFragment =
                SearchFragmentDirections.actionSearchFragmentToDetailsFragment(id);
        Navigation.findNavController(viewAttr).navigate(actionSearchFragmentToDetailsFragment);
    }
}