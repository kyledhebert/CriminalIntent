package com.kylehebert.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.kylehebert.criminalintent.database.CrimeCursorWrapper;
import com.kylehebert.criminalintent.database.CrimeDbSchema.CrimeTable;
import com.kylehebert.criminalintent.database.CrimeDbHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by kylehebert on 9/21/15.
 */
public class CrimeLab {

    private static CrimeLab sCrimeLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static CrimeLab get (Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    public void addCrime(Crime crime) {
        ContentValues values = getContentValues(crime);

        //inserting rows into the database
        mDatabase.insert(CrimeTable.NAME, null, values);
    }

    private CrimeLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new CrimeDbHelper(mContext).getWritableDatabase();
    }

    public List<Crime> getCrimes(){
        List<Crime> crimes = new ArrayList<>();

        CrimeCursorWrapper cursor = queryCrimes(null, null); //no args, retrieve all

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) { //until cursor is at the end of the dataset
                crimes.add(cursor.getCrime());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return crimes;
    }

    public Crime getCrime(UUID id) {
        CrimeCursorWrapper cursor = queryCrimes(
                CrimeTable.Columns.UUID + " = ?",
                new String[] { id.toString() }
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getCrime();
        } finally {
            cursor.close();
        }

    }

    public void updateCrime(Crime crime) {
        String uuidString = crime.getID().toString();
        ContentValues values = getContentValues(crime);

        //update the rows in the database WHERE UUID = uuidString
        mDatabase.update(CrimeTable.NAME, values,
                CrimeTable.Columns.UUID + " = ?", //? prevents sql injection
                new String[] {uuidString});
    }

    /*
    creates a ContentValue instance from a Crime instance
     */
    private static ContentValues getContentValues(Crime crime) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CrimeTable.Columns.UUID, crime.getID().toString());
        contentValues.put(CrimeTable.Columns.TITLE, crime.getTitle());
        contentValues.put(CrimeTable.Columns.DATE, crime.getDate().getTime());
        contentValues.put(CrimeTable.Columns.SOLVED, crime.isSolved() ? 1 : 0);

        return contentValues;
    }

    /*
    use CrimeCursorWrapper to read from the database, getCrimes will walk the cursor
    to populate the Crime list
     */
    private CrimeCursorWrapper queryCrimes(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                CrimeTable.NAME,
                null, //Columns - null selects all columns
                whereClause,
                whereArgs,
                null, //groupBy
                null, //having
                null //orderBy
        );

        return new CrimeCursorWrapper(cursor);
    }


}
