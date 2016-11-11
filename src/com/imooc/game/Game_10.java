package com.imooc.game;

import java.util.LinkedList;

import com.imooc.myBaseGame.CommonGuideGame_00_20;
import com.imooc.myConstant.MyConstant;
import com.imooc.mySufaceView.MainActivity;
import com.imooc.snake.Node;
import com.imooc.snake.Snake;
import com.imooc.utils.Utils;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Game_10 extends CommonGuideGame_00_20
{

	public Game_10()
	{
	}

	@Override
	public String[] getGuideString()
	{
		String[] text =
		{ "�ռ�30��,��Ҫ�ռ������ɫ,ע���Լ�������ֵ" };
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
			Utils.enterNextCheckPoint("��һ��", "����СС����Ϸѵ��,��Ӧ������������?", "���ǽ���ʼʱ������,������ķ�Ӧ", "��60�����ռ�20��");
		}
	}

	@Override
	public long setTimeLimite()
	{
		return 0;
	}

	@Override
	public void hpIsOver()
	{
		Utils.reStartCheckPoint("ʧ��", "ע�ⲻҪ�ռ��Լ���˵���ɫ", "��ǰ�����ռ���ɫ(��),����ή���Լ�������ֵ");
	}

	@Override
	public Snake getSnake()
	{
		return new Snake()
		{

			@Override
			public void initBody(LinkedList<Node> mLinkedList)
			{
				Node node = new Node(MyConstant.COLOR_GREEN, MainActivity.screenWidth / 2, MainActivity.screenHeight / 2);
				mLinkedList.add(node);
				mLinkedList.add(node);
				mLinkedList.add(node);
				mLinkedList.add(node);
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
				return 15;
			}
		};
	}

	@Override
	public int gameOverPos()
	{
		return 10;
	}

}
