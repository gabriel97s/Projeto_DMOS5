package br.edu.ifsp.projeto_dmos5.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.projeto_dmos5.model.Elephant;


public class ElephantDAO {

    private final SQLiteHelper dbHelper;

    private Long currentTimeMilli;

    public ElephantDAO(Context context) {
        dbHelper = new SQLiteHelper(context);
        currentTimeMilli = System.currentTimeMillis();
    }

    public void add(Elephant elephant) {
        if (elephant == null) throw new NullPointerException();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(ElephantContractDAO.ElephantEntry.COLUMN_NAME_AFILLIATION, elephant.getAffiliation());
        values.put(ElephantContractDAO.ElephantEntry.COLUMN_NAME_DOB, elephant.getDob());
        values.put(ElephantContractDAO.ElephantEntry.COLUMN_NAME_DOD, elephant.getDod());
        values.put(ElephantContractDAO.ElephantEntry.COLUMN_NAME_FICTIONAL, elephant.getFictional());
        values.put(ElephantContractDAO.ElephantEntry.COLUMN_NAME_IMAGE, elephant.getImage());
        values.put(ElephantContractDAO.ElephantEntry.COLUMN_NAME_INDEX, elephant.getIndex());
        values.put(ElephantContractDAO.ElephantEntry.COLUMN_NAME_NAME, elephant.getName());
        values.put(ElephantContractDAO.ElephantEntry.COLUMN_NAME_NOTE, elephant.getNote());
        values.put(ElephantContractDAO.ElephantEntry.COLUMN_NAME_SEX, elephant.getSex());
        values.put(ElephantContractDAO.ElephantEntry.COLUMN_NAME_SPECIES, elephant.getSpecies());
        values.put(ElephantContractDAO.ElephantEntry.COLUMN_NAME_WIKILINK, elephant.getWikilink());
        values.put(ElephantContractDAO.ElephantEntry.COLUMN_NAME_CREATED_AT, currentTimeMilli);

        db.insert(ElephantContractDAO.ElephantEntry.TABLE_NAME, null, values);

        db.close();
    }

    public List<Elephant> findAll() {

        List<Elephant> elephants = new ArrayList<>(100);

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] columns = new String[]{
                ElephantContractDAO.ElephantEntry._ID,
                ElephantContractDAO.ElephantEntry.COLUMN_NAME_AFILLIATION,
                ElephantContractDAO.ElephantEntry.COLUMN_NAME_DOB,
                ElephantContractDAO.ElephantEntry.COLUMN_NAME_DOD,
                ElephantContractDAO.ElephantEntry.COLUMN_NAME_FICTIONAL,
                ElephantContractDAO.ElephantEntry.COLUMN_NAME_IMAGE,
                ElephantContractDAO.ElephantEntry.COLUMN_NAME_INDEX,
                ElephantContractDAO.ElephantEntry.COLUMN_NAME_NAME,
                ElephantContractDAO.ElephantEntry.COLUMN_NAME_NOTE,
                ElephantContractDAO.ElephantEntry.COLUMN_NAME_SEX,
                ElephantContractDAO.ElephantEntry.COLUMN_NAME_SPECIES,
                ElephantContractDAO.ElephantEntry.COLUMN_NAME_WIKILINK,
                ElephantContractDAO.ElephantEntry.COLUMN_NAME_CREATED_AT
        };

        Integer hours = 2, minutes = 60, seconds = 60, miliseconds = 1000;

        long currentTimeInMillis = System.currentTimeMillis();

        long beforeTimeInMillisTwoHours
                = currentTimeInMillis - (hours * minutes * seconds * miliseconds);

        /*
         * Na tabela de elefantes tem uma coluna chamada, CREATED_AT, nela eu salvo a data no
         * formato timestamp, introduzi uma lógica somente pra trazer os elefantes que estão
         * no banco a menos de 2 horas e 1 minuto.
         */

        String selection = ElephantContractDAO.ElephantEntry.COLUMN_NAME_CREATED_AT
                + " >= ?";
        String[] selectionArgs = {String.valueOf(beforeTimeInMillisTwoHours)};
        String sortOrder = ElephantContractDAO.ElephantEntry.COLUMN_NAME_INDEX + " ASC";

        Cursor cursor = db.query(
                ElephantContractDAO.ElephantEntry.TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );

        Elephant elephant;

        while (cursor.moveToNext()) {
            elephant = new Elephant(
                    cursor.getString(0)
                    , cursor.getString(1)
                    , cursor.getString(2)
                    , cursor.getString(3)
                    , cursor.getString(4)
                    , cursor.getString(5)
                    , cursor.getInt(6)
                    , cursor.getString(7)
                    , cursor.getString(8)
                    , cursor.getString(9)
                    , cursor.getString(10)
                    , cursor.getString(11)
            );

            elephants.add(elephant);
        }
        cursor.close();

        db.close();

        return elephants;
    }

    public void refreshTimeMilliBeforeInsert() {
        currentTimeMilli = System.currentTimeMillis();
    }
}
