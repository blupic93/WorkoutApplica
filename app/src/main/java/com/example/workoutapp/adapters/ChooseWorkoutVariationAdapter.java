package com.example.workoutapp.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.workoutapp.R;
import com.example.workoutapp.models.WorkoutVariation;

import java.util.ArrayList;

public class ChooseWorkoutVariationAdapter extends BaseAdapter {

    Context context;
    ArrayList<WorkoutVariation> workoutVariationsList;

    public ChooseWorkoutVariationAdapter(Context context, ArrayList<WorkoutVariation> workoutVariationsList) {
        super();
        this.context = context;
        this.workoutVariationsList = workoutVariationsList;
    }


    @Override
    public int getCount() {
        return this.workoutVariationsList.size();
    }

    @Override
    public Object getItem(int position) {
        return workoutVariationsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.layout_workout_variation_item, null);
        }

        WorkoutVariation current = workoutVariationsList.get(position);

        TextView tvItemName = convertView.findViewById(R.id.tvWvName);
        TextView tvItemType = convertView.findViewById(R.id.tvWvType);

        tvItemName.setText(current.getName());
        tvItemType.setText(workoutVariationsList.get(position).getSubType());

        return convertView;
    }
}
