package com.example.workoutapp.adapters;


import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.workoutapp.R;
import com.example.workoutapp.models.MyWorkout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MyWorkoutsAdapter extends BaseAdapter{

    Context context;
    ArrayList<MyWorkout> myWorkoutArrayList;

    public MyWorkoutsAdapter(Context context, ArrayList<MyWorkout> myWorkoutArrayList) {
        this.context = context;
        this.myWorkoutArrayList = myWorkoutArrayList;
    }

    @Override
    public int getCount() {
        return myWorkoutArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return myWorkoutArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        WorkoutViewHolder holder = new WorkoutViewHolder();

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.layout_my_workout_item, null);

            holder.tvMwItemStart = convertView.findViewById(R.id.tvMwItemStart);
            holder.tvMwItemisFinished = convertView.findViewById(R.id.tvMwItemisFinished);
            holder.tvMwItemNumberOfExercises = convertView.findViewById(R.id.tvMwItemNumberOfExercises);

            convertView.setTag(holder);
        }
        else {
            holder = (WorkoutViewHolder) convertView.getTag();
        }

        MyWorkout current = myWorkoutArrayList.get(position);

        holder.tvMwItemStart.setText(
                String.valueOf(WorkoutViewHolder.getParsedDate(current.getStart()))
        );
        holder.tvMwItemisFinished.setText(
                String.valueOf(WorkoutViewHolder.getParsedDate(current.getEnd()))
        );
        holder.tvMwItemNumberOfExercises.setText(String.valueOf(current.getNumberOfExercises()));


        return convertView;
    }

    static class WorkoutViewHolder {

        TextView tvMwItemStart;
        TextView tvMwItemisFinished;
        TextView tvMwItemNumberOfExercises;


        public static String getParsedDate(String unixTimestamp){
            try {
                if(unixTimestamp.equals("0"))
                    throw new Exception();
                Date date = new java.sql.Date(Long.parseLong(unixTimestamp));
                SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd.MM.yyyy HH:mm");
                String formattedDate = sdf.format(date);
                return formattedDate;
            }
            catch (Exception ex) {
                return "";
            }
        }
    }
}
