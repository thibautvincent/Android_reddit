package thibautvincent.reddit.Model.Subreddit;

import com.google.gson.annotations.Expose;

/**
 * Created by Thibaut on 8/23/16.
 */
public class SubredditWrapper {
    @Expose
    private String kind;

    @Expose
    private SubredditData data;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public SubredditData getData() {
        return data;
    }

    public void setData(SubredditData data) {
        this.data = data;
    }
}
