package com.imooc.game;

import java.util.LinkedList;
import java.util.Vector;

import com.imooc.gameMenu.SimpleGameMenuFail;
import com.imooc.myBaseGame.CommonGame_00_20;
import com.imooc.myConstant.MyConstant;
import com.imooc.myParticle.ParticleManager;
import com.imooc.myParticle.PieceParticle;
import com.imooc.mySnake.Node;
import com.imooc.mySnake.Snake;
import com.imooc.mySufaceView.MainActivity;
import com.imooc.mySufaceView.MyAplication;
import com.imooc.utils.Utils;
import com.imooc.utils.Utils.Position;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Game_3 extends CommonGame_00_20
{

	private float alpha = 255;
	private float decreaseAlpha;
	private String text;


	public Game_3()
	{
		decreaseAlpha = Utils.alphaDecreaseInNearBytime(5);
		text = "那就尝试一下从最左侧无碰撞的移动到最右端~";
	}

	@Override
	public long setTimeLimite()
	{
		return 111111110;
	}

	@Override
	protected Vector<PieceParticle> createPartice()
	{
		ParticleManager manager = ParticleManager.newInstance();
		return manager.createParticle(200);
	}

	@Override
	public Snake getSnake()
	{
		return new Snake()
		{

			@Override
			public void initBody(LinkedList<Node> mLinkedList)
			{
				Node node = new Node(MyConstant.COLOR_BLACK, 0, MainActivity.screenHeight / 2);
				mLinkedList.add(node);
				mLinkedList.add(node);
				mLinkedList.add(node);
				mLinkedList.add(node);
				mLinkedList.add(node);
				mLinkedList.add(node);
			}

			@Override
			public int getInitHp()
			{
				return 0;
			}
		};
	}

	@Override
	public int gameOverPos()
	{
		return 3;
	}

	@Override
	public void childDraw(Canvas canvas, Paint paint, int screenWidth, int screenHeight)
	{
		Utils.drawAlphaText(Position.CEN_UP, canvas, text, paint, alpha);
	}

	@Override
	public void childLogic()
	{
		if (mCollectionNUM > 0)
		{
			MyAplication.getSurfaceView().setOnISurfaceViewCallBack(new SimpleGameMenuFail("抱歉, 不能与任何斑点触碰!", ""));
		}
		if (alpha > 0)
		{
			alpha -= decreaseAlpha;
		}
		if (mSnake.getList().getFirst().getX() >= MainActivity.screenWidth)
		{
			Utils.enterNextCheckPoint("你成功了", "下一关任务", "不能停! 不能停! 不能停!");
		}
	}

	@Override
	public void sameColorCrossHandle(int color)
	{
		mCollectionNUM++;
	}

	@Override
	public void oppositeColorCrossHandle(int color)
	{
		mCollectionNUM++;
	}

	@Override
	public void differentColorCrossHandle(int color)
	{
		mCollectionNUM++;
	}

	@Override
	public void birthColorCrosshandle(int color)
	{
		mCollectionNUM++;
	}

	@Override
	public void hpIsOver()
	{
	}

	@Override
	public void timeIsOver(long usedTime)
	{
	}


}
