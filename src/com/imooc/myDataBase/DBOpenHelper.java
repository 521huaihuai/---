package com.imooc.myDataBase;

import com.imooc.myConstant.MyConstant;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBOpenHelper extends SQLiteOpenHelper
{

	private static final String DATABASE_NAME = "games.db";// Êý¾Ý¿âÃû³Æ

	public DBOpenHelper(Context context, int version)
	{
		super(context, DATABASE_NAME, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{

		String sql = "create table game (" + MyConstant.GAMEID + " INTEGER PRIMARY KEY," + MyConstant.RANKING + " INTEGER," + MyConstant.STAR + " INTEGER,"
				+ MyConstant.LOCK + " INTEGER," + MyConstant.CREATED + " TEXT" + ");";
		Log.i("521huaihuai", "" + sql);
		db.execSQL(sql);
		Log.i("521huaihuai", "" + sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		Log.i("521huaihuai", "onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) newVersion=" + newVersion);
		db.execSQL("DROP TABLE IF EXISTS diary");
		onCreate(db);
	}

}
