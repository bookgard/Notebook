package jp.co.sony.mc.cast.notebook.mvvm.viewModel;
/*
 * © 2021 Sony Corporation.
 */

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;

import jp.co.sony.mc.cast.notebook.mvvm.dp.bean.Notebook;
import jp.co.sony.mc.cast.notebook.mvvm.repostitory.NotebookRepository;

public class EditViewModel extends BaseViewModel{

    private final NotebookRepository notebookRepository;

    @ViewModelInject
    EditViewModel(NotebookRepository notebookRepository) {
        this.notebookRepository = notebookRepository;
    }

    /*
    * 添加笔记
    * */
    public void addNotebook(Notebook notebook) {
        failed = notebookRepository.failed;
        notebookRepository.saveNotebook(notebook);
    }

    public LiveData<Notebook> notebook;

    /*
    * 根据id搜索笔记
    * */
    public void queryById(int uid) {
        failed = notebookRepository.failed;
        notebook = notebookRepository.getNotebookByID(uid);

    }

    /*
    * 跟新笔记
    * */
    public void updateNotebook(Notebook notebook) {
        failed = notebookRepository.failed;
        notebookRepository.updateNotebook(notebook);
    }

    /*
    * 删除笔记
    * */
    public void deleteNotebook(Notebook notebook) {
        notebookRepository.deleteNotebook(notebook);
        failed = notebookRepository.failed;
    }
}
