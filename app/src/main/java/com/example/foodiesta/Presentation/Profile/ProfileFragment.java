package com.example.foodiesta.Presentation.Profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.foodiesta.Data.Local_data.MealsLocalDataSource;
import com.example.foodiesta.Data.Repository.ProfileRepo;
import com.example.foodiesta.Model.Favorite.FavoriteEntity;
import com.example.foodiesta.R;
import com.example.foodiesta.Utilities.CustomDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.List;
import java.util.Objects;

public class ProfileFragment extends Fragment {

    private TextView chefNameTextView, chefEmailTextView;
    private FirebaseAuth firebaseAuth;
    private String userId;
    private Button favoriteBackUpBtn , calenderBackUpBtn , logOutBtn;
    private FirebaseFirestore firebaseFirestore;
    private ProfilePresenter profilePresenter;

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
        logOutBtnClicked(view);
    }

    private void logOutBtnClicked(View view) {
        logOutBtn.setOnClickListener(
                clicked -> {
                    FirebaseAuth.getInstance().signOut();
                    Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_welcomeFragment);
                }
        );
    }


    private void initUI(View view) {
        chefNameTextView = view.findViewById(R.id.profile_tv_chef_name);
        chefEmailTextView = view.findViewById(R.id.profile_tv_chef_email);
        favoriteBackUpBtn = view.findViewById(R.id.profile_btn_backup_favorite);
        calenderBackUpBtn = view.findViewById(R.id.profile_btn_backup_calendet) ;
        logOutBtn = view.findViewById(R.id.profile_btn_log_out) ;
        //fire base
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    private void initPresenter() {
        MealsLocalDataSource mealsLocalDataSource = new MealsLocalDataSource(getContext());
        ProfileRepo profileRepo = new ProfileRepo(mealsLocalDataSource);
        profilePresenter = new ProfilePresenter(profileRepo, this);
    }

    private void favoriteBackUpBtnClicked() {
        favoriteBackUpBtn.setOnClickListener(
                click -> {
                    userId = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();
                    profilePresenter.getListOfFavoriteMeal(userId, firebaseFirestore, firebaseAuth);
                }
        );
    }

    public void successInsert() {
        CustomDialog customDialog = new CustomDialog(getContext()) ;
        customDialog.success("BackUp Success" , "");
    }

    private void getUserName() {

        userId = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();

        DocumentReference documentReference = firebaseFirestore.collection("chef_data").document(userId);
        documentReference.addSnapshotListener(getActivity(), new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                chefNameTextView.setText(value.getString("chefName"));
                chefEmailTextView.setText(value.getString("chefEmail"));
                Log.e("FirestoreError", "Document does not exist for userId: " + userId);
            }
        });
    }

}