package nghiepnguyen.com.phrasebook.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "phrasebookVN.sqlite";
    public static final String DBLOCATION = "/data/data/nghiepnguyen.com.phrasebook/databases/";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase mDatabase;
    private Context mContext;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void openDatabase() {
        String dbPath = mContext.getDatabasePath(DATABASE_NAME).getPath();
        if (mDatabase != null && mDatabase.isOpen()) {
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase() {
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
                    Category category = (Category) getDataRowFromDataReader(cursor);
                    lstCategory.add(category);
                }
            }
            closeDatabase();
        } catch (Exception ex) {
            lstCategory = new ArrayList<Category>();
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
                    category = (Category) getDataRowFromDataReader(cursor);
                }
            }
            closeDatabase();
        } catch (Exception ex) {
            return null;
        }
        return category;
    }

    protected static Object getDataRowFromDataReader(Cursor dr) {
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
}
