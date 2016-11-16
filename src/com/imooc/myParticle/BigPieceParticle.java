package com.imooc.myParticle;

import java.io.Serializable;

import com.imooc.myConstant.MyConstant;

/**
 * ��������</br>
 * ���ɱ���, ���ڹ滮·��
 * 
 * @author zjm
 *
 */
public class BigPieceParticle extends Particle implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -123123451L;


	public BigPieceParticle(int color, int mx, int my)
	{
		this(color, MyConstant.BIG_PARTICLE_RADIUS, mx, my);
	}

	/**
	 * ��ֹ�Ĵ�������, �ʺ�����
	 * 
	 * @param mColor
	 * @param mRadius
	 * @param mx
	 * @param my
	 */
	public BigPieceParticle(int mColor, int mRadius, int mx, int my)
	{
		super(mColor, mRadius, mx, my);
	}

	/**
	 * �ƶ��Ĵ�������, �ʺ���ײ(Ĭ���ٶ�1)
	 * 
	 * @param mColor
	 * @param mRadius
	 * @param mx
	 * @param my
	 * @param direction
	 */
	public BigPieceParticle(int mColor, int mRadius, int mx, int my, double direction)
	{
		this(0, mColor, mRadius, mx, my, direction);
	}

	public BigPieceParticle(long mLiveTime, int mColor, int mRadius, int mx, int my, double mDirection)
	{
		super(mLiveTime, mColor, mRadius, mx, my, mDirection);
	}

	public BigPieceParticle(int mColor, int mRadius, int mx, int my, double direction, float speed)
	{
		this(0, mColor, mRadius, mx, my, direction, speed, 100);
	}

	public BigPieceParticle(long mLiveTime, int mColor, int mRadius, int mx, int my, double mDirection, float speed, float alpha)
	{
		super(mLiveTime, mColor, mRadius, mx, my, mDirection, speed, alpha);
	}

}
