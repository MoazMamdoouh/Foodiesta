package com.example.foodiesta.Login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodiesta.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.FirebaseAuth;


public class ForgetPasswordBottomSheet extends BottomSheetDialogFragment {

    private EditText emailEditText;
    private Button resetButton ;
    private FirebaseAuth firebaseAuth ;
    public ForgetPasswordBottomSheet() {
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
        return inflater.inflate(R.layout.fragment_forget_password_bottom_sheet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);
        resetBtnClicked(view);
    }

    private void resetBtnClicked(View view) {

        resetButton.setOnClickListener(clicked->{
            String mail = emailEditText.getText().toString() ;
            firebaseAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(getContext(), "Link Sent Successfully ", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getContext(), "Failed to send link Please Try Again", Toast.LENGTH_SHORT).show();
                }
            });
            dismiss();
        });
    }

    private void initUI(View view) {
        emailEditText = view.findViewById(R.id.forget_password_et_email);
        resetButton = view.findViewById(R.id.forget_password_btn_reset) ;
        firebaseAuth = FirebaseAuth.getInstance();
    }

    
}