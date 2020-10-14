package br.edu.ifsp.projeto_dmos5.dao;

import android.provider.BaseColumns;

public final class ElephantContractDAO {

    private ElephantContractDAO() {
    }

    public static class ElephantEntry implements BaseColumns {
        public static final String TABLE_NAME = "elephant";
        public static final String COLUMN_NAME_AFILLIATION = "affiliation";
        public static final String COLUMN_NAME_DOB = "dob";
        public static final String COLUMN_NAME_DOD = "dod";
        public static final String COLUMN_NAME_FICTIONAL = "fictional";
        public static final String COLUMN_NAME_IMAGE = "image";
        public static final String COLUMN_NAME_INDEX = "_index";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_NOTE = "note";
        public static final String COLUMN_NAME_SEX = "sex";
        public static final String COLUMN_NAME_SPECIES = "species";
        public static final String COLUMN_NAME_WIKILINK = "wikilink";
        public static final String COLUMN_NAME_CREATED_AT = "created_at";
    }

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ElephantEntry.TABLE_NAME + "("
                    + ElephantEntry._ID + " INTEGER PRIMARY KEY,"
                    + ElephantEntry.COLUMN_NAME_AFILLIATION + " TEXT,"
                    + ElephantEntry.COLUMN_NAME_DOB + " INTEGER,"
                    + ElephantEntry.COLUMN_NAME_DOD + " INTEGER,"
                    + ElephantEntry.COLUMN_NAME_FICTIONAL + " TEXT,"
                    + ElephantEntry.COLUMN_NAME_IMAGE + " TEXT,"
                    + ElephantEntry.COLUMN_NAME_INDEX + " INTEGER,"
                    + ElephantEntry.COLUMN_NAME_NAME + " TEXT,"
                    + ElephantEntry.COLUMN_NAME_NOTE + " TEXT,"
                    + ElephantEntry.COLUMN_NAME_SEX + " TEXT,"
                    + ElephantEntry.COLUMN_NAME_SPECIES + " TEXT,"
                    + ElephantEntry.COLUMN_NAME_WIKILINK + " TEXT,"
                    + ElephantEntry.COLUMN_NAME_CREATED_AT + " INTEGER);";
}
