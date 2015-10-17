package com.kylehebert.criminalintent.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.kylehebert.criminalintent.database.CrimeDbSchema.CrimeTable;

/**
 * Created by kylehebert on 10/15/15. This class will set up a new
 * db if needed, or update the db if one exists
 */
public class CrimeDbHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "crimeBase.db";

    public CrimeDbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        //define the database table
        database.execSQL("create table " + CrimeTable.NAME +"(" +
        " _id integer primary key autoincrement, " +
        CrimeTable.Columns.UUID + ", " +
        CrimeTable.Columns.TITLE + ", " +
        CrimeTable.Columns.DATE + ", " +
        CrimeTable.Columns.SOLVED + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {

    }


}
