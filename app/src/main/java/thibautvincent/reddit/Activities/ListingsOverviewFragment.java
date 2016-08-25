package thibautvincent.reddit.Activities;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import thibautvincent.reddit.API.RedditAPI;
import thibautvincent.reddit.Adapters.ListingsAdapter;
import thibautvincent.reddit.Model.Listings.ListingData;
import thibautvincent.reddit.Model.Listings.ListingWrapper;
import thibautvincent.reddit.Model.Listings.ListingsData;
import thibautvincent.reddit.Model.Listings.ListingsWrapper;
import thibautvincent.reddit.R;
import thibautvincent.reddit.Services.RedditService;

public class ListingsOverviewFragment extends Fragment {

    private ListingsAdapter listingsAdapter;
    private String selectedSubreddit;
    @Bind(R.id.listings_overview)
    public ListView listingsList;

    public static ListingsOverviewFragment newInstance() {
        ListingsOverviewFragment fragment = new ListingsOverviewFragment();
        return fragment;
    }

    public ListingsOverviewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listings_overview, container, false);

        ButterKnife.bind(this, view); // ButterKnife

        Intent intent = getActivity().getIntent();

        if(intent != null && intent.hasExtra("SUBREDDITURL")){
            selectedSubreddit = intent.getStringExtra("SUBREDDITURL");

        }

        getActivity().setTitle(selectedSubreddit);


        this.listingsAdapter = new ListingsAdapter(
                this.getActivity(),
                R.layout.listing_item,
                R.id.listings_overview,
                new ArrayList<ListingData>()
        );

        this.fetchData();

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_custom_listings, menu);
    }


    public void fetchData() {

        SharedPreferences sp = getActivity().getSharedPreferences("MY_PREFS", Activity.MODE_PRIVATE);
        int max = sp.getInt("POSTS_LOAD", 20);

        Retrofit retrofitAdapter = new Retrofit
                .Builder()
                .baseUrl("http://www.reddit.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RedditAPI api = retrofitAdapter.create(RedditAPI.class);
        Bundle args = getArguments();
        Call<ListingsWrapper> call = api.getListingsOfSubreddit("r", selectedSubreddit.replace("/r/", ""), max);

        try {
            Response<ListingsWrapper> data= call.execute();
            final ListingsData listingsData = data.body().getData();

            for (ListingWrapper listingWrapper : listingsData.getChildren()) {
                ListingData listingData = listingWrapper.getData();
                listingsAdapter.add(listingData);
            }

            listingsList.setAdapter(listingsAdapter);
            listingsAdapter.notifyDataSetChanged();

            listingsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ListingData listingData = listingsAdapter.getItem(position);

                    Toast.makeText(getActivity(), listingData.getTitle(), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getActivity(), ListingDetailActivity.class);
                    intent.putExtra("TITLE", listingData.getTitle());
                    intent.putExtra("IMAGE", listingData.getThumbnail());
                    intent.putExtra("URL", listingData.getUrl());


                    startActivity(intent);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.btnRefresh) {
            this.listingsAdapter.clearData();
            this.fetchData();
            return true;
        }
        if (item.getItemId() == R.id.action_settings) {
            Intent intent = new Intent(getContext(), SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return false;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }
}
