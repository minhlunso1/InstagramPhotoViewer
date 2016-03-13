package minhna.submission.instagramphotoviewer.pojo;

/**
 * Created by Administrator on 13-Mar-16.
 */
public class ChildItem {
    private String ava;
    private String name;
    private String comment;

    public ChildItem(String name, String comment) {
        this.name = name;
        this.comment = comment;
    }

    public ChildItem(String ava, String name, String comment) {
        this.ava = ava;
        this.name = name;
        this.comment = comment;
    }

    public String getAva() {
        return ava;
    }

    public void setAva(String ava) {
        this.ava = ava;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
