package com.imooc.game;

import java.util.LinkedList;
import java.util.Vector;

import com.imooc.control.IMoveListener;
import com.imooc.myBaseGame.CommonGuideGame_00_20;
import com.imooc.myConstant.MyConstant;
import com.imooc.myParticle.ParticleManager;
import com.imooc.myParticle.PieceParticle;
import com.imooc.mySnake.Node;
import com.imooc.mySnake.Snake;
import com.imooc.mySufaceView.MainActivity;
import com.imooc.utils.Utils;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Game_20 extends CommonGuideGame_00_20 implements IMoveListener
{

	private boolean isStart;
	private boolean isSuccess;
	private boolean isFail;


	public Game_20()
	{
		setOnMoveListener(this);
	}

	@Override
	public String[] getGuideString()
	{
		String[] text =
		{ "从最左侧无停顿移动到最右侧", "15秒内收集25个" };
		return text;
	}

	@Override
	public int[] getGuideIndexTime()
	{
		return new int[]
		{ 2, 2};
	}

	@Override
	public Vector<PieceParticle> createPartice()
	{
		ParticleManager manager = ParticleManager.newInstance();
		return manager.createParticleColors(150, new int[]
		{ MyConstant.COLOR_GOLD, MyConstant.COLOR_GREEN, MyConstant.COLOR_BLACK });
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
		if (mCollectionNUM >= 25)
		{
			if (mSnake.getList().getFirst().getX() >= MainActivity.screenWidth)
			{
				isSuccess = true;
				enterNextCheckPoint("准备好了吗?", "接下来我们将展开更加丰富的玩法(真的很晕 =_=)");
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
		isFail = true;
		Utils.reStartCheckPoint("失败", "注意不要收集自己相克的颜色", "否则会降低自己的生命值");
	}

	@Override
	public int gameOverPos()
	{
		return 20;
	}

	@Override
	public void timeIsOver(long usedTime)
	{
		Utils.reStartCheckPoint("失败", "超时了!", "请在15秒内完成!");
	}

	@Override
	public Snake getSnake()
	{
		return new Snake()
		{

			@Override
			public void initBody(LinkedList<Node> mLinkedList)
			{
				Snake.Factory.createPeerNode(mLinkedList, 0, MainActivity.screenHeight / 2, MyConstant.COLOR_GREEN, 15);
			}

			@Override
			public int getInitHp()
			{
				return 5;
			}
		};
	}


	@Override
	public void actionMOVE(float xm, float ym)
	{

	}

	@Override
	public void actionDOWN(int x, int y)
	{

	}

	@Override
	public void actionUP(int x, int y)
	{
		if (isStart && !isSuccess && !isFail)
		{
			Utils.reStartCheckPoint("失败了", "无停顿哦!");
		}
	}

}
