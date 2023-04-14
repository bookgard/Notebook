package jp.co.sony.mc.cast.notebook.mvvm.view;
/*
 * © 2021 Sony Corporation.
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class BaseActivity extends AppCompatActivity {
       protected AppCompatActivity context;

//       private LoadingDialog loadingDialog;

       @Override
       protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this;
//        BaseApplication.getAc
       }

    protected void showMsg(CharSequence msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

       protected void back(Toolbar toolbar) {
        toolbar.setNavigationOnClickListener(view -> finish());
       }

       protected void jumpActivity(final Class<?> clazz) {
           startActivity(new Intent(context,clazz));
       }

       protected void setStatusBar(boolean dark) {
        View decor = getWindow().getDecorView();
        if (dark) {
         decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
         decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
       }
}
