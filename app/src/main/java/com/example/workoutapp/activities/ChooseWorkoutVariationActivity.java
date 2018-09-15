package com.example.workoutapp.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.workoutapp.R;
import com.example.workoutapp.adapters.ChooseWorkoutVariationAdapter;
import com.example.workoutapp.helpers.DbHelper;
import com.example.workoutapp.models.WorkoutVariation;

import java.util.ArrayList;

public class ChooseWorkoutVariationActivity extends Activity {

    ArrayList<WorkoutVariation> variations = new ArrayList<>();
    DbHelper dbHelper;
    ListView lvAddWorkoutVariation;
    ChooseWorkoutVariationAdapter chooseWorkoutVariationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_workout_variation);
        Intent intent = getIntent();
        int mainworkoutid = intent.getExtras().getInt("MAINWORKOUTID");
        dbHelper = new DbHelper(this);

        lvAddWorkoutVariation = findViewById(R.id.lvAddWorkoutVariation);

        variations = dbHelper.getWorkoutVariationsByMainWorkout(mainworkoutid);
        this.setFinishOnTouchOutside(false);

        chooseWorkoutVariationAdapter = new ChooseWorkoutVariationAdapter(this, variations);
        lvAddWorkoutVariation.setAdapter(chooseWorkoutVariationAdapter);

        lvAddWorkoutVariation.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                WorkoutVariation selectedWorkoutVariation = (WorkoutVariation) chooseWorkoutVariationAdapter.getItem(position);
                Intent returnIntent = new Intent();
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(ChooseWorkoutVariationActivity.this);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("selectedWorkoutVariationId", selectedWorkoutVariation.getId());
                editor.commit();
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
                return false;
            }
        });

        lvAddWorkoutVariation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(ChooseWorkoutVariationActivity.this);
                builder.setTitle("Primjeri izvođenja.");
                LayoutInflater factory = LayoutInflater.from(ChooseWorkoutVariationActivity.this);
                final View viewInflate = factory.inflate(R.layout.layout_workout_examples_item, null);
                WorkoutVariation selectedWorkoutVariation = (WorkoutVariation) chooseWorkoutVariationAdapter.getItem(position);
                if(selectedWorkoutVariation.getName().startsWith("Klasični sklekovi")) {
                    ImageView imageView1 = viewInflate.findViewById(R.id.imgWorkout1);
                    imageView1.setImageResource(R.drawable.pushups_classic_1);
                    ImageView imageView2 = viewInflate.findViewById(R.id.imgWorkout2);
                    imageView2.setImageResource(R.drawable.pushups_classic_2);
                    ImageView imageView3 = viewInflate.findViewById(R.id.imgWorkout3);
                    imageView3.setImageResource(R.drawable.pushups_classic_3);
                    builder.setView(viewInflate);
                }
                else if(selectedWorkoutVariation.getName().startsWith("Uski sklekovi")){
                        ImageView imageView1 = viewInflate.findViewById(R.id.imgWorkout1);
                        imageView1.setImageResource(R.drawable.pushup_close_grip_1);
                        ImageView imageView2 = viewInflate.findViewById(R.id.imgWorkout2);
                        imageView2.setImageResource(R.drawable.pushup_close_grip_2);
                        builder.setView(viewInflate);
                }
                else if(selectedWorkoutVariation.getName().startsWith("Široki sklekovi")){
                    ImageView imageView1 = viewInflate.findViewById(R.id.imgWorkout1);
                    imageView1.setImageResource(R.drawable.pushup_wide_grip_1);
                    ImageView imageView2 = viewInflate.findViewById(R.id.imgWorkout2);
                    imageView2.setImageResource(R.drawable.pushup_wide_grip_2);
                    builder.setView(viewInflate);
                }
                else if(selectedWorkoutVariation.getName().startsWith("Široki zgibovi")){
                    ImageView imageView1 = viewInflate.findViewById(R.id.imgWorkout1);
                    imageView1.setImageResource(R.drawable.pullup_widegrip_1);
                    ImageView imageView2 = viewInflate.findViewById(R.id.imgWorkout2);
                    imageView2.setImageResource(R.drawable.pullup_widegrip_2);
                    builder.setView(viewInflate);
                }
                else if(selectedWorkoutVariation.getName().startsWith("Uski zgibovi")){
                    ImageView imageView1 = viewInflate.findViewById(R.id.imgWorkout1);
                    imageView1.setImageResource(R.drawable.pullup_closegrip_1);
                    ImageView imageView2 = viewInflate.findViewById(R.id.imgWorkout2);
                    imageView2.setImageResource(R.drawable.pullup_closegrip_2);
                    builder.setView(viewInflate);
                }
                else if(selectedWorkoutVariation.getName().startsWith("Klasični zgibovi")){
                    ImageView imageView1 = viewInflate.findViewById(R.id.imgWorkout1);
                    imageView1.setImageResource(R.drawable.pullup_classic_2);
                    ImageView imageView2 = viewInflate.findViewById(R.id.imgWorkout2);
                    imageView2.setImageResource(R.drawable.pullup_classic_3);
                    ImageView imageView3 = viewInflate.findViewById(R.id.imgWorkout3);
                    imageView3.setImageResource(R.drawable.pullup_classic_4);
                    builder.setView(viewInflate);
                }

                builder.setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
                builder.setCancelable(false);
            }
        });
    }
}
