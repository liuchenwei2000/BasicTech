package entity;

/**
 * <p>
 * <p>
 * Created by liuchenwei on 2016/7/6.
 */
public class Body {

    private String mid;
    private String content;

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Body{" +
                "mid='" + mid + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
