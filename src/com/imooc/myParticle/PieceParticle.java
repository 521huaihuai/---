package com.imooc.myParticle;

import java.io.Serializable;

import com.imooc.myConstant.MyConstant;

/**
 * »ù±¾¿ÅÁ£, Á£×Ó
 * 
 * @author zjm
 *
 */
public class PieceParticle extends Particle implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -12311L;


	public PieceParticle(int color, int mx, int my)
	{
		this(color, MyConstant.PARTICLE_RADIUS, mx, my);
	}

	public PieceParticle(int mColor, int mRadius, int mx, int my)
	{
		super(mColor, mRadius, mx, my);
	}

	public PieceParticle(int mColor, int mRadius, int mx, int my, double direction)
	{
		this(0, mColor, mRadius, mx, my, direction);
	}

	public PieceParticle(long mLiveTime, int mColor, int mRadius, int mx, int my, double mDirection)
	{
		super(mLiveTime, mColor, mRadius, mx, my, mDirection);
	}

	public PieceParticle(long mLiveTime, int mColor, int mRadius, int mx, int my, double mDirection, float speed, float alpha)
	{
		super(mLiveTime, mColor, mRadius, mx, my, mDirection, speed, alpha);
	}

}
