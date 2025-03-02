package com.example.foodiesta.Profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodiesta.Data.Local_data.MealsLocalDataSource;
import com.example.foodiesta.Data.Remore_data.MealsRemoteFireBase;
import com.example.foodiesta.R;
import com.example.foodiesta.Utilities.CustomDialog;
import com.example.foodiesta.Utilities.LoadingDialog;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.List;
import java.util.Objects;

public class ProfileFragment<T> extends Fragment {

    private TextView chefNameTextView, chefEmailTextView;
    private FirebaseAuth firebaseAuth;
    private String userId;
    private Button favoriteBackUpBtn , calenderBackUpBtn , logOutBtn , downloadFavoriteBtn , downloadCalenderBtn;
    private FirebaseFirestore firebaseFirestore;
    private ProfilePresenter profilePresenter;
    private LoadingDialog loadingDialog ;

    public ProfileFragment() {
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
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);
        initPresenter();
        getUserName();
        favoriteBackUpBtnClicked();
        downloadFavoriteBtnClicked();
        logOutBtnClicked(view);
        calenderBtnClicked();
        downloadCalenderBtn();
    }

    private void logOutBtnClicked(View view) {
        logOutBtn.setOnClickListener(
                clicked -> {
                    new MaterialAlertDialogBuilder(requireContext())
                            .setTitle("Logout" )
                            .setMessage("Are you sure you want Logout ?")
                            .setPositiveButton("logout", (dialog, which) -> {
                                FirebaseAuth.getInstance().signOut();
                                Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_welcomeFragment);
                            })
                            .setNegativeButton("Cancel", (dialog, which) -> {
                            })
                            .show();
                }
        );
    }


    private void initUI(View view) {
        chefNameTextView = view.findViewById(R.id.profile_tv_chef_name);
        chefEmailTextView = view.findViewById(R.id.profile_tv_chef_email);
        favoriteBackUpBtn = view.findViewById(R.id.profile_btn_backup_favorite);
        calenderBackUpBtn = view.findViewById(R.id.profile_btn_backup_calendet) ;
        logOutBtn = view.findViewById(R.id.profile_btn_log_out) ;
        downloadFavoriteBtn = view.findViewById(R.id.profile_btn_fetch_favorite) ;
        downloadCalenderBtn = view.findViewById(R.id.profile_btn_fetch_calendet) ;
        loadingDialog = new LoadingDialog(getContext()) ;
        //fire base
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    private void initPresenter() {
        MealsRemoteFireBase mealsRemoteFireBase = new MealsRemoteFireBase() ;
        MealsLocalDataSource mealsLocalDataSource = new MealsLocalDataSource(getContext());
        ProfileRepo profileRepo = new ProfileRepo(mealsLocalDataSource , mealsRemoteFireBase);
        profilePresenter = new ProfilePresenter(profileRepo, this);
    }

    private void favoriteBackUpBtnClicked() {
        favoriteBackUpBtn.setOnClickListener(
                click -> {
                    showLoadingAnimation();
                    userId = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();
                    profilePresenter.getListOfFavoriteMeal(userId, firebaseFirestore);
                }
        );
    }

    private void downloadFavoriteBtnClicked() {
        downloadFavoriteBtn.setOnClickListener(
                clicked -> {
                    showLoadingAnimation();
                    userId = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();
                    profilePresenter.downloadAllFavoriteMeals(userId,firebaseFirestore);
                    Toast.makeText(getContext(), "clicked", Toast.LENGTH_LONG).show();
                }
        );
    }

    public void successInsertFavorite(String type) {
        hideLoadingAnimation();
        if(type.equals("favorite")){
            profilePresenter.deleteAllFavoriteMealsFromRoom();
        }else {
            profilePresenter.deleteAllCalenderMealsFromRoom();
        }

    }

    void successDownLoadFavorite(List<T> downloadedList  , String type){
        hideLoadingAnimation();
        if(type.equals("favorite")){
            profilePresenter.inseMealsList(downloadedList , type);
        }else {
            profilePresenter.inseMealsList(downloadedList , type);
        }
    }
    private void calenderBtnClicked(){
        calenderBackUpBtn.setOnClickListener(
                clicked -> {
                    showLoadingAnimation();
                    userId = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();
                    profilePresenter.insertCalenderMealsToServer(firebaseFirestore , userId) ;
                }
        );
    }

    private void downloadCalenderBtn(){
        downloadCalenderBtn.setOnClickListener(
                clicked ->{
                    showLoadingAnimation();
                    userId = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();
                    profilePresenter.downLoadAllCalenderMeals(userId,firebaseFirestore);
                }
        );
    }
    private void getUserName() {

        userId = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();

        DocumentReference documentReference = firebaseFirestore.collection("chef_data").document(userId);
        documentReference.addSnapshotListener(getActivity(), new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if(value != null && value.exists()){
                    chefNameTextView.setText(value.getString("chefName"));
                    chefEmailTextView.setText(value.getString("chefEmail"));
                }
            }
        });
    }

    private void showLoadingAnimation(){
        loadingDialog.showLoadingAnimation();
    }
    private void hideLoadingAnimation(){
        loadingDialog.hideDialog();
    }

}