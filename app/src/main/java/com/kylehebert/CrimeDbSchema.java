package com.kylehebert;

/**
 * Created by kylehebert on 10/15/15. This class will define the database
 * schema for the app.
 */
public class CrimeDbSchema {

    public static final class CrimeTable {
        public static final String NAME = "crimes";

        public static final class Columns {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String SOLVED = "solved";

        }

    }

}
