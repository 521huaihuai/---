package com.imooc.game;

import java.util.LinkedList;

import com.imooc.control.IMoveListener;
import com.imooc.myBaseGame.CommonGuideGame_00_20;
import com.imooc.myConstant.MyConstant;
import com.imooc.mySnake.Node;
import com.imooc.mySnake.Snake;
import com.imooc.mySufaceView.MainActivity;
import com.imooc.utils.Utils;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Game_18 extends CommonGuideGame_00_20 implements IMoveListener
{

	private boolean isStart;
	private boolean isSuccess;


	public Game_18()
	{
		setOnMoveListener(this);
	}

	@Override
	public String[] getGuideString()
	{
		String[] text =
		{ "从最左侧无停顿移动到最右侧", "15秒内收集15个" };
		return text;
	}

	@Override
	public int[] getGuideIndexTime()
	{
		return new int[]
		{ 2, 2 };
	}

	@Override
	public void detailDraw(Canvas canvas, Paint paint, int screenWidth, int screenHeight)
	{
	}

	@Override
	public void detailLogic()
	{
		if (mAlpha < 230)
		{
			isStart = true;
		}
		if (mCollectionNUM >= 15)
		{
			if (mSnake.getList().getFirst().getX() >= MainActivity.screenWidth)
			{
				isSuccess = true;
				Utils.enterNextCheckPoint("下一关", "从最左侧无停顿移动到最右侧", "15秒内收集15个");
			}
		}
	}

	@Override
	public long setTimeLimite()
	{
		return 15000;
	}

	@Override
	public void hpIsOver()
	{
		Utils.reStartCheckPoint("失败", "注意不要收集自己相克的颜色", "否则会降低自己的生命值");
	}

	@Override
	public int gameOverPos()
	{
		return 18;
	}

	@Override
	public void timeIsOver(long usedTime)
	{
		Utils.reStartCheckPoint("失败", "超时了!", "请在15秒内完成哦!");
	}

	@Override
	public Snake getSnake()
	{
		return new Snake()
		{

			@Override
			public void initBody(LinkedList<Node> mLinkedList)
			{
				Snake.Factory.createPeerNode(mLinkedList, 0, MainActivity.screenHeight / 2, MyConstant.COLOR_GOLD, 10);
			}

			@Override
			public int getInitHp()
			{
				return 5;
			}
		};
	}

	@Override
	public void actionUP(int x, int y)
	{
		if (isStart && !isSuccess)
		{
			Utils.reStartCheckPoint("失败了", "无停顿哦!");
		}
	}

	@Override
	public void actionMOVE(float xm, float ym)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void actionDOWN(int x, int y)
	{
		// TODO Auto-generated method stub

	}

}
