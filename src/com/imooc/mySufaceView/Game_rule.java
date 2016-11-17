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
		{ "依据五行相克相生的规则", "金克木, 木克土, 土克水, 水克火, 火克金", "金生水, 水生木, 木生火,"
				+ " 火生土, 土生金", "黄色代表金,绿色代表木,黑色代表土,蓝色代表水,红色代表火","",
				"游戏增添的其他组件(规则):", "小型粒子, 大型粒子", "技能粒子:(包含各种特效)",
				"五行色自带(获取)技能",
				"迷雾组件, 隐藏关卡, 障碍物", "智能追踪五行色","探索未知" };

		mVector = ParticleManager.newInstance().createParticle(100);
	}

	@Override
	public void draw(Canvas canvas, Paint paint, int screenWidth, int screenHeight)
	{
		Utils.drawText(Position.CEN_UP_UP, canvas, "游戏规则", MyAplication.getTitleSize(), paint);
		paint.setColor(MyConstant.COLOR_BLACK);
		Utils.drawMessageText(messages, canvas, MyAplication.getTextSize(), paint);
		for (PieceParticle pieceParticle : mVector)
		{
			paint.setColor(pieceParticle.getColor());
			canvas.drawCircle(pieceParticle.getX(), pieceParticle.getY(), pieceParticle.getRadius(), paint);
		}
		paint.setColor(MyConstant.COLOR_RED);
		//Utils.drawText(Position.CEN_DOWN_DOWN, canvas, "参与设计 : ...........", MyAplication.getTextSize(), paint);
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
