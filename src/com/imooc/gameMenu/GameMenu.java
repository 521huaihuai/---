package com.imooc.gameMenu;

import java.util.ArrayList;
import java.util.List;

import com.imooc.myConstant.MyConstant;
import com.imooc.myDataBase.MySQLiteGame;
import com.imooc.mySufaceView.MainActivity;
import com.imooc.mySufaceView.MyAplication;
import com.imooc.mySufaceView.Pos;
import com.imooc.utils.Utils;

public class GameMenu
{

	// 游戏菜单的位置
	private Pos mPos;
	// 游戏关卡
	private int Checkpoint;
	// 动态游戏菜单的速度
	private int speed;
	private int color;
	// 该关卡是否上锁
	private int isLocked;
	// 该关卡的得分
	private int score;


	public static GameMenu[] createGameMenus(int startPos)
	{
		MySQLiteGame sqLiteGame = new MySQLiteGame(MyAplication.getContext());
		GameMenu[] mGameMenus = new GameMenu[6];
		mGameMenus[0] = new GameMenu(startPos, sqLiteGame.findIsLock(startPos), sqLiteGame.findStar(startPos));
		mGameMenus[1] = new GameMenu(startPos + 1, sqLiteGame.findIsLock(startPos + 1), sqLiteGame.findStar(startPos + 1));
		mGameMenus[2] = new GameMenu(startPos + 2, sqLiteGame.findIsLock(startPos + 2), sqLiteGame.findStar(startPos + 2));
		mGameMenus[3] = new GameMenu(startPos + 3, sqLiteGame.findIsLock(startPos + 3), sqLiteGame.findStar(startPos + 3));
		mGameMenus[4] = new GameMenu(0, 1, -11);
		mGameMenus[5] = new GameMenu(-1, 1, -11);
		List<Pos> list = product5Circle(MainActivity.screenWidth, MainActivity.screenHeight);
		for (int i = 0; i < 6; i++)
		{
			mGameMenus[i].setPos(list.get(i));
		}
		return mGameMenus;
	}

	private static List<Pos> product5Circle(int screenWidth, int screenHeight)
	{
		List<Pos> mList = new ArrayList<Pos>();
		float mRadius = Utils.getAdapterMenuRadius();
		Pos pos = null;
		pos = drawGameMenu(mRadius, 0, 0, screenWidth / 3, screenHeight / 2);
		mList.add(pos);
		pos = drawGameMenu(mRadius, screenWidth / 3, 0, screenWidth / 3, screenHeight / 2);
		mList.add(pos);
		pos = drawGameMenu(mRadius, 0, screenHeight / 2, screenWidth / 3, screenHeight / 2);
		mList.add(pos);
		pos = drawGameMenu(mRadius, screenWidth / 3, screenHeight / 2, screenWidth / 3, screenHeight / 2);
		mList.add(pos);
		pos = drawGameMenu(mRadius, 2 * screenWidth / 3, 0, screenWidth / 3, screenHeight / 2);
		mList.add(pos);
		pos = drawGameMenu(mRadius, 2 * screenWidth / 3, screenHeight / 2, screenWidth / 3, screenHeight / 2);
		mList.add(pos);
		return mList;
	}

	/**
	 * 绘制动态的游戏开始按钮
	 * 
	 * @param radius
	 * @param speed
	 * @param maxWidth
	 * @param maxHeight
	 */
	public static Pos drawGameMenu(float radius, int startX, int startY, int width, int height)
	{
		int x = (int) (Math.random() * (width - 2 * radius) + radius + startX);
		int y = (int) (Math.random() * (height - 2 * radius) + radius + startY);
		return new Pos(x, y);
	}

	public GameMenu(int checkpoint, int lock, int score)
	{
		Checkpoint = checkpoint;
		isLocked = lock;
		this.score = score;
		color = MyConstant.COLORS[Math.abs(checkpoint % 5)];
	}

	public int getSocre()
	{
		return score;
	}

	public int isLocked()
	{
		return isLocked;
	}

	public int getColor()
	{
		return color;
	}

	public void setColor(int color)
	{
		this.color = color;
	}

	public int getSpeed()
	{
		return speed;
	}

	public int getCheckpoint()
	{
		return Checkpoint;
	}

	public Pos getPos()
	{
		return mPos;
	}

	public void setPos(Pos mPos)
	{
		this.mPos = mPos;
	}

}
