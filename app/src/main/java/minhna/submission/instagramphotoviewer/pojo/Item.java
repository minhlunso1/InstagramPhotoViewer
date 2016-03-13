package minhna.submission.instagramphotoviewer.pojo;

/**
 * Created by Administrator on 13-Mar-16.
 */
public class Item {
    private String id;
    private String profileAva;
    private String profileName;
    private String timeStamp;
    private String image;
    private String likeCount;
    private ChildItem latestChild1;
    private ChildItem latestChild2;

    public Item(){}

    public Item(String id, String profileAva, String profileName, String timeStamp, String image, String likeCount, ChildItem latestChild1, ChildItem latestChild2) {
        this.id = id;
        this.profileAva = profileAva;
        this.profileName = profileName;
        this.timeStamp = timeStamp;
        this.image = image;
        this.likeCount = likeCount;
        this.latestChild1 = latestChild1;
        this.latestChild2 = latestChild2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProfileAva() {
        return profileAva;
    }

    public void setProfileAva(String profileAva) {
        this.profileAva = profileAva;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ChildItem getLatestChild1() {
        return latestChild1;
    }

    public void setLatestChild1(ChildItem latestChild1) {
        this.latestChild1 = latestChild1;
    }

    public ChildItem getLatestChild2() {
        return latestChild2;
    }

    public void setLatestChild2(ChildItem latestChild2) {
        this.latestChild2 = latestChild2;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }
}
