package com.imooc.particle;

import java.util.LinkedList;

import com.imooc.myConstant.MyConstant;
import com.imooc.myConstant.Pos;
import com.imooc.mySufaceView.MainActivity;
import com.imooc.snake.Snake;
import com.imooc.utils.Utils;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * 竖直消除效果
 * 
 * @author Administrator
 *
 */
public class PowerfulParticle_vertical extends PowerfulParticleAbstract
{

	public PowerfulParticle_vertical(int mColor, int mx, int my)
	{
		super(mColor, mx, my);
	}

	public PowerfulParticle_vertical(int mColor, int mx, int my, double mDirection)
	{
		super(mColor, mx, my, mDirection);
	}

	public PowerfulParticle_vertical(int mColor, int mRadius, int mx, int my, double mDirection)
	{
		super(mColor, mRadius, mx, my, mDirection);
	}

	public PowerfulParticle_vertical(long mLiveTime, int mColor, int mRadius, int mx, int my,
			double mDirection)
	{
		super(mLiveTime, mColor, mRadius, mx, my, mDirection);
	}

	@Override
	public void drawPowerfulParticle(Canvas canvas, Paint paint)
	{
		paint.setColor(mColor);
		canvas.drawCircle(mx, my, mRadius, paint);
		paint.setColor(Utils.getDifferentColor(mColor));
		paint.setStrokeWidth(MyConstant.STROKE_WIDTH);
		canvas.drawLine(mx, my - 0.6f * mRadius, mx, my + 0.6f * mRadius, paint);
		paint.setStrokeWidth(1);
	}

	@Override
	public void effectParticle(LinkedList<Pos> mPos, Snake snake, Canvas canvas, Paint paint, int times)
	{
		int x;
		int y;
		float step = 0;
		Pos node = null;
		for (int i = 0; i < mPos.size(); i++)
		{
			paint.setColor(snake.getList().get(i).getColor());
			node = mPos.get(i);
			x = node.getX();
			y = node.getY();
			step = (Math.max(MainActivity.screenHeight - y, y)) / mTimes;
			canvas.drawLine(x, y, x, y - times * step, paint);
			canvas.drawLine(x, y, x, y + step * times, paint);
		}
		// step = (Math.max(MainActivity.screenHeight - my, my)) / mTimes;
		// canvas.drawLine(mx, my, mx, my + step * times, paint);
		// canvas.drawLine(mx, my, mx, my - step * times, paint);
	}

	@Override
	public void removeParticle(PieceParticle particle)
	{
		if (powerfulParticleListener != null)
		{
			powerfulParticleListener.onRemoveParticleCallBack(particle);
		}
	}

	@Override
	public boolean RangeDetermination(PieceParticle mParticle, int startx, int starty, int endx, int endy)
	{
		return (mParticle.getX() + mParticle.getRadius()) < Math.min(startx, endx)
				|| (mParticle.getX() - mParticle.getRadius()) > Math.max(startx, endx);
	}

}
