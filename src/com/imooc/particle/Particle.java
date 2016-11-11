package com.imooc.particle;

import com.imooc.mySufaceView.MainActivity;
import com.imooc.mySufaceView.Pos;
import com.imooc.utils.Utils;

/**
 * 粒子类</br>
 * <p>
 * 用于描述粒子的基本信息
 * 
 * @author zjm
 * 
 * @since 1.0
 *
 */
public class Particle
{

	// 粒子颜色
	protected int mColor;
	// 粒子半径
	protected int mRadius;
	// 粒子坐标
	protected int mx, my;
	// 粒子方向
	protected double mDirection;
	// 速度
	protected float mSpeed;
	// 透明度
	protected float mAlpha;
	// 粒子存活时间
	protected long mLiveTime;
	// 超时粒子是否需要移除
	protected boolean isNeedRemove;


	public Particle(int mColor, int mRadius, int mx, int my)
	{
		super();
		this.mColor = mColor;
		this.mRadius = mRadius;
		this.mx = mx;
		this.my = my;
		mLiveTime = 0;
		mDirection = 0;
		mSpeed = 0;
		mAlpha = 100;
	}

	public Particle(long mLiveTime, int mColor, int mRadius, int mx, int my, double mDirection)
	{
		super();
		this.mLiveTime = mLiveTime;
		this.mColor = mColor;
		this.mRadius = mRadius;
		this.mx = mx;
		this.my = my;
		this.mDirection = mDirection;
		mSpeed = 0;
		mAlpha = 100;
	}

	public Particle(long mLiveTime, int mColor, int mRadius, int mx, int my, double mDirection, float speed, float alpha)
	{
		super();
		this.mLiveTime = mLiveTime;
		this.mColor = mColor;
		this.mRadius = mRadius;
		this.mx = mx;
		this.my = my;
		this.mDirection = mDirection;
		mSpeed = speed;
		mAlpha = alpha;
	}

	/**
	 * 判定该粒子是否可以移除
	 * 
	 * @return if <code>Ture</code>, return ture.
	 */
	public boolean isOkRemoveParticle()
	{
		// 透明度为0 ,或者到达生存的时间, 则设置该粒子可以移除
		if (mAlpha == 0 || mLiveTime < 0)
		{
			isNeedRemove = true;
		}
		// 为将来可以移出边界设置, 最大移出范围后粒子将设置为可以移出
		if (mx < (0 - MainActivity.screenWidth / 3) || mx > (MainActivity.screenWidth + MainActivity.screenWidth / 3))
		{
			isNeedRemove = true;
		}
		if (my < (0 - MainActivity.screenWidth / 3) || my > (MainActivity.screenHeight + MainActivity.screenWidth / 3))
		{
			isNeedRemove = true;
		}
		return isNeedRemove;
	}

	/**
	 * 粒子反射
	 * 
	 * @param speed
	 */
	public void reflectParticle(float speed)
	{
		Pos pos = Utils.getMoveDistance(mDirection, mx, my, speed);
		mx = pos.getX();
		my = pos.getY();
		// 如果越界
		if (Utils.isOutOfBunds(mx, my))
		{
			if (mx < 0 || mx > MainActivity.screenWidth)
			{
				mDirection = (180 - mDirection);
			}
			else
			{
				mDirection = -mDirection;
			}
		}
	}

	/**
	 * 触动远离触点粒子
	 * 
	 * @param speed
	 */
	public void onTouchReflectParticle(float x, float y, float speed)
	{
		float direction = (float) (Math.atan((y - my) / (x - mx)) * 180 / Math.PI);
		mDirection = 180 + direction;
		Pos pos = Utils.getMoveDistance(mDirection, mx, my, speed);
		mx = (pos.getX());
		my = (pos.getY());
	}

	public long getLiveTime()
	{
		return mLiveTime;
	}

	public boolean isNeedRemove()
	{
		return isNeedRemove;
	}

	public int getColor()
	{
		return mColor;
	}

	public int getRadius()
	{
		return mRadius;
	}

	public int getX()
	{
		return mx;
	}

	public int getY()
	{
		return my;
	}

	public double getDirection()
	{
		return mDirection;
	}

	public void setLiveTime(long mLiveTime)
	{
		this.mLiveTime = mLiveTime;
	}

	public void setNeedRemove(boolean isNeedRemove)
	{
		this.isNeedRemove = isNeedRemove;
	}

	public void setColor(int mColor)
	{
		this.mColor = mColor;
	}

	public void setRadius(int mRadius)
	{
		this.mRadius = mRadius;
	}

	public void setX(int mx)
	{
		this.mx = mx;
	}

	public void setY(int my)
	{
		this.my = my;
	}

	public void setDirection(double mDirection)
	{
		this.mDirection = mDirection;
	}

	public float getAlpha()
	{
		return mAlpha;
	}

	public void setAlpha(float mAlpha)
	{
		this.mAlpha = mAlpha;
	}

}
