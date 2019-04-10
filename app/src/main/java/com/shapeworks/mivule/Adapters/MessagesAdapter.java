package com.shapeworks.mivule.Adapters;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.shapeworks.mivule.Entities.Message;
import com.shapeworks.mivule.Entities.Message;
import com.shapeworks.mivule.R;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by koomabenjamin on 12/13/17.
 */

public class MessagesAdapter extends FirestoreAdapter<MessagesAdapter.ViewHolder>{

    public interface OnMessagesSelectedListener {

        void onMessagesSelected(DocumentSnapshot restaurant);

    }

    private MessagesAdapter.OnMessagesSelectedListener mListener;

    public MessagesAdapter(Query query, MessagesAdapter.OnMessagesSelectedListener listener) {
        super(query);
        mListener = listener;
    }



    @Override
    public MessagesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new MessagesAdapter.ViewHolder(inflater.inflate(R.layout.messages_list_item_00, parent, false));
    }

    @Override
    public void onBindViewHolder(MessagesAdapter.ViewHolder holder, int position) {
        holder.bind(getSnapshot(position), mListener);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.message_icon)
        ImageView icon;

        @BindView(R.id.message_subject)
        TextView subject;

        @BindView(R.id.message_sender)
        TextView sender;

        @BindView(R.id.message_receivedDate)
        TextView recievedDate;

        @BindView(R.id.message_content)
        TextView content;

        @BindView(R.id.message_timeStamp)
        TextView timeStamp;



        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final DocumentSnapshot snapshot,
                         final MessagesAdapter.OnMessagesSelectedListener listener){
            Message restaurant = snapshot.toObject(Message.class);
            Resources resources = itemView.getResources();

//            Load image
//            Glide.with(imageView.getContext())
//                    .load(restaurant.getPhoto())
//                    .into(imageView);

//            icon.setText(restaurant.getEmail());
//            ratingBar.setRating((float) restaurant.getAvgRating());
            subject.setText(restaurant.getSubject());
            content.setText(restaurant.getContent());
//            numRatingsView.setText(resources.getString(R.string.fmt_num_ratings, restaurant.getNumRatings()));
//            priceView.setText(RestaurantUtil.getPriceString(restaurant));

            // Click listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onMessagesSelected(snapshot);
                    }
                }
            });
        }
    }
}

//public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MyHolder> {
//
//    private List<Message> messages = new ArrayList<>();
//
//    public MessagesAdapter(List<Message> messages) {
//        this.messages = messages;
//    }
//
//    @Override
//    public MessagesAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.messages_list_item_00, parent, false);
//
//        return new MessagesAdapter.MyHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(MessagesAdapter.MyHolder holder, int position) {
//
//        Message message = messages.get(position);
//        holder.subject.setText(message.subject);
//        holder.sender.setText(message.sender);
//        holder.content.setText(message._content);
//        holder.time.setText(message.time);
//        holder.date.setText(message.date);
//    }
//
//    @Override
//    public int getItemCount() {
//        return messages.size();
//    }
//
//    class MyHolder extends RecyclerView.ViewHolder{
//        TextView subject ,content ,sender, date, time;
//
//
//        public MyHolder(View itemView) {
//            super(itemView);
//            subject = (TextView) itemView.findViewById(R.id.msg_subject);
//            content = (TextView) itemView.findViewById(R.id.msg_content);
//            time = (TextView) itemView.findViewById(R.id.msg_time);
//            sender = (TextView) itemView.findViewById(R.id.msg_sender);
//            date = (TextView) itemView.findViewById(R.id.msg_date);
//
//        }
//    }
//}
