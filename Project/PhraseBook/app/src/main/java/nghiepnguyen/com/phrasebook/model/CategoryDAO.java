package nghiepnguyen.com.phrasebook.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import java.util.ArrayList;

public class CategoryDAO extends DatabaseHelper {
	public CategoryDAO(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	// @Override
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

	public ArrayList<Category> GetAllCategory() {
		ArrayList<Category> lstCategory = new ArrayList<Category>();
		try {
			String strSQL = "SELECT *" + " FROM Category";
			Cursor cursor = DatabaseHelper.mDatabase.rawQuery(strSQL, null);
			{
				while (cursor.moveToNext()) {
					Category category = (Category) getDataRowFromDataReader(cursor);
					lstCategory.add(category);
				}
			}
		} catch (Exception ex) {
			lstCategory = new ArrayList<Category>();
		}
		return lstCategory;
	}
	
	public Category getNameById(int id) {
		Category category=new Category();
		try {
			String strSQL = "SELECT *" + " FROM Category Where Id="+id;
			Cursor cursor = DatabaseHelper.mDatabase.rawQuery(strSQL, null);
			{
				while (cursor.moveToNext()) {
					category = (Category) getDataRowFromDataReader(cursor);
				}
			}
		} catch (Exception ex) {
			return null;
		}
		return category;
	}
}
