package thibautvincent.reddit.Model.Subreddit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thibaut on 8/23/16.
 */
public class SubredditsData {
    @Expose
    private String modhash;

    @Expose
    private String after;

    @Expose
    private Object before;

    @Expose
    private List<SubredditWrapper> children = new ArrayList<SubredditWrapper>();

    public String getModhash() {
        return modhash;
    }

    public void setModhash(String modhash) {
        this.modhash = modhash;
    }

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }

    public Object getBefore() {
        return before;
    }

    public void setBefore(Object before) {
        this.before = before;
    }

    public List<SubredditWrapper> getChildren() {
        return children;
    }

    public void setChildren(List<SubredditWrapper> children) {
        this.children = children;
    }
}
