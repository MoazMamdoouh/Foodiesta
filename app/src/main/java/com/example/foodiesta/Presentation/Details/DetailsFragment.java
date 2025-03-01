package com.example.foodiesta.Presentation.Details;

import android.app.DatePickerDialog;
import android.net.Uri;
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
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodiesta.Data.Local_data.MealsLocalDataSource;
import com.example.foodiesta.Data.Remore_data.MealsRemoteDataSource;
import com.example.foodiesta.Data.Repository.Deatails_repo.DetailsRepo;
import com.example.foodiesta.Model.Details.DetailsResponse;
import com.example.foodiesta.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;

public class DetailsFragment extends Fragment implements DetailsGateWay  {

   private ImageView mealImage ;
   private DetailsPresenter detailsPresenter ;
   private TextView mealName ;
   private Button categoryBtn;
   private Button countryBtn;
   private TextView mealInstructions ;
   private RecyclerView recyclerView ;
   private DetailsAdapter detailsAdapter ;
   private YouTubePlayerView youTubePlayerView ;
   private ImageView favIcon , calenderIcon;
   private String categoryName ;
   private int mealId ;
   private String mealUrl ;
   private String mealNameString  ;
   private DatePickerDialog datePickerDialog ;

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
        categoryBtnClicked(view) ;
        favoriteIconClicked() ;
        calenderIconClicked();
    }

    private void calenderIconClicked() {
        calenderIcon.setOnClickListener(clicked ->{
                datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        detailsPresenter.insertMealToCalender(year , month, dayOfMonth , mealId , mealUrl , mealNameString);
                    }
                }, 2025 , 0 , 15) ;
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            });

    }

    private void favoriteIconClicked() {
        favIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favIcon.setImageResource(R.drawable.favorite_icon);
                detailsPresenter.insertMealToFavorite(mealId , mealUrl , mealNameString );
            }
        });
    }

    private void categoryBtnClicked(View view) {
        categoryBtn.setOnClickListener( category ->
                sendCategoryNameToSearch(view)
                );
    }

    private void sendCategoryNameToSearch(View view){
        DetailsFragmentDirections.ActionDetailsFragmentToSearchFragment
                detailsFragmentToSearchFragment =
                DetailsFragmentDirections.actionDetailsFragmentToSearchFragment(categoryName) ;
        Navigation.findNavController(view).navigate(detailsFragmentToSearchFragment);
        Log.i("TAG", "sendCategoryNameToSearch: "+ categoryName);
    }
    private void initDetailsPresenter() {
        DetailsRepo detailsRepo = new DetailsRepo(MealsRemoteDataSource.getInstance() ,new MealsLocalDataSource(getContext())) ;
        detailsPresenter = new DetailsPresenter(detailsRepo , this) ;
    }
    private void requestMealDetailById(){
        int id  = DetailsFragmentArgs.fromBundle(getArguments()).getMealId();
        detailsPresenter.getMealDetails(id);
    }
    private void initUI(View view) {
        mealImage = view.findViewById(R.id.details_iv_meal_image) ;
        mealName = view.findViewById(R.id.details_tv_meal_name) ;
        categoryBtn = view.findViewById(R.id.details_chip_category);
        countryBtn = view.findViewById(R.id.details_chip_counrty);
        mealInstructions = view.findViewById(R.id.details_tv_meal_instructions_change) ;
        youTubePlayerView = view.findViewById(R.id.details_youtube_player_view) ;
        favIcon = view.findViewById(R.id.details_iv_favorite) ;
        calenderIcon = view.findViewById(R.id.details_iv_Calender) ;
        initRecyclerView(view);
    }
    private void initRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.details_rv_ingrediants);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        detailsAdapter = new DetailsAdapter(getContext() , new ArrayList<>() , new ArrayList<>() ) ;
        recyclerView.setAdapter(detailsAdapter);
    }
    @Override
    public void ShowDetails(DetailsResponse detailsResponse) {
          setMealImage(detailsResponse);
          setbuttons(detailsResponse);
          setMealNameAndInstruction(detailsResponse);
          setIngredientList(detailsResponse);
          setYoutubeVideo(detailsResponse);
          categoryName = detailsResponse.getListOfRandomMeals().get(0).getMealCategory() ;
          mealId = detailsResponse.getListOfRandomMeals().get(0).getMealId();
          mealNameString = detailsResponse.getListOfRandomMeals().get(0).getMealName();
          mealUrl = detailsResponse.getListOfRandomMeals().get(0).getMealImage() ;
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
    private void setbuttons(DetailsResponse detailsResponse) {
        categoryBtn.setText(detailsResponse.getListOfRandomMeals().get(0).getMealCategory());
        countryBtn.setText(detailsResponse.getListOfRandomMeals().get(0).getMealCountry());
    }
    private void setYoutubeVideo(DetailsResponse detailsResponse) {
            getLifecycle().addObserver(youTubePlayerView);

            String videoUrl = detailsResponse.getListOfRandomMeals().get(0).getMealVideo();
            String videoId = extractYoutubeVideoId(videoUrl);

            if (videoId != null) {
                youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        youTubePlayer.loadVideo(videoId, 0);
                    }
                });
            } else {
                Log.e("YouTube", "Invalid YouTube URL: " + videoUrl);
            }
        }
    private String extractYoutubeVideoId(String url) {
        if (url == null || url.isEmpty()) {
            return null;
        }
        Uri uri = Uri.parse(url);
        String videoId = null;

        if (url.contains("youtube.com")) {
            videoId = uri.getQueryParameter("v");
        } else if (url.contains("youtu.be")) {
            videoId = uri.getLastPathSegment();
        }
        return videoId;
    }

}

