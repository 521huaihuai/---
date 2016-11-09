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
	// ��Ϸ������
	private int screenW, screenH;
	// Ĭ��Ϊÿ50����ִ��һ�λ���
	private int mSleepTime = 50;
	// ������POS
	private ISurfaceViewCallBack mCallBack;

	public void setOnISurfaceViewCallBack(ISurfaceViewCallBack callBack) {
		this.mCallBack = callBack;
	}

	/**
	 * SurfaceView��ʼ������
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
	 * SurfaceView��ͼ��������Ӧ�˺���
	 */
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		screenW = this.getWidth();
		screenH = this.getHeight();
		if (mCallBack != null) {
			mCallBack.surfaceCreatedCallBack(screenW, screenH);
		}
		flag = true;
		// ʵ���߳�
		th = new Thread(this);
		th.setPriority(Thread.MAX_PRIORITY);
		// �����߳�
		th.start();
	}

	/**
	 * ��Ϸ��ͼ
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
	 * �����¼�����
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (mCallBack != null) {
			return mCallBack.onTouchEventCallBack(event);
		}
		return true;
	}

	/**
	 * ��Ϸ�߼�
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
	 * SurfaceView��ͼ״̬�����ı䣬��Ӧ�˺���
	 */
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
	}

	/**
	 * SurfaceView��ͼ����ʱ����Ӧ�˺���
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
