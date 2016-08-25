package thibautvincent.reddit.Services;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import thibautvincent.reddit.API.RedditAPI;
import thibautvincent.reddit.Model.Listings.ListingData;
import thibautvincent.reddit.Model.Listings.ListingWrapper;
import thibautvincent.reddit.Model.Listings.ListingsData;
import thibautvincent.reddit.Model.Listings.ListingsWrapper;
import thibautvincent.reddit.Model.Subreddit.SubredditData;
import thibautvincent.reddit.Model.Subreddit.SubredditWrapper;
import thibautvincent.reddit.Model.Subreddit.SubredditsWrapper;

/**
 * Created by Thibaut on 8/25/16.
 */
public class RedditService {

    private static RedditService instance = null;
    private Retrofit retrofitAdapter;
    private RedditAPI api;
    private Gson gson;
    private String after = "";

    public synchronized static RedditService getInstance() {
        if (instance == null) {
            instance = new RedditService();
        }
        return instance;
    }

    private RedditService() {
        this.gson = new GsonBuilder().create();
        this.retrofitAdapter = new Retrofit
                .Builder()
                .baseUrl("http://www.reddit.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        this.api = retrofitAdapter.create(RedditAPI.class);
    }

    public ArrayList<SubredditData> getPopularSubreddits() {
        ArrayList<SubredditData> data = new ArrayList<>();
        try {
            Call call = this.api.getSubreddits();
            Response<SubredditsWrapper> response= call.execute();
            for (SubredditWrapper subredditWrapper : response.body().getData().getChildren()) {
                data.add(subredditWrapper.getData());
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public ArrayList<ListingData> getListingsOfSubreddit(String section, String subreddit, int limit) {
        ArrayList<ListingData> data = new ArrayList<>();
        try {
            Call call = this.api.getListingsOfSubreddit(section, subreddit, limit);
            Response<ListingsWrapper> response= call.execute();
            after = response.body().getData().getAfter();
            for (ListingWrapper listingWrapper : response.body().getData().getChildren()) {
                data.add(listingWrapper.getData());
            }
        } catch(IOException ex) {
            ex.printStackTrace();
        }
        return data;
    }

    public ArrayList<ListingData> getExtraListingsOfSubreddit(String section, String subreddit, int limit) {
        ArrayList<ListingData> data = new ArrayList<>();
        Log.i(after, after);
        Call call = this.api.getExtraListingsOfSubreddit(section, subreddit, limit, after);
        try {
            Response<ListingsWrapper> response = call.execute();
            for (ListingWrapper listingWrapper : response.body().getData().getChildren()) {
                data.add(listingWrapper.getData());
            }
            after = response.body().getData().getAfter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

}
