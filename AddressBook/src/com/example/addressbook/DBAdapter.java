package com.example.addressbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {

	private static final String TAG = "DBAdapter"; //used for logging database version changes
			
	// Field Names:
	public static final String KEY_ROWID = "_id";
	public static final String KEY_NAME = "name";
	public static final String KEY_STREET_ADDRESS = "street_address";
	public static final String KEY_CITY = "city";
	public static final String KEY_STATE = "state";
	public static final String KEY_ZIP = "zip";
	//public static final String KEY_NAME = "name";  //****
	
	public static final String[] ALL_KEYS = new String[] {KEY_ROWID, KEY_NAME, KEY_STREET_ADDRESS, KEY_CITY, KEY_STATE, KEY_ZIP}; //****
	
	// Column Numbers for each Field Name:
	public static final int COL_ROWID = 0;
	public static final int COL_NAME = 1;
	public static final int COL_STREET_ADDRESS = 2;
	public static final int COL_CITY = 3;
	public static final int COL_STATE = 4;
	public static final int COL_ZIP = 5;
	//public static final int COL_NAME = 3;  //****
	
	
	// DataBase info:
	public static final String DATABASE_NAME = "dbAddressBook";
	public static final String DATABASE_TABLE = "mainAddressBook";
	public static final int DATABASE_VERSION = 5; // The version number must be incremented each time a change to DB structure occurs.
		//**************************//**************************//**************************
		//**************************//**************************
		
		
	//SQL statement to create database
	private static final String DATABASE_CREATE_SQL = 
			"CREATE TABLE " + DATABASE_TABLE 
			+ " (" + KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ KEY_NAME + " TEXT NOT NULL, "
			+ KEY_STREET_ADDRESS + " TEXT, "
			+ KEY_CITY + " TEXT, "
			+ KEY_STATE + " TEXT, "
			+ KEY_ZIP + " INTEGER"//**********
			+ ");";
	
	private final Context context;
	private DatabaseHelper myDBHelper;
	private SQLiteDatabase db;


	public DBAdapter(Context ctx) {
		this.context = ctx;
		myDBHelper = new DatabaseHelper(context);
	}
	
	// Open the database connection.
	public DBAdapter open() {
		db = myDBHelper.getWritableDatabase();
		return this;
	}
	
	// Close the database connection.
	public void close() {
		myDBHelper.close();
	}
	
	// Add a new set of values to be inserted into the database.
	public long insertRow(String name, String street_address, String city, String state, String zip) {  //************** String name
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_NAME, name);
		initialValues.put(KEY_STREET_ADDRESS, street_address);
		initialValues.put(KEY_CITY, city);
		initialValues.put(KEY_STATE, state);
		initialValues.put(KEY_ZIP, zip);
		
		//*****************
				
		// Insert the data into the database.
		return db.insert(DATABASE_TABLE, null, initialValues);
	}
	
	// Delete a row from the database, by rowId (primary key)
	public boolean deleteRow(long rowId) {
		String where = KEY_ROWID + "=" + rowId;
		return db.delete(DATABASE_TABLE, where, null) != 0;
	}
	
	public void deleteAll() {
		Cursor c = getAllRows();
		long rowId = c.getColumnIndexOrThrow(KEY_ROWID);
		if (c.moveToFirst()) {
			do {
				deleteRow(c.getLong((int) rowId));				
			} while (c.moveToNext());
		}
		c.close();
	}
	
	// Return all data in the database.
	public Cursor getAllRows() {
		String where = null;
		Cursor c = 	db.query(true, DATABASE_TABLE, ALL_KEYS, where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Get a specific row (by rowId)
	public Cursor getRow(long rowId) {
		String where = KEY_ROWID + "=" + rowId;
		Cursor c = 	db.query(true, DATABASE_TABLE, ALL_KEYS, 
						where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}
	
	// Change an existing row to be equal to new data.
	public boolean updateRow(long rowId, String name, String street_address, String city, String state, String zip) {  //********
		String where = KEY_ROWID + "=" + rowId;
		ContentValues newValues = new ContentValues();
		newValues.put(KEY_NAME, name);
		newValues.put(KEY_STREET_ADDRESS, street_address);
		newValues.put(KEY_CITY, city);
		newValues.put(KEY_STATE, state);
		newValues.put(KEY_ZIP, zip);
		
		//************************************
		// Insert it into the database.
		return db.update(DATABASE_TABLE, newValues, where, null) != 0;
	}

	
	private static class DatabaseHelper extends SQLiteOpenHelper
	{
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase _db) {
			_db.execSQL(DATABASE_CREATE_SQL);			
		}

		@Override
		public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading application's database from version " + oldVersion
					+ " to " + newVersion + ", which will destroy all old data!");
			
			// Destroy old database:
			_db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			
			// Recreate new database:
			onCreate(_db);
		}
	}


}
