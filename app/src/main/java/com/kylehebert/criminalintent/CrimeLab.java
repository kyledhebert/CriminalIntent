package com.kylehebert.criminalintent;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by kylehebert on 9/21/15.
 */
public class CrimeLab {

    private static final String TAG = "CrimeLab";
    private static final String FILENAME = "crimes.json";

    private ArrayList<Crime> mCrimes;
    private CriminalIntentJSONSerializer mJSONSerializer;

    private static CrimeLab sCrimeLab;
    private Context mAppContext;

    public static CrimeLab get (Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context){
        mAppContext = context;
        mJSONSerializer = new CriminalIntentJSONSerializer(mAppContext,FILENAME);

        /*
        CrimeLab will now check for existing data and load the data
        into an ArrayLost instead of always creating an empty ArrayList
         */
        try {
            mCrimes = mJSONSerializer.loadCrimes();
        } catch (Exception e) {
            mCrimes = new ArrayList<Crime>();
            Log.e(TAG, "Error loading crimes", e);
        }


    }

    public void addCrime(Crime crime) {
        mCrimes.add(crime);
    }

    /*
    try to serialize the list of crimes and
    indicate success or failure with a boolean
     */
    public boolean saveCrimes() {
        try {
            mJSONSerializer.saveCrimes(mCrimes);
            Log.d(TAG, "crimes saved to file");
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error saving crimes: ", e);
            return false;
        }
    }

    public List<Crime> getCrimes(){
        return mCrimes;
    }

    public Crime getCrime(UUID id) {
        for (Crime crime : mCrimes) {
            if (crime.getID().equals(id)) {
                return crime;
            }
        }
        return null;
    }


}
