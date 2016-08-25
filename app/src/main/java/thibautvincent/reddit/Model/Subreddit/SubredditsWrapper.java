package thibautvincent.reddit.Model.Subreddit;


import com.google.gson.annotations.Expose;

/**
 * Created by Thibaut on 8/23/16.
 */
public class SubredditsWrapper {

    @Expose
    private String kind;
    @Expose
    private SubredditsData data;

    public void setKind(String kind) {
        this.kind = kind;
    }

    public void setData(SubredditsData data) {
        this.data = data;
    }

    public String getKind() {
        return kind;
    }

    public SubredditsData   getData() {
        return data;
    }
}
