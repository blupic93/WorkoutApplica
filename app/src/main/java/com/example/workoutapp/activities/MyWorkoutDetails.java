package com.example.workoutapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.workoutapp.MainActivity;
import com.example.workoutapp.R;
import com.example.workoutapp.helpers.DbHelper;
import com.example.workoutapp.models.MyWorkout;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MyWorkoutDetails extends AppCompatActivity {

    public static final String MYWORKOUTID = "MyWorkoutId";
    Button btn_back_from_workout_details;
    Button btn_finish_myworkout;
    int myWorkoutId;
    MyWorkout myWorkout;
    DbHelper dbHelper;
    TextView tv_mw_start;
    TextView tv_mw_end;
    TextView tv_mw_numberofexercises;
    TextView tv_mw_isfinished;
    TextView tv_mw_duration;
    TextView tv_mw_place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_workout_details);

        myWorkoutId = getIntent().getExtras().getInt(MYWORKOUTID);
        dbHelper = new DbHelper(this);
        getMyWorkout();
        getGuiElements();
        setTextViews();

        if(myWorkout.isFinished() == 1){
            btn_finish_myworkout.setVisibility(View.GONE);
        }

        btn_back_from_workout_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyWorkoutDetails.this, MainActivity.class));
                finish();
            }
        });

        btn_finish_myworkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String duration;
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                duration = String.valueOf(timestamp.getTime() - Long.valueOf(myWorkout.getStart()));
                dbHelper.updateMyWorkout(myWorkoutId, 1, String.valueOf(timestamp.getTime()), duration);
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });

        tv_mw_numberofexercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent workoutVariationDetails = new Intent();
                workoutVariationDetails.putExtra(WorkoutVariationDetails.MYWORKOUTID, myWorkoutId);
                workoutVariationDetails.setClass(MyWorkoutDetails.this, WorkoutVariationDetails.class);
                startActivity(workoutVariationDetails);
                finish();
            }
        });
    }

    private void setTextViews() {
        tv_mw_start.setText(getParsedDate(this.myWorkout.getStart()));
        tv_mw_end.setText(getParsedDate(this.myWorkout.getEnd()));
        tv_mw_numberofexercises.setText(String.valueOf(this.myWorkout.getNumberOfExercises()));
        tv_mw_isfinished.setText(parseIsFinished(String.valueOf(this.myWorkout.isFinished())));
        tv_mw_duration.setText(getParsedDuration(this.myWorkout.getDuration()));
        tv_mw_place.setText(this.myWorkout.getPlace());
    }

    private static String getParsedDuration(String duration) {
        try {
            long millis = Long.valueOf(duration);
            return String.format("%02d min, %02d sec",
                    TimeUnit.MILLISECONDS.toMinutes(millis),
                    TimeUnit.MILLISECONDS.toSeconds(millis) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
            );
        }
        catch (Exception ex){
            return "";
        }
    }

    private static String parseIsFinished(String value){
        if(value.equals("0"))
            return "ne";
        else
            return "da";
    }

    private static String getParsedDate(String unixTimestamp){
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

    private void getMyWorkout() {
        myWorkout = dbHelper.getMyWorkout(myWorkoutId);
    }

    private void getGuiElements(){
        btn_back_from_workout_details = findViewById(R.id.btn_back_from_workout_details);
        btn_finish_myworkout = findViewById(R.id.btn_finish_myworkout);
        tv_mw_start = findViewById(R.id.tv_mw_start);
        tv_mw_end = findViewById(R.id.tv_mw_end);
        tv_mw_numberofexercises = findViewById(R.id.tv_mw_numberofexercises);
        tv_mw_isfinished = findViewById(R.id.tv_mw_isfinished);
        tv_mw_duration = findViewById(R.id.tv_mw_duration);
        tv_mw_place = findViewById(R.id.tv_mw_place);
    }

    private void makeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
