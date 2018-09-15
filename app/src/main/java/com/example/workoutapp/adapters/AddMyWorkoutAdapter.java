package com.example.workoutapp.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.workoutapp.R;
import com.example.workoutapp.models.AddMyWorkoutHelpModel;
import com.example.workoutapp.models.WorkoutVariation;

import java.util.ArrayList;

public class AddMyWorkoutAdapter extends BaseAdapter {
    Context context;
    ArrayList<AddMyWorkoutHelpModel> addMyWorkoutHelpModels;

    public AddMyWorkoutAdapter(Context context, ArrayList<AddMyWorkoutHelpModel> addMyWorkoutHelpModels) {
        super();
        this.context = context;
        this.addMyWorkoutHelpModels = addMyWorkoutHelpModels;
    }

    @Override
    public int getCount() {
        return this.addMyWorkoutHelpModels.size();
    }

    @Override
    public Object getItem(int position) {
        return addMyWorkoutHelpModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        WorkoutViewHolder holder = new WorkoutViewHolder();

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.layout_main_workout_item, null);

            holder.tvItemName = convertView.findViewById(R.id.tvWmName);
            holder.tvItemType = convertView.findViewById(R.id.tvWmType);
            holder.tvItemSubType = convertView.findViewById(R.id.tvWmSubType);

            convertView.setTag(holder);
        }
        else {
            holder = (WorkoutViewHolder) convertView.getTag();
        }

        AddMyWorkoutHelpModel current = addMyWorkoutHelpModels.get(position);



        holder.tvItemName.setText(current.getName());
        holder.tvItemType.setText(current.getType());
        holder.tvItemSubType.setText(current.getSubType());

        return convertView;
    }

    static class WorkoutViewHolder {

        TextView tvItemName;
        TextView tvItemType;
        TextView tvItemSubType;

    }
}
