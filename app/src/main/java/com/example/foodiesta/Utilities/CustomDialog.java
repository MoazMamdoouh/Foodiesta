package com.example.foodiesta.Utilities;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import com.example.foodiesta.R;

public class CustomDialog {
    private Dialog dialog;
    private TextView actionTv ;
    private TextView requirementTv ;

    public CustomDialog(Context context) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.setCancelable(true);
        actionTv = dialog.findViewById(R.id.custom_dialog_tv_action) ;
        requirementTv = dialog.findViewById(R.id.custom_dialog_tv_requirement) ;
    }

    public void success(String required , String action){
        actionTv.setText(required);
        requirementTv.setText(action);
        dialog.show();
    }
    public void failed(){
        actionTv.setText("Registration Failed");
        requirementTv.setText("Please Try Again");
        dialog.show();
    }

    public void dismiss() {
        dialog.dismiss();
    }
}
