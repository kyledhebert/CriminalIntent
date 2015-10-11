package com.kylehebert.criminalintent;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by kylehebert on 9/20/15.
 */
public class Crime {

    private UUID mID;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    //vars for saving Crime objects in JSON format
    private static final String JSON_ID = "id";
    private static final String JSON_TITLE = "title";
    private static final String JSON_SOLVED = "solved";
    private static final String JSON_DATE = "date";


    public Crime(){
        //Generate a unique identified
        mID = UUID.randomUUID();
        mDate = new Date();

    }

    //load crimes from the filesystem when the app is launched
    public Crime (JSONObject jsonObject) throws JSONException {
        mID = UUID.fromString(jsonObject.getString(JSON_ID));
        if (jsonObject.has(JSON_TITLE)) {
            mTitle = jsonObject.getString(JSON_TITLE);
        }
        mSolved = jsonObject.getBoolean(JSON_SOLVED);
        mDate =  new Date(jsonObject.getLong(JSON_DATE));
    }

    //convert the data in a Crime object to JSON format
    public JSONObject toJSON() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(JSON_ID, mID.toString());
        jsonObject.put(JSON_TITLE, mTitle);
        jsonObject.put(JSON_SOLVED, mSolved);
        jsonObject.put(JSON_DATE, mDate.getTime());
        return jsonObject;
    }

    public UUID getID() {
        return mID;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }




}
