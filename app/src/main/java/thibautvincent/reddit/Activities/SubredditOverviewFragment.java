package thibautvincent.reddit.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import thibautvincent.reddit.API.RedditAPI;
import thibautvincent.reddit.Adapters.SubredditAdapter;
import thibautvincent.reddit.Model.Subreddit.SubredditData;
import thibautvincent.reddit.Model.Subreddit.SubredditWrapper;
import thibautvincent.reddit.Model.Subreddit.SubredditsData;
import thibautvincent.reddit.Model.Subreddit.SubredditsWrapper;
import thibautvincent.reddit.R;

public class SubredditOverviewFragment extends Fragment {

    private SubredditAdapter subredditAdapter;

    public ListView subredditsList;

    public static SubredditOverviewFragment newInstance() {
        SubredditOverviewFragment fragment = new SubredditOverviewFragment();
        return fragment;
    }

    public SubredditOverviewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_subreddit_overview, container, false);

        this.subredditAdapter = new SubredditAdapter(getContext(), R.layout.subreddit_item, R.id.subredditlist, new ArrayList<SubredditData>());

        subredditsList = (ListView)view.findViewById(R.id.subredditlist);
        this.fetchData();

        return view;
    }

    public void fetchData() {
        Retrofit retrofitAdapter = new Retrofit
                .Builder()
                .baseUrl("http://www.reddit.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RedditAPI api = retrofitAdapter.create(RedditAPI.class);


        Call<SubredditsWrapper> call = api.getSubreddits();
        try {
            Response<SubredditsWrapper> data= call.execute();
            SubredditsData subredditsData = data.body().getData();

            for (SubredditWrapper subredditWrapper : subredditsData.getChildren()) {
                SubredditData subredditData = subredditWrapper.getData();
                subredditAdapter.add(subredditData);
            }

            subredditsList.setAdapter(subredditAdapter);
            subredditAdapter.notifyDataSetChanged();

            subredditsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    SubredditData subredditData = subredditAdapter.getItem(position);

                    Toast.makeText(getActivity(), subredditData.getTitle(), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getActivity(), ListingsOverviewActivity.class);
                    intent.putExtra("SUBREDDITURL", subredditData.getUrl());
                    startActivity(intent);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
