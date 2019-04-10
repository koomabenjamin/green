package com.shapeworks.mivule.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shapeworks.mivule.Entities.Products;
import com.shapeworks.mivule.Entities.Products;
import com.shapeworks.mivule.R;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;
import com.shapeworks.mivule.Adapters.FirestoreAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by koomabenjamin on 12/13/17.
 */

public class ProductsAdapter extends FirestoreAdapter<ProductsAdapter.ViewHolder> {


    public interface OnProductsSelectedListener {

        void onProductstSelected(DocumentSnapshot restaurant);

    }

    private OnProductsSelectedListener mListener;

    public ProductsAdapter(Query query, OnProductsSelectedListener listener) {
        super(query);
        mListener = listener;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.product_list_item_00, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(getSnapshot(position), mListener);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.product_icon)
        ImageView icon;

        @BindView(R.id.product_name)
        TextView name;

        @BindView(R.id.product_price)
        TextView price;

        @BindView(R.id.product_availableQuantity)
        TextView availableQuantity;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final DocumentSnapshot snapshot,
                         final OnProductsSelectedListener listener){
            Products restaurant = snapshot.toObject(Products.class);
            Resources resources = itemView.getResources();

            // Load image
//            Glide.with(imageView.getContext())
//                    .load(restaurant.getPhoto())
//                    .into(imageView);

            name.setText(restaurant.getName());
//            ratingBar.setRating((float) restaurant.getAvgRating());
            price.setText(restaurant.getPrice());
            availableQuantity.setText(restaurant.getAvailableQuantity());
//            numRatingsView.setText(resources.getString(R.string.fmt_num_ratings, restaurant.getNumRatings()));
//            priceView.setText(RestaurantUtil.getPriceString(restaurant));

            // Click listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onProductstSelected(snapshot);
                    }
                }
            });
        }
    }
}

//public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyViewHolder> {
//
//    private List<Products> products = new ArrayList<>();
//
//    public class MyViewHolder extends RecyclerView.ViewHolder {
//
//        public TextView name, price, quantity;
//        private Context context;
//        private ImageView icon;
//        private Dialog dialog;
//
//        public MyViewHolder(View view) {
//            super(view);
//            context = view.getContext();
//            name = (TextView) view.findViewById(R.id.pdct_name);
//            price = (TextView) view.findViewById(R.id.pdct_px);
//            quantity = (TextView) view.findViewById(R.id.pdct_qty);
//
//
//        }
//
//    }
//
//
//    public ProductsAdapter(List<Products> employees) {
//        this.products = employees;
//    }
//
//    @Override
//    public ProductsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.product_list_item_00, parent, false);
//
//        return new ProductsAdapter.MyViewHolder(itemView);
//    }
//
//
//    @Override
//    public void onBindViewHolder(final ProductsAdapter.MyViewHolder holder, int position) {
//
//        Products employee = products.get(position);
//        holder.name.setText(employee.name);
//        holder.quantity.setText(employee.status);
//        holder.price.setText(employee.price);
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return products.size();
//    }
//}
