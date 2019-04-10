package com.shapeworks.mivule.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.shapeworks.mivule.Entities.Forums;
import com.shapeworks.mivule.Entities.Projects;
import com.shapeworks.mivule.HomeActivity;
import com.shapeworks.mivule.ProjectsActivity;
import com.shapeworks.mivule.R;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by KoomaBenjamin on 01/11/2017.
 */

public class ProjectsAdapter extends FirestoreAdapter<ProjectsAdapter.ViewHolder> {


    public interface OnProjectSelectedListener {

        void onProjectSelected(DocumentSnapshot restaurant);

    }

    private OnProjectSelectedListener mListener;

    public ProjectsAdapter(Query query, OnProjectSelectedListener listener) {
        super(query);
        mListener = listener;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.project_card_item_00, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(getSnapshot(position), mListener);
    }

static class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.project_icon)
    ImageView imageView;

    @BindView(R.id.project_name)
    TextView projectName;

    @BindView(R.id.project_brief)
    TextView projectBrief;

    @BindView(R.id.project_deadline)
    TextView projectCategory;

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
                     final OnProjectSelectedListener listener){
        Projects restaurant = snapshot.toObject(Projects.class);
        Resources resources = itemView.getResources();

        // Load image
//            Glide.with(imageView.getContext())
//                    .load(restaurant.getPhoto())
//                    .into(imageView);

        projectName.setText(restaurant.getName());
//            ratingBar.setRating((float) restaurant.getAvgRating());
        projectBrief.setText(restaurant.getBrief());
        projectCategory.setText(restaurant.getOwner());
//            numRatingsView.setText(resources.getString(R.string.fmt_num_ratings, restaurant.getNumRatings()));
//            priceView.setText(RestaurantUtil.getPriceString(restaurant));

        // Click listener
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onProjectSelected(snapshot);
                }
            }
        });
    }
}
}


//public class ProjectsAdapter extends RecyclerView.Adapter<ProjectsAdapter.MyViewHolder> {
//
//    private List<Projects> moviesList = new ArrayList<>();
//
//    public class MyViewHolder extends RecyclerView.ViewHolder {
//
//        public TextView name, brief, deadline;
//        private Context context;
//        private ImageView icon;
//        private CardView cardView;
//        private FloatingActionButton mDeleteBtn;
//
//        public MyViewHolder(View view) {
//            super(view);
//            context = view.getContext();
//            name = (TextView) view.findViewById(R.id.project_name);
//            brief = (TextView) view.findViewById(R.id.project_brief);
//            deadline = (TextView) view.findViewById(R.id.project_deadline);
//            icon = (ImageView) view.findViewById(R.id.project_icon);
//
//            mDeleteBtn = (FloatingActionButton) view.findViewById(R.id.delete_btn);
//            cardView = (CardView) view.findViewById(R.id.card_view);
//
//        }
//
//
//
//    }
//
//
//    public ProjectsAdapter(List<Projects> moviesList) {
//        this.moviesList = moviesList;
//    }
//
//    @Override
//    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.project_card_item_00, parent, false);
//
//        return new MyViewHolder(itemView);
//    }
//
//
//    @Override
//    public void onBindViewHolder(final ProjectsAdapter.MyViewHolder holder, final int position) {
//
//        Projects cons = moviesList.get(position);
//        holder.name.setText(cons.project_name);
//        holder.brief.setText(cons.project_brief);
//        holder.deadline.setText(cons.project_deadline);
//        holder.icon.setImageResource(cons.project_icon);
//
//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//
//        holder.mDeleteBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                moviesList.remove(position);
//                notifyDataSetChanged();
//            }
//        });
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return moviesList.size();
//    }
//}
