package nghiepnguyen.com.phrasebook.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String KEY_NUMBER = "NUMBER";
    private SQLiteDatabase mDatabase;
    private Context mContext;
    private String databaseName;

    public DatabaseHelper(Context context, String databaseName) {
        super(context, databaseName, null, DATABASE_VERSION);
        mContext = context;
        this.databaseName = databaseName;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    private void openDatabase() {
        String dbPath = mContext.getDatabasePath(databaseName).getPath();
        if (mDatabase != null && mDatabase.isOpen()) {
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    private void closeDatabase() {
        if (mDatabase != null)
            mDatabase.close();
    }


    public ArrayList<Category> GetAllCategory() {
        ArrayList<Category> lstCategory = new ArrayList<Category>();
        try {
            String strSQL = "SELECT *" + " FROM category";
            openDatabase();
            Cursor cursor = mDatabase.rawQuery(strSQL, null);
            {
                while (cursor.moveToNext()) {
                    Category category = (Category) castDataToCategory(cursor);
                    lstCategory.add(category);
                }
            }
            closeDatabase();
        } catch (Exception ex) {
            lstCategory = new ArrayList<>();
        }
        return lstCategory;
    }

    public Category getNameById(int id) {
        Category category = new Category();
        try {
            String strSQL = "SELECT *" + " FROM category Where Id=" + id;
            openDatabase();
            Cursor cursor = mDatabase.rawQuery(strSQL, null);
            {
                while (cursor.moveToNext()) {
                    category = (Category) castDataToCategory(cursor);
                }
            }
            closeDatabase();
        } catch (Exception ex) {
            return null;
        }
        return category;
    }

    public ArrayList<Phrase> GetAllPhraseOfCategory(int value) {
        ArrayList<Phrase> lstPhrase = new ArrayList<>();
        try {
            String strSQL = "SELECT *" + " FROM PHRASE WHERE IDCATEGORY=" + value;
            openDatabase();
            Cursor cursor = mDatabase.rawQuery(strSQL, null);
            {
                while (cursor.moveToNext()) {
                    Phrase phrase = (Phrase) castDataToPhrase(cursor);
                    lstPhrase.add(phrase);
                }
            }
            closeDatabase();
        } catch (Exception ex) {
            lstPhrase = new ArrayList<>();
        }
        return lstPhrase;
    }

    public ArrayList<Phrase> GetAllPhraseOfCategoryByName(int value, String query) {
        ArrayList<Phrase> lstPhrase = new ArrayList<Phrase>();
        try {
            String strSQL = "SELECT *" + " FROM PHRASE WHERE IDCATEGORY="
                    + value + " AND PHRASE LIKE '%" + query + "%'";
            openDatabase();
            Cursor cursor = mDatabase.rawQuery(strSQL, null);
            {
                while (cursor.moveToNext()) {
                    Phrase phrase = (Phrase) castDataToPhrase(cursor);
                    lstPhrase.add(phrase);
                }
            }
            closeDatabase();
        } catch (Exception ex) {
            lstPhrase = new ArrayList<>();
        }
        return lstPhrase;
    }

    public ArrayList<Phrase> GetAllPhraseFavoriteOfCategory() {
        ArrayList<Phrase> lstPhrase = new ArrayList<Phrase>();
        try {
            String strSQL = "SELECT *" + " FROM PHRASE WHERE NUMBER=1";
            openDatabase();
            Cursor cursor = mDatabase.rawQuery(strSQL, null);
            {
                while (cursor.moveToNext()) {
                    Phrase phrase = (Phrase) castDataToPhrase(cursor);
                    lstPhrase.add(phrase);
                }
            }
            closeDatabase();
        } catch (Exception ex) {
            lstPhrase = new ArrayList<>();
        }
        return lstPhrase;
    }

    public void UpdatePhrase(int number, int id) {
        try {
            ContentValues phare = new ContentValues();
            phare.put(KEY_NUMBER, number);
            openDatabase();
            mDatabase.update("PHRASE", phare, "ID=" + id, null);
            closeDatabase();
        } catch (Exception ex) {
        }
    }

    private static Object castDataToCategory(Cursor dr) {
        Category category = new Category();
        if (!dr.isNull(0)) {
            category.setId(dr.getInt(0));
        }
        if (!dr.isNull(1)) {
            category.setNamecategory(dr.getString(1));
        }
        if (!dr.isNull(2)) {
            category.setImg(dr.getString(2));
        }
        if (!dr.isNull(3)) {
            category.setLock(dr.getInt(3));
        }
        if (!dr.isNull(4)) {
            category.setDescription(dr.getString(4));
        }
        return category;
    }

    private static Object castDataToPhrase(Cursor dr) {
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

}
