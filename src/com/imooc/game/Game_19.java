package com.imooc.game;

import java.util.LinkedList;
import java.util.Vector;

import com.imooc.control.IMoveListener;
import com.imooc.myBaseGame.CommonGuideGame_00_20;
import com.imooc.myConstant.MyConstant;
import com.imooc.mySufaceView.MainActivity;
import com.imooc.particle.PieceParticle;
import com.imooc.snake.Node;
import com.imooc.snake.Snake;
import com.imooc.utils.Utils;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Game_19 extends CommonGuideGame_00_20 implements IMoveListener
{

	private boolean isStart;
	private boolean isSuccess;
	private boolean isFail;


	public Game_19()
	{
		setOnMoveListener(this);
	}

	@Override
	public String[] getGuideString()
	{
		String[] text =
		{ "���������ͣ���ƶ������Ҳ�", "15�����ռ�15��" };
		return text;
	}

	@Override
	public int[] getGuideIndexTime()
	{
		return new int[]
		{ 2, 2 };
	}

	@Override
	public Vector<PieceParticle> createPartice()
	{
		PieceParticle.Manager manager = PieceParticle.newInstance();
		return manager.createParticleColors(100, new int[]
		{ MyConstant.COLOR_BLACK, MyConstant.COLOR_BLUE, MyConstant.COLOR_RED });
	}

	@Override
	public void detailDraw(Canvas canvas, Paint paint, int screenWidth, int screenHeight)
	{
	}

	@Override
	public void detailLogic()
	{
		if (mAlpha < 220)
		{
			isStart = true;
		}
		if (mCollectionNUM >= 15)
		{
			if (mSnake.getList().getFirst().getX() >= MainActivity.screenWidth)
			{
				isSuccess = true;
				Utils.enterNextCheckPoint("��һ��", "���������ͣ���ƶ������Ҳ�", "15�����ռ�25��");
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
		Utils.reStartCheckPoint("ʧ��", "ע�ⲻҪ�ռ��Լ���˵���ɫ", "����ή���Լ�������ֵ");
	}

	@Override
	public int gameOverPos()
	{
		return 19;
	}

	@Override
	public void timeIsOver(long usedTime)
	{
		Utils.reStartCheckPoint("ʧ��", "��ʱ��!", "����15�������!");
	}

	@Override
	public Snake getSnake()
	{
		return new Snake()
		{

			@Override
			public void initBody(LinkedList<Node> mLinkedList)
			{
				Snake.Factory.createPeerNode(mLinkedList, 0, MainActivity.screenHeight / 2, MyConstant.COLOR_BLUE, 100);
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
			Utils.reStartCheckPoint("ʧ����", "��ͣ��Ŷ!");
		}
	}

}
