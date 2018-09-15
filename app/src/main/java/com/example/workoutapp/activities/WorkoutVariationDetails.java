package com.example.workoutapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.workoutapp.MainActivity;
import com.example.workoutapp.R;
import com.example.workoutapp.adapters.VariationDetailsAdapter;
import com.example.workoutapp.helpers.DbHelper;
import com.example.workoutapp.models.AddMyWorkoutHelpModel;
import com.example.workoutapp.models.MainWorkout;
import com.example.workoutapp.models.WorkoutConnectionHelpModel;
import com.example.workoutapp.models.WorkoutVariation;

import java.util.ArrayList;
import java.util.List;

public class WorkoutVariationDetails extends AppCompatActivity {

    public static final String MYWORKOUTID = "MyWorkoutDetailsId";
    ListView lvVariationDetails;
    ArrayList<WorkoutConnectionHelpModel> variationDetailsData;
    int myWorkoutId;
    DbHelper dbHelper;
    ArrayList<AddMyWorkoutHelpModel> listData = new ArrayList<>();
    VariationDetailsAdapter variationDetailsAdapter;
    Button btn_back_from_variation_details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_variation_details);

        dbHelper = new DbHelper(this);
        myWorkoutId = getIntent().getIntExtra(MYWORKOUTID, 0);
        lvVariationDetails = findViewById(R.id.lvVariationDetails);
        btn_back_from_variation_details = findViewById(R.id.btn_back_from_variation_details);

        variationDetailsData = getData();
        setVariationDetailsList();

        btn_back_from_variation_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WorkoutVariationDetails.this, MainActivity.class));
                finish();
            }
        });
    }

    private void setVariationDetailsList() {
        for (WorkoutConnectionHelpModel workoutConnectionHelpModel
                : variationDetailsData){
            MainWorkout mainWorkout = dbHelper.getMainWorkout(workoutConnectionHelpModel.getMainWorkoutsId());
            WorkoutVariation workoutVariation = dbHelper.getWorkoutVariation(workoutConnectionHelpModel.getWorkoutVariationsId());
            listData.add(new AddMyWorkoutHelpModel(workoutVariation.getName(), mainWorkout.getType(), workoutVariation.getSubType()));
        }
        variationDetailsAdapter = new VariationDetailsAdapter(this, listData);
        lvVariationDetails.setAdapter(variationDetailsAdapter);
    }

    public ArrayList<WorkoutConnectionHelpModel> getData() {
        return dbHelper.getWorkoutsConnection(myWorkoutId);
    }
}
