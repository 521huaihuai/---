package com.imooc.game;

import java.util.LinkedList;

import com.imooc.myConstant.MyConstant;
import com.imooc.mySufaceView.MainActivity;
import com.imooc.snake.Node;
import com.imooc.snake.Snake;
import com.imooc.utils.Utils;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Game_16 extends GuideCommonGame{

	public Game_16() {
	}

	@Override
	public String[] getGuideString() {
		String[] text = { "在20秒内收集不同于自身颜色的斑点15个" };
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
		if (mCollectionNUM == 15) {
			Utils.enterNextCheckPoint("go on !", "从最左侧移动到最右侧", "收集10个,不触碰相克颜色");
		}
	}

	@Override
	public long setTimeLimite() {
		return 20000;
	}

	@Override
	public void hpIsOver() {
		Utils.reStartCheckPoint("失败", "注意不要收集自己相克的颜色", "否则会降低自己的生命值");
	}

	@Override
	public int gameOverPos() {
		return 16;
	}

	@Override
	public void timeIsOver(long usedTime) {
		Utils.reStartCheckPoint("失败", "超时了!", "请在20秒内完成哦!");
	}

	@Override
	public Snake getSnake() {
		return new Snake() {

			@Override
			public void initBody(LinkedList<Node> mLinkedList) {
				Node node = null;
				for (int i = 0; i < 3; i++) {
					node = new Node(MyConstant.COLOR_BLACK, MainActivity.screenWidth / 2, MainActivity.screenHeight / 2);
					mLinkedList.add(node);
				}
			}

			@Override
			public int getInitHp() {
				return 15;
			}
		};
	}

	@Override
	public void sameColorCrossHandle(int color) {
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
