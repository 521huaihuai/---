package com.imooc.mySufaceView;

import java.util.Vector;

import com.imooc.myConstant.MyConstant;
import com.imooc.myParticle.ParticleManager;
import com.imooc.myParticle.PieceParticle;
import com.imooc.utils.Utils;
import com.imooc.utils.Utils.Position;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

public class Game_rule implements ISurfaceViewCallBack
{

	private String[] messages;
	private Vector<PieceParticle> mVector;


	public Game_rule()
	{
		messages = new String[]
		{ "����������������Ĺ���", "���ľ, ľ����, ����ˮ, ˮ�˻�, ��˽�", "����ˮ, ˮ��ľ, ľ����,"
				+ " ������, ������", "��ɫ�����,��ɫ����ľ,��ɫ������,��ɫ����ˮ,��ɫ�����","",
				"��Ϸ������������(����):", "С������, ��������", "��������:(����������Ч)",
				"����ɫ�Դ�(��ȡ)����",
				"�������, ���عؿ�, �ϰ���", "����׷������ɫ","̽��δ֪" };

		mVector = ParticleManager.newInstance().createParticle(100);
	}

	@Override
	public void draw(Canvas canvas, Paint paint, int screenWidth, int screenHeight)
	{
		Utils.drawText(Position.CEN_UP_UP, canvas, "��Ϸ����", MyAplication.getTitleSize(), paint);
		paint.setColor(MyConstant.COLOR_BLACK);
		Utils.drawMessageText(messages, canvas, MyAplication.getTextSize(), paint);
		for (PieceParticle pieceParticle : mVector)
		{
			paint.setColor(pieceParticle.getColor());
			canvas.drawCircle(pieceParticle.getX(), pieceParticle.getY(), pieceParticle.getRadius(), paint);
		}
		paint.setColor(MyConstant.COLOR_RED);
		//Utils.drawText(Position.CEN_DOWN_DOWN, canvas, "������� : ...........", MyAplication.getTextSize(), paint);
	}

	@Override
	public void logic()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceCreatedCallBack(int screenW, int screenH)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onTouchEventCallBack(MotionEvent event)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
