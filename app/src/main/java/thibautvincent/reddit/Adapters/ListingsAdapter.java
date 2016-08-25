package thibautvincent.reddit.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import thibautvincent.reddit.Model.Listings.ListingData;
import thibautvincent.reddit.Model.Subreddit.SubredditData;
import thibautvincent.reddit.R;

/**
 * Created by Thibaut on 8/23/16.
 */
public class ListingsAdapter extends ArrayAdapter<ListingData> {

    private Context context;
    private int resourceId;
    private ArrayList<ListingData> listings;

    public ListingsAdapter(Context context, int resource, int textViewResourceId, ArrayList<ListingData> objects) {
        super(context, resource, textViewResourceId, objects);
        this.context = context;
        this.resourceId = resource;
        this.listings = objects;
    }

    @Override
    public int getCount() {
        return this.listings.size();
    }

    @Override
    public ListingData getItem(int position) {
        return this.listings.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListingViewHolder listingViewHolder = null;

        if (convertView == null) {
            LayoutInflater layoutInflater = ((Activity) this.context).getLayoutInflater();
            convertView = layoutInflater.inflate(this.resourceId, parent, false);

            listingViewHolder = new ListingViewHolder();


            convertView.setTag(listingViewHolder);
        } else {
            listingViewHolder = (ListingViewHolder) convertView.getTag();
        }

        ListingData data = this.getItem(position);

        listingViewHolder.tvTitle = (TextView)convertView.findViewById(R.id.listing_topic_title);
        listingViewHolder.tvTitle.setText(data.getTitle());

        listingViewHolder.ivImage = (ImageView) convertView.findViewById(R.id.listing_topic_image);
        if (!data.getThumbnail().isEmpty()){
            Picasso.with(this.context).load(data.getThumbnail()).into(listingViewHolder.ivImage);
        }

        return convertView;
    }

    public void clearData() {
        ArrayList<ListingData> temp = this.listings;
        this.listings.removeAll(temp);
        notifyDataSetChanged();
    }


    public class ListingViewHolder {
        public TextView tvTitle;
        public ImageView ivImage;
    }
}
