package com.imooc.game;

import com.imooc.gameMenu.SimpleGameMenuSuccess;
import com.imooc.mySufaceView.MyAplication;
import com.imooc.snake.RedSnake;
import com.imooc.snake.Snake;
import com.imooc.utils.Utils;
import com.imooc.utils.Utils.Position;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Game_6 extends GuideCommonGame{
	private float alpha = 255;
	private float decreaseAlpha;
	private boolean isEat;

	public Game_6() {
		decreaseAlpha = Utils.alphaDecreaseInNearBytime(3);
	}

	@Override
	public String[] getGuideString() {
		String[] text = { "����������ϰһ����������", "���ľ������ˮ�˻�˽�", "�ƿ��̿˺ڿ����˺�˻�", "�ڽ�������20�뾡�鳢�԰�!" };
		return text;
	}

	@Override
	public int[] getGuideIndexTime() {
		return new int[] { 2, 5, 10, 2 };
	}

	@Override
	public void detailDraw(Canvas canvas, Paint paint, int screenWidth, int screenHeight) {
		if (isEat) {
			if (alpha < 10) {
				isEat = false;
			}
			Utils.drawAlphaText(Position.CEN_UP, canvas, "�������˵���ɫ, �ή������ֵ", paint, alpha);
		}
	}

	@Override
	public void detailLogic() {
		if (alpha > 0) {
			alpha -= decreaseAlpha;
		}
	}

	@Override
	public long setTimeLimite() {
		return 40000;
	}

	@Override
	public void hpIsOver() {

	}

	@Override
	public void timeIsOver(long usedTime) {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		MyAplication.getSurfaceView().setOnISurfaceViewCallBack(
				new SimpleGameMenuSuccess("Ok, �о����?", "�ǲ��� ����Ҫ����ϰһ��?", "���ľ������ˮ�˻�˽�", "�ƿ��̿˺ڿ����˺�˻�", "�ƽ���������ˮ������"));
	}

	@Override
	public Snake getSnake() {
		return new RedSnake();
	}

	@Override
	public int gameOverPos() {
		return 6;
	}

	@Override
	public void sameColorCrossHandle(int color) {

	}

	@Override
	public void oppositeColorCrossHandle(int color) {
		mSnake.setHp(mSnake.getCurrentHp() - 5);
		isEat = true;
		alpha = 255;
	}

	@Override
	public void birthColorCrosshandle(int color) {

	}

	@Override
	public void differentColorCrossHandle(int color) {
	}
}
