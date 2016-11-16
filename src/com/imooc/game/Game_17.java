package com.imooc.game;

import java.util.LinkedList;

import com.imooc.myBaseGame.CommonGuideGame_00_20;
import com.imooc.myConstant.MyConstant;
import com.imooc.mySnake.Node;
import com.imooc.mySnake.Snake;
import com.imooc.mySufaceView.MainActivity;
import com.imooc.utils.Utils;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Game_17 extends CommonGuideGame_00_20
{

	public Game_17()
	{
	}

	@Override
	public String[] getGuideString()
	{
		String[] text =
		{ "��������ƶ������Ҳ�", "�ռ�15��,�����������ɫ" };
		return text;
	}

	@Override
	public int[] getGuideIndexTime()
	{
		return new int[]
		{ 2, 4 };
	}

	@Override
	public void detailDraw(Canvas canvas, Paint paint, int screenWidth, int screenHeight)
	{
	}

	@Override
	public void detailLogic()
	{
		if (mCollectionNUM >= 15)
		{
			if (mSnake.getList().getFirst().getX() >= MainActivity.screenWidth)
			{
				Utils.enterNextCheckPoint("ͨ����", "���������ͣ���ƶ������Ҳ�", "15�����ռ�15��");
			}
		}
	}

	@Override
	public void hpIsOver()
	{
		Utils.reStartCheckPoint("ʧ��", "ע�ⲻҪ�ռ��Լ���˵���ɫ", "����ή���Լ�������ֵ");
	}

	@Override
	public int gameOverPos()
	{
		return 17;
	}

	@Override
	public Snake getSnake()
	{
		return new Snake()
		{

			@Override
			public void initBody(LinkedList<Node> mLinkedList)
			{
				Node node = new Node(MyConstant.COLOR_GREEN, 0, MainActivity.screenHeight / 2);
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
				mLinkedList.add(node);
				mLinkedList.add(node);
				mLinkedList.add(node);

			}

			@Override
			public int getInitHp()
			{
				return 5;
			}
		};
	}

}
