package com.imooc.myBaseGame;

import com.imooc.utils.Utils;
import com.imooc.utils.Utils.Position;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class CommonGuideGame_00_20 extends CommonGame_00_20
{

	protected float mAlpha = 255;
	private float decreaseAlpha;
	private String[] text;
	private int[] time;
	protected int currentIndex = 0;
	private int length;


	public CommonGuideGame_00_20()
	{
		text = getGuideString();
		time = getGuideIndexTime();
		if (text != null)
		{
			length = text.length;
			decreaseAlpha = Utils.alphaDecreaseInNearBytime(time[0]);
		}
	}

	@Override
	public void childDraw(Canvas canvas, Paint paint, int screenWidth, int screenHeight)
	{
		if (mAlpha < 10)
		{
			if (currentIndex < (length - 1))
			{
				currentIndex++;
				decreaseAlpha = Utils.alphaDecreaseInNearBytime(time[currentIndex]);
				mAlpha = 255;
			}
		}
		if (text != null)
		{
			paint.setColor(mSnake.getColor());
			Utils.drawAlphaText(Position.CEN_UP, canvas, text[currentIndex], paint, mAlpha);
		}
		detailDraw(canvas, paint, screenWidth, screenHeight);
	}

	@Override
	public void childLogic()
	{
		if (mAlpha > 0)
		{
			mAlpha -= decreaseAlpha;
		}
		detailLogic();
	}

	@Override
	public void sameColorCrossHandle(int color)
	{
		mCollectionNUM++;
	}

	@Override
	public void oppositeColorCrossHandle(int color)
	{
		mSnake.setHp(mSnake.getCurrentHp() - 5);
		mCollectionNUM++;
	}

	@Override
	public void birthColorCrosshandle(int color)
	{
		mCollectionNUM++;
	}

	@Override
	public void differentColorCrossHandle(int color)
	{
		mCollectionNUM++;
	}

	@Override
	public long setTimeLimite()
	{
		return 0;
	}

	@Override
	public void hpIsOver()
	{
		Utils.reStartCheckPoint("ʧ����", "�������ɫ���������ֵ�½�");
	}

	@Override
	public void timeIsOver(long usedTime)
	{
		Utils.reStartCheckPoint("ʧ��", "��ʱ��!", "ע��涨��ʱ" + timeLimite / 1000 + "��");
	}

	/**
	 * ������
	 */
	public abstract String[] getGuideString();

	/**
	 * ÿ������������Ӧ��ʱ��
	 */
	public abstract int[] getGuideIndexTime();

	public abstract void detailDraw(Canvas canvas, Paint paint, int screenWidth, int screenHeight);

	public abstract void detailLogic();
}
