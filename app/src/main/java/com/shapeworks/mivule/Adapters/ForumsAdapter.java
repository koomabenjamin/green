package com.shapeworks.mivule.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.shapeworks.mivule.Entities.Forums;
import com.shapeworks.mivule.R;
import com.shapeworks.mivule.detail.d_forum;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by KoomaBenjamin on 01/11/2017.
 */
public class ForumsAdapter extends FirestoreAdapter<ForumsAdapter.ViewHolder>{

    public interface OnForumsSelectedListener {

        void onForumsSelected(DocumentSnapshot restaurant);

    }

    private ForumsAdapter.OnForumsSelectedListener mListener;

    public ForumsAdapter(Query query, ForumsAdapter.OnForumsSelectedListener listener) {
        super(query);
        mListener = listener;
    }



    @Override
    public ForumsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ForumsAdapter.ViewHolder(inflater.inflate(R.layout.forum_card_item_00, parent, false));
    }

    @Override
    public void onBindViewHolder(ForumsAdapter.ViewHolder holder, int position) {
        holder.bind(getSnapshot(position), mListener);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.forum_icon)
        ImageView icon;

        @BindView(R.id.forum_title)
        TextView name;

        @BindView(R.id.forum_brief)
        TextView experitse;

        @BindView(R.id.forum_owner)
        TextView brief;



        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final DocumentSnapshot snapshot,
                         final ForumsAdapter.OnForumsSelectedListener listener){
            Forums restaurant = snapshot.toObject(Forums.class);
            Resources resources = itemView.getResources();

//            Load image
//            Glide.with(imageView.getContext())
//                    .load(restaurant.getPhoto())
//                    .into(imageView);

//            icon.setText(restaurant.getEmail());
//            ratingBar.setRating((float) restaurant.getAvgRating());
            brief.setText(restaurant.getForumTitle());
            name.setText(restaurant.getForumObjective());
            experitse.setText(restaurant.getForumAdmin());
//            numRatingsView.setText(resources.getString(R.string.fmt_num_ratings, restaurant.getNumRatings()));
//            priceView.setText(RestaurantUtil.getPriceString(restaurant));

            // Click listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onForumsSelected(snapshot);
                    }
                }
            });
        }
    }
}
//public class ForumsAdapter extends RecyclerView.Adapter<ForumsAdapter.MyViewHolder>  {
//
//private List<Forums> moviesList;
//
//public class MyViewHolder extends RecyclerView.ViewHolder {
//
//    public TextView topic, brief, timeStamp;
//    public Button btnForum;
//    private Context context;
//    private ImageView icon;
//    private Dialog dialog;
//
//    public MyViewHolder(View view) {
//        super(view);
//        context = view.getContext();
//        topic = (TextView) view.findViewById(R.id.forum_topic);
//        brief = (TextView) view.findViewById(R.id.forum_brief);
//        timeStamp = (TextView) view.findViewById(R.id.forum_timeStamp);
//        icon = (ImageView) view.findViewById(R.id.forum_icon);
//        btnForum =(Button) view.findViewById(R.id.btn_forum);
//
//        btnForum.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, d_forum.class);
////                intent.putExtra();
//                context.startActivity(intent);
//            }
//        });
//
//        //dialog = (Dialog) view.
//
//        view.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View view) {
//
////                    Intent intent = new Intent(context, HomeActivity.class);
////                    context.startActivity(intent);
//            }
//        });
//    }
////        @Override
////        public void onClick(View v){
////
////        }
//}
//
//
//    public ForumsAdapter(List<Forums> moviesList) {
//        this.moviesList = moviesList;
//    }
//
//    @Override
//    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.forum_card_item_00, parent, false);
//
//        return new MyViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(final MyViewHolder holder, int position) {
//
//        Forums cons = moviesList.get(position);
//        holder.topic.setText(cons.forum_topic);
//        holder.brief.setText(cons.forum_brief);
//        holder.timeStamp.setText(cons.forum_timesStamp);
//        holder.icon.setImageResource(cons.forum_icon);
//
//        holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
//            @Override
//            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
//                contextMenu.add(holder.getAdapterPosition(), 0, 0, "exp1");
//                contextMenu.add(holder.getAdapterPosition(), 1, 0, "exp2");
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return moviesList.size();
//    }
//}
