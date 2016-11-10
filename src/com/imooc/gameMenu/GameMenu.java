package com.imooc.gameMenu;

import java.util.ArrayList;
import java.util.List;

import com.imooc.myConstant.MyConstant;
import com.imooc.mySufaceView.MainActivity;
import com.imooc.mySufaceView.Pos;
import com.imooc.utils.Utils;

public class GameMenu {

	// 游戏菜单的位置
	private Pos mPos;
	// 游戏关卡
	private int Checkpoint;
	// 动态游戏菜单的速度
	private int speed;
	private int color;

	public static GameMenu[] createGameMenus(int startPos) {
		GameMenu[] mGameMenus = new GameMenu[6];
		mGameMenus[0] = new GameMenu(startPos);
		mGameMenus[1] = new GameMenu(startPos + 1);
		mGameMenus[2] = new GameMenu(startPos + 2);
		mGameMenus[3] = new GameMenu(startPos + 3);
		mGameMenus[4] = new GameMenu(0);
		mGameMenus[5] = new GameMenu(-1);
		List<Pos> list = product5Circle(MainActivity.screenWidth, MainActivity.screenHeight);
		for (int i = 0; i < 6; i++) {
			mGameMenus[i].setPos(list.get(i));
		}
		return mGameMenus;
	}

	private static List<Pos> product5Circle(int screenWidth, int screenHeight) {
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
	public static Pos drawGameMenu(float radius, int startX, int startY, int width, int height) {
		int x = (int) (Math.random() * (width - 2 * radius) + radius + startX);
		int y = (int) (Math.random() * (height - 2 * radius) + radius + startY);
		return new Pos(x, y);
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getCheckpoint() {
		return Checkpoint;
	}

	public void setCheckpoint(int checkpoint) {
		Checkpoint = checkpoint;
	}

	public GameMenu(int checkpoint) {
		super();
		Checkpoint = checkpoint;

		color = MyConstant.COLORS[Math.abs(checkpoint % 5)];
	}

	public Pos getPos() {
		return mPos;
	}

	public void setPos(Pos mPos) {
		this.mPos = mPos;
	}

}
