package com.imooc.gameMenu;

import java.util.Vector;

import com.imooc.myConstant.MyConstant;
import com.imooc.mySufaceView.ISurfaceViewCallBack;
import com.imooc.mySufaceView.MainActivity;
import com.imooc.mySufaceView.MyAplication;
import com.imooc.mySufaceView.Pos;
import com.imooc.particle.PieceParticle;
import com.imooc.utils.Utils;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;

public class MenuCallBack implements ISurfaceViewCallBack {
	private GameMenu[] mGameMenus;
	private float mRadius;
	private Vector<PieceParticle> mVector;
	private Vector<PieceParticle> mMoveVector;
	private Vector<PieceParticle> mMoveVectorB;
	private float textZie;

	public MenuCallBack(GameMenu... gameMenus) {
		mGameMenus = gameMenus;
	}

	@Override
	public void surfaceCreatedCallBack(int screenWidth, int screenHeight) {
		MainActivity.screenHeight = screenHeight;
		MainActivity.screenWidth = screenWidth;
		mGameMenus = GameMenu.createGameMenus(MainActivity.currentRelevant);
		mRadius = Utils.getAdapterMenuRadius();
		PieceParticle.Manager manager = PieceParticle.newInstance();
		mVector = manager.createParticle(100);
		mMoveVector = manager.createBIGParticleWithMove(20, 15);
		mMoveVectorB = manager.createBIGParticleWithMove(10, 25);
		textZie = MyAplication.getTextSize();
	}

	@Override
	public void draw(Canvas canvas, Paint paint, int screenWidth, int screenHeight) {
		Pos pos = null;
		Utils.drawParticle(canvas, mVector, paint);
		Utils.drawParticle(canvas, mMoveVector, paint);
		Utils.drawParticle(canvas, mMoveVectorB, paint);
		for (GameMenu menu : mGameMenus) {
			pos = menu.getPos();
			paint.setColor(MyConstant.COLOR_BLACK);
			canvas.drawCircle(pos.getX(), pos.getY(), mRadius, paint);
			paint.setColor(MyConstant.COLORS[2]);
			paint.setTextSize(MyAplication.getTextSize());
			int po = menu.getCheckpoint();
			String text = "第 " + menu.getCheckpoint() + " 关";
			float textWidth = 0.0f;
			if (po <= 0) {
				if (po == 0) {
					text = "上一页";
				}
				if (po == -1) {
					text = "下一页";
				}
				textWidth = textZie * (text.length());
			}else {
				textWidth = textZie * (text.length() - 2);
			}
			canvas.drawText(text, pos.getX() - textWidth / 2, pos.getY(), paint);
		}
	}

	@Override
	public boolean onTouchEventCallBack(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();
		for (GameMenu menu : mGameMenus) {
			if (checkitIsPress(menu, x, y)) {
				// 进入游戏
				int point = menu.getCheckpoint();
				try {
					try {
						if (point == 0) {
							// 进入上一页
							MainActivity.currentRelevant -= 4;
							if (MainActivity.currentRelevant <= 0) {
								MainActivity.currentRelevant = 1;
							}
							MyAplication.getSurfaceView()
									.setOnISurfaceViewCallBack(new CommonGameMenu(MainActivity.currentRelevant));
						} else if (point == -1) {
							// 进入下一页
							MainActivity.currentRelevant += 4;
							if (MainActivity.currentRelevant >= 100) {
								////////////////////////////////
								MainActivity.currentRelevant = 1;
							}
							MyAplication.getSurfaceView()
									.setOnISurfaceViewCallBack(new CommonGameMenu(MainActivity.currentRelevant));
						} else {
							ISurfaceViewCallBack game = (ISurfaceViewCallBack) Class
									.forName("com.imooc.game.Game_" + point).newInstance();
							MyAplication.getSurfaceView().setOnISurfaceViewCallBack(game);
						}
					} catch (InstantiationException e) {
						Log.e("521huaihuai", e.getMessage());
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						Log.e("521huaihuai", e.getMessage());
						e.printStackTrace();
					}
				} catch (ClassNotFoundException e) {
					Log.e("521huaihuai", e.getMessage());
					e.printStackTrace();
				}
				return true;
			}
		}

		for (PieceParticle particle : mMoveVectorB) {
			if (Utils.isInRound(particle, x, y, Utils.getAdapterMenuRadius())) {
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
	private boolean checkitIsPress(GameMenu menu, float x, float y) {
		if (x > (menu.getPos().getX() - mRadius) && x < (menu.getPos().getX() + mRadius)) {
			if (y > (menu.getPos().getY() - mRadius) && y < (menu.getPos().getY() + mRadius)) {
				menu.setColor(Color.RED);
				return true;
			}
		}
		return false;
	}

	@Override
	public void logic() {
		for (PieceParticle particle : mMoveVector) {
			particle.reflectParticle(1.0f);
		}
		for (PieceParticle particle : mMoveVectorB) {
			particle.reflectParticle(1.0f);
		}

	}
}