package com.imooc.game;

import com.imooc.snake.BlueSnake;
import com.imooc.snake.Snake;
import com.imooc.utils.Utils;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Game_7 extends GuideCommonGame{

	public Game_7() {
	}

	@Override
	public String[] getGuideString() {
		String[] text = { "收集20个,不要收集相克颜色,注意自己的生命值" };
		return text;
	}

	@Override
	public int[] getGuideIndexTime() {
		return new int[] { 6 };
	}

	@Override
	public void detailDraw(Canvas canvas, Paint paint, int screenWidth, int screenHeight) {
	}

	@Override
	public void detailLogic() {
		if (mCollectionNUM == 20) {
			Utils.enterNextCheckPoint("下一关", "任务 : 收集20个", "不要收集相克颜色,注意自己的生命值");
		}
	}

	@Override
	public long setTimeLimite() {
		return 0;
	}

	@Override
	public void hpIsOver() {
		Utils.reStartCheckPoint("失败", "注意不要收集自己相克的颜色","当前不能收集黑色(土),否则会降低自己的生命值");
	}

	@Override
	public void timeIsOver(long usedTime) {
	}

	@Override
	public Snake getSnake() {
		return new BlueSnake();
	}

	@Override
	public int gameOverPos() {
		return 7;
	}

	@Override
	public void sameColorCrossHandle(int color) {
		mCollectionNUM++;
	}

	@Override
	public void oppositeColorCrossHandle(int color) {
		mSnake.setHp(mSnake.getCurrentHp() - 5);
	}

	@Override
	public void birthColorCrosshandle(int color) {
		mCollectionNUM++;
	}

	@Override
	public void differentColorCrossHandle(int color) {
		mCollectionNUM++;
	}

}
