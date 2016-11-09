package com.imooc.game;

import java.util.LinkedList;

import com.imooc.myConstant.MyConstant;
import com.imooc.mySufaceView.MainActivity;
import com.imooc.snake.Node;
import com.imooc.snake.Snake;
import com.imooc.utils.Utils;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Game_17 extends GuideCommonGame {

	public Game_17() {
	}

	@Override
	public String[] getGuideString() {
		String[] text = { "从最左侧移动到最右侧", "收集10个,不触碰相克颜色" };
		return text;
	}

	@Override
	public int[] getGuideIndexTime() {
		return new int[] { 2, 4 };
	}

	@Override
	public void detailDraw(Canvas canvas, Paint paint, int screenWidth, int screenHeight) {
	}

	@Override
	public void detailLogic() {
		if (mCollectionNUM >= 10) {
			if (mSnake.getList().getFirst().getX() >= MainActivity.screenWidth) {
				Utils.enterNextCheckPoint("通过了", "从最左侧移动到最右侧", "15秒内收集10个");
			}
		}
	}

	@Override
	public long setTimeLimite() {
		return 0;
	}

	@Override
	public void hpIsOver() {
		Utils.reStartCheckPoint("失败", "注意不要收集自己相克的颜色", "否则会降低自己的生命值");
	}

	@Override
	public int gameOverPos() {
		return 17;
	}

	@Override
	public void timeIsOver(long usedTime) {
		// Utils.reStartCheckPoint("失败", "超时了!", "请在15秒内完成哦!");
	}

	@Override
	public Snake getSnake() {
		return new Snake() {

			@Override
			public void initBody(LinkedList<Node> mLinkedList) {
				Node node = new Node(MyConstant.COLOR_GREEN, 0, MainActivity.screenHeight / 2);
				mLinkedList.add(node);
				mLinkedList.add(node);
				mLinkedList.add(node);
				mLinkedList.add(node);
				mLinkedList.add(node);
				mLinkedList.add(node);
				mLinkedList.add(node);
				mLinkedList.add(node);
				mLinkedList.add(node);
				mLinkedList.add(node);
				mLinkedList.add(node);
				mLinkedList.add(node);
				mLinkedList.add(node);

			}

			@Override
			public int getInitHp() {
				return 5;
			}
		};
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
