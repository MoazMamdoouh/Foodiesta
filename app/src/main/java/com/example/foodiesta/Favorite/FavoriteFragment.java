package com.example.foodiesta.Favorite;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodiesta.Data.Local_data.MealsLocalDataSource;
import com.example.foodiesta.Favorite.Favorite.FavoriteEntity;
import com.example.foodiesta.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment implements OnFavoriteItemClicked {

    private RecyclerView recyclerView ;
    private FavoriteAdapter favoriteAdapter ;
    private FavoritePresenter favoritePresenter ;

    public FavoriteFragment() {
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
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUI(view);
        initPresenter() ;
        requestFavoriteMeals();
    }

     void requestFavoriteMeals() {
                favoritePresenter.requestFavoriteMeals() ;
    }
    void getListOfFavoriteMeals(List<FavoriteEntity> favoriteEntities){
        favoriteAdapter.setList(favoriteEntities);
    }

    private void initPresenter() {
        MealsLocalDataSource mealsLocalDataSource = new MealsLocalDataSource(getContext()) ;
        FavoriteRepo favoriteRepo = new FavoriteRepo(mealsLocalDataSource) ;
        favoritePresenter = new FavoritePresenter(favoriteRepo , this);
    }

    private void initUI(View view) {
        recyclerView = view.findViewById(R.id.favorite_rv) ;
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext() , 2) ;
        recyclerView.setLayoutManager(gridLayoutManager);
        favoriteAdapter = new FavoriteAdapter(getContext() , new ArrayList<>() , this) ;
        recyclerView.setAdapter(favoriteAdapter);
    }

    @Override
    public void onFavoriteItemClickListener(int id, String url, String name) {
        new MaterialAlertDialogBuilder(requireContext())
                .setTitle("Delete "+ name )
                .setMessage("Are you sure you want to delete "+name+ " ?")
                .setPositiveButton("Delete", (dialog, which) -> {
                    favoritePresenter.deleteMealFromFavorite(id , url , name );
                })
                .setNegativeButton("Cancel", (dialog, which) -> {
                })
                .show();
    }

    @Override
    public void onFavoriteCardClicked(int id) {
        
    }
}