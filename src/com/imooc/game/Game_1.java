package com.imooc.game;

import java.util.LinkedList;

import com.imooc.mySufaceView.ISurfaceViewCallBack;
import com.imooc.mySufaceView.MainActivity;
import com.imooc.mySufaceView.MyAplication;
import com.imooc.snake.Node;
import com.imooc.snake.RedSnake;
import com.imooc.snake.Snake;
import com.imooc.utils.Utils;
import com.imooc.utils.Utils.Position;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

public class Game_1 implements ISurfaceViewCallBack {
	private LinkedList<Node> mList;
	private int x;
	private int y;
	private float xm;
	private float ym;
	private Snake mRedSnake;
	private float alpha = 255;
	private float decreaseAlpha;
	private String text[] = new String[] { "这是引导游戏", "你可以尝试左右或上下滑动来控制图中红色的图形的移动", "当你移动幅度小 时, 它的速度也是缓慢的, 反正则较快",
			"是不是很简单呢!接下来我们来尝试几个简单的小游戏吧!", "" };
	private int index = 0;

	/**
	 * 游戏初始化
	 */
	public Game_1() {
		mRedSnake = new RedSnake();
		mList = mRedSnake.getList();
		decreaseAlpha = Utils.alphaDecreaseInNearBytime(3);
	}

	@Override
	public void draw(Canvas canvas, Paint paint, int screenWidth, int screenHeight) {
		if (alpha < 10) {
			if (index < 4) {
				index++;
				if (index == 4) {
					MainActivity.currentRelevant = 2;
					MyAplication.getSurfaceView().setOnISurfaceViewCallBack(new Game_2());
				}
				alpha = 255;
			}
		}
		Utils.drawAlphaText(Position.CEN, canvas, text[index], paint, alpha);

		paint.setColor(mRedSnake.getColor());
		for (Node pos : mList) {
			canvas.drawCircle(pos.getX(), pos.getY(), mRedSnake.getRadius(), paint);
		}
	}

	@Override
	public void logic() {
		mRedSnake.moveSnake(xm, ym);
		if (alpha > 0) {
			alpha -= decreaseAlpha;
		}
	}

	@Override
	public void surfaceCreatedCallBack(int screenW, int screenH) {
	}

	@Override
	public boolean onTouchEventCallBack(MotionEvent event) {
		int action = event.getAction();
		if (action == MotionEvent.ACTION_DOWN) {
			x = (int) event.getX();
			y = (int) event.getY();
		} else if (action == MotionEvent.ACTION_MOVE) {
			xm = (event.getX() - x);
			ym = (event.getY() - y);
		} else if (action == MotionEvent.ACTION_UP) {
			xm = 0;
			ym = 0;
		}
		return true;
	}

}
