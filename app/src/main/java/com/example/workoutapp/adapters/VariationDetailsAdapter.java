package com.example.workoutapp.adapters;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.workoutapp.R;
import com.example.workoutapp.models.AddMyWorkoutHelpModel;

import java.util.ArrayList;

public class VariationDetailsAdapter extends BaseAdapter {

    Context context;
    ArrayList<AddMyWorkoutHelpModel> addMyWorkoutHelpModelArrayList;

    public VariationDetailsAdapter(Context context, ArrayList<AddMyWorkoutHelpModel> addMyWorkoutHelpModelArrayList) {
        this.context = context;
        this.addMyWorkoutHelpModelArrayList = addMyWorkoutHelpModelArrayList;
    }

    @Override
    public int getCount() {
        return addMyWorkoutHelpModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.addMyWorkoutHelpModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        WorkoutViewHolder holder = new WorkoutViewHolder();

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.layout_variation_details_item, null);

            holder.tvVdName = convertView.findViewById(R.id.tvVdName);
            holder.tvVdType = convertView.findViewById(R.id.tvVdType);
            holder.tvVdSubType = convertView.findViewById(R.id.tvVdSubType);

            convertView.setTag(holder);
        }
        else {
            holder = (WorkoutViewHolder) convertView.getTag();
        }

        AddMyWorkoutHelpModel current = addMyWorkoutHelpModelArrayList.get(position);
        holder.tvVdName.setText(current.getName());
        holder.tvVdType.setText(current.getType());
        holder.tvVdSubType.setText(current.getSubType());

        return convertView;
    }

    static class WorkoutViewHolder {

        TextView tvVdName;
        TextView tvVdType;
        TextView tvVdSubType;
    }
}
