package thibautvincent.reddit.Model.Subreddit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Thibaut on 8/23/16.
 */
public class SubredditData {
    @SerializedName("banner_img")
    @Expose
    private String banner;

    @SerializedName("user_sr_theme_enabled")
    @Expose
    private boolean userSubredditThemeEnabled;

    @SerializedName("submit_text")
    @Expose
    private String submitTextHtml;

    @SerializedName("display_name")
    @Expose
    private String displayName;

    @SerializedName("header_img")
    @Expose
    private String headerImg;

    @Expose
    private String title;

    @SerializedName("icon_img")
    @Expose
    private String iconImg;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Expose
    private String url;

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public boolean isUserSubredditThemeEnabled() {
        return userSubredditThemeEnabled;
    }

    public void setUserSubredditThemeEnabled(boolean userSubredditThemeEnabled) {
        this.userSubredditThemeEnabled = userSubredditThemeEnabled;
    }

    public String getSubmitTextHtml() {
        return submitTextHtml;
    }

    public void setSubmitTextHtml(String submitTextHtml) {
        this.submitTextHtml = submitTextHtml;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getHeaderImg() {
        return headerImg;
    }

    public void setHeaderImg(String headerImg) {
        this.headerImg = headerImg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIconImg() {
        return iconImg;
    }

    public void setIconImg(String iconImg) {
        this.iconImg = iconImg;
    }
}
