package com.shapeworks.mivule.Adapters;

/**
 * Created by Kooma Benamin on 2/27/2018.
 */
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shapeworks.mivule.Entities.Newsfeed;
import com.shapeworks.mivule.R;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;
import com.shapeworks.mivule.detail.DetailFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Kooma Benamin on 2/26/2018.
 */

public class NewsfeedAdapter extends FirestoreAdapter<NewsfeedAdapter.ViewHolder> {

    public interface OnNewsfeedSelectedListener {

        void onNewsfeedSelected(DocumentSnapshot restaurant);

    }

    private NewsfeedAdapter.OnNewsfeedSelectedListener mListener;

    public NewsfeedAdapter(Query query, NewsfeedAdapter.OnNewsfeedSelectedListener listener) {
        super(query);
        mListener = listener;
    }



    @Override
    public NewsfeedAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new NewsfeedAdapter.ViewHolder(inflater.inflate(R.layout.news_card_item_00, parent, false));
    }

    @Override
    public void onBindViewHolder(NewsfeedAdapter.ViewHolder holder, int position) {
        holder.bind(getSnapshot(position), mListener);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.news_icon)
        ImageView icon;

        @BindView(R.id.news_title)
        TextView title;

        @BindView(R.id.news_brief)
        TextView brief;

        @BindView(R.id.news_likes)
        TextView likes;

        @BindView(R.id.news_comments)
        TextView comments;

        @BindView(R.id.news_image)
        ImageView image;

        @BindView(R.id.news_share)
        TextView share;

//        @BindView(R.id.restaurant_item_price)
//        TextView priceView;
//
//        @BindView(R.id.restaurant_item_category)
//        TextView categoryView;
//
//        @BindView(R.id.restaurant_item_city)
//        TextView cityView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final DocumentSnapshot snapshot,
                         final NewsfeedAdapter.OnNewsfeedSelectedListener listener){
            Newsfeed restaurant = snapshot.toObject(Newsfeed.class);
            Resources resources = itemView.getResources();

//            Load image
            Glide.with(image.getContext())
                    .load(restaurant.getDownloadUrls())
                    .into(image);

//            icon.setText(restaurant.getEmail());
//            ratingBar.setRating((float) restaurant.getAvgRating());
            brief.setText(restaurant.getBrief());
            title.setText(restaurant.getTitle());
//            numRatingsView.setText(resources.getString(R.string.fmt_num_ratings, restaurant.getNumRatings()));
//            priceView.setText(RestaurantUtil.getPriceString(restaurant));

            // Click listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onNewsfeedSelected(snapshot);
                    }
                }
            });
        }
    }
}
