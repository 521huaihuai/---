package com.imooc.gameMenu;

import java.util.Vector;

import com.imooc.myConstant.MyConstant;
import com.imooc.myParticle.ParticleManager;
import com.imooc.myParticle.PieceParticle;
import com.imooc.mySufaceView.ISurfaceViewCallBack;
import com.imooc.mySufaceView.MainActivity;
import com.imooc.mySufaceView.MyAplication;
import com.imooc.mySufaceView.Pos;
import com.imooc.mygame.R;
import com.imooc.utils.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;

public class CommonGameMenu implements ISurfaceViewCallBack
{

	private GameMenu[] gameMenus;
	private float mRaius;
	private Vector<PieceParticle> mVector;
	private Vector<PieceParticle> mMoveVector;
	private Vector<PieceParticle> mMoveVectorB;
	private float textZie;
	private Bitmap mBitmap;
	// 得分图标
	private Bitmap scoreBitmap_0;
	private Bitmap scoreBitmap_0_left;
	private Bitmap scoreBitmap_0_right;
	private Bitmap scoreBitmap_1;
	private Bitmap scoreBitmap_1_left;
	private Bitmap scoreBitmap_1_right;
	private Bitmap scoreBitmap_2;
	private Bitmap scoreBitmap_2_left;
	private Bitmap scoreBitmap_2_right;


	public CommonGameMenu(int startPos)
	{
		gameMenus = GameMenu.createGameMenus(startPos);
		mRaius = Utils.getAdapterMenuRadius();
		ParticleManager manager = ParticleManager.newInstance();
		mVector = manager.createParticle(100);
		mMoveVector = manager.createBIGParticleWithMove(20, MyConstant.MID_PARTICLE_RADIUS);
		mMoveVectorB = manager.createBIGParticleWithMove(10, MyConstant.BIG_PARTICLE_RADIUS);
		textZie = MyAplication.getTextSize();
		mBitmap = BitmapFactory.decodeResource(MyAplication.getContext().getResources(), R.drawable.wallet_base_safekeyboard_lock_small);
		scoreBitmap_0 = BitmapFactory.decodeResource(MyAplication.getContext().getResources(), R.drawable.evaluation_0);
		scoreBitmap_1 = BitmapFactory.decodeResource(MyAplication.getContext().getResources(), R.drawable.evaluation_1);
		scoreBitmap_2 = BitmapFactory.decodeResource(MyAplication.getContext().getResources(), R.drawable.food_ratingbar_full_filled);
		Matrix roateMatrix_left = new Matrix();
		roateMatrix_left.setRotate(20);
		Matrix roateMatrix_right = new Matrix();
		roateMatrix_right.setRotate(-20);
		scoreBitmap_0_left = Bitmap.createBitmap(scoreBitmap_0, 0, 0, scoreBitmap_0.getWidth(), scoreBitmap_0.getHeight(), roateMatrix_left, false);
		scoreBitmap_0_right = Bitmap.createBitmap(scoreBitmap_0, 0, 0, scoreBitmap_0.getWidth(), scoreBitmap_0.getHeight(), roateMatrix_right, false);
		scoreBitmap_1_left = Bitmap.createBitmap(scoreBitmap_1, 0, 0, scoreBitmap_1.getWidth(), scoreBitmap_1.getHeight(), roateMatrix_left, false);
		scoreBitmap_1_right = Bitmap.createBitmap(scoreBitmap_1, 0, 0, scoreBitmap_1.getWidth(), scoreBitmap_1.getHeight(), roateMatrix_right, false);
		scoreBitmap_2_left = Bitmap.createBitmap(scoreBitmap_2, 0, 0, scoreBitmap_2.getWidth(), scoreBitmap_2.getHeight(), roateMatrix_left, false);
		scoreBitmap_2_right = Bitmap.createBitmap(scoreBitmap_2, 0, 0, scoreBitmap_2.getWidth(), scoreBitmap_2.getHeight(), roateMatrix_right, false);
	}

	@Override
	public void draw(Canvas canvas, Paint paint, int screenWidth, int screenHeight)
	{
		Utils.drawParticle(canvas, mVector, paint);
		Utils.drawParticle(canvas, mMoveVectorB, paint);
		Utils.drawParticle(canvas, mMoveVector, paint);
		for (GameMenu menu : gameMenus)
		{
			Pos pos = null;
			pos = menu.getPos();
			paint.setColor(MyConstant.COLOR_BLACK);
//			RadialGradient rg = new RadialGradient(pos.getX(), pos.getY(), Utils.getAdapterMenuRadius() / 2, Color.WHITE, MyConstant.COLOR_BLACK,
//					TileMode.MIRROR);
//			paint.setShader(rg);
			if (menu.isLocked() == 1)
			{

				// paint.setAlpha(MyConstant.LOCK_ALPHA);
			}
			canvas.drawCircle(pos.getX(), pos.getY(), mRaius, paint);
			//paint.setShader(null);
			paint.setAlpha(255);
			if (menu.isLocked() == 0)
			{
				// 绘制上锁图标
				canvas.drawBitmap(mBitmap, pos.getX() - mBitmap.getWidth() / 2, pos.getY() + mBitmap.getHeight() / 2, paint);
			}
			else
			{
				// 绘制得分图标
				drawScoreBitmap(menu.getSocre(), canvas, paint, pos.getX(), pos.getY());
			}

			paint.setColor(MyConstant.COLORS[2]);
			paint.setTextSize(MyAplication.getTextSize());
			int po = menu.getCheckpoint();
			String text = "第 " + menu.getCheckpoint() + " 关";
			float textWidth = 0.0f;
			if (po <= 0)
			{
				if (po == 0)
				{
					text = "上一页";
				}
				if (po == -1)
				{
					text = "下一页";
				}
				textWidth = textZie * (text.length());
			}
			else
			{
				textWidth = textZie * (text.length() - 2);
			}
			canvas.drawText(text, pos.getX() - textWidth / 2, pos.getY(), paint);
		}
	}

	@Override
	public void logic()
	{
		for (PieceParticle particle : mMoveVector)
		{
			particle.reflectParticle(1.0f);
		}
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
			for (GameMenu menu : gameMenus)
			{
				if (checkitIsPress(menu, x, y))
				{
					// 进入游戏
					int point = menu.getCheckpoint();
					try
					{
						try
						{
							if (point == 0)
							{
								// 进入上一页
								MainActivity.currentRelevant -= 4;
								if (MainActivity.currentRelevant <= 0)
								{
									MainActivity.currentRelevant = 1;
								}
								MyAplication.getSurfaceView().setOnISurfaceViewCallBack(new CommonGameMenu(MainActivity.currentRelevant));
								return true;
							}
							else if (point == -1)
							{
								// 进入下一页
								MainActivity.currentRelevant += 4;
								if (MainActivity.currentRelevant >= 100)
								{

									////////////////////////////////
									MainActivity.currentRelevant = 1;
								}
								MyAplication.getSurfaceView().setOnISurfaceViewCallBack(new CommonGameMenu(MainActivity.currentRelevant));
								return true;
							}
							else
							{
								if (!Utils.checkIsLocked(point))
								{
									ISurfaceViewCallBack game = (ISurfaceViewCallBack) Class.forName("com.imooc.game.Game_" + point).newInstance();
									MyAplication.getSurfaceView().setOnISurfaceViewCallBack(game);
								}
							}
						}
						catch (InstantiationException e)
						{
							Log.e("521huaihuai", e.getMessage());
							e.printStackTrace();
						}
						catch (IllegalAccessException e)
						{
							Log.e("521huaihuai", e.getMessage());
							e.printStackTrace();
						}
					}
					catch (ClassNotFoundException e)
					{
						Log.e("521huaihuai", e.getMessage());
						e.printStackTrace();
					}
					return true;
				}
			}

		}
		for (PieceParticle particle : mMoveVectorB)
		{
			if (Utils.isInRound(particle, x, y, Utils.getAdapterMenuRadius()))
			{
				particle.onTouchReflectParticle(x, y, 5.0f);
			}
		}
		return true;
	}

	/**
	 * 检查该菜单是否被点击
	 * 
	 * @param menu
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean checkitIsPress(GameMenu menu, float x, float y)
	{
		if (x > (menu.getPos().getX() - mRaius) && x < (menu.getPos().getX() + mRaius))
		{
			if (y > (menu.getPos().getY() - mRaius) && y < (menu.getPos().getY() + mRaius))
			{
				menu.setColor(Color.RED);
				return true;
			}
		}
		return false;
	}

	/**
	 * 绘制星星判定
	 */
	public void drawScoreBitmap(int score, Canvas canvas, Paint paint, int centerX, int centerY)
	{
		score = score / 10;
		switch (score)
		{
			case -1:
				break;
			// 0 - 9分
			case 0:
				canvas.drawBitmap(scoreBitmap_0_left, centerX - Utils.getAdapterMenuRadius() / 3 - scoreBitmap_0_left.getWidth() / 2,
						centerY + scoreBitmap_0_left.getHeight() / 2, paint);
				canvas.drawBitmap(scoreBitmap_0, centerX - scoreBitmap_0.getWidth() / 2, centerY + scoreBitmap_0.getHeight(), paint);
				canvas.drawBitmap(scoreBitmap_0_right, centerX + Utils.getAdapterMenuRadius() / 3 - scoreBitmap_0_right.getWidth() / 2,
						centerY + scoreBitmap_0_right.getHeight() / 2, paint);
				break;
			case 1:
				canvas.drawBitmap(scoreBitmap_1_left, centerX - Utils.getAdapterMenuRadius() / 3 - scoreBitmap_1_left.getWidth() / 2,
						centerY + scoreBitmap_1_left.getHeight() / 2, paint);
				canvas.drawBitmap(scoreBitmap_0, centerX - scoreBitmap_0.getWidth() / 2, centerY + scoreBitmap_0.getHeight(), paint);
				canvas.drawBitmap(scoreBitmap_0_right, centerX + Utils.getAdapterMenuRadius() / 3 - scoreBitmap_0_right.getWidth() / 2,
						centerY + scoreBitmap_0_right.getHeight() / 2, paint);
				break;
			case 2:
				canvas.drawBitmap(scoreBitmap_2_left, centerX - Utils.getAdapterMenuRadius() / 3 - scoreBitmap_2_left.getWidth() / 2,
						centerY + scoreBitmap_2_left.getHeight() / 2, paint);
				canvas.drawBitmap(scoreBitmap_0, centerX - scoreBitmap_0.getWidth() / 2, centerY + scoreBitmap_0.getHeight(), paint);
				canvas.drawBitmap(scoreBitmap_0_right, centerX + Utils.getAdapterMenuRadius() / 3 - scoreBitmap_0_right.getWidth() / 2,
						centerY + scoreBitmap_0_right.getHeight() / 2, paint);

				break;
			case 3:
				canvas.drawBitmap(scoreBitmap_2_left, centerX - Utils.getAdapterMenuRadius() / 3 - scoreBitmap_2_left.getWidth() / 2,
						centerY + scoreBitmap_2_left.getHeight() / 2, paint);
				canvas.drawBitmap(scoreBitmap_1, centerX - scoreBitmap_1.getWidth() / 2, centerY + scoreBitmap_1.getHeight(), paint);
				canvas.drawBitmap(scoreBitmap_0_right, centerX + Utils.getAdapterMenuRadius() / 3 - scoreBitmap_0_right.getWidth() / 2,
						centerY + scoreBitmap_0_right.getHeight() / 2, paint);
				break;
			case 4:
				canvas.drawBitmap(scoreBitmap_2_left, centerX - Utils.getAdapterMenuRadius() / 3 - scoreBitmap_2_left.getWidth() / 2,
						centerY + scoreBitmap_2_left.getHeight() / 2, paint);
				canvas.drawBitmap(scoreBitmap_2, centerX - scoreBitmap_2.getWidth() / 2, centerY + scoreBitmap_2.getHeight(), paint);
				canvas.drawBitmap(scoreBitmap_0_right, centerX + Utils.getAdapterMenuRadius() / 3 - scoreBitmap_0_right.getWidth() / 2,
						centerY + scoreBitmap_0_right.getHeight() / 2, paint);
				break;
			case 5:
				canvas.drawBitmap(scoreBitmap_2_left, centerX - Utils.getAdapterMenuRadius() / 3 - scoreBitmap_2_left.getWidth() / 2,
						centerY + scoreBitmap_2_left.getHeight() / 2, paint);
				canvas.drawBitmap(scoreBitmap_2, centerX - scoreBitmap_2.getWidth() / 2, centerY + scoreBitmap_2.getHeight(), paint);
				canvas.drawBitmap(scoreBitmap_1_right, centerX + Utils.getAdapterMenuRadius() / 3 - scoreBitmap_1_right.getWidth() / 2,
						centerY + scoreBitmap_1_right.getHeight() / 2, paint);

				break;

			// 60分以上
			default:
				canvas.drawBitmap(scoreBitmap_2_left, centerX - Utils.getAdapterMenuRadius() / 3 - scoreBitmap_2_left.getWidth() / 2,
						centerY + scoreBitmap_2_left.getHeight() / 2, paint);
				canvas.drawBitmap(scoreBitmap_2, centerX - scoreBitmap_2.getWidth() / 2, centerY + scoreBitmap_2.getHeight(), paint);
				canvas.drawBitmap(scoreBitmap_2_right, centerX + Utils.getAdapterMenuRadius() / 3 - scoreBitmap_2_right.getWidth() / 2,
						centerY + scoreBitmap_2_right.getHeight() / 2, paint);
				break;
		}
	}
}
