package com.imooc.gameMenu;

import java.util.Vector;

import com.imooc.myConstant.MyConstant;
import com.imooc.myDataBase.BeanGame;
import com.imooc.myDataBase.MySQLiteGame;
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
import android.util.Log;
import android.view.MotionEvent;

public class SimpleGameMenuSuccess implements ISurfaceViewCallBack
{

	private Bitmap mBitmap;
	private Bitmap reStart;
	private Bitmap nextBmp;
	private float bmpWidth;
	private float bmpHeight;
	private String text;
	private String[] message;
	private Vector<PieceParticle> mMoveVectorB;
	private float textZie;


	public SimpleGameMenuSuccess(String text, String... message)
	{
		mBitmap = BitmapFactory.decodeResource(MyAplication.getContext().getResources(), R.drawable.aaa);
		reStart = BitmapFactory.decodeResource(MyAplication.getContext().getResources(), R.drawable.img_appwidget91_voice_refresh_pressed);
		nextBmp = BitmapFactory.decodeResource(MyAplication.getContext().getResources(), R.drawable.img_appwidget91_voice_playmode_normal);
		bmpHeight = mBitmap.getHeight();
		bmpWidth = mBitmap.getWidth();
		this.text = text;
		this.message = message;
		ParticleManager manager = ParticleManager.newInstance();
		mMoveVectorB = manager.createBIGParticleWithMove(10, MyConstant.BIG_PARTICLE_RADIUS);
		textZie = MyAplication.getTextSize();
		
		//更新游戏数据
		MySQLiteGame sqLiteGame = new MySQLiteGame(MyAplication.getContext());
		// 解锁游戏并更新
		//sqLiteGame.update(new BeanGame(1, 1, 1, ""), MainActivity.currentRelevant);
		sqLiteGame.insert(new BeanGame(MainActivity.currentRelevant + 1, 0, 0, 1, ""));
		Log.e("521huaihuai", "isLock = " + sqLiteGame.findIsLock(MainActivity.currentRelevant));
	}

	@Override
	public void draw(Canvas canvas, Paint paint, int screenWidth, int screenHeight)
	{
		Utils.drawParticle(canvas, mMoveVectorB, paint);
		paint.setColor(MyConstant.COLOR_BLUE);
		Utils.drawText(Position.CEN_UP_UP, canvas, text, (int) (textZie + 9), paint);
		Utils.drawMessageText(message, canvas, (int) (textZie), paint);
		canvas.drawBitmap(mBitmap, (1.0f * screenWidth / 3 - bmpWidth / 2), 0.6f * screenHeight - bmpHeight / 2, paint);
		canvas.drawBitmap(mBitmap, (2.0f * screenWidth / 3 - bmpWidth / 2), 0.6f * screenHeight - bmpHeight / 2, paint);
		canvas.drawBitmap(reStart, (1.0f * screenWidth / 3 - reStart.getWidth() / 2), 0.6f * screenHeight - reStart.getHeight() / 2, paint);
		canvas.drawBitmap(nextBmp, (2.0f * screenWidth / 3 - nextBmp.getWidth() / 2), 0.6f * screenHeight - nextBmp.getHeight() / 2, paint);
		// canvas.drawText("再试一次", (1.0f * screenWidth / 3 - size * 4.0f / 2),
		// 0.65f * screenHeight, paint);
		// canvas.drawText("下一关", (2.0f * screenWidth / 3 - size * 3.0f / 2),
		// 0.65f * screenHeight, paint);
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
		float minx = (1.0f * MainActivity.screenWidth / 3 - bmpWidth / 2);
		float maxX = (1.0f * MainActivity.screenWidth / 3 + bmpWidth / 2);
		float miny = 0.6f * MainActivity.screenHeight - bmpHeight / 2;
		float maxy = 0.6f * MainActivity.screenHeight + bmpHeight / 2;
		if (x > minx && x < maxX)
		{
			if (y > miny && y < maxy) { return 0; }
		}
		if (x > (minx + MainActivity.screenWidth / 3) && x < (maxX + MainActivity.screenWidth / 3))
		{
			if (y > miny && y < maxy) { return 1; }
		}
		return -1;
	}

}
