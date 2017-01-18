package entity;

/**
 * <p>
 * <p>
 * Created by liuchenwei on 2016/7/6.
 */
public class User {

    private String uid;
    private String title;
    private String avatar;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", title='" + title + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
