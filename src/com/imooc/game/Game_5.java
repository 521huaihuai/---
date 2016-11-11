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
		{ "您可以看到右上角您的生命值降低了!", "继续尝试吃黑色,把生命值降为0" };
	}

	@Override
	public String[] getGuideString()
	{
		String[] text =
		{ "我们的第一条规则来了..", "依据金克木克土克水克火克金原则", "所对应的颜色是", "黄色代表金,绿色代表木,黑色代表土", "蓝色代表水,红色代表火", "比如,你现在是蓝色(水)", "如果吃了黑色(土)", "你的生命值就会降低" };
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
		Utils.enterNextCheckPoint("游戏? 规则?", "是不是有点晕呢? 没记住?", "接下来我们提供一系列简单的小游戏来加深印象", "金克木克土克水克火克金", "小提示 :黄金绿树黑土水蓝火红");
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
