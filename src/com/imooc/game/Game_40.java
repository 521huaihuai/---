package com.imooc.game;

import java.util.LinkedList;
import java.util.List;

import com.imooc.control.Control;
import com.imooc.control.IMoveListener;
import com.imooc.control.Move;
import com.imooc.myBaseGame.CommonGame_21_40;
import com.imooc.myConstant.MyConstant;
import com.imooc.mySufaceView.MainActivity;
import com.imooc.mySufaceView.MyAplication;
import com.imooc.particle.PieceParticle;
import com.imooc.snake.Node;
import com.imooc.snake.Snake;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Game_40 extends CommonGame_21_40 implements IMoveListener
{

	private Snake mSnake;
	private boolean isGameOver;


	public Game_40()
	{
		setOnMoveListener(this);
	}

	@Override
	public Snake getSnake()
	{
		mSnake = new Snake()
		{

			@Override
			public void initBody(LinkedList<Node> mLinkedList)
			{
				mLinkedList.add(new Node(MyConstant.COLOR_BLUE, 0, MainActivity.screenHeight / 2));
				mLinkedList.add(new Node(MyConstant.COLOR_BLACK, 0, MainActivity.screenHeight / 2));
				mLinkedList.add(new Node(MyConstant.COLOR_GOLD, 0, MainActivity.screenHeight / 2));
				mLinkedList.add(new Node(MyConstant.COLOR_GREEN, 0, MainActivity.screenHeight / 2));
				mLinkedList.add(new Node(MyConstant.COLOR_RED, 0, MainActivity.screenHeight / 2));
				mLinkedList.add(new Node(MyConstant.COLOR_BLUE, 0, MainActivity.screenHeight / 2));
				mLinkedList.add(new Node(MyConstant.COLOR_BLACK, 0, MainActivity.screenHeight / 2));
				mLinkedList.add(new Node(MyConstant.COLOR_GOLD, 0, MainActivity.screenHeight / 2));
				mLinkedList.add(new Node(MyConstant.COLOR_GREEN, 0, MainActivity.screenHeight / 2));
				mLinkedList.add(new Node(MyConstant.COLOR_RED, 0, MainActivity.screenHeight / 2));
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
		return mSnake;
	}

	@Override
	public void childDraw(Canvas canvas, Paint paint, int screenWidth, int screenHeight)
	{
		paint.setColor(MyConstant.COLOR_RED);
		canvas.drawText("我们再给游戏增加一些难度,", screenWidth / 3, screenHeight / 4, paint);
		canvas.drawText("你是多彩的, ", screenWidth / 2, screenHeight / 3, paint);
		canvas.drawText("当然了, 它的属性也是由头决定的!", screenWidth / 3, 2 * screenHeight / 3, paint);
		if (mSnake.getList().size() <= 0)
		{
			// 结束游戏
			canvas.drawCircle(screenWidth / 4, screenHeight / 2, 160, paint);
			canvas.drawCircle(3 * screenWidth / 4, screenHeight / 2, 160, paint);
			canvas.drawText("你 输了 !", screenWidth / 2, screenHeight / 2, paint);
			isGameOver = true;
		}
	}

	@Override
	public void childLogic()
	{

	}

	@Override
	public void sameColorCrossHandle(int color)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void oppositeColorCrossHandle(int color)
	{
		// mSnake.setHp(mSnake.getCurrentHp() - 5);
		mSnake.getList().removeFirst();
	}

	@Override
	public void differentColorCrossHandle(int color)
	{
		List<Node> mList = mSnake.getList();
		mList.get(0).setColor(color);
	}

	@Override
	public void actionMOVE(float xm, float ym)
	{

	}

	@Override
	public void actionDOWN(int x, int y)
	{
		if (isGameOver)
		{
			MyAplication.getSurfaceView().setOnISurfaceViewCallBack(new Game_40());
		}
	}

	@Override
	public void actionUP(int x, int y)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public int gameOverPos()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long setTimeLimite()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void hpIsOver()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void timeIsOver(long usedTime)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void birthColorCrosshandle(int color)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onRemoveParticleCallBack(PieceParticle particle)
	{
		// TODO Auto-generated method stub

	}

}
