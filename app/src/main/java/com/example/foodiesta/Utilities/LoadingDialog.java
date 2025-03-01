package com.example.foodiesta.Utilities;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import com.example.foodiesta.R;

public class LoadingDialog {

    private Dialog dialog ;
    private Context context ;

    public LoadingDialog(Context context) {
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.loading_lay_out);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        dialog.setCancelable(false);
    }

    public void showLoadingAnimation(){
        dialog.show();
    }
    public void hideDialog(){
        dialog.dismiss();
    }
}
