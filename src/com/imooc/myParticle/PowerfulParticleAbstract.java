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
	// 技能持续时间
	public static float effectTime = 0.5f;
	// 技能在该持续时间内进行的操作绘制次数
	protected float mTimes;
	//
	private boolean isFirstEat = true;
	// 记录吃到技能粒子时蛇各个节点的位置集合
	private LinkedList<Pos> mPos;
	// 最后返回的粒子
	private Vector<PieceParticle> mParticles;
	// 技能粒子产生效果后的回调接口
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
		// 交给子类实现具体绘制
		effectParticle(mPos, snake, canvas, paint, times);
		// 检测所有不在线上的点,进行添加(操作大概100次)
		int startX = mPos.getFirst().getX();
		int endX = mPos.getLast().getX();
		int starty = mPos.getFirst().getY();
		int endy = mPos.getLast().getY();
		for (PieceParticle mParticle : vector)
		{
			// 如果是在这范围内的点直接添加
			if (RangeDetermination(mParticle, startX, starty, endX, endy))
			{
				mParticles.add(mParticle);
			}
			else
			{
				// 将要处理的粒子
				removeParticle(mParticle);
			}
		}
		canvas.restore();
		return mParticles;
	}

	/**
	 * 产生的效果绘制
	 * 
	 * @param mPos
	 *            效果点坐标
	 * @param snake
	 *            蛇
	 * @param canvas
	 *            画布
	 * @param paint
	 *            画笔
	 * @param times
	 *            第几次绘制
	 */
	public abstract void effectParticle(LinkedList<Pos> mPos, Snake snake, Canvas canvas, Paint paint, int times);

	// 将要处理的粒子
	public abstract void removeParticle(PieceParticle particle);

	/**
	 * 范围判定
	 * 
	 * @return 是否在判定的范围外, 如果是返回<code>true</code>, 否则为<code>false</code>
	 */
	public abstract boolean RangeDetermination(PieceParticle currentParticle, int startx, int starty, int endx, int endy);

}
