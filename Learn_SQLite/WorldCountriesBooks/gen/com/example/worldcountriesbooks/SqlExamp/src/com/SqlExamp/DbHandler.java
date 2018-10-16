package com.SqlExamp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHandler extends SQLiteOpenHelper {
	static String DATABASE_NAME="art.db";
	String TABLE_NAME="test";
	String FIRSTN="FName";
	String LASTN="LName";
	String NO="Contact";
	String EMAIL="Email";
	

	public DbHandler(Context context) {
		super(context, DATABASE_NAME, null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase sd) {
		sd.execSQL("CREATE TABLE test(FName TEXT PRIMARY KEY,LName TEXT,Contact INTEGER,Email TEXT)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
