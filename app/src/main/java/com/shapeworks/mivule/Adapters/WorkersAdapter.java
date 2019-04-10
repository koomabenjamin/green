package com.shapeworks.mivule.Adapters;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shapeworks.mivule.Entities.Workers;
import com.shapeworks.mivule.R;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Kooma Benamin on 2/27/2018.
 */

public class WorkersAdapter extends FirestoreAdapter<WorkersAdapter.ViewHolder>{

    public interface OnWorkersSelectedListener {

        void onWorkersSelected(DocumentSnapshot restaurant);

    }

    private WorkersAdapter.OnWorkersSelectedListener mListener;

    public WorkersAdapter(Query query, WorkersAdapter.OnWorkersSelectedListener listener) {
        super(query);
        mListener = listener;
    }



    @Override
    public WorkersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new WorkersAdapter.ViewHolder(inflater.inflate(R.layout.employee_item_00, parent, false));
    }

    @Override
    public void onBindViewHolder(WorkersAdapter.ViewHolder holder, int position) {
        holder.bind(getSnapshot(position), mListener);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.worker_icon)
        ImageView icon;

        @BindView(R.id.worker_name)
        TextView name;

        @BindView(R.id.worker_intro)
        TextView intro;

        @BindView(R.id.worker_email)
        TextView email;

        @BindView(R.id.worker_experience)
        TextView experience;

        @BindView(R.id.worker_availability)
        TextView availability;



        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final DocumentSnapshot snapshot,
                         final WorkersAdapter.OnWorkersSelectedListener listener){
            Workers restaurant = snapshot.toObject(Workers.class);
            Resources resources = itemView.getResources();

//            Load image
//            Glide.with(imageView.getContext())
//                    .load(restaurant.getPhoto())
//                    .into(imageView);

//            icon.setText(restaurant.getEmail());
//            ratingBar.setRating((float) restaurant.getAvgRating());
            intro.setText(restaurant.getIntro());
            name.setText(restaurant.getName());
//            numRatingsView.setText(resources.getString(R.string.fmt_num_ratings, restaurant.getNumRatings()));
//            priceView.setText(RestaurantUtil.getPriceString(restaurant));

            // Click listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onWorkersSelected(snapshot);
                    }
                }
            });
        }
    }
}