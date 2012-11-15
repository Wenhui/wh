package com.example.easylife;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Database {
	
	public static final String KEY_ROWID = "_id";
	public static final String KEY_TITLE = "bill_title";
	public static final String KEY_PRICE = "bill_price";
	public static final String KEY_CATEGORY = "bill_category";
	public static final String KEY_STATUS = "bill_status";
	
	
	private static final String DATABASE_NAME = "billDB";
	private static final String DATABASE_TABLE = "billTabel";
	private static final int DATABASE_VERSION = 1;
	
	private DBHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	private static class DBHelper extends SQLiteOpenHelper{

		public DBHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" + 
					KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
					KEY_TITLE + " TEXT NOT NULL, " + 
					KEY_PRICE + " TEXT NOT NULL, " + 
					KEY_CATEGORY + " TEXT NOT NULL, " + 
					KEY_STATUS + " TEXT NOT NULL);"	
			);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}
		
	}
	
	public Database(Context c){
		ourContext = c;
	}
	
	public Database open() throws SQLException{
		ourHelper = new DBHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}
	
	public void close(){
		ourHelper.close();
	}

	public long createEntry(String title, double price, String category,
			boolean status) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(KEY_TITLE, title);
		cv.put(KEY_PRICE, price);
		cv.put(KEY_CATEGORY, category);
		cv.put(KEY_STATUS, status);
		return ourDatabase.insert(DATABASE_TABLE, null, cv);
		
		
	}
}
