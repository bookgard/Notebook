package jp.co.sony.mc.cast.notebook.mvvm.ui.adapter;
/*
 * © 2021 Sony Corporation.
 */


import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;

import java.util.List;


import jp.co.sony.mc.cast.notebook.mvvm.R;

import jp.co.sony.mc.cast.notebook.mvvm.databinding.ItemNotebookBinding;
import jp.co.sony.mc.cast.notebook.mvvm.dp.bean.Notebook;
import jp.co.sony.mc.cast.notebook.mvvm.view.EditActivity;

public class NotebookAdapter extends BaseQuickAdapter<Notebook, BaseDataBindingHolder<ItemNotebookBinding>> {

    private boolean isBatchDeletion;

    /*
    * 设置批量删除
    * */
    public void setBatchDeletion(boolean batchDeletion) {isBatchDeletion = batchDeletion;}

    public NotebookAdapter(@NonNull List<Notebook> data) {
        super(R.layout.item_notebook,data);
    }

    @Override
    protected void convert(@NonNull BaseDataBindingHolder<ItemNotebookBinding> itemNotebookBindingBaseDataBindingHolder, Notebook notebook) {
        ItemNotebookBinding binding = itemNotebookBindingBaseDataBindingHolder.getDataBinding();
        if (binding != null) {
            binding.setNotebook(notebook);
//            binding.setIsBatchDeletion(isBatchDeletion);
            binding.setOnClick(new NotebookAdapter.ClickBinding());
            binding.executePendingBindings();
        }
    }

    public static class ClickBinding {
        public void itemClick(Notebook notebook, View view) {
            Log.i("AAAA", "itemClick: ");
            Intent intent = new Intent(view.getContext(), EditActivity.class);
            intent.putExtra("uid",notebook.getUid());
            view.getContext().startActivity(intent);
        }
    }
}
