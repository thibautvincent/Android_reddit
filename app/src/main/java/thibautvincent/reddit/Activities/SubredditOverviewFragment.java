package thibautvincent.reddit.Activities;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import thibautvincent.reddit.Adapters.SubredditAdapter;
import thibautvincent.reddit.Model.Subreddit.SubredditData;
import thibautvincent.reddit.R;
import thibautvincent.reddit.Services.RedditService;

public class SubredditOverviewFragment extends Fragment {

    private SubredditAdapter subredditAdapter;
    private RedditService redditService;

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
        try {
            this.redditService = RedditService.getInstance();
            subredditAdapter.addAll(this.redditService.getPopularSubreddits());

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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
