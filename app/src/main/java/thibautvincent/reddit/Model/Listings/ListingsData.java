package thibautvincent.reddit.Model.Listings;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thibaut on 8/23/16.
 */
public class ListingsData {

    @Expose
    private String modhash;

    @Expose
    private String after;

    @Expose
    private Object before;

    @Expose
    private List<ListingWrapper> children = new ArrayList<ListingWrapper>();

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

    public List<ListingWrapper> getChildren() {
        return children;
    }

    public void setChildren(List<ListingWrapper> children) {
        this.children = children;
    }
}
