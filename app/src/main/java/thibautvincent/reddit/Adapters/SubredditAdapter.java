package thibautvincent.reddit.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.BinderThread;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import thibautvincent.reddit.Model.Subreddit.SubredditData;
import thibautvincent.reddit.R;

/**
 * Created by Thibaut on 8/23/16.
 */
public class SubredditAdapter extends ArrayAdapter<SubredditData> {

    private Context context;

    private ArrayList<SubredditData> subreddits;
    private int layoutResourceId;

    public SubredditAdapter(Context context, int resource, int textViewResourceId, ArrayList<SubredditData> objects) {
        super(context, resource, textViewResourceId, objects);
        this.context = context;
        this.layoutResourceId = resource;
        this.subreddits = objects;
    }

    @Override
    public int getCount() {
        return subreddits.size();
    }

    @Override
    public SubredditData getItem(int position) {
        return subreddits.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SubredditViewHolder subredditViewHolder = null;

        if (convertView == null) {
            LayoutInflater layoutInflater = ((Activity) this.context).getLayoutInflater();
            convertView = layoutInflater.inflate(this.layoutResourceId, parent, false);

            subredditViewHolder = new SubredditViewHolder();


            convertView.setTag(subredditViewHolder);
        } else {
            subredditViewHolder = (SubredditViewHolder) convertView.getTag();
        }

        SubredditData data = this.getItem(position);

        subredditViewHolder.tvTitle = (TextView)convertView.findViewById(R.id.subreddit_topic_title);
        subredditViewHolder.tvUrl = (TextView)convertView.findViewById(R.id.subreddit_topic_url);

        subredditViewHolder.tvTitle.setText(data.getTitle());
        subredditViewHolder.tvUrl.setText(data.getUrl());

        return convertView;
    }

    public class SubredditViewHolder {
        public TextView tvTitle;
        public TextView tvUrl;
    }

}


