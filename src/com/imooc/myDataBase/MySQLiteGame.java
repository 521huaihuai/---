package com.imooc.myDataBase;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class MySQLiteGame
{

	private DBOpenHelper helper;


	public MySQLiteGame()
	{
		super();
	}

	public MySQLiteGame(Context context)
	{
		helper = new DBOpenHelper(context, 1);
	}

	/**
	 * open database
	 */
	public void opendatabase()
	{
		SQLiteDatabase db = helper.getWritableDatabase();
		helper.onOpen(db);
	}

	/**
	 * 插入一个游戏关卡
	 * 
	 * @param BeanGame
	 */
	public void insert(BeanGame game)
	{
		Log.e("521huaihuai", "" + game.getGameID() + game.getGameRanking() + game.getStar());
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("INSERT INTO game(gameId,ranking,star,lock,created)VALUES(?,?,?,?,?)", new Object[]
		{ game.getGameID(), game.getGameRanking(), game.getStar(), game.isLock(), game.getCreated() });
		db.close();
	}

	// /**
	// * 根据id 删除
	// *
	// * @param id
	// */
	// public void delete(Integer id)
	// {
	// SQLiteDatabase db = helper.getWritableDatabase();
	// db.execSQL("DELETE FROM GAME WHERE ID=?", new Object[]
	// { id });
	// db.close();
	// }

	/**
	 * 更新到游戏数据库中
	 * 
	 */
	public void update(BeanGame game, Integer gameId)
	{
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("UPDATE game SET ranking=?,star=?,lock=?,created=? WHERE gameId=?", new Object[]
		{ game.getGameRanking(), game.getStar(), game.isLock(), game.getCreated(), gameId });
		db.close();
	}

	// /**
	// * playCount++ 更新Songs
	// *
	// * @param p
	// */
	// public void playCount(BeanMusics songs)
	// {
	// int playcount = 0;
	// SQLiteDatabase db = helper.getWritableDatabase();
	// Cursor cursor = db.rawQuery("SELECT
	// path,musicname,artist,duration,playcount FROM SONGS WHERE ID=?", new
	// String[]
	// { songs.getID().toString() });
	// if (cursor.moveToNext())
	// {
	// playcount = cursor.getInt(4);
	// }
	// cursor.close();
	// db.execSQL("UPDATE SONGS SET path=?,musicname=?,artist=?
	// ,duration=?,playcount=? WHERE ID=?", new Object[]
	// { songs.getMusicPath(), songs.getMusicName(), songs.getArtName(),
	// songs.getDuration(), playcount + 1, songs.getID() });
	// Log.i("521huaihuai", songs.getID() + songs.getMusicName() + playcount);
	// db.close();
	// }

	/**
	 * 根据id查找
	 * 
	 * @param id
	 * @return
	 */
	public BeanGame find(Integer ID)
	{
		SQLiteDatabase db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery("SELECT ranking,star,lock,created FROM game WHERE gameId=?", new String[]
		{ ID.toString() });
		BeanGame p = null;
		if (cursor.moveToNext())
		{
			int ranking = cursor.getInt(0);
			int star = cursor.getInt(1);
			int lock = cursor.getInt(2);
			String created = cursor.getString(3);
			p = new BeanGame(ranking, star, lock, created);
		}
		cursor.close();
		db.close();
		return p;

	}

	/**
	 * 根据id查找lock
	 * 
	 * @param id
	 * @return 0 代表上锁
	 */
	public Integer findIsLock(Integer ID)
	{
		int lock = 0;
		SQLiteDatabase db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery("SELECT lock FROM game WHERE gameId=?", new String[]
		{ ID.toString() });
		if (cursor.moveToNext())
		{
			lock = cursor.getInt(0);
		}
		cursor.close();
		db.close();
		return lock;
	}

	/**
	 * 查询所有的mySongs 对象
	 * 
	 * @return mySongs
	 */
	public List<BeanGame> findAll()
	{
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM GAME", null);
		List<BeanGame> myGames = new ArrayList<BeanGame>();
		while (cursor.moveToNext())
		{
			Integer gameId = cursor.getInt(0);
			Integer ranking = cursor.getInt(1);
			Integer star = cursor.getInt(2);
			Integer lock = cursor.getInt(3);
			String created = cursor.getString(4);
			myGames.add(new BeanGame(gameId, ranking, star, lock, created));
		}
		cursor.close();
		db.close();
		return myGames;
	}
}
