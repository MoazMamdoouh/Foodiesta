package com.example.foodiesta.Favorite;

import android.graphics.Canvas;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodiesta.Data.Local_data.MealsLocalDataSource;
import com.example.foodiesta.Favorite.Favorite.FavoriteEntity;
import com.example.foodiesta.MainActivity.MainActivity;
import com.example.foodiesta.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class FavoriteFragment extends Fragment implements OnFavoriteItemClicked {

    private RecyclerView recyclerView ;
    private FavoriteAdapter favoriteAdapter ;
    private FavoritePresenter favoritePresenter ;
    private View viewAtt ;

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
        swipeItemToDelete();
        viewAtt = view ;
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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext()) ;
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
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
        FavoriteFragmentDirections.ActionFavoriteFragment2ToDetailsFragment actionFavoriteFragment2ToDetailsFragment
                = FavoriteFragmentDirections.actionFavoriteFragment2ToDetailsFragment(id) ;
        Navigation.findNavController(viewAtt).navigate(actionFavoriteFragment2ToDetailsFragment);
    }

    private void swipeItemToDelete(){
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0 , ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                favoriteAdapter.removeItem(position);
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                new RecyclerViewSwipeDecorator.Builder(c , recyclerView,viewHolder , dX,dY,actionState , isCurrentlyActive)
                        .addSwipeLeftBackgroundColor(ContextCompat.getColor(requireContext() , R.color.Remove_color))
                        .addSwipeLeftActionIcon(R.drawable.delete_icon)
                                .addSwipeLeftLabel("Delete")
                                        .setSwipeLeftLabelColor(ContextCompat.getColor( requireContext(),R.color.white))
                                                .create()
                                                        .decorate();
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        } ;

       new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView);
    }

}