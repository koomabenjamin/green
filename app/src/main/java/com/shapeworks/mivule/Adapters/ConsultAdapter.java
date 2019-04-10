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

import com.shapeworks.mivule.Entities.Consultant;
import com.shapeworks.mivule.R;
import com.shapeworks.mivule.detail.d_consult;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by KoomaBenjamin on 26/10/2017.
 */


public class ConsultAdapter extends FirestoreAdapter<ConsultAdapter.ViewHolder>{

public interface OnConsultSelectedListener {

    void onConsultSelected(DocumentSnapshot restaurant);

}

    private ConsultAdapter.OnConsultSelectedListener mListener;

    public ConsultAdapter(Query query, ConsultAdapter.OnConsultSelectedListener listener) {
        super(query);
        mListener = listener;
    }



    @Override
    public ConsultAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ConsultAdapter.ViewHolder(inflater.inflate(R.layout.consult_card_item_00, parent, false));
    }

    @Override
    public void onBindViewHolder(ConsultAdapter.ViewHolder holder, int position) {
        holder.bind(getSnapshot(position), mListener);
    }

static class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.consult_icon)
    ImageView icon;

    @BindView(R.id.consult_name)
    TextView name;

    @BindView(R.id.consult_expertise)
    TextView experitse;

    @BindView(R.id.consult_brief)
    TextView brief;

    @BindView(R.id.consult_rating)
    RatingBar rating;



    public ViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(final DocumentSnapshot snapshot,
                     final ConsultAdapter.OnConsultSelectedListener listener){
        Consultant restaurant = snapshot.toObject(Consultant.class);
        Resources resources = itemView.getResources();

//            Load image
//            Glide.with(imageView.getContext())
//                    .load(restaurant.getPhoto())
//                    .into(imageView);

//            icon.setText(restaurant.getEmail());
//            ratingBar.setRating((float) restaurant.getAvgRating());
        brief.setText(restaurant.getEmail());
        name.setText(restaurant.getName());
        experitse.setText(restaurant.getExpertise());
//            numRatingsView.setText(resources.getString(R.string.fmt_num_ratings, restaurant.getNumRatings()));
//            priceView.setText(RestaurantUtil.getPriceString(restaurant));

        // Click listener
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onConsultSelected(snapshot);
                }
            }
        });
    }
}
}


//public class ConsultAdapter extends RecyclerView.Adapter<ConsultAdapter.MyViewHolder>  {
//
//    private List<Consult> moviesList;
//    public class MyViewHolder extends RecyclerView.ViewHolder {
//
//        public TextView title, name, brief;
//        private Context context;
//        private ImageView icon;
//        private Dialog dialog;
//        private RatingBar mRatingBar;
//        private Button btnConsult;
//
//        public MyViewHolder(View view) {
//            super(view);
//            context = view.getContext();
//            title = (TextView) view.findViewById(R.id.title);
//            name = (TextView) view.findViewById(R.id.name);
//            brief = (TextView) view.findViewById(R.id.brief);
//            icon = (ImageView) view.findViewById(R.id.consult_icon);
//            mRatingBar = (RatingBar) view.findViewById(R.id.ratingBar);
//            btnConsult = (Button) view.findViewById(R.id.btn_consult);
//
//            btnConsult.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(context, d_consult.class);
////                    intent.putExtra();
//                    context.startActivity(intent);
//                }
//            });
//
//
//            //dialog = (Dialog) view.
//
//            view.setOnClickListener(new View.OnClickListener(){
//
//                @Override
//                public void onClick(View view) {
//
////                    Intent intent = new Intent(context, HomeActivity.class);
////                    context.startActivity(intent);
//                }
//            });
//        }
////        @Override
////        public void onClick(View v){
////
////        }
//    }
//
//
//    public ConsultAdapter(List<Consult> moviesList) {
//        this.moviesList = moviesList;
//    }
//
//    @Override
//    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.consult_card_item_00, parent, false);
//
//        return new MyViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(final MyViewHolder holder, int position) {
//
//        Consult cons = moviesList.get(position);
////        holder.title.setText(cons.getTitle());
////        holder.genre.setText(cons.getGenre());
////        holder.year.setText(cons.getYear());
//        holder.title.setText(cons.title);
//        holder.name.setText(cons.name);
//        holder.brief.setText(cons.brief);
//        holder.icon.setImageResource(cons.icon);
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
