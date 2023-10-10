package com.example.farmingapp.ApiData

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(private var context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "RegisterDataBase.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "register"
        private const val COLUMN_ID = "UserId"
        private const val COLUMN_AGE = "UserAGE"
        private const val COLUMN_FNAME = "UserFNAME"
        private const val COLUMN_LNAME = "UserLNAME"
        private const val COLUMN_PHONE = "UserPHONE"
        private const val COLUMN_TITLE = "UserTITLE"
        private const val COLUMN_PASSWORD = "UserPASSWORD"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = ("CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_FNAME TEXT," +
                "$COLUMN_LNAME TEXT," +
                "$COLUMN_AGE INTEGER," +
                "$COLUMN_TITLE TEXT," +
                "$COLUMN_PHONE INTEGER," +
                "$COLUMN_PASSWORD TEXT)")
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun insertUser(
        UserFNAME: String,
        UserLNAME: String,
        UserAGE: String,
        UserTITLE: String,
        UserPHONE: String,
        UserPASSWORD: String,
    ): Long {

        val values = ContentValues().apply {
            put(COLUMN_FNAME, UserFNAME)
            put(COLUMN_LNAME, UserLNAME)
            put(COLUMN_AGE, UserAGE)
            put(COLUMN_TITLE, UserTITLE)
            put(COLUMN_PHONE, UserPHONE)
            put(COLUMN_PASSWORD, UserPASSWORD)
        }
        val db = writableDatabase
        return db.insert(TABLE_NAME, null, values)
    }

    fun readUser(UserTITLE: String, UserPHONE: String, UserPASSWORD: String): Boolean {
        val db = readableDatabase
        val selection =
            "$COLUMN_TITLE = ? AND $COLUMN_PHONE = ? AND $COLUMN_PASSWORD = ?"
        val selectionArgs = arrayOf(UserTITLE, UserPHONE, UserPASSWORD)
        val cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null, null)

        val userExists = cursor.count > 0
        cursor.close()
        return userExists
    }


}
