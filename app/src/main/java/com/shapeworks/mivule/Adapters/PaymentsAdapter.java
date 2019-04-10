package com.shapeworks.mivule.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shapeworks.mivule.Entities.Payments;
import com.shapeworks.mivule.R;
import android.content.res.Resources;
import android.widget.ImageView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;

import java.text.DateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

/**
 * Created by KoomaBenjamin on 01/11/2017.
 */

public class PaymentsAdapter extends FirestoreAdapter<PaymentsAdapter.ViewHolder> {


    public interface OnProjectSelectedListener {

        void onProjectSelected(DocumentSnapshot restaurant);

    }

    private OnProjectSelectedListener mListener;
    private Context context;

    public PaymentsAdapter(Query query, OnProjectSelectedListener listener) {
        super(query);
        mListener = listener;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.payments_list_item_00, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(getSnapshot(position), mListener);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.payments_icon)
        ImageView icon;

        @BindView(R.id.payments_type)
        TextView type;

        @BindView(R.id.payments_reason)
        TextView reason;

        @BindView(R.id.payments_brief)
        TextView brief;

        @BindView(R.id.payments_recordDate)
        TextView recordDate;

        @BindView(R.id.payments_timeStamp)
        TextView timeStamp;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final DocumentSnapshot snapshot,
                         final OnProjectSelectedListener listener){
            Payments restaurant = snapshot.toObject(Payments.class);
            Resources resources = itemView.getResources();

            // Load image
//            Glide.with(imageView.getContext())
//                    .load(restaurant.getPhoto())
//                    .into(imageView);

            type.setText(restaurant.getEmail());
//            ratingBar.setRating((float) restaurant.getAvgRating());
            reason.setText(restaurant.getTransId());
            brief.setText(restaurant.getPaymentAmount());

            Date date = new Date(restaurant.getPaymentDate().getTime());
            String stringDate = DateFormat.getDateInstance().format(date);

            recordDate.setText(stringDate);
            timeStamp.setText(restaurant.getPaymentStatus());
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

//public class PaymentsAdapter extends RecyclerView.Adapter<PaymentsAdapter.MyHoder>{
//    private List<Payments> payment = new ArrayList<>();
//
//    public PaymentsAdapter(List<Payments> payment) {
//        this.payment = payment;
//    }
//
//    @Override
//    public PaymentsAdapter.MyHoder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.payments_list_item_00, parent, false);
//
//        return new PaymentsAdapter.MyHoder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(PaymentsAdapter.MyHoder holder, int position) {
//
//        Payments payments = payment.get(position);
//        holder.purpose.setText(payments.purpose);
//        holder.brief.setText(payments.brief);
//        holder.type.setText(payments.type);
//        holder.time.setText(payments.time);
//        holder.date.setText(payments.date);
//    }
//
//    @Override
//    public int getItemCount() {
//        return payment.size();
//    }
//
//    class MyHoder extends RecyclerView.ViewHolder{
//        TextView purpose ,brief ,type, date, time;
//
//
//        public MyHoder(View itemView) {
//            super(itemView);
//            purpose = (TextView) itemView.findViewById(R.id.pyt_purpose);
//            brief = (TextView) itemView.findViewById(R.id.pyt_brief);
//            time = (TextView) itemView.findViewById(R.id.pyt_time);
//            type = (TextView) itemView.findViewById(R.id.pyt_type);
//            date = (TextView) itemView.findViewById(R.id.pyt_date);
//
//        }
//    }
//}
