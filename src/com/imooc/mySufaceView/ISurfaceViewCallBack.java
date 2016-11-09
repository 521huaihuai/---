package com.imooc.mySufaceView;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

public interface ISurfaceViewCallBack {

	void draw(Canvas canvas, Paint paint, int screenWidth, int screenHeight);

	void logic();

	void surfaceCreatedCallBack(int screenW, int screenH);

	boolean onTouchEventCallBack(MotionEvent event);

}
