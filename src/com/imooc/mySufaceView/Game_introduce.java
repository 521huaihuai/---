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

public class Game_introduce implements ISurfaceViewCallBack
{

	private String[] messages;
	private Vector<PieceParticle>mVector;


	public Game_introduce()
	{
		messages = new String[]
		{ "���˿����� : zjm", "��Ϸ�ؿ�����", "01-20 : �Կ����������, ��������Ϊ��",
				"21-40 : �Կ���˼ά����, ��Ӧ����Ϊ��", "41-60 : �Կ����������, ˲�䷴ӦΪ��", "61-80: ����������Ϸ.","80- : ̽��δ֪" };
		
		mVector = ParticleManager.newInstance().createParticle(100);
	}

	@Override
	public void draw(Canvas canvas, Paint paint, int screenWidth, int screenHeight)
	{
		Utils.drawText(Position.CEN_UP_UP, canvas,  "��Ϸ���", MyAplication.getTitleSize(), paint);
		paint.setColor(MyConstant.COLOR_BLACK);
		Utils.drawMessageText(messages, canvas, MyAplication.getTextSize(), paint);
		for (PieceParticle pieceParticle : mVector)
		{
			paint.setColor(pieceParticle.getColor());
			canvas.drawCircle(pieceParticle.getX(), pieceParticle.getY(), pieceParticle.getRadius(), paint);
		}
		paint.setColor(MyConstant.COLOR_RED);
		Utils.drawText(Position.CEN_DOWN_DOWN, canvas,  "������� : ...........", MyAplication.getTextSize(), paint);
	}

	@Override
	public void logic()
	{

	}

	@Override
	public void surfaceCreatedCallBack(int screenW, int screenH)
	{

	}

	@Override
	public boolean onTouchEventCallBack(MotionEvent event)
	{
		return false;
	}

}
