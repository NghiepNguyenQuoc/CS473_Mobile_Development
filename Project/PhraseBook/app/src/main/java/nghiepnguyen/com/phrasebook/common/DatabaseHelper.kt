package nghiepnguyen.com.phrasebook.common

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import nghiepnguyen.com.phrasebook.model.Category
import nghiepnguyen.com.phrasebook.model.Phrase
import java.util.*


class DatabaseHelper(private val mContext: Context, private val dbName: String) : SQLiteOpenHelper(mContext, dbName, null, DATABASE_VERSION) {
    private var mDatabase: SQLiteDatabase? = null

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {

    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {

    }

    private fun openDatabase() {
        val dbPath = mContext.getDatabasePath(dbName).path
        if (mDatabase != null && mDatabase!!.isOpen) {
            return
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE)
    }

    private fun closeDatabase() {
        if (mDatabase != null)
            mDatabase!!.close()
    }


    fun getAllCategory(): ArrayList<Category> {
        var lstCategory = ArrayList<Category>()
        try {
            val strSQL = "SELECT *" + " FROM category"
            openDatabase()
            val cursor = mDatabase!!.rawQuery(strSQL, null)
            run {
                while (cursor.moveToNext()) {
                    val category = castDataToCategory(cursor) as Category
                    lstCategory.add(category)
                }
            }
            closeDatabase()
        } catch (ex: Exception) {
            lstCategory = ArrayList()
        }

        return lstCategory
    }

    fun getAllPhraseOfCategory(value: Int): ArrayList<Phrase> {
        var lstPhrase = ArrayList<Phrase>()
        try {
            val strSQL = "SELECT * FROM PHRASE WHERE IDCATEGORY=$value"
            openDatabase()
            val cursor = mDatabase!!.rawQuery(strSQL, null)
            run {
                while (cursor.moveToNext()) {
                    val phrase = castDataToPhrase(cursor) as Phrase
                    lstPhrase.add(phrase)
                }
            }
            closeDatabase()
        } catch (ex: Exception) {
            lstPhrase = ArrayList()
        }

        return lstPhrase
    }

    fun getAllPhraseOfCategoryByName(value: Int, query: String): ArrayList<Phrase> {
        var lstPhrase = ArrayList<Phrase>()
        try {
            val strSQL = ("SELECT *" + " FROM PHRASE WHERE IDCATEGORY="
                    + value + " AND PHRASE LIKE '%" + query + "%'")
            openDatabase()
            val cursor = mDatabase!!.rawQuery(strSQL, null)
            run {
                while (cursor.moveToNext()) {
                    val phrase = castDataToPhrase(cursor) as Phrase
                    lstPhrase.add(phrase)
                }
            }
            closeDatabase()
        } catch (ex: Exception) {
            lstPhrase = ArrayList()
        }

        return lstPhrase
    }

    fun getAllPhraseFavoriteOfCategory(): ArrayList<Phrase> {
        var lstPhrase = ArrayList<Phrase>()
        try {
            val strSQL = "SELECT *" + " FROM PHRASE WHERE NUMBER=1"
            openDatabase()
            val cursor = mDatabase!!.rawQuery(strSQL, null)
            run {
                while (cursor.moveToNext()) {
                    val phrase = castDataToPhrase(cursor) as Phrase
                    lstPhrase.add(phrase)
                }
            }
            closeDatabase()
        } catch (ex: Exception) {
            lstPhrase = ArrayList()
        }

        return lstPhrase
    }

    fun updatePhrase(number: Int, id: Int) {
        try {
            val phare = ContentValues()
            phare.put(KEY_NUMBER, number)
            openDatabase()
            mDatabase!!.update("PHRASE", phare, "ID=$id", null)
            closeDatabase()
        } catch (ex: Exception) {
        }

    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val KEY_NUMBER = "NUMBER"

        private fun castDataToCategory(dr: Cursor): Any {
            val category = Category()
            if (!dr.isNull(0)) {
                category.id = dr.getInt(0)
            }
            if (!dr.isNull(1)) {
                category.namecategory = dr.getString(1)
            }
            if (!dr.isNull(2)) {
                category.img = dr.getString(2)
            }
            if (!dr.isNull(3)) {
                category.lock = dr.getInt(3)
            }
            if (!dr.isNull(4)) {
                category.description = dr.getString(4)
            }
            return category
        }

        private fun castDataToPhrase(dr: Cursor): Any {
            val phrase = Phrase()
            if (!dr.isNull(0)) {
                phrase.id = dr.getInt(0)
            }
            if (!dr.isNull(1)) {
                phrase.idCategory = dr.getInt(1)
            }
            if (!dr.isNull(2)) {
                phrase.phrase = dr.getString(2)
            }
            if (!dr.isNull(3)) {
                phrase.description1 = dr.getString(3)
            }
            if (!dr.isNull(4)) {
                phrase.description2 = dr.getString(4)
            }
            if (!dr.isNull(5)) {
                phrase.description3 = dr.getString(5)
            }
            if (!dr.isNull(6)) {
                phrase.pronunciation = dr.getString(6)
            }
            if (!dr.isNull(7)) {
                phrase.description4 = dr.getString(7)
            }
            if (!dr.isNull(8)) {
                phrase.mean = dr.getString(8)
            }
            if (!dr.isNull(9)) {
                phrase.description5 = dr.getString(9)
            }
            if (!dr.isNull(10)) {
                phrase.sound = dr.getString(10)
            }
            if (!dr.isNull(11)) {
                phrase.number = dr.getInt(11)
            }
            return phrase
        }
    }

}
