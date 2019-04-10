package com.shapeworks.mivule.Adapters;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shapeworks.mivule.Entities.Tasks;
import com.shapeworks.mivule.R;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by koomabenjamin on 12/11/17.
 */
public class TaskAdapter extends FirestoreAdapter<TaskAdapter.ViewHolder> {


    public interface OnTaskSelectedListener {

        void onTaskSelected(DocumentSnapshot restaurant);

    }

    private OnTaskSelectedListener mListener;

    public TaskAdapter(Query query, OnTaskSelectedListener listener) {
        super(query);
        mListener = listener;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.task_list_item_00, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(getSnapshot(position), mListener);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.task_icon)
        ImageView icon;

        @BindView(R.id.task_brief)
        TextView brief;

        @BindView(R.id.task_title)
        TextView title;

        @BindView(R.id.task_duration)
        TextView duration;

        @BindView(R.id.task_recordDate)
        TextView task_recordDate;



        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final DocumentSnapshot snapshot,
                         final OnTaskSelectedListener listener){
            Tasks restaurant = snapshot.toObject(Tasks.class);
            Resources resources = itemView.getResources();

            // Load image
//            Glide.with(imageView.getContext())
//                    .load(restaurant.getPhoto())
//                    .into(imageView);

            title.setText(restaurant.getProjectName());
//            ratingBar.setRating((float) restaurant.getAvgRating());
//            duration.setText(restaurant.getDuration());
            brief.setText(restaurant.getAgentId());
            duration.setText(restaurant.getDuration());
            task_recordDate.setText(restaurant.getStartDate().toString());

//            numRatingsView.setText(resources.getString(R.string.fmt_num_ratings, restaurant.getNumRatings()));
//            priceView.setText(RestaurantUtil.getPriceString(restaurant));

            // Click listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onTaskSelected(snapshot);
                    }
                }
            });
        }
    }
}

//public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyHoder> {
//
//    private List<Tasks> task = new ArrayList<>();
//
//    public TaskAdapter(List<Tasks> moviesList) {
//        this.task = moviesList;
//    }
//
//    @Override
//    public MyHoder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.task_list_item_00, parent, false);
//
//        return new MyHoder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(MyHoder holder, int position) {
//
//        Tasks tasks = task.get(position);
//        holder.title.setText(tasks.task_title);
//        holder.detail.setText(tasks.task_brief);
//        holder.deadline.setText(tasks.deadline);
//        holder.duration.setText(tasks.duration);
//    }
//
//    @Override
//    public int getItemCount() {
//        return task.size();
//    }
//
//    class MyHoder extends RecyclerView.ViewHolder{
//        TextView title,detail,duration, deadline;
//
//
//        public MyHoder(View itemView) {
//            super(itemView);
//            title = (TextView) itemView.findViewById(R.id.task_title);
//            detail = (TextView) itemView.findViewById(R.id.task_detail);
//            deadline = (TextView) itemView.findViewById(R.id.task_deadline);
//            duration = (TextView) itemView.findViewById(R.id.task_duration);
//
//        }
//    }
//}
