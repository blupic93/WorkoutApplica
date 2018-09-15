package com.example.workoutapp.activities;

import com.example.workoutapp.MainActivity;
import com.example.workoutapp.R;
import com.example.workoutapp.adapters.AddMyWorkoutAdapter;
import com.example.workoutapp.adapters.ChooseWorkoutVariationAdapter;
import com.example.workoutapp.helpers.DbHelper;
import com.example.workoutapp.models.AddMyWorkoutHelpModel;
import com.example.workoutapp.models.MainWorkout;
import com.example.workoutapp.models.MyWorkout;
import com.example.workoutapp.models.WorkoutVariation;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AddWorkoutActivity extends AppCompatActivity {

    DbHelper dbHelper;
    Spinner spMainWorkouts;
    List<String> arrayMainSpinner = new ArrayList<String>();
    ArrayList<WorkoutVariation> workoutVariations;
    LinearLayout llAddWorkoutBtn;
    Button btnAddWorkout;
    Button btnGoToVariationSelect;
    ListView lvSelectedWorkouts;
    WorkoutVariation workoutVariationFromResult;
    ArrayList<AddMyWorkoutHelpModel> addMyWorkoutHelpModels = new ArrayList<>();
    AddMyWorkoutAdapter addMyWorkoutAdapter;
    MainWorkout mainWorkout;
    String place = "";
    Button btnAddPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_workout);

        dbHelper = new DbHelper(this);
        workoutVariations = new ArrayList<WorkoutVariation>();

        //dohvati vježbe iz db
        final ArrayList<MainWorkout> mainWorkouts = dbHelper.getMainWorkouts();

        //getGuiElements - dohvati elemente iz xml-a po id-u
         getGuiElements();

        addMyWorkoutAdapter = new AddMyWorkoutAdapter(this, addMyWorkoutHelpModels);
        lvSelectedWorkouts.setAdapter(addMyWorkoutAdapter);
        //izvuci imena vježbi iz liste i dodaj ih u novu list stringova - ne može se prikazati cijeli objekt nego samo ime
        //znaci izvuce se iz ArrayList<MainWorkout> mainWorkouts nova lista List<String> arraySpinner
        setMainSpinnerArrayList(mainWorkouts);
        //postavi elemente u spinner (dropdown)
        setMainSpinner();

        btnGoToVariationSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                mainWorkout = dbHelper.getMainWorkout(spMainWorkouts.getSelectedItem().toString());
                intent.putExtra("MAINWORKOUTID", mainWorkout.getId());
                intent.setClass(AddWorkoutActivity.this, ChooseWorkoutVariationActivity.class);
                Log.d("MainWorkoutId", Integer.toString(mainWorkout.getId()));
                startActivityForResult(intent, 1);

            }
        });

        btnAddWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                if(workoutVariations.size() == 0)
                    makeToast("Morate odabrati vježbu.");
                if(place.isEmpty())
                    makeToast("Morate odabrati mjesto.");
                dbHelper.insertNewMyWorkout(new MyWorkout(String.valueOf(timestamp.getTime()),
                        "0", workoutVariations.size(),
                         0,"0", place));
                int myWorkoutId = dbHelper.getLastMyWorkoutId();
                for (WorkoutVariation workoutVariation:
                        workoutVariations) {
                    dbHelper.insertNewWorkoutConnection(myWorkoutId, workoutVariation.getWorkoutId(),
                            workoutVariation.getId());
                }
                startActivity(new Intent(AddWorkoutActivity.this, MainActivity.class));
                finish();
            }
        });

        btnAddPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(AddWorkoutActivity.this, MapsActivity.class),
                        MapsActivity.PLACE_CODE);
            }
        });
    }

    private void getGuiElements() {
        spMainWorkouts = findViewById(R.id.spAddMainWorkout);
        llAddWorkoutBtn = findViewById(R.id.llAddWorkoutBtn);
        btnAddWorkout = findViewById(R.id.btnAddWorkout);
        btnGoToVariationSelect = findViewById(R.id.btnGoToVariationSelect);
        lvSelectedWorkouts = findViewById(R.id.lvSelectedWorkouts);
        llAddWorkoutBtn = findViewById(R.id.llAddWorkoutBtn);
        btnAddPlace = findViewById(R.id.btnAddPlace);
    }

    private void setMainSpinnerArrayList(ArrayList<MainWorkout> mainWorkouts) {
        for (MainWorkout mainWorkout :
                mainWorkouts){
            arrayMainSpinner.add(mainWorkout.getName());
        }
    }

    private void setMainSpinner() {
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrayMainSpinner);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down vieww
        spMainWorkouts.setAdapter(spinnerArrayAdapter);
    }


    private void makeToast(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(AddWorkoutActivity.this);
                Integer id = sharedPref.getInt("selectedWorkoutVariationId", 0);
                workoutVariationFromResult = dbHelper.getWorkoutVariation(id);
                addMyWorkoutHelpModels.add(new AddMyWorkoutHelpModel(
                        spMainWorkouts.getSelectedItem().toString(),
                        mainWorkout.getType(),
                        workoutVariationFromResult.getSubType()));

                workoutVariations.add(workoutVariationFromResult);
                addMyWorkoutAdapter.notifyDataSetChanged();
                llAddWorkoutBtn.setVisibility(View.VISIBLE);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                makeToast("Varijacija nije dodana.");
            }
        }
        else if(requestCode == MapsActivity.PLACE_CODE) {
            if(resultCode == Activity.RESULT_OK){
                place = data.getStringExtra(MapsActivity.PLACE_TAG);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                makeToast("Mjesto nije dodano.");
            }
        }
    }

}
