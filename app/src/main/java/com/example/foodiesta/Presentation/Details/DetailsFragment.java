package com.example.foodiesta.Presentation.Details;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.foodiesta.Data.Remore_data.MealsRemoteDataSource;
import com.example.foodiesta.Data.Repository.Deatails_repo.DetailsRepo;
import com.example.foodiesta.Model.Details.DetailsResponse;
import com.example.foodiesta.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;

public class DetailsFragment extends Fragment implements DetailsGateWay , OnItemListener {

   private ImageView mealImage ;
   private DetailsPresenter detailsPresenter ;
   private TextView mealName ;
   private Button categoryChip ;
   private Button countryChip ;
   private TextView mealInstructions ;
   private RecyclerView recyclerView ;
   private DetailsAdapter detailsAdapter ;
   private YouTubePlayerView youTubePlayerView ;

    public DetailsFragment() {
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
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUI(view);
        initDetailsPresenter();
        requestMealDetailById();

    }
    private void initDetailsPresenter() {
        DetailsRepo detailsRepo = new DetailsRepo(MealsRemoteDataSource.getInstance()) ;
        detailsPresenter = new DetailsPresenter(detailsRepo , this) ;
    }
    private void requestMealDetailById(){
        int id  = DetailsFragmentArgs.fromBundle(getArguments()).getMealId();
        detailsPresenter.getMealDetails(id);
    }
    private void initUI(View view) {
        mealImage = view.findViewById(R.id.details_iv_meal_image) ;
        mealName = view.findViewById(R.id.details_tv_meal_name) ;
        categoryChip = view.findViewById(R.id.details_chip_category);
        countryChip = view.findViewById(R.id.details_chip_counrty);
        mealInstructions = view.findViewById(R.id.details_tv_meal_instructions_change) ;
        youTubePlayerView = view.findViewById(R.id.details_youtube_player_view) ;
       // mealVideo = view.findViewById(R.id.details_vv_meal_video)  ;
        initRecyclerView(view);
    }
    private void initRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.details_rv_ingrediants);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        detailsAdapter = new DetailsAdapter(getContext() , new ArrayList<>() , new ArrayList<>() , this) ;
        recyclerView.setAdapter(detailsAdapter);
    }
    @Override
    public void ShowDetails(DetailsResponse detailsResponse) {
          setMealImage(detailsResponse);
          setChips(detailsResponse);
          setMealNameAndInstruction(detailsResponse);
          setIngredientList(detailsResponse);
          setYoutubeVideo(detailsResponse);
    }

    private void setIngredientList(DetailsResponse detailsResponse) {
        detailsAdapter.setIngredientList(detailsResponse.getListOfRandomMeals().get(0).getIngreientList()
        ,detailsResponse.getListOfRandomMeals().get(0).getMeasureList());
        detailsAdapter.notifyDataSetChanged();
    }
    private void setMealNameAndInstruction(DetailsResponse detailsResponse) {
        mealName.setText(detailsResponse.getListOfRandomMeals().get(0).getMealName());
        mealInstructions.setText(detailsResponse.getListOfRandomMeals().get(0).getMealInstructions());
    }
    private void setMealImage(DetailsResponse detailsResponse){
        Glide.with(getContext()).load(detailsResponse.getListOfRandomMeals().get(0).getMealImage())
                .placeholder(R.drawable.food)
                .error(R.drawable.fooderror)
                .into(mealImage);
    }
    private void setChips(DetailsResponse detailsResponse) {
        categoryChip.setText(detailsResponse.getListOfRandomMeals().get(0).getMealCategory());
        countryChip.setText(detailsResponse.getListOfRandomMeals().get(0).getMealCountry());
    }

    private void setYoutubeVideo(DetailsResponse detailsResponse) {
        getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = "S0Q4gqBUs7c";
                youTubePlayer.loadVideo(videoId, 0);
            }
        });
    }

    @Override
    public void onItemListener(String name) {
    }
}