package com.imooc.game;

import com.imooc.myBaseGame.CommonGuideGame_00_20;
import com.imooc.snake.BlueSnake;
import com.imooc.snake.Snake;
import com.imooc.utils.Utils;
import com.imooc.utils.Utils.Position;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Game_5 extends CommonGuideGame_00_20
{

	private int size;
	private float alpha = 255;
	private int index = 0;
	private float decreaseAlpha;
	private String[] selfGuide;


	public Game_5()
	{
		decreaseAlpha = Utils.alphaDecreaseInNearBytime(7);
		selfGuide = new String[]
		{ "�����Կ������Ͻ���������ֵ������!", "�������ԳԺ�ɫ,������ֵ��Ϊ0" };
	}

	@Override
	public String[] getGuideString()
	{
		String[] text =
		{ "���ǵĵ�һ����������..", "���ݽ��ľ������ˮ�˻�˽�ԭ��", "����Ӧ����ɫ��", "��ɫ�����,��ɫ����ľ,��ɫ������", "��ɫ����ˮ,��ɫ�����", "����,����������ɫ(ˮ)", "������˺�ɫ(��)", "�������ֵ�ͻή��" };
		size = text.length;
		return text;
	}

	@Override
	public int[] getGuideIndexTime()
	{
		return new int[]
		{ 2, 8, 2, 6, 6, 4, 4, 4 };
	}

	@Override
	public void detailDraw(Canvas canvas, Paint paint, int screenWidth, int screenHeight)
	{
		if (mSnake.getCurrentHp() <= 10)
		{
			if (alpha < 10)
			{
				if (index < 1)
				{
					index++;
					alpha = 255;
				}
			}
			Utils.drawAlphaText(Position.CEN_UP, canvas, selfGuide[index], paint, alpha);
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
	public void hpIsOver()
	{
		Utils.enterNextCheckPoint("��Ϸ? ����?", "�ǲ����е�����? û��ס?", "�����������ṩһϵ�м򵥵�С��Ϸ������ӡ��", "���ľ������ˮ�˻�˽�", "С��ʾ :�ƽ���������ˮ�����");
	}

	@Override
	public Snake getSnake()
	{
		return new BlueSnake();
	}

	@Override
	public int gameOverPos()
	{
		return 5;
	}

	@Override
	public void oppositeColorCrossHandle(int color)
	{
		if (currentIndex == (size - 1))
		{
			mSnake.setHp(mSnake.getCurrentHp() - 5);

		}
	}

}
