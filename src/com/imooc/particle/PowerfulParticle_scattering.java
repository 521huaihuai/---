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
 * Á£×ÓÉ¢ÉäÐ§¹û
 * 
 * @author zjm
 *
 */
public class PowerfulParticle_scattering extends PowerfulParticleAbstract
{

	public PowerfulParticle_scattering(int mColor, int mx, int my)
	{
		super(mColor, mx, my);
	}

	public PowerfulParticle_scattering(int mColor, int mx, int my, double mDirection)
	{
		super(mColor, mx, my, mDirection);
	}

	public PowerfulParticle_scattering(int mColor, int mRadius, int mx, int my, double mDirection)
	{
		super(mColor, mRadius, mx, my, mDirection);
	}

	public PowerfulParticle_scattering(long mLiveTime, int mColor, int mRadius, int mx, int my, double mDirection)
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
		canvas.drawCircle(mx, my, mRadius / 2, paint);
		paint.setStrokeWidth(1);
	}

	@Override
	public void effectParticle(LinkedList<Pos> mPos, Snake snake, Canvas canvas, Paint paint, int times)
	{
		float step = 0;
		int size = mPos.size();
		for (int i = 0; i < size; i++)
		{
			paint.setColor(snake.getList().get(i).getColor());
			step = MainActivity.screenHeight * 1.0f / 4 / mTimes;
			canvas.drawLine(mx, my, mx + (float) ((times * step) * Math.cos(Math.PI / size * i)), (float) (my - times * step * Math.sin(Math.PI / size * i)),
					paint);
			canvas.drawLine(mx, my, mx - (float) ((times * step) * Math.cos(Math.PI / size * i)), (float) (my + step * times * Math.sin(Math.PI / size * i)),
					paint);
		}
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

		return (mParticle.getRadius() + MainActivity.screenHeight * 1.0f / 4) < Math
				.sqrt((Math.pow(mParticle.getX() - startx, 2) + Math.pow(mParticle.getY() - starty, 2)));
	}

}
