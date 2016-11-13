package com.imooc.myParticle;

import java.util.Vector;

import com.imooc.myConstant.MyConstant;
import com.imooc.mySufaceView.MainActivity;

/**
 * 粒子管理者 </br>
 * 用于管理粒子的创建,移除等
 * 
 * @author zjm
 *
 */
public class ParticleManager
{
	
	private static ParticleManager manager = new ParticleManager();
	
	private ParticleManager()
	{
	}
	
	public static ParticleManager newInstance()
	{
		return manager;
	}

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
