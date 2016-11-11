package com.imooc.particle;

import com.imooc.mySufaceView.MainActivity;
import com.imooc.mySufaceView.Pos;
import com.imooc.utils.Utils;

/**
 * ������</br>
 * <p>
 * �����������ӵĻ�����Ϣ
 * 
 * @author zjm
 * 
 * @since 1.0
 *
 */
public class Particle
{

	// ������ɫ
	protected int mColor;
	// ���Ӱ뾶
	protected int mRadius;
	// ��������
	protected int mx, my;
	// ���ӷ���
	protected double mDirection;
	// �ٶ�
	protected float mSpeed;
	// ͸����
	protected float mAlpha;
	// ���Ӵ��ʱ��
	protected long mLiveTime;
	// ��ʱ�����Ƿ���Ҫ�Ƴ�
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
	 * �ж��������Ƿ�����Ƴ�
	 * 
	 * @return if <code>Ture</code>, return ture.
	 */
	public boolean isOkRemoveParticle()
	{
		// ͸����Ϊ0 ,���ߵ��������ʱ��, �����ø����ӿ����Ƴ�
		if (mAlpha == 0 || mLiveTime < 0)
		{
			isNeedRemove = true;
		}
		// Ϊ���������Ƴ��߽�����, ����Ƴ���Χ�����ӽ�����Ϊ�����Ƴ�
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
	 * ���ӷ���
	 * 
	 * @param speed
	 */
	public void reflectParticle(float speed)
	{
		Pos pos = Utils.getMoveDistance(mDirection, mx, my, speed);
		mx = pos.getX();
		my = pos.getY();
		// ���Խ��
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
	 * ����Զ�봥������
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
