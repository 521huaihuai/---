package com.imooc.myParticle;

import java.util.Vector;

import com.imooc.myConstant.MyConstant;
import com.imooc.mySufaceView.MainActivity;

/**
 * ���ӹ����� </br>
 * ���ڹ������ӵĴ���,�Ƴ���
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
	 * ����count ����������
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
	 * �����Զ�����ɫ������
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
	 * �����Զ��������
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
	 * �������з��������
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
