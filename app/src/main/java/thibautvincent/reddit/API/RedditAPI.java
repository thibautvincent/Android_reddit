package thibautvincent.reddit.API;


import retrofit2.Call;
import retrofit2.http.*;
import thibautvincent.reddit.Model.Listings.ListingsWrapper;
import thibautvincent.reddit.Model.Subreddit.SubredditsWrapper;

/**
 * Created by Thibaut on 8/23/16.
 */
public interface RedditAPI {

    @GET("/subreddits/popular.json")
    Call<SubredditsWrapper> getSubreddits();

    @GET("{section}/{subreddit}.json")
    Call<ListingsWrapper> getListingsOfSubreddit(@Path("section") String section, @Path("subreddit") String subreddit, @Query("limit") int limit);

    @GET("{section}/{subreddit}.json")
    Call<ListingsWrapper> getExtraListingsOfSubreddit(@Path("section") String section, @Path("subreddit") String subreddit, @Query("limit") int limit, @Query("after") String after);
}
