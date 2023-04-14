package jp.co.sony.mc.cast.notebook.mvvm.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;


import dagger.hilt.android.AndroidEntryPoint;
import jp.co.sony.mc.cast.notebook.mvvm.R;
import jp.co.sony.mc.cast.notebook.mvvm.databinding.ActivityMainBinding;
import jp.co.sony.mc.cast.notebook.mvvm.ui.adapter.NotebookAdapter;
import jp.co.sony.mc.cast.notebook.mvvm.viewModel.MainViewModel;

@AndroidEntryPoint
public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ActivityMainBinding mMainBinding;
    private MainViewModel mMainViewModel;
    private boolean hasNotebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mMainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        setStatusBar(true);
        back(mMainBinding.toolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMainViewModel.getNotebooks();
        mMainViewModel.notebooks.observe(this,notebooks -> {
            if (notebooks.size() > 0) {
                mMainBinding.rvNotebook.setLayoutManager(new LinearLayoutManager(context));
                mMainBinding.rvNotebook.setAdapter(new NotebookAdapter(notebooks));
                hasNotebook = true;
            } else {
                hasNotebook = false;
            }

            mMainBinding.setHasNotebook(hasNotebook);
        });
        mMainViewModel.failed.observe(this,result -> Log.d(TAG,"onResume: "+result));
    }

    public void toEdit(View view) {
        jumpActivity(EditActivity.class);
    }
}