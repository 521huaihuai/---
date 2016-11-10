package com.imooc.particle;

import java.util.LinkedList;

import com.imooc.myConstant.MyConstant;
import com.imooc.mySufaceView.MainActivity;
import com.imooc.mySufaceView.Pos;
import com.imooc.snake.Snake;
import com.imooc.utils.Utils;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * 竖直消除效果
 * 
 * @author zjm
 * @since 1.0
 *
 */
public class PowerfulParticle_horizontal extends PowerfulParticleAbstract {



	public PowerfulParticle_horizontal(int mColor, int mx, int my)
	{
		super(mColor, mx, my);
	}

	@Override
	public void drawPowerfulParticle(Canvas canvas, Paint paint) {
		paint.setColor(mColor);
		canvas.drawCircle(mx, my, mRadius, paint);
		paint.setColor(Utils.getDifferentColor(mColor));
		paint.setStrokeWidth(MyConstant.STROKE_WIDTH);
		canvas.drawLine(mx - 0.6f * mRadius, my, mx + 0.6f * mRadius, my, paint);
		paint.setStrokeWidth(1);
	}

	public PowerfulParticle_horizontal(int mColor, int mx, int my, double mDirection)
	{
		super(mColor, mx, my, mDirection);
		// TODO Auto-generated constructor stub
	}

	public PowerfulParticle_horizontal(int mColor, int mRadius, int mx, int my, double mDirection)
	{
		super(mColor, mRadius, mx, my, mDirection);
		// TODO Auto-generated constructor stub
	}

	public PowerfulParticle_horizontal(long mLiveTime, int mColor, int mRadius, int mx, int my,
			double mDirection)
	{
		super(mLiveTime, mColor, mRadius, mx, my, mDirection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void effectParticle(LinkedList<Pos> mPos, Snake snake, Canvas canvas, Paint paint, int times) {
		int x;
		int y;
		float step = 0;
		Pos node = null;
		for (int i = 0; i < mPos.size(); i++) {
			paint.setColor(snake.getList().get(i).getColor());
			node = mPos.get(i);
			x = node.getX();
			y = node.getY();
			step = (Math.max(MainActivity.screenWidth - x, x)) / mTimes;
			canvas.drawLine(x, y, x - times * step, y, paint);
			canvas.drawLine(x, y, x + step * times, y, paint);
		}
		// step = (Math.max(MainActivity.screenHeight - my, my)) / mTimes;
		// canvas.drawLine(mx, my, mx, my + step * times, paint);
		// canvas.drawLine(mx, my, mx, my - step * times, paint);
	}

	@Override
	public void removeParticle(PieceParticle particle) {
		if (powerfulParticleListener != null) {
			powerfulParticleListener.onRemoveParticleCallBack(particle);
		}
	}

	@Override
	public boolean RangeDetermination(PieceParticle mParticle, int startx, int starty, int endx, int endy) {
		return (mParticle.getY() + mParticle.getRadius()) < Math.min(starty, endy)
				|| (mParticle.getY() - mParticle.getRadius()) > Math.max(starty, endy);
	}

}
