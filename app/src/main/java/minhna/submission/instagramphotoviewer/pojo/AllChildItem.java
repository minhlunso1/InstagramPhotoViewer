package minhna.submission.instagramphotoviewer.pojo;

import java.util.List;

/**
 * Created by Administrator on 13-Mar-16.
 */
public class AllChildItem {
    private long id;
    private List<ChildItem> list;

    public AllChildItem(long id, List<ChildItem> list) {
        this.id = id;
        this.list = list;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<ChildItem> getList() {
        return list;
    }

    public void setList(List<ChildItem> list) {
        this.list = list;
    }
}
