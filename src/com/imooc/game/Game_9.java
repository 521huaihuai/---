package com.imooc.game;

import java.util.LinkedList;

import com.imooc.myBaseGame.CommonGuideGame_00_20;
import com.imooc.myConstant.MyConstant;
import com.imooc.snake.Node;
import com.imooc.snake.Snake;
import com.imooc.utils.Utils;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Game_9 extends CommonGuideGame_00_20
{

	public Game_9()
	{
	}

	@Override
	public String[] getGuideString()
	{
		String[] text =
		{ "收集30个,不要收集相克颜色,注意自己的生命值" };
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
			Utils.enterNextCheckPoint("下一关", "任务 : 收集30个", "不要收集相克颜色,注意自己的生命值");
		}
	}

	@Override
	public void hpIsOver()
	{
		Utils.reStartCheckPoint("失败", "注意不要收集自己相克的颜色", "当前不能收集红色(火),否则会降低自己的生命值");
	}

	@Override
	public Snake getSnake()
	{
		return new Snake()
		{

			@Override
			public void initBody(LinkedList<Node> mLinkedList)
			{
				Snake.Factory.createPeerNode(mLinkedList, MyConstant.COLOR_GOLD, 15);
			}

			@Override
			public int getInitHp()
			{
				return 15;
			}
		};
	}

	@Override
	public int gameOverPos()
	{
		return 9;
	}
}
