package minhna.submission.instagramphotoviewer.pojo;

/**
 * Created by Administrator on 13-Mar-16.
 */
public class ChildItem {
    private String name;
    private String comment;

    public ChildItem(String name, String comment) {
        this.name = name;
        this.comment = comment;
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
