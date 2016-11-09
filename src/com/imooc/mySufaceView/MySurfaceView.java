package com.imooc.mySufaceView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

/**
 * 
 * @author Himi
 *
 */
public class MySurfaceView extends SurfaceView implements Callback, Runnable {
	private SurfaceHolder sfh;
	private Canvas canvas;
	private Paint paint;
	private Thread th;
	private boolean flag;
	// 游戏界面宽高
	private int screenW, screenH;
	// 默认为每50毫秒执行一次绘制
	private int mSleepTime = 50;
	// 触摸点POS
	private ISurfaceViewCallBack mCallBack;

	public void setOnISurfaceViewCallBack(ISurfaceViewCallBack callBack) {
		this.mCallBack = callBack;
	}

	/**
	 * SurfaceView初始化函数
	 */
	public MySurfaceView(Context context) {
		super(context);
		sfh = this.getHolder();
		sfh.addCallback(this);
		paint = new Paint();
		paint.setTextSize(MyAplication.getTextSize());
		paint.setColor(Color.WHITE);
		paint.setAntiAlias(true);
		setFocusable(true);
	}

	/**
	 * SurfaceView视图创建，响应此函数
	 */
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		screenW = this.getWidth();
		screenH = this.getHeight();
		if (mCallBack != null) {
			mCallBack.surfaceCreatedCallBack(screenW, screenH);
		}
		flag = true;
		// 实例线程
		th = new Thread(this);
		th.setPriority(Thread.MAX_PRIORITY);
		// 启动线程
		th.start();
	}

	/**
	 * 游戏绘图
	 */
	public void myDraw() {
		try {
			canvas = sfh.lockCanvas();
			if (canvas != null) {
				canvas.drawColor(Color.WHITE);
				if (mCallBack != null) {
					mCallBack.draw(canvas, paint, screenW, screenH);
				}
			}
		} catch (Exception e) {
		} finally {
			if (canvas != null)
				sfh.unlockCanvasAndPost(canvas);
		}
	}

	/**
	 * 触屏事件监听
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (mCallBack != null) {
			return mCallBack.onTouchEventCallBack(event);
		}
		return true;
	}

	/**
	 * 游戏逻辑
	 */
	private void logic() {
		if (mCallBack != null) {
			mCallBack.logic();
		}
	}

	@Override
	public void run() {
		while (flag) {
			long start = System.currentTimeMillis();
			myDraw();
			logic();
			long end = System.currentTimeMillis();
			try {
				if (end - start < mSleepTime) {
					Thread.sleep(mSleepTime - (end - start));
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * SurfaceView视图状态发生改变，响应此函数
	 */
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
	}

	/**
	 * SurfaceView视图消亡时，响应此函数
	 */
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		flag = false;
	}

	public boolean isRunning() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public Paint getPaint() {
		return paint;
	}

	public void setSleepTime(int mSleepTime) {
		this.mSleepTime = mSleepTime;
	}

	public int getScreenWidth() {
		return screenW;
	}

	public int getScreenHeight() {
		return screenH;
	}
}
