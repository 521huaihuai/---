package com.imooc.myParticle;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import com.imooc.myConstant.MyConstant;
import com.imooc.mySnake.Node;
import com.imooc.mySnake.Snake;
import com.imooc.mySufaceView.Pos;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class PowerfulParticleAbstract extends Particle implements IPowerfulParticle
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7104933518482718118L;
	// ���ܳ���ʱ��
	public static float effectTime = 0.5f;
	// �����ڸó���ʱ���ڽ��еĲ������ƴ���
	protected float mTimes;
	//
	private boolean isFirstEat = true;
	// ��¼�Ե���������ʱ�߸����ڵ��λ�ü���
	private LinkedList<Pos> mPos;
	// ��󷵻ص�����
	private Vector<PieceParticle> mParticles;
	// �������Ӳ���Ч����Ļص��ӿ�
	protected IPowerfulParticleListener powerfulParticleListener;


	public PowerfulParticleAbstract(int mColor, int mx, int my)
	{
		this(0, mColor, MyConstant.POWERFULPARTICLE_RADIUS, mx, my, 0);
	}

	public PowerfulParticleAbstract(int mColor, int mx, int my, double mDirection)
	{
		this(0, mColor, MyConstant.POWERFULPARTICLE_RADIUS, mx, my, mDirection);
	}

	public PowerfulParticleAbstract(int mColor, int mRadius, int mx, int my, double mDirection)
	{
		this(0, mColor, mRadius, mx, my, mDirection);
	}

	public PowerfulParticleAbstract(long mLiveTime, int mColor, int mRadius, int mx, int my, double mDirection)
	{
		super(mLiveTime, mColor, mRadius, mx, my, mDirection);
		mParticles = new Vector<PieceParticle>();
		mPos = new LinkedList<Pos>();
		mDirection = 360 * Math.random();
		mTimes = ((effectTime * 1000 / MyConstant.SLEEPTIME));
	}

	public void setOnPowerfulParticleListener(IPowerfulParticleListener powerfulParticleListener)
	{
		this.powerfulParticleListener = powerfulParticleListener;
	}

	public abstract void drawPowerfulParticle(Canvas canvas, Paint paint);

	@Override
	public Vector<PieceParticle> releaseEffectParticle(Vector<PieceParticle> vector, Snake snake, Canvas canvas, Paint paint, int times)
	{
		canvas.save();
		if (isFirstEat)
		{
			List<Node> mList = snake.getList();
			Pos pos = null;
			for (Node mNode : mList)
			{
				pos = new Pos(mNode.getX(), mNode.getY());
				mPos.add(pos);
			}
			isFirstEat = false;
		}
		// ��������ʵ�־������
		effectParticle(mPos, snake, canvas, paint, times);
		// ������в������ϵĵ�,�������(�������100��)
		int startX = mPos.getFirst().getX();
		int endX = mPos.getLast().getX();
		int starty = mPos.getFirst().getY();
		int endy = mPos.getLast().getY();
		for (PieceParticle mParticle : vector)
		{
			// ��������ⷶΧ�ڵĵ�ֱ�����
			if (RangeDetermination(mParticle, startX, starty, endX, endy))
			{
				mParticles.add(mParticle);
			}
			else
			{
				// ��Ҫ���������
				removeParticle(mParticle);
			}
		}
		canvas.restore();
		return mParticles;
	}

	/**
	 * ������Ч������
	 * 
	 * @param mPos
	 *            Ч��������
	 * @param snake
	 *            ��
	 * @param canvas
	 *            ����
	 * @param paint
	 *            ����
	 * @param times
	 *            �ڼ��λ���
	 */
	public abstract void effectParticle(LinkedList<Pos> mPos, Snake snake, Canvas canvas, Paint paint, int times);

	// ��Ҫ���������
	public abstract void removeParticle(PieceParticle particle);

	/**
	 * ��Χ�ж�
	 * 
	 * @return �Ƿ����ж��ķ�Χ��, ����Ƿ���<code>true</code>, ����Ϊ<code>false</code>
	 */
	public abstract boolean RangeDetermination(PieceParticle currentParticle, int startx, int starty, int endx, int endy);

}
