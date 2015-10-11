package com.kylehebert.criminalintent;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by kylehebert on 10/9/15.
 */
public class CriminalIntentJSONSerializer {

    private Context mContext;
    private String mFilename;

    public CriminalIntentJSONSerializer(Context context, String filename) {
        mContext = context;
        mFilename = filename;
    }

    //loads crimes from the filesystem

    public ArrayList<Crime> loadCrimes() throws IOException, JSONException {
        ArrayList<Crime> crimes = new ArrayList<Crime>();
        BufferedReader bufferedReader = null;
        try {
            //open and read the file into a StringBuilder
            InputStream inputStream = mContext.openFileInput(mFilename);
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                //line breaks are omitted and irrelevant
                jsonString.append(line);
            }
            //parse the JSON using JSONTokener
            JSONArray array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();

            //build the array of crimes from JSONobjects
            for (int i = 0; i < array.length(); i++) {
                crimes.add(new Crime(array.getJSONObject(i)));
            }
        } catch (FileNotFoundException fnf) {
                //ignore this one; it happens when starting fresh
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
        return crimes;

    }



    public void saveCrimes(ArrayList<Crime> crimes)
            throws JSONException, IOException {
        //build and array in JSON
        JSONArray array = new JSONArray();
        for (Crime crime : crimes) {
            array.put(crime.toJSON());
        }

        //write the file to disk
        Writer writer = null;
        try {
            OutputStream outputStream = mContext.openFileOutput(mFilename, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(outputStream);
            writer.write(array.toString());
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
