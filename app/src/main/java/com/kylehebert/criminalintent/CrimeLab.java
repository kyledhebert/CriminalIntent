package com.kylehebert.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.kylehebert.CrimeDbHelper;
import com.kylehebert.CrimeDbSchema;
import com.kylehebert.CrimeDbSchema.CrimeTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by kylehebert on 9/21/15.
 */
public class CrimeLab {

    private static CrimeLab sCrimeLab;

    public void addCrime(Crime crime) {
    }

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static CrimeLab get (Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new CrimeDbHelper(mContext).getWritableDatabase();
    }

    public List<Crime> getCrimes(){
        return null;
    }

    public Crime getCrime(UUID id) {
        return null;
    }

    /*
    creates a ContentValue instance from a Crime instance
     */
    private static ContentValues getContentValues(Crime crime) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CrimeTable.Columns.UUID, crime.getID().toString());
        contentValues.put(CrimeTable.Columns.TITLE, crime.getTitle());
        contentValues.put(CrimeTable.Columns.DATE, crime.getDate().getTime());
        contentValues.put(CrimeTable.Columns.SOLVED, crime.isSolved() ? 1:0);

        return contentValues;
    }


}
