package com.shapeworks.mivule.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shapeworks.mivule.Entities.Consultant;
import com.shapeworks.mivule.Entities.Users;
import com.shapeworks.mivule.R;

import java.util.List;

/**
 * Created by KoomaBenjamin on 27/10/2017.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder>{
    private List<Users> moviesList;


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView title, year, genre;
        private Context context;
        private Dialog dialog;

        public MyViewHolder(View view) {
            super(view);
            context = view.getContext();
            title = (TextView) view.findViewById(R.id.title);
            genre = (TextView) view.findViewById(R.id.genre);
            year = (TextView) view.findViewById(R.id.year);

            //dialog = (Dialog) view.

            view.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {

//                    Intent intent = new Intent(context, HomeActivity.class);
//                    context.startActivity(intent);
                }
            });
        }
//        @Override
//        public void onClick(View v){
//
//        }
    }


    public UserAdapter(List<Users> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_01, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final UserAdapter.MyViewHolder holder, int position) {

        Users cons = moviesList.get(position);
//        holder.title.setText(cons.getTitle());
//        holder.genre.setText(cons.getGenre());
//        holder.year.setText(cons.getYear());
        holder.title.setText(cons.name);
        holder.genre.setText(cons.email);

        holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                contextMenu.add(holder.getAdapterPosition(), 0, 0, "exp1");
                contextMenu.add(holder.getAdapterPosition(), 1, 0, "exp2");
            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
