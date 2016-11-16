package com.imooc.game;

import com.imooc.myBaseGame.CommonGuideGame_00_20;
import com.imooc.mySnake.BlueSnake;
import com.imooc.mySnake.Snake;
import com.imooc.utils.Utils;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Game_7 extends CommonGuideGame_00_20
{

	public Game_7()
	{
	}

	@Override
	public String[] getGuideString()
	{
		String[] text =
		{ "�ռ�30��,��Ҫ�ռ������ɫ,ע���Լ�������ֵ" };
		return text;
	}

	@Override
	public int[] getGuideIndexTime()
	{
		return new int[]
		{ 6 };
	}

	@Override
	public void detailDraw(Canvas canvas, Paint paint, int screenWidth, int screenHeight)
	{
	}

	@Override
	public void detailLogic()
	{
		if (mCollectionNUM == 30)
		{
			Utils.enterNextCheckPoint("��һ��", "���� : �ռ�30��", "��Ҫ�ռ������ɫ,ע���Լ�������ֵ");
		}
	}

	@Override
	public void hpIsOver()
	{
		Utils.reStartCheckPoint("ʧ��", "ע�ⲻҪ�ռ��Լ���˵���ɫ", "��ǰ�����ռ���ɫ(��),����ή���Լ�������ֵ");
	}

	@Override
	public Snake getSnake()
	{
		return new BlueSnake();
	}

	@Override
	public int gameOverPos()
	{
		return 7;
	}
}
