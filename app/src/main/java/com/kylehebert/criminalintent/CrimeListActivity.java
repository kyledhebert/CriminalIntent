package com.kylehebert.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by kylehebert on 9/21/15.
 */
public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
