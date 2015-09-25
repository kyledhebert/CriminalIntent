package com.kylehebert.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class CrimeActivity extends SingleFragmentActivity {

    private static final String EXTRA_CRIME_ID = "com.kylehebert.criminalintent.crime_id";
    private static final String EXTRA_CRIME_POSITION = "com.kyledhebert.criminalintent.crime_position";

    /*
    This Intent method gets called by the CrimeHolder in CrimeListFragment when the user
    tap on a crime in the list.
     */
    public static Intent newIntent(Context packageContext, UUID crimeId){
        Intent intent = new Intent(packageContext, CrimeActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

    @Override
    protected Fragment createFragment(){
        UUID crimeID = (UUID)getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(crimeID);
    }


}
