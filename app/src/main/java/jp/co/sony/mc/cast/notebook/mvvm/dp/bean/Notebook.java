package jp.co.sony.mc.cast.notebook.mvvm.dp.bean;
/*
 * © 2021 Sony Corporation.
 */
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "notebook")
public class Notebook {

    @PrimaryKey(autoGenerate = true)
    private int uid;
    private String title;
    private String content;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Ignore
    public Notebook(String title,String content) {
        this.title = title;
        this.content = content;
    }

    public Notebook() {

    }
}
