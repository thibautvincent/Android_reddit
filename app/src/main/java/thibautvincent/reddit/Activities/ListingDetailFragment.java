package thibautvincent.reddit.Activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import thibautvincent.reddit.R;

public class ListingDetailFragment extends Fragment {

    @Bind(R.id.listing_detail_title)
    public TextView tvTitle;

    @Bind(R.id.listing_detail_url)
    public TextView tvUrl;

    @Bind(R.id.listing_detail_image)
    public ImageView ivImage;

    private int id;

    public static ListingDetailFragment newInstance() {
        ListingDetailFragment fragment = new ListingDetailFragment();
        return fragment;
    }

    public ListingDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_listing_detail, container, false);
        ButterKnife.bind(this, view);

        Intent intent = getActivity().getIntent();
        Log.i("IMAGE", intent.getStringExtra("IMAGE"));

        if (intent != null) {
            if (intent.hasExtra("TITLE")) {
               tvTitle.setText(intent.getStringExtra("TITLE"));
            }

            if (intent.hasExtra("URL")) {
                tvUrl.setText(intent.getStringExtra("URL"));
            }

            if (intent.hasExtra("URL") && !intent.getStringExtra("URL").isEmpty()) {
                Picasso.with(getActivity().getApplicationContext()).load(intent.getStringExtra("URL")).into(ivImage);
            }
        }

        return view;
    }

}
