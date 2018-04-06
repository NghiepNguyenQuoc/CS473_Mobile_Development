package nghiepnguyen.com.phrasebook.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class PhraseDAO {
    public static final String KEY_NUMBER = "NUMBER";
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static PhraseDAO instance;

    public PhraseDAO(Context context) {
        this.openHelper = new DatabaseHelper(context);
    }

    public static PhraseDAO getInstance(Context context) {
        if (instance == null) {
            instance = new PhraseDAO(context);
        }
        return instance;
    }

    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    // @Override
    protected static Object getDataRowFromDataReader(Cursor dr) {
        Phrase phrase = new Phrase();
        if (!dr.isNull(0)) {
            phrase.setId(dr.getInt(0));
        }
        if (!dr.isNull(1)) {
            phrase.setIdCategory(dr.getInt(1));
        }
        if (!dr.isNull(2)) {
            phrase.setPhrase(dr.getString(2));
        }
        if (!dr.isNull(3)) {
            phrase.setDescription1(dr.getString(3));
        }
        if (!dr.isNull(4)) {
            phrase.setDescription2(dr.getString(4));
        }
        if (!dr.isNull(5)) {
            phrase.setDescription3(dr.getString(5));
        }
        if (!dr.isNull(6)) {
            phrase.setPronunciation(dr.getString(6));
        }
        if (!dr.isNull(7)) {
            phrase.setDescription4(dr.getString(7));
        }
        if (!dr.isNull(8)) {
            phrase.setMean(dr.getString(8));
        }
        if (!dr.isNull(9)) {
            phrase.setDescription5(dr.getString(9));
        }
        if (!dr.isNull(10)) {
            phrase.setSound(dr.getString(10));
        }
        if (!dr.isNull(11)) {
            phrase.setNumber(dr.getInt(11));
        }
        return phrase;
    }

    public ArrayList<Phrase> GetAllPhraseOfCategory(int value) {
        ArrayList<Phrase> lstPhrase = new ArrayList<Phrase>();
        try {
            String strSQL = "SELECT *" + " FROM PHRASE WHERE IDCATEGORY="
                    + value;
            Cursor cursor = database.rawQuery(strSQL, null);
            {
                while (cursor.moveToNext()) {
                    Phrase phrase = (Phrase) getDataRowFromDataReader(cursor);
                    lstPhrase.add(phrase);
                }
            }
        } catch (Exception ex) {
            lstPhrase = new ArrayList<Phrase>();
        }
        return lstPhrase;
    }

    public ArrayList<Phrase> GetAllPhraseOfCategoryByName(int value, String query) {
        ArrayList<Phrase> lstPhrase = new ArrayList<Phrase>();
        try {
            String strSQL = "SELECT *" + " FROM PHRASE WHERE IDCATEGORY="
                    + value + " AND PHRASE LIKE '%" + query + "%'";
            Cursor cursor = database.rawQuery(strSQL, null);
            {
                while (cursor.moveToNext()) {
                    Phrase phrase = (Phrase) getDataRowFromDataReader(cursor);
                    lstPhrase.add(phrase);
                }
            }
        } catch (Exception ex) {
            lstPhrase = new ArrayList<Phrase>();
        }
        return lstPhrase;
    }

    public ArrayList<Phrase> GetAllPhraseFavoriteOfCategory() {
        ArrayList<Phrase> lstPhrase = new ArrayList<Phrase>();
        try {
            String strSQL = "SELECT *" + " FROM PHRASE WHERE NUMBER=1";
            Cursor cursor = database.rawQuery(strSQL, null);
            {
                while (cursor.moveToNext()) {
                    Phrase phrase = (Phrase) getDataRowFromDataReader(cursor);
                    lstPhrase.add(phrase);
                }
            }
        } catch (Exception ex) {
            lstPhrase = new ArrayList<Phrase>();
        }
        return lstPhrase;
    }

    public void UpdatePhrase(int number, int id) {
        try {
            /*String strSQL="Update Phrase set number="+number+" Where id="+id;
            Log.d("THONG BAO", strSQL);
			DatabaseHelper.mDatabase.rawQuery(strSQL, null);*/
            ContentValues phare = new ContentValues();
            phare.put(KEY_NUMBER, number);
            Log.d("THONG BAO", phare + "");
            database.update("PHRASE", phare, "ID=" + id, null);
        } catch (Exception ex) {
        }
    }
}
