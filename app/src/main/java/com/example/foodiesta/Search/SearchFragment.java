package com.example.foodiesta.Search;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.airbnb.lottie.LottieAnimationView;
import com.example.foodiesta.MainActivity.MainActivity;
import com.example.foodiesta.Search.Country.CountryObjectResponse;
import com.example.foodiesta.Utilities.FoodObjectResponse;
import com.example.foodiesta.Search.Category.CategoryObjectResponse;
import com.example.foodiesta.Search.Ingredient.IngredientObjectResponse;
import com.example.foodiesta.R;
import com.example.foodiesta.Utilities.LoadingDialog;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;

public class SearchFragment extends Fragment implements SearchShowResponse, OnSearchItemClicked {

    private CategoriesAdapter searchIngredientAdapter ;
    private RecyclerView searchRecyclerView;
    private SearchPresenter searchPresenter ;
    private EditText searchEditText ;
    private Button ingredientBtn ,countryBtn , categoryBtn ;
    private boolean filterFlag = true ;
    private LottieAnimationView lottieAnimationView ;
    private SearchResponseAdapter searchListOfSpacificItem;
    private IngredientsAdapter allIngredientsAdapter ;
    private CountryAdapter countryAdapter ;
    private View viewAtt;
    private LoadingDialog loadingDialog ;
    private String filterName = "" ;
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
        ((MainActivity) requireActivity()).showBottomNav(true);
        initUI(view) ;
        initPresenter() ;
        showSearchLottieFile();
        categoryBtnClicked();
        ingredientBtnClicked();
        countryBtnClicked();
        viewAtt = view;
    }
    private void initUI(View view) {
        searchEditText = view.findViewById(R.id.search_et_search_bar) ;
        initRecyclerView(view) ;
        lottieAnimationView = view.findViewById(R.id.search_lottie_file_search) ;
        ingredientBtn = view.findViewById(R.id.search_btn_ingredient) ;
        countryBtn = view.findViewById(R.id.search_btn_country) ;
        categoryBtn = view.findViewById(R.id.search_btn_category) ;
        loadingDialog = new LoadingDialog(getContext());
    }
    private void initRecyclerView(View view) {
        searchRecyclerView = view.findViewById(R.id.search_rv_list) ;
        searchRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        searchRecyclerView.setLayoutManager(linearLayoutManager);
        searchIngredientAdapter = new CategoriesAdapter(getContext() , new ArrayList<>() , this);
        //category adapter
        searchListOfSpacificItem = new SearchResponseAdapter(getContext() , new ArrayList<>() , this);
        allIngredientsAdapter = new IngredientsAdapter(getContext() , new ArrayList<>() , this) ;
        countryAdapter = new CountryAdapter(getContext() , new ArrayList<>() , this)  ;
    }
    private void initPresenter() {
        searchPresenter = new SearchPresenter(this , this) ;
    }
    private void showSearchLottieFile() {
        if(filterFlag) {
            lottieAnimationView.playAnimation();
        }else lottieAnimationView.cancelAnimation();
    }
    private void categoryBtnClicked() {
        categoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterName = "category" ;
                showLoadingLottieFile();
                changeFilterBtnLayout("category");
                requestRandomListOfCategory();
            }
        });
    }
    @SuppressLint("CheckResult")
    private void setupCategorySearchObserver(CategoryObjectResponse response){
        Observable<String> obs = Observable.create(emitter -> {
            searchEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    emitter.onNext(s.toString());
                }
                @Override
                public void afterTextChanged(Editable s) {}
            });
        });

        obs.debounce(1, TimeUnit.SECONDS).flatMap(item -> Observable.fromIterable(response.getListOfCategoriesSearches())
                .filter(listItem -> listItem.getCategoryName().toLowerCase().contains(item.toLowerCase())).toList().toObservable())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        onNext -> {
                            searchIngredientAdapter.setList(onNext);
                        }
                );
    }
    private void requestRandomListOfCategory() {
        searchPresenter.requestListOfCategory();
    }
    void getListOfCategoriesByName(FoodObjectResponse foodObjectResponse){
        searchRecyclerView.setAdapter(searchListOfSpacificItem);
        searchListOfSpacificItem.setListOfSearchResult(foodObjectResponse.getListOfRandomMeals());
        setupRandomMealResponseObserver(foodObjectResponse);
    }
    @SuppressLint("CheckResult")
    private void setupRandomMealResponseObserver(FoodObjectResponse foodObjectResponse){
        Observable<String> obs = Observable.create(emitter -> {
            searchEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    emitter.onNext(s.toString());
                }
                @Override
                public void afterTextChanged(Editable s) {}
            });
        });

        obs.debounce(1, TimeUnit.SECONDS).flatMap(item -> Observable.fromIterable(foodObjectResponse.getListOfRandomMeals())
                        .filter(listItem -> listItem.getMealName().toLowerCase().contains(item.toLowerCase())).toList().toObservable())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        onNext -> {
                            searchListOfSpacificItem.setListOfSearchResult(onNext);
                        }
                );
    }

     private void ingredientBtnClicked() {
        ingredientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoadingLottieFile();
                changeFilterBtnLayout("ingredient");
                searchPresenter.requestListOfIngredient();

            }
        });
    }
    void getAllIngredient(IngredientObjectResponse ingredientObjectResponse){
            disableLoadingLottieFile();
            allIngredientsAdapter.setAllIngredientList(ingredientObjectResponse.getAllIngredientsList());
            searchRecyclerView.setAdapter(allIngredientsAdapter);
            setUpAllIngredientObserver(ingredientObjectResponse);
    }

    @SuppressLint("CheckResult")
    void setUpAllIngredientObserver(IngredientObjectResponse ingredientObjectResponse){
        Observable<String> obs = Observable.create(emitter -> {
            searchEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    emitter.onNext(s.toString());
                }
                @Override
                public void afterTextChanged(Editable s) {}
            });
        });
        obs.debounce(1, TimeUnit.SECONDS).flatMap(item -> Observable.fromIterable(ingredientObjectResponse.getAllIngredientsList())
                        .filter(listItem -> listItem.getIngredientName().toLowerCase().contains(item.toLowerCase())).toList().toObservable())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        onNext -> {
                            allIngredientsAdapter.setAllIngredientList(onNext);
                        }
                );
    }
    void getListOfAllIngredientByName(FoodObjectResponse foodObjectResponse){
         disableLoadingLottieFile();
         searchListOfSpacificItem.setListOfSearchResult(foodObjectResponse.getListOfRandomMeals());
         searchRecyclerView.setAdapter(searchListOfSpacificItem);
         setupRandomMealResponseObserver(foodObjectResponse);
    }

    private void countryBtnClicked() {
        countryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoadingLottieFile();
                changeFilterBtnLayout("country");
                searchPresenter.requestListOfCountries();
            }
        });
    }
     void getAllCountries(CountryObjectResponse countryObjectResponse){
            disableLoadingLottieFile();
            countryAdapter.setCountyList(countryObjectResponse.getListOfCountriesSearches());
            searchRecyclerView.setAdapter(countryAdapter);
            setUpAllCountriesObserver(countryObjectResponse);
    }
    @SuppressLint("CheckResult")
    void setUpAllCountriesObserver(CountryObjectResponse countryObjectResponse){
        Observable<String> obs = Observable.create(emitter -> {
            searchEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    emitter.onNext(s.toString());
                }
                @Override
                public void afterTextChanged(Editable s) {}
            });
        });
        obs.debounce(1, TimeUnit.SECONDS).flatMap(item -> Observable.fromIterable(countryObjectResponse.getListOfCountriesSearches())
                        .filter(listItem -> listItem.getCountryName().toLowerCase().contains(item.toLowerCase())).toList().toObservable())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        onNext -> {
                            countryAdapter.setCountyList(onNext);
                        }
                );
    }

    void getListOfCountriesByName(FoodObjectResponse foodObjectResponse){
        searchListOfSpacificItem.setListOfSearchResult(foodObjectResponse.getListOfRandomMeals());
        searchRecyclerView.setAdapter(searchListOfSpacificItem);
        setupRandomMealResponseObserver(foodObjectResponse);
    }

    private void showLoadingLottieFile(){
        lottieAnimationView.setVisibility(View.GONE);
        loadingDialog.showLoadingAnimation();
    }
    private void disableLoadingLottieFile(){
      loadingDialog.hideDialog();
    }

    @Override
    public void onShowResponseOfRandomListByFilter(CategoryObjectResponse randomMealsResponse) {
        disableLoadingLottieFile();
        searchIngredientAdapter.setList(randomMealsResponse.getListOfCategoriesSearches());
        searchRecyclerView.setAdapter(searchIngredientAdapter);
        setupCategorySearchObserver(randomMealsResponse);
    }

    @Override
    public void failureResponse(String msg) {
        Toast.makeText(getContext(), "Error" + msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFilterItemClicked(String  itemName , String filterName) {
        if(filterName.equals("category")){
            searchPresenter.requestListOfSpacificCategory(itemName );
        }else if (filterName.equals("ingredient")){
            searchPresenter.requestListOfSpacificIngredient(itemName);
        }else if (filterName.equals("country")){
            searchPresenter.requestListOfSpacificCountry(itemName);
        }

    }

    @Override
    public void onSearchItemClicked(int id) {
       com.example.foodiesta.Search.SearchFragmentDirections.ActionSearchFragmentToDetailsFragment actionSearchFragmentToDetailsFragment
                = com.example.foodiesta.Search.SearchFragmentDirections.actionSearchFragmentToDetailsFragment(id) ;
        Navigation.findNavController(viewAtt).navigate(actionSearchFragmentToDetailsFragment);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void changeFilterBtnLayout(String filterName){
        if(filterName.equals("ingredient")){
            searchEditText.setHint("Search By Ingredient ...");
            ingredientBtn.setBackground(getResources().getDrawable(R.drawable.search_btn_clicked_background));
            ingredientBtn.setTextColor(getResources().getColor(R.color.white));

            countryBtn.setBackground(getResources().getDrawable(R.drawable.search_btn_background));
            countryBtn.setTextColor(getResources().getColor(R.color.purple));

            categoryBtn.setBackground(getResources().getDrawable(R.drawable.search_btn_background));
            categoryBtn.setTextColor(getResources().getColor(R.color.purple));
        }else if (filterName.equals("category")){
            searchEditText.setHint("Search By Category ...");
            categoryBtn.setBackground(getResources().getDrawable(R.drawable.search_btn_clicked_background));
            categoryBtn.setTextColor(getResources().getColor(R.color.white));

            ingredientBtn.setBackground(getResources().getDrawable(R.drawable.search_btn_background));
            ingredientBtn.setTextColor(getResources().getColor(R.color.purple));

            countryBtn.setBackground(getResources().getDrawable(R.drawable.search_btn_background));
            countryBtn.setTextColor(getResources().getColor(R.color.purple));
        }else if (filterName.equals("country")){
            searchEditText.setHint("Search By Country ...");
            countryBtn.setBackground(getResources().getDrawable(R.drawable.search_btn_clicked_background));
            countryBtn.setTextColor(getResources().getColor(R.color.white));

            ingredientBtn.setBackground(getResources().getDrawable(R.drawable.search_btn_background));
            ingredientBtn.setTextColor(getResources().getColor(R.color.purple));

            categoryBtn.setBackground(getResources().getDrawable(R.drawable.search_btn_background));
            categoryBtn.setTextColor(getResources().getColor(R.color.purple));
        }
    }
}