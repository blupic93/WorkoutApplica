package com.example.workoutapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.workoutapp.activities.AddWorkoutActivity;
import com.example.workoutapp.activities.MyWorkoutDetails;
import com.example.workoutapp.adapters.MyWorkoutsAdapter;
import com.example.workoutapp.helpers.DbHelper;
import com.example.workoutapp.models.MyWorkout;
import com.leinardi.android.speeddial.SpeedDialActionItem;
import com.leinardi.android.speeddial.SpeedDialView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvMyWorkouts;
    DbHelper dbHelper;
    MyWorkoutsAdapter myWorkoutsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //postavi layout
        setContentView(R.layout.activity_main);
        //postavi Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dbHelper = new DbHelper(this);
        //postavi SpeedDialView, button u donjem desnom kutu, koristen library
        //pogledaj pod Gradle Scripts/ build.gradle (Module:app)/implementation "com.leinardi.android:speed-dial:1.0-alpha03"
        final SpeedDialView speedDialView = findViewById(R.id.speedDial);
        //dodaj jedan fab u taj speed dial view
        speedDialView.addFabOptionItem(
                new SpeedDialActionItem.Builder(R.id.fab, R.drawable.workout)
                        .setLabel("Dodaj vježbu")
                        .create()
        );
        //listeneri na speeddial, ako je menu otvoren zatvori i obrnuto
        speedDialView.setMainFabOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (speedDialView.isFabMenuOpen()) {
                    speedDialView.closeOptionsMenu();
                }
            }
        });

        //listeneri na speeddial ali za fabove u meniju
        speedDialView.setOptionFabSelectedListener(new SpeedDialView.OnOptionFabSelectedListener() {
            @Override
            public void onOptionFabSelected(SpeedDialActionItem speedDialActionItem) {
                switch (speedDialActionItem.getId()) {
                    case R.id.fab:
                        //ako je kliknut fab dodaj vježbu idi na novi activity
                        Intent intent = new Intent(getApplicationContext(), AddWorkoutActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    default:
                        break;
                }
            }
        });

        setMyWorkoutsList();

        lvMyWorkouts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyWorkout selectedWorkout = (MyWorkout) myWorkoutsAdapter.getItem(position);
                Intent intent = new Intent(MainActivity.this, MyWorkoutDetails.class);
                intent.putExtra(MyWorkoutDetails.MYWORKOUTID, selectedWorkout.getId());
                startActivity(intent);
            }
        });
    }

    private void setMyWorkoutsList() {
        lvMyWorkouts = findViewById(R.id.lvMyWorkouts);
        ArrayList<MyWorkout> l = dbHelper.getMyWorkouts();
        myWorkoutsAdapter = new MyWorkoutsAdapter(this, dbHelper.getMyWorkouts());
        lvMyWorkouts.setAdapter(myWorkoutsAdapter);
    }


    private void makeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
