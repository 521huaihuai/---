package com.imooc.game;

import java.util.LinkedList;

import com.imooc.myConstant.MyConstant;
import com.imooc.mySufaceView.MainActivity;
import com.imooc.snake.Node;
import com.imooc.snake.Snake;
import com.imooc.utils.Utils;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Game_12 extends GuideCommonGame{

	public Game_12() {
	}

	@Override
	public String[] getGuideString() {
		String[] text = { "��30�����ռ�15��" };
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
			Utils.enterNextCheckPoint("��һ��", "��20�����ռ��ߵ�15��");
		}
	}

	@Override
	public long setTimeLimite() {
		return 30000;
	}

	@Override
	public void hpIsOver() {
		Utils.reStartCheckPoint("ʧ��", "ע�ⲻҪ�ռ��Լ���˵���ɫ","����ή���Լ�������ֵ");
	}
	

	@Override
	public int gameOverPos() {
		return 12;
	}


	@Override
	public void timeIsOver(long usedTime) {
		Utils.reStartCheckPoint("ʧ��", "��ʱ��!","����30�������Ŷ!");
	}

	@Override
	public Snake getSnake() {
		return new Snake() {
			
			@Override
			public void initBody(LinkedList<Node> mLinkedList) {
				Node node = null;
				for (int i = 0; i < 10; i++) {
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
