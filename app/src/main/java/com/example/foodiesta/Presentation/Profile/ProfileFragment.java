package com.example.foodiesta.Presentation.Profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.foodiesta.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Objects;

public class ProfileFragment extends Fragment {

    private TextView chefNameTextView , chefEmailTextView ;
    private FirebaseAuth firebaseAuth ;
    private String userId ;
    private FirebaseFirestore firebaseFirestore ;
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

          firebaseAuth = FirebaseAuth.getInstance() ;
          firebaseFirestore = FirebaseFirestore.getInstance();
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

    private void initUI(View view) {
        chefNameTextView = view.findViewById(R.id.profile_tv_chef_name) ;
        chefEmailTextView = view.findViewById(R.id.profile_tv_chef_email) ;
    }
}