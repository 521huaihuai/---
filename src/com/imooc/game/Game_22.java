package com.imooc.game;

import java.util.LinkedList;
import java.util.Vector;

import com.imooc.control.Control;
import com.imooc.control.Move;
import com.imooc.myConstant.MyConstant;
import com.imooc.mySufaceView.MainActivity;
import com.imooc.particle.PieceParticle;
import com.imooc.particle.PowerfulParticleAbstract;
import com.imooc.particle.PowerfulParticle_horizontal;
import com.imooc.particle.PowerfulParticle_horizontal_vertical;
import com.imooc.particle.PowerfulParticle_scattering;
import com.imooc.particle.PowerfulParticle_vertical;
import com.imooc.snake.Node;
import com.imooc.snake.Snake;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Game_22 extends GuideCommonGame
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
		return PieceParticle.newInstance().createBIGParticle(150, MyConstant.PARTICLE_RADIUS);
	}

	@Override
	public void onRemoveParticleCallBack(PieceParticle particle)
	{
		mCollectionNUM++;
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
					mList.addFirst(new Node(pos.getColor(), x, y, pos.getRadius()));

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
