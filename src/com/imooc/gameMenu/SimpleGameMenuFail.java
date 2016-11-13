package com.imooc.gameMenu;

import java.util.Vector;

import com.imooc.myConstant.MyConstant;
import com.imooc.myParticle.ParticleManager;
import com.imooc.myParticle.PieceParticle;
import com.imooc.mySufaceView.ISurfaceViewCallBack;
import com.imooc.mySufaceView.MainActivity;
import com.imooc.mySufaceView.MyAplication;
import com.imooc.mygame.R;
import com.imooc.utils.Utils;
import com.imooc.utils.Utils.Position;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

public class SimpleGameMenuFail implements ISurfaceViewCallBack
{

	private Bitmap mBitmap;
	private Bitmap reStart;
	private float bmpWidth;
	private float bmpHeight;
	private String text;
	private String[] message;
	private Vector<PieceParticle> mMoveVectorB;


	public SimpleGameMenuFail(String text, String... message)
	{
		mBitmap = BitmapFactory.decodeResource(MyAplication.getContext().getResources(), R.drawable.aaa);
		reStart = BitmapFactory.decodeResource(MyAplication.getContext().getResources(), R.drawable.img_appwidget91_voice_refresh_pressed);
		bmpHeight = mBitmap.getHeight();
		bmpWidth = mBitmap.getWidth();
		this.text = text;
		this.message = message;
		ParticleManager manager = ParticleManager.newInstance();
		mMoveVectorB = manager.createBIGParticleWithMove(10, MyConstant.BIG_PARTICLE_RADIUS);
	}

	@Override
	public void draw(Canvas canvas, Paint paint, int screenWidth, int screenHeight)
	{
		Utils.drawParticle(canvas, mMoveVectorB, paint);
		int size = MyAplication.getTextSize();
		Utils.drawText(Position.CEN_UP_UP, canvas, text, size + 9, paint);
		Utils.drawMessageText(message, canvas, size, paint);
		canvas.drawBitmap(mBitmap, (1.0f * screenWidth / 2 - bmpWidth / 2), 0.6f * screenHeight - bmpHeight / 2, paint);
		canvas.drawBitmap(reStart, (1.0f * screenWidth / 2 - reStart.getWidth() / 2), 0.6f * screenHeight - reStart.getHeight() / 2, paint);
	}

	@Override
	public void logic()
	{
		for (PieceParticle particle : mMoveVectorB)
		{
			particle.reflectParticle(1.0f);
		}
	}

	@Override
	public void surfaceCreatedCallBack(int screenW, int screenH)
	{

	}

	@Override
	public boolean onTouchEventCallBack(MotionEvent event)
	{
		float x = event.getX();
		float y = event.getY();
		if (event.getAction() == MotionEvent.ACTION_DOWN)
		{
			int menu = checkitIsPress(x, y);
			if (menu == -1)
			{
				for (PieceParticle particle : mMoveVectorB)
				{
					if (Utils.isInRound(particle, x, y, Utils.getAdapterMenuRadius()))
					{
						particle.onTouchReflectParticle(x, y, Utils.getAdapterMenuRadius() / 4);
					}
				}
				return false;
			}
			else
			{
				menu += MainActivity.currentRelevant;
			}
			ISurfaceViewCallBack game = null;
			try
			{
				Utils.saveDataInt(MyAplication.getContext(), "checkPoint", menu);
				game = (ISurfaceViewCallBack) Class.forName("com.imooc.game.Game_" + menu).newInstance();
			}
			catch (InstantiationException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (IllegalAccessException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (ClassNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			MyAplication.getSurfaceView().setOnISurfaceViewCallBack(game);
			return true;
		}
		return true;
	}

	private int checkitIsPress(float x, float y)
	{
		float minx = (1.0f * MainActivity.screenWidth / 2 - bmpWidth / 2);
		float maxX = (1.0f * MainActivity.screenWidth / 2 + bmpWidth / 2);
		float miny = 0.6f * MainActivity.screenHeight - bmpHeight / 2;
		float maxy = 0.6f * MainActivity.screenHeight + bmpHeight / 2;
		if (x > minx && x < maxX)
		{
			if (y > miny && y < maxy) { return 0; }
		}
		return -1;
	}

}
