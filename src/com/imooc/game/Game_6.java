package com.imooc.game;

import com.imooc.myBaseGame.CommonGuideGame_00_20;
import com.imooc.mySnake.RedSnake;
import com.imooc.mySnake.Snake;
import com.imooc.utils.Utils;
import com.imooc.utils.Utils.Position;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Game_6 extends CommonGuideGame_00_20
{

	private float alpha = 255;
	private float decreaseAlpha;
	private boolean isEat;


	public Game_6()
	{
		decreaseAlpha = Utils.alphaDecreaseInNearBytime(3);
	}

	@Override
	public String[] getGuideString()
	{
		String[] text =
		{ "�����������������, ��ǶԽ������Ĺؿ����а���", "���ľ������ˮ�˻�˽�", "�ƿ��̿˺ڿ����˺�˻�", "�ڽ�������20�뾡�鳢�԰�!" };
		return text;
	}

	@Override
	public int[] getGuideIndexTime()
	{
		return new int[]
		{ 4, 4, 10, 2 };
	}

	@Override
	public void detailDraw(Canvas canvas, Paint paint, int screenWidth, int screenHeight)
	{
		if (isEat)
		{
			if (alpha < 10)
			{
				isEat = false;
			}
			Utils.drawAlphaText(Position.CEN_UP, canvas, "�������˵���ɫ, �ή������ֵ", paint, alpha);
		}
	}

	@Override
	public void detailLogic()
	{
		if (alpha > 0)
		{
			alpha -= decreaseAlpha;
		}
	}

	@Override
	public long setTimeLimite()
	{
		return 40000;
	}

	@Override
	public void timeIsOver(long usedTime)
	{
		try
		{
			Thread.sleep(500);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		Utils.enterNextCheckPoint("Ok, �о����?", "�ټ�һ�ǰ�?!", "���ľ������ˮ�˻�˽�", "�ƿ��̿˺ڿ����˺�˻�");
	}

	@Override
	public Snake getSnake()
	{
		return new RedSnake();
	}

	@Override
	public int gameOverPos()
	{
		return 6;
	}

	@Override
	public void oppositeColorCrossHandle(int color)
	{
		mSnake.setHp(mSnake.getCurrentHp() - 5);
		isEat = true;
		alpha = 255;
	}
}
