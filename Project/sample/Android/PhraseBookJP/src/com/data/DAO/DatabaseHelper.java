package com.data.DAO;

import java.io.File;
import java.util.Locale;
import com.data.DTO.Category;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {
	Category category = new Category();

	private static final String DATABASE_NAME = "phrasebookNB.sqlite";
	private static final int DATABASE_VERSION = 2;
	static SQLiteDatabase mDatabase;
	Context mContext;
	public String dbPath = getPath() + "/" + DATABASE_NAME;

	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

	public void open() {
		try {
			mDatabase = SQLiteDatabase.openDatabase(dbPath, null,
					SQLiteDatabase.OPEN_READWRITE);
			mDatabase.setLocale(new Locale("vi"));

		} catch (SQLiteException ex) {
			ex.printStackTrace();
			Log.i("aaa", "There is no valid dictionary database " + " at path ");
		}
	}

	@Override
	public void close() {
		if (mDatabase != null)
			mDatabase.close();
		super.close();
	}

	public String getPath() {
		String state = Environment.getExternalStorageState();
		String myPath = "";

		if (Environment.MEDIA_MOUNTED.equals(state)) {
			File fsdcard = Environment.getExternalStorageDirectory();
			String sdcard = fsdcard.getAbsolutePath();
			myPath = sdcard + "/PhraseBook";
			File myfile = new File(myPath);

			if (myfile.exists()) {
			} else {
				String[] Dir = myPath.split(fsdcard.separator);
				for (int i = 3; i < Dir.length; i++) {
					sdcard = sdcard + File.separator + Dir[i];
					File dbDir = new File(sdcard);
					boolean b = dbDir.mkdir();
				}
			}
			return myPath;
		} else {
			Toast.makeText(mContext, "Khong co the nho", Toast.LENGTH_SHORT)
					.show();
			return myPath;
		}
	}
}
