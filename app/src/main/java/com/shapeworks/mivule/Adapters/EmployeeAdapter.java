package com.shapeworks.mivule.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shapeworks.mivule.Entities.Employee;
import com.shapeworks.mivule.Entities.Projects;
import com.shapeworks.mivule.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by koomabenjamin on 12/8/17.
 */

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.MyViewHolder>{

    private List<Employee> employees = new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name, email, experience, status, intro;
        private Context context;
        private ImageView icon;
        private Dialog dialog;

        public MyViewHolder(View view) {
            super(view);

            context = view.getContext();
//            name = (TextView) view.findViewById(R.id.wrk_name);
//            email = (TextView) view.findViewById(R.id.wrk_email);
//            experience = (TextView) view.findViewById(R.id.wrk_experience);
//            status = (TextView) view.findViewById(R.id.wrk_availability);
//            intro = (TextView) view.findViewById(R.id.wrk_intro);


        }

    }


    public EmployeeAdapter(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public EmployeeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.employee_item_00, parent, false);

        return new EmployeeAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final EmployeeAdapter.MyViewHolder holder, int position) {

        Employee employee = employees.get(position);
        holder.name.setText(employee.name);
        holder.status.setText(employee.status);
        holder.email.setText(employee.email);
        holder.experience.setText(employee.experience);
        holder.intro.setText(employee.intro);

    }

    @Override
    public int getItemCount() {
        return employees.size();
    }
}
