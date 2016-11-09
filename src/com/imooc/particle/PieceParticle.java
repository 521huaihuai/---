package com.imooc.particle;

import java.util.Vector;

import com.imooc.myConstant.MyConstant;
import com.imooc.mySufaceView.MainActivity;

/**
 * 基本颗粒, 粒子
 * 
 * @author zjm
 *
 */
public class PieceParticle extends Particle
{

	// 粒子管理者
	private static Manager manager = new Manager();

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

	public PieceParticle(long mLiveTime, int mColor, int mRadius, int mx, int my,
			double mDirection)
	{
		super(mLiveTime, mColor, mRadius, mx, my, mDirection);
	}

	public PieceParticle(long mLiveTime, int mColor, int mRadius, int mx, int my, double mDirection, float speed, float alpha)
	{
		super(mLiveTime, mColor, mRadius, mx, my, mDirection, speed, alpha);
	}

	public static class Manager
	{

		/**
		 * 创建count 数量的粒子
		 * 
		 * @param count
		 * @return
		 */
		public Vector<PieceParticle> createParticle(int count)
		{
			Vector<PieceParticle> vector = new Vector<PieceParticle>();
			PieceParticle particle = null;
			for (int i = 0; i < count; i++)
			{
				particle = new PieceParticle(MyConstant.COLORS[(int) (Math.random() * 5)],
						(int) (MainActivity.screenWidth * Math.random()),
						(int) (MainActivity.screenHeight * Math.random()));
				vector.add(particle);
			}
			return vector;
		}

		/**
		 * 创建自定义颜色的粒子
		 * 
		 * @param count
		 * @param colors
		 * @return
		 */
		public Vector<PieceParticle> createParticleColors(int count, int... colors)
		{
			Vector<PieceParticle> vector = new Vector<PieceParticle>();
			PieceParticle particle = null;
			int colorSize = colors.length;
			for (int i = 0; i < count; i++)
			{
				particle = new PieceParticle(colors[(int) (Math.random() * colorSize)],
						(int) (MainActivity.screenWidth * Math.random()),
						(int) (MainActivity.screenHeight * Math.random()));
				vector.add(particle);
			}
			return vector;
		}

		/**
		 * 创建自定义的粒子
		 * 
		 * @param count
		 * @param raius
		 * @return
		 */
		public Vector<PieceParticle> createBIGParticle(int count, int raius)
		{
			Vector<PieceParticle> vector = new Vector<PieceParticle>();
			PieceParticle particle = null;
			for (int i = 0; i < count; i++)
			{
				particle = new PieceParticle(MyConstant.COLORS[(int) (Math.random() * 5)], raius,
						(int) (MainActivity.screenWidth * Math.random()),
						(int) (MainActivity.screenHeight * Math.random()));
				vector.add(particle);
			}
			return vector;
		}

		/**
		 * 创建带有方向的粒子
		 * 
		 * @param count
		 * @param raius
		 * @return
		 */
		public Vector<PieceParticle> createBIGParticleWithMove(int count, int raius)
		{
			Vector<PieceParticle> vector = new Vector<PieceParticle>();
			PieceParticle particle = null;
			for (int i = 0; i < count; i++)
			{
				particle = new PieceParticle(MyConstant.COLORS[(int) (Math.random() * 5)], raius,
						(int) (MainActivity.screenWidth * Math.random()),
						(int) (MainActivity.screenHeight * Math.random()), Math.random() * 360);
				vector.add(particle);
			}
			return vector;
		}

	}

	public static Manager newInstance()
	{
		return manager;
	}

}
