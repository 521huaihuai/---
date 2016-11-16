package com.imooc.game;

import com.imooc.myBaseGame.CommonGame_00_20;
import com.imooc.mySnake.RedSnake;
import com.imooc.mySnake.Snake;
import com.imooc.utils.Utils;
import com.imooc.utils.Utils.Position;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Game_2 extends CommonGame_00_20{

	private float alpha = 255;
	private float decreaseAlpha;
	private String[] text;
	private int index = 0;

	public Game_2() {
		decreaseAlpha = Utils.alphaDecreaseInNearBytime(6);
		text = new String[]{"您可以看到左上角是时间, 右上角分别是血量和收集的个数","尝试尽快收集屏幕上的任意20个小斑点吧~"};
	}

	@Override
	public long setTimeLimite() {
		return 111111110;
	}

	@Override
	public Snake getSnake() {
		return new RedSnake();
	}

	@Override
	public int gameOverPos() {
		return 2;
	}

	@Override
	public void childDraw(Canvas canvas, Paint paint, int screenWidth, int screenHeight) {
		if (alpha < 10) {
			if (index < 1) {
				index++;
				alpha = 255;
			}
		}
		Utils.drawAlphaText(Position.CEN_UP, canvas, text[index], paint, alpha);
	}

	@Override
	public void childLogic() {
		if (mCollectionNUM == 20) {
			enterNextCheckPoint("你成功的收集了20个斑点",0, 0, 5,"很简单?没难度?嘿嘿!");
		}
		if (alpha > 0) {
			alpha -= decreaseAlpha;
		}
	}

	@Override
	public void sameColorCrossHandle(int color) {
		mCollectionNUM++;
	}

	@Override
	public void oppositeColorCrossHandle(int color) {
		mCollectionNUM++;
	}

	@Override
	public void differentColorCrossHandle(int color) {
		mCollectionNUM++;
	}

	@Override
	public void birthColorCrosshandle(int color) {
		mCollectionNUM++;
	}

	@Override
	public void hpIsOver() {
	}

	@Override
	public void timeIsOver(long usedTime) {
	}

}
