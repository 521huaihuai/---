package com.imooc.game;

import java.util.LinkedList;
import java.util.Vector;

import com.imooc.control.Control;
import com.imooc.control.Move;
import com.imooc.myBaseGame.CommonGuideGame_20_40;
import com.imooc.myConstant.MyConstant;
import com.imooc.myParticle.ParticleManager;
import com.imooc.myParticle.PieceParticle;
import com.imooc.myParticle.PowerfulParticleAbstract;
import com.imooc.myParticle.PowerfulParticle_horizontal;
import com.imooc.myParticle.PowerfulParticle_horizontal_vertical;
import com.imooc.myParticle.PowerfulParticle_scattering;
import com.imooc.myParticle.PowerfulParticle_vertical;
import com.imooc.mySnake.Node;
import com.imooc.mySnake.Snake;
import com.imooc.mySufaceView.MainActivity;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Game_45 extends CommonGuideGame_20_40
{

	@Override
	public String[] getGuideString()
	{
		return null;
	}

	@Override
	public int[] getGuideIndexTime()
	{
		return null;
	}

	@Override
	protected Vector<PowerfulParticleAbstract> createPowfulPartice()
	{
		Vector<PowerfulParticleAbstract> particleAbstracts = new Vector<PowerfulParticleAbstract>();
		particleAbstracts
				.add(new PowerfulParticle_vertical(MyConstant.COLOR_BLACK, MainActivity.screenWidth / 3, MainActivity.screenHeight / 3, Math.random() * 360));
		particleAbstracts
				.add(new PowerfulParticle_horizontal(MyConstant.COLOR_BLUE, MainActivity.screenWidth / 2, MainActivity.screenHeight / 3, Math.random() * 360));
		particleAbstracts.add(new PowerfulParticle_horizontal_vertical(MyConstant.COLOR_BLUE, MainActivity.screenWidth / 3, MainActivity.screenHeight / 2,
				Math.random() * 360));
		particleAbstracts
				.add(new PowerfulParticle_scattering(MyConstant.COLOR_RED, MainActivity.screenWidth / 2, MainActivity.screenHeight / 4, Math.random() * 360));
		return particleAbstracts;
	}

	@Override
	protected Vector<PieceParticle> createPartice()
	{
		return ParticleManager.newInstance().createBIGParticle(150, MyConstant.PARTICLE_RADIUS);
	}


	@Override
	public void detailDraw(Canvas canvas, Paint paint, int screenWidth, int screenHeight)
	{

	}

	@Override
	public void detailLogic()
	{
		for (PowerfulParticleAbstract powerfulParticleAbstract : mPowfularticles)
		{
			powerfulParticleAbstract.reflectParticle(1.0f);
		}
	}

	@Override
	public int gameOverPos()
	{
		return 22;
	}

	@Override
	public Snake getSnake()
	{
		return new Snake()
		{

			@Override
			public void initBody(LinkedList<Node> mLinkedList)
			{
				Snake.Factory.createPeerNode(mLinkedList, MyConstant.COLOR_RED);
				Snake.Factory.createPeerNode(mLinkedList, MyConstant.COLOR_BLUE);
				Snake.Factory.createPeerNode(mLinkedList, MyConstant.COLOR_BLACK);
				Snake.Factory.createPeerNode(mLinkedList, MyConstant.COLOR_GOLD);
				Snake.Factory.createPeerNode(mLinkedList, MyConstant.COLOR_GREEN);
			}

			@Override
			public void moveSnake(float xm, float ym)
			{
				LinkedList<Node> mList = getList();
				if (mList.size() > 0)
				{
					Node pos = mList.getFirst();
					Move move = Control.move(xm, ym, getSpeed());
					int x = (int) (pos.getX() + move.getX());
					int y = (int) (pos.getY() + move.getY());
					mList.addFirst(new Node(pos.getColor(), x, y, MyConstant.SNAKE_RADIUS));

					int size = mList.size();
					for (int i = 1; i < size - 1; i++)
					{
						pos = mList.get(i);
						pos.setColor(mList.get(i + 1).getColor());
					}
					mList.removeLast();
				}
			}

			@Override
			public int getInitHp()
			{
				return 15;
			}
		};
	}
}
