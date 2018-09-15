package com.example.workoutapp.helpers;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.workoutapp.models.MainWorkout;
import com.example.workoutapp.models.MyWorkout;
import com.example.workoutapp.models.WorkoutConnectionHelpModel;
import com.example.workoutapp.models.WorkoutVariation;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {

    public static final int DBVersion = 1;
    private static String DATABASE_NAME = "WorkoutApp.db";

    //My workouts table
    public static final String MYWORKOUTS_TABLE_NAME = "my_workouts";
    public static final String MYWORKOUTS_COL_1 = "ID";
    public static final String MYWORKOUTS_COL_2 = "START";
    public static final String MYWORKOUTS_COL_3 = "END";
    public static final String MYWORKOUTS_COL_4 = "NUMBER_OF_EXERCISES";
    public static final String MYWORKOUTS_COL_5 = "FINISHED";
    public static final String MYWORKOUTS_COL_6 = "DURATION";
    public static final String MYWORKOUTS_COL_7 = "PLACE";

    //My workouts table

    //Workouts connection table
    public static final String WORKOUTSCONNECTION_TABLE_NAME = "workouts_connection";
    public static final String WORKOUTSCONNECTION_COL_1 = "ID";
    public static final String WORKOUTSCONNECTION_COL_2 = "MYWORKOUTSID";
    public static final String WORKOUTSCONNECTION_COL_3 = "MAINWORKOUTSID";
    public static final String WORKOUTSCONNECTION_COL_4 = "WORKOUTVARIATIONSID";
    //Workouts connection table

    //Main workouts table
    public static final String MAINWORKOUTS_TABLE_NAME = "main_workouts";
    public static final String MAINWORKOUTS_COL_1 = "ID";
    public static final String MAINWORKOUTS_COL_2 = "NAME";
    public static final String MAINWORKOUTS_COL_3 = "NUMBER_OF_VARIATIONS";
    public static final String MAINWORKOUTS_COL_4 = "TYPE";
    //Main workouts table

    //Workout variations table
    public static final String WORKOUTVARIATIONS_TABLE_NAME = "workout_variations";
    public static final String WORKOUTVARIATIONS_COL_1 = "ID";
    public static final String WORKOUTVARIATIONS_COL_2 = "MAINWORKOUTID";
    public static final String WORKOUTVARIATIONS_COL_3 = "NAME";
    public static final String WORKOUTVARIATIONS_COL_4 = "SUBTYPE";
    //Workout variations table

    //Variation images table
    /*public static final String VARIATIONIMAGES_TABLE_NAME = "variation_images";
    public static final String VARIATIONIMAGES_COL_1 = "ID";
    public static final String VARIATIONIMAGES_COL_2 = "WORKOUTVARIATIONID";
    public static final String VARIATIONIMAGES_COL_3 = "VALUE";*/
    //Variation images table



    public static final String CREATE_TABLE_MYWORKOUTS = "CREATE TABLE " + MYWORKOUTS_TABLE_NAME +
            "(" + MYWORKOUTS_COL_1 + " INTEGER PRIMARY KEY," + MYWORKOUTS_COL_2 + " TEXT,"
            + MYWORKOUTS_COL_3 + " TEXT," + MYWORKOUTS_COL_4 + " INTEGER," + MYWORKOUTS_COL_5 + " INTEGER," +
            MYWORKOUTS_COL_6 + " TEXT," + MYWORKOUTS_COL_7 + " TEXT);";

    public static final String CREATE_TABLE_WORKOUTSCONNECTION = "CREATE TABLE " + WORKOUTSCONNECTION_TABLE_NAME +
            "(" + WORKOUTSCONNECTION_COL_1 + " INTEGER PRIMARY KEY," + WORKOUTSCONNECTION_COL_2 + " INTEGER,"
            + WORKOUTSCONNECTION_COL_3 + " INTEGER," + WORKOUTSCONNECTION_COL_4 + " INTEGER);";

    public static final String CREATE_TABLE_MAINWORKPUTS = "CREATE TABLE " + MAINWORKOUTS_TABLE_NAME +
            "(" + MAINWORKOUTS_COL_1 + " INTEGER PRIMARY KEY," + MAINWORKOUTS_COL_2 + " TEXT,"
            + MAINWORKOUTS_COL_3 + " INTEGER," + MAINWORKOUTS_COL_4 + " TEXT);";

    public static final String CREATE_TABLE_WORKOUTVARIATIONS = "CREATE TABLE " + WORKOUTVARIATIONS_TABLE_NAME +
            "(" + WORKOUTVARIATIONS_COL_1 + " INTEGER PRIMARY KEY," + WORKOUTVARIATIONS_COL_2 + " INTEGER,"
            + WORKOUTVARIATIONS_COL_3 + " TEXT," + WORKOUTVARIATIONS_COL_4 + " TEXT);";

    /*public static final String CREATE_TABLE_VARIATIONIMAGES = "CREATE TABLE " + VARIATIONIMAGES_TABLE_NAME +
            "(" + VARIATIONIMAGES_COL_1 + " INTEGER PRIMARY KEY," + VARIATIONIMAGES_COL_2 + " INTEGER," +
            VARIATIONIMAGES_COL_3 + " BLOB);";*/

    public DbHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DBVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MAINWORKPUTS);
        db.execSQL(CREATE_TABLE_WORKOUTVARIATIONS);
        db.execSQL(CREATE_TABLE_MYWORKOUTS);
        db.execSQL(CREATE_TABLE_WORKOUTSCONNECTION);
        //db.execSQL(CREATE_TABLE_VARIATIONIMAGES);
        populateMainWorkoutsTable(db);
        populateWorkoutVariationsTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MAINWORKOUTS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + WORKOUTVARIATIONS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MYWORKOUTS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + WORKOUTSCONNECTION_TABLE_NAME);
        //db.execSQL("DROP TABLE IF EXISTS " + VARIATIONIMAGES_TABLE_NAME);
        onCreate(db);
    }

    private void populateMainWorkoutsTable(SQLiteDatabase db){
        ContentValues values = new ContentValues();
        values.put(MAINWORKOUTS_COL_2, "Sklekovi");
        values.put(MAINWORKOUTS_COL_3, 3);
        values.put(MAINWORKOUTS_COL_4, "Prsa");
        db.insert(MAINWORKOUTS_TABLE_NAME,null,values);

        values = new ContentValues();
        values.put(MAINWORKOUTS_COL_2, "Zgibovi");
        values.put(MAINWORKOUTS_COL_3, 3);
        values.put(MAINWORKOUTS_COL_4, "Leđa");
        db.insert(MAINWORKOUTS_TABLE_NAME,null,values);


    }

    private void populateWorkoutVariationsTable(SQLiteDatabase db){
        ContentValues values = new ContentValues();
        values.put(WORKOUTVARIATIONS_COL_2, 1);
        values.put(WORKOUTVARIATIONS_COL_3, "Klasični sklekovi");
        values.put(WORKOUTVARIATIONS_COL_4, "Srednji dio prsa");
        db.insert(WORKOUTVARIATIONS_TABLE_NAME,null,values);

        values = new ContentValues();
        values.put(WORKOUTVARIATIONS_COL_2, 1);
        values.put(WORKOUTVARIATIONS_COL_3, "Uski sklekovi");
        values.put(WORKOUTVARIATIONS_COL_4, "Srednji dio prsa i triceps");
        db.insert(WORKOUTVARIATIONS_TABLE_NAME,null,values);

        values = new ContentValues();
        values.put(WORKOUTVARIATIONS_COL_2, 1);
        values.put(WORKOUTVARIATIONS_COL_3, "Široki sklekovi");
        values.put(WORKOUTVARIATIONS_COL_4, "Vanjski dio prsa");
        db.insert(WORKOUTVARIATIONS_TABLE_NAME,null,values);

        values = new ContentValues();
        values.put(WORKOUTVARIATIONS_COL_2, 2);
        values.put(WORKOUTVARIATIONS_COL_3, "Široki zgibovi");
        values.put(WORKOUTVARIATIONS_COL_4, "Vanjski gornji dio leđa i ramena");
        db.insert(WORKOUTVARIATIONS_TABLE_NAME,null,values);

        values = new ContentValues();
        values.put(WORKOUTVARIATIONS_COL_2, 2);
        values.put(WORKOUTVARIATIONS_COL_3, "Uski zgibovi");
        values.put(WORKOUTVARIATIONS_COL_4, "Biceps i unutrašnji gornji dio leđa");
        db.insert(WORKOUTVARIATIONS_TABLE_NAME,null,values);

        values = new ContentValues();
        values.put(WORKOUTVARIATIONS_COL_2, 2);
        values.put(WORKOUTVARIATIONS_COL_3, "Klasični zgibovi");
        values.put(WORKOUTVARIATIONS_COL_4, "Ramena i gornji srednji dio leđa");
        db.insert(WORKOUTVARIATIONS_TABLE_NAME,null,values);

    }

    public ArrayList<MainWorkout> getMainWorkouts() {
        ArrayList<MainWorkout> mainWorkouts = new ArrayList<MainWorkout>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + MAINWORKOUTS_TABLE_NAME + ";";
        MainWorkout mainWorkout = null;
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()) {
            do {
                mainWorkout = new MainWorkout(cursor.getInt(0),cursor.getString(1), cursor.getInt(2), cursor.getString(3));
                mainWorkouts.add(mainWorkout);
            }while (cursor.moveToNext());
        }
        db.close();
        return mainWorkouts;
    }

    public MainWorkout getMainWorkout(int id) {
        MainWorkout mainWorkout = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + MAINWORKOUTS_TABLE_NAME + " WHERE " + MAINWORKOUTS_COL_1 + "=" + id +";";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()) {
            do {
                mainWorkout = new MainWorkout(cursor.getInt(0),cursor.getString(1), cursor.getInt(2), cursor.getString(3));
            }while (cursor.moveToNext());
        }
        db.close();
        return mainWorkout;
    }

    public MainWorkout getMainWorkout(String name) {
        MainWorkout mainWorkout = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + MAINWORKOUTS_TABLE_NAME + " WHERE " + MAINWORKOUTS_COL_2 + "=" + "'" + name + "'";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()) {
            do {
                mainWorkout = new MainWorkout(cursor.getInt(0),cursor.getString(1), cursor.getInt(2), cursor.getString(3));
            }while (cursor.moveToNext());
        }
        db.close();
        return mainWorkout;
    }

    public ArrayList<WorkoutVariation> getWorkoutVariationsByMainWorkout(int mainWorkoutId) {
        ArrayList<WorkoutVariation> workoutVariations = new ArrayList<WorkoutVariation>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + WORKOUTVARIATIONS_TABLE_NAME + " WHERE " +
                WORKOUTVARIATIONS_COL_2 + "=" + mainWorkoutId + ";";
        WorkoutVariation workoutVariation = null;
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()) {
            do {
                workoutVariation = new WorkoutVariation(cursor.getInt(0),cursor.getInt(1), cursor.getString(2), cursor.getString(3));
                workoutVariations.add(workoutVariation);
            }while (cursor.moveToNext());
        }
        db.close();
        return workoutVariations;
    }


    public WorkoutVariation getWorkoutVariation(int id){
        WorkoutVariation workoutVariation = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + WORKOUTVARIATIONS_TABLE_NAME + " WHERE " + WORKOUTVARIATIONS_COL_1 + "=" + id +";";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()) {
            do {
                workoutVariation = new WorkoutVariation(cursor.getInt(0),cursor.getInt(1), cursor.getString(2), cursor.getString(3));
            }while (cursor.moveToNext());
        }
        db.close();
        return workoutVariation;
    }

    public void insertNewWorkoutConnection(int myWorkoutId, int mainWorkoutId, int workoutVariationId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "INSERT INTO " + WORKOUTSCONNECTION_TABLE_NAME + "(" + WORKOUTSCONNECTION_COL_2
                + "," + WORKOUTSCONNECTION_COL_3 + "," + WORKOUTSCONNECTION_COL_4 + ") VALUES("
                + myWorkoutId + "," + mainWorkoutId + "," + workoutVariationId + ")";
        Log.d("SQL", sql);
        /*ContentValues values = new ContentValues();
        values.put(WORKOUTSCONNECTION_COL_2, myWorkoutId);
        values.put(WORKOUTSCONNECTION_COL_3, mainWorkoutId);
        values.put(WORKOUTSCONNECTION_COL_4, workoutVariationId);
        db.insert(MAINWORKOUTS_TABLE_NAME, null, values);*/
        db.execSQL(sql);
        db.close();
        Log.d(WORKOUTSCONNECTION_TABLE_NAME, "New row inserted.");
    }

    public void insertNewMyWorkout(MyWorkout myWorkout) {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(MYWORKOUTS_TABLE_NAME, CREATE_TABLE_MYWORKOUTS);
        ContentValues values = new ContentValues();
        values.put(MYWORKOUTS_COL_2, myWorkout.getStart());
        values.put(MYWORKOUTS_COL_3, myWorkout.getEnd());
        values.put(MYWORKOUTS_COL_4, myWorkout.getNumberOfExercises());
        values.put(MYWORKOUTS_COL_5, myWorkout.isFinished());
        values.put(MYWORKOUTS_COL_6, myWorkout.getDuration());
        values.put(MYWORKOUTS_COL_7, myWorkout.getPlace());
        db.insert(MYWORKOUTS_TABLE_NAME,null,values);
        db.close();

        Log.d(MYWORKOUTS_TABLE_NAME, "New row inserted.");
    }

    public int getLastMyWorkoutId() {
        int id = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + MYWORKOUTS_TABLE_NAME + " ORDER BY ID DESC LIMIT 1";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()) {
            do {
                id = cursor.getInt(0);
            }
            while (cursor.moveToNext());
        }
        db.close();
        return id;
    }


    public ArrayList<MyWorkout> getMyWorkouts() {
        ArrayList<MyWorkout> myWorkouts = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + MYWORKOUTS_TABLE_NAME + " ORDER BY ID";
        MyWorkout myWorkout = null;

        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()) {
            do {
                myWorkout = new MyWorkout(cursor.getInt(0),cursor.getString(1),
                        cursor.getString(2), cursor.getInt(3),
                        cursor.getInt(4), cursor.getString(5), cursor.getString(6));
                myWorkouts.add(myWorkout);
            }while (cursor.moveToNext());
        }
        db.close();
        return myWorkouts;
    }

    public MyWorkout getMyWorkout(int id){
        MyWorkout myWorkout = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + MYWORKOUTS_TABLE_NAME + " WHERE " + MYWORKOUTS_COL_1 + "=" + id +";";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()) {
            do {
                myWorkout = new MyWorkout(cursor.getInt(0),cursor.getString(1),
                        cursor.getString(2), cursor.getInt(3),
                        cursor.getInt(4), cursor.getString(5), cursor.getString(6));
            }while (cursor.moveToNext());
        }
        db.close();
        return myWorkout;
    }

    public void updateMyWorkout(int id, int isFinished, String end, String duration){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MYWORKOUTS_COL_5, isFinished);
        cv.put(MYWORKOUTS_COL_3, end);
        cv.put(MYWORKOUTS_COL_6, duration);
        db.update(MYWORKOUTS_TABLE_NAME, cv, MYWORKOUTS_COL_1 + "=" + id, null);

        db.close();
    }

    public ArrayList<WorkoutConnectionHelpModel> getWorkoutsConnection(int myWorkoutsId){
        ArrayList<WorkoutConnectionHelpModel> workoutConnections = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + WORKOUTSCONNECTION_TABLE_NAME +
                " WHERE " + WORKOUTSCONNECTION_COL_2 + " = " + myWorkoutsId + " ORDER BY " + WORKOUTSCONNECTION_COL_1 + ";";
        WorkoutConnectionHelpModel workoutConnectionHelpModel = null;

        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()) {
            do {
                workoutConnectionHelpModel = new WorkoutConnectionHelpModel(cursor.getInt(0),cursor.getInt(1),
                        cursor.getInt(2), cursor.getInt(3));
                workoutConnections.add(workoutConnectionHelpModel);
            }while (cursor.moveToNext());
        }
        db.close();
        return workoutConnections;
    }
}
