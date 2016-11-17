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
		{ "个人开发者 : zjm", "游戏关卡分类", "01-20 : 以考验操作能力, 控制能力为主",
				"21-40 : 以考验思维能力, 反应能力为主", "41-60 : 以考验策略能力, 瞬间反应为主", "61-80: 敬请享受游戏.","80- : 探索未知" };
		
		mVector = ParticleManager.newInstance().createParticle(100);
	}

	@Override
	public void draw(Canvas canvas, Paint paint, int screenWidth, int screenHeight)
	{
		Utils.drawText(Position.CEN_UP_UP, canvas,  "游戏简介", MyAplication.getTitleSize(), paint);
		paint.setColor(MyConstant.COLOR_BLACK);
		Utils.drawMessageText(messages, canvas, MyAplication.getTextSize(), paint);
		for (PieceParticle pieceParticle : mVector)
		{
			paint.setColor(pieceParticle.getColor());
			canvas.drawCircle(pieceParticle.getX(), pieceParticle.getY(), pieceParticle.getRadius(), paint);
		}
		paint.setColor(MyConstant.COLOR_RED);
		Utils.drawText(Position.CEN_DOWN_DOWN, canvas,  "参与设计 : ...........", MyAplication.getTextSize(), paint);
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
