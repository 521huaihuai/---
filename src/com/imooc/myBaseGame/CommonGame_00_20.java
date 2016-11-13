package com.imooc.myBaseGame;

import java.util.LinkedList;
import java.util.Vector;

import com.imooc.control.IMoveListener;
import com.imooc.myConstant.MyConstant;
import com.imooc.myCrossListener.ICrossParticleListener;
import com.imooc.myParticle.ParticleManager;
import com.imooc.myParticle.PieceParticle;
import com.imooc.myParticle.PowerfulParticleAbstract;
import com.imooc.mySnake.Node;
import com.imooc.mySnake.RedSnake;
import com.imooc.mySnake.Snake;
import com.imooc.mySufaceView.ISurfaceViewCallBack;
import com.imooc.mySufaceView.MainActivity;
import com.imooc.utils.Utils;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

public abstract class CommonGame_00_20 implements ISurfaceViewCallBack, ICrossParticleListener
{

	// 粒子
	private Vector<PieceParticle> mVector;
	// 蛇
	protected Snake mSnake;
	// 收集限制
	protected int mCollectionNUM = 0;
	// 蛇节点的管理
	private LinkedList<Node> mList;
	// 蛇头结点坐标
	private int x;
	private int y;
	// 手指滑动距离
	private float xm;
	private float ym;
	// 时间限制
	protected long timeLimite;
	// 用于计时
	private long oldTime = 0;
	// 蛇半径
	private float radius;

	// 移动监听回调
	private IMoveListener moveListener;


	// 初始化
	public CommonGame_00_20()
	{
		mSnake = getSnake();
		if (mSnake == null)
		{
			mSnake = new RedSnake();
		}
		mList = mSnake.getList();
		radius = mSnake.getRadius();
		timeLimite = setTimeLimite();
		// 如果忘记设置时间,则默认设置为一个比较长的时间
		if (timeLimite == 0)
		{
			timeLimite = 99999999;
		}
		oldTime = System.currentTimeMillis();
		mVector = createPartice();
		MainActivity.currentRelevant = gameOverPos();
		mSnake.setOnCrossParticleListener(this);
	}

	/**
	 * 游戏结束位置(即当前关卡)
	 */
	public abstract int gameOverPos();

	/**
	 * 以毫秒为单位, 设置游戏结束时间
	 */
	public abstract long setTimeLimite();

	/**
	 * 获取默认的蛇
	 */
	public abstract Snake getSnake();

	/**
	 * 血量降为0
	 */
	public abstract void hpIsOver();

	/**
	 * 超时回调
	 */
	public abstract void timeIsOver(long usedTime);

	/**
	 * 子类的绘制
	 * 
	 * @param canvas
	 * @param paint
	 * @param screenWidth
	 * @param screenHeight
	 */
	public abstract void childDraw(Canvas canvas, Paint paint, int screenWidth, int screenHeight);

	/**
	 * 子类的逻辑
	 */
	public abstract void childLogic();

	@Override
	public void draw(Canvas canvas, Paint paint, int screenWidth, int screenHeight)
	{
		// 绘制蛇 (为每节设置颜色)// 绘制大概2-3ms
		for (Node node : mList)
		{
			paint.setColor(node.getColor());
			canvas.drawCircle(node.getX(), node.getY(), mSnake.getRadius(), paint);
		}
		// 子类的绘制
		childDraw(canvas, paint, screenWidth, screenHeight);
		
		// 绘制大概1-2ms
		paint.setColor(MyConstant.COLOR_RED);
		// 绘制血量
		Utils.drawHp(canvas, paint, radius / 2, mSnake.getCurrentHp());
		// 绘制收集
		Utils.drawCollection(canvas, paint, mCollectionNUM);
		// 绘制时间
		Utils.drawTime(canvas, paint, screenWidth, System.currentTimeMillis() - oldTime);
		
		// 绘制粒子//绘制大概8-10ms
		for (PieceParticle particle : mVector)
		{
			paint.setColor(particle.getColor());
			canvas.drawCircle(particle.getX(), particle.getY(), particle.getRadius(), paint);
		}
	}

	@Override
	public void logic()
	{
		// 检查是否越界,越界则无法向外移动(后期可以向外探索)
		checkIsOutofView();
		// 蛇的移动, 可以重写蛇类的moveSnake方法,改变移动方式
		mSnake.moveSnake(xm, ym);
		// 检测当前第一个节点是否有任何的碰撞
		Vector<PieceParticle> vector = getAllCrossParticle(mVector);
		// 如果蛇穿过某个粒子所触发的事件
		mSnake.crossParticle(vector);
		// 移除穿过的点
		removeAllCrossParticle(vector);
		// 如果超时 ,给子类提示
		if ((System.currentTimeMillis() - oldTime) > timeLimite)
		{
			timeIsOver((System.currentTimeMillis() - oldTime));
		}
		// 获取当前的血量, 检测是否符合游戏继续下去
		if (mSnake.getCurrentHp() <= 0)
		{
			// 血量为零时, 通知子类具体逻辑处理
			hpIsOver();
		}
		childLogic();
	}

	/**
	 * 检测是否越界
	 * 
	 * @param g
	 * @param f
	 */
	private void checkIsOutofView()
	{

		// 如果越界
		float currentx = mList.getFirst().getX();
		float currentY = mList.getFirst().getY();
		if (currentx <= 0)
		{
			if (xm <= 0)
			{
				xm = 0;
			}
		}
		if (currentx >= MainActivity.screenWidth)
		{
			if (xm >= 0)
			{
				xm = 0;
			}

		}
		if (currentY <= 0)
		{
			if (ym <= 0)
			{
				ym = 0;
			}
		}
		if (currentY >= MainActivity.screenHeight)
		{
			if (ym >= 0)
			{
				ym = 0;
			}
		}
	}

	/**
	 * 所需要的时间
	 */
	public long getUsedTime()
	{
		return (System.currentTimeMillis() - oldTime) / 1000;
	}

	/**
	 * 获取碰撞点
	 * 
	 * @param mVector2
	 * @return
	 */
	public Vector<PieceParticle> getAllCrossParticle(Vector<PieceParticle> mVector2)
	{

		// 思考如何优化算法

		Vector<PieceParticle> vector = new Vector<PieceParticle>();
		int cenX = mList.getFirst().getX();
		int cenY = mList.getFirst().getY();
		int currentX;
		int currentY;
		Utils.setStartTime();
		for (PieceParticle particle : mVector2)
		{
			currentX = particle.getX();
			currentY = particle.getY();
			int distance = (int) Math.sqrt(Math.pow((cenX - currentX), 2) + Math.pow((cenY - currentY), 2));
			if (distance < (MyConstant.SNAKE_RADIUS + particle.getRadius()))
			{
				// 碰撞
				vector.add(particle);
			}
		}
		return vector;
	}


	/**
	 * 移除所有穿过点
	 */
	private void removeAllCrossParticle(Vector<PieceParticle> vector)
	{
		for (PieceParticle particle : vector)
		{
			mVector.remove(particle);
		}
	}


	/**
	 * 获取粒子
	 */
	protected Vector<PieceParticle> createPartice()
	{
		ParticleManager manager = ParticleManager.newInstance();
		return manager.createParticle(100);
	}

	/**
	 * 创建技能粒子(默认不创建, 子类进行重写)
	 */
	protected Vector<PowerfulParticleAbstract> createPowfulPartice()
	{
		return null;
	}

	@Override
	public boolean onTouchEventCallBack(MotionEvent event)
	{
		int action = event.getAction();
		if (action == MotionEvent.ACTION_DOWN)
		{
			x = (int) event.getX();
			y = (int) event.getY();
			if (moveListener != null)
			{
				moveListener.actionDOWN(x, y);
			}
		}
		else if (action == MotionEvent.ACTION_MOVE)
		{
			xm = (event.getX() - x);
			ym = (event.getY() - y);
			if (moveListener != null)
			{
				moveListener.actionMOVE(xm, ym);
			}
		}
		else if (action == MotionEvent.ACTION_UP)
		{
			xm = 0;
			ym = 0;
			if (moveListener != null)
			{
				moveListener.actionUP(x, y);
			}
		}
		return true;
	}

	/**
	 * 移动监听接口
	 * 
	 * @param mListener
	 */
	public void setOnMoveListener(IMoveListener mListener)
	{
		if (mListener != null)
		{
			this.moveListener = mListener;
		}
	}

	@Override
	public void surfaceCreatedCallBack(int screenW, int screenH)
	{

	}

}
