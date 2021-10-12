package com.example.api_crud;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.api_crud.Model.TaskModel;

import java.util.ArrayList;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<TaskModel> taskModel;
    private String url = "";

    public ActivityAdapter(Context context, ArrayList<TaskModel> taskModel){
        this.context = context;
        this.taskModel = taskModel;
    }

    @NonNull
    @Override
    public ActivityAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.list_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityAdapter.MyViewHolder holder, int position) {
        holder.task_name.setText(taskModel.get(position).getName());
        holder.isDone.setChecked(taskModel.get(position).isDone());
    }

    @Override
    public int getItemCount() {

        return taskModel.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView task_name;
        private CheckBox isDone;

        public MyViewHolder(@NonNull View itemview){
            super(itemview);
            task_name = (TextView) itemview.findViewById(R.id.task_name);
            isDone = (CheckBox) itemview.findViewById(R.id.is_done);
        }

    }
}
