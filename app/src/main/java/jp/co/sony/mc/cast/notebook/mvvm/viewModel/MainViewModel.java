package jp.co.sony.mc.cast.notebook.mvvm.viewModel;
/*
 * © 2021 Sony Corporation.
 */

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import jp.co.sony.mc.cast.notebook.mvvm.dp.bean.Notebook;
import jp.co.sony.mc.cast.notebook.mvvm.repostitory.NotebookRepository;

public class MainViewModel extends BaseViewModel {

    private final NotebookRepository notebookRepository;

    public LiveData<List<Notebook>> notebooks;

    @ViewModelInject
    MainViewModel(NotebookRepository notebookRepository) {
        this.notebookRepository = notebookRepository;
    }

    public void getNotebooks() {
        failed = notebookRepository.failed;
        notebooks = notebookRepository.getNotebooks();
    }
}
