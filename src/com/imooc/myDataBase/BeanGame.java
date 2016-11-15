package com.imooc.myDataBase;

public class BeanGame
{

	private int gameId = 1;
	private int ranking = 0;
	private int star = 0;
	private int lock = 0;
	private String created = "";


	public BeanGame(Integer id, Integer ranking, Integer star, Integer lock, String created)
	{
		this.gameId = id;
		this.ranking = ranking;
		this.lock = lock;
		this.star = star;
		this.created = created;
	}

	public BeanGame(int ranking, int star, int lock, String created)
	{
		this.ranking = ranking;
		this.lock = lock;
		this.star = star;
		this.created = created;
	}

	public int getGameID()
	{
		return gameId;
	}

	public int getGameRanking()
	{
		// TODO Auto-generated method stub
		return ranking;
	}

	public int getStar()
	{
		// TODO Auto-generated method stub
		return star;
	}

	public int isLock()
	{
		// TODO Auto-generated method stub
		return lock;
	}

	public String getCreated()
	{
		// TODO Auto-generated method stub
		return created;
	}

}
