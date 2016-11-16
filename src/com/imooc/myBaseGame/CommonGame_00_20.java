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

	// ����
	private Vector<PieceParticle> mVector;
	// ��
	protected Snake mSnake;
	// �ռ�����
	protected int mCollectionNUM = 0;
	// �߽ڵ�Ĺ���
	private LinkedList<Node> mList;
	// ��ͷ�������
	private int x;
	private int y;
	// ��ָ��������
	private float xm;
	private float ym;
	// ʱ������
	protected long timeLimite;
	// ���ڼ�ʱ
	private long oldTime = 0;
	// �߰뾶
	private float radius;

	// �ƶ������ص�
	private IMoveListener moveListener;


	// ��ʼ��
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
		// �����������ʱ��,��Ĭ������Ϊһ���Ƚϳ���ʱ��
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
	 * ��Ϸ����λ��(����ǰ�ؿ�)
	 */
	public abstract int gameOverPos();

	/**
	 * �Ժ���Ϊ��λ, ������Ϸ����ʱ��
	 */
	public abstract long setTimeLimite();

	/**
	 * ��ȡĬ�ϵ���
	 */
	public abstract Snake getSnake();

	/**
	 * Ѫ����Ϊ0
	 */
	public abstract void hpIsOver();

	/**
	 * ��ʱ�ص�
	 */
	public abstract void timeIsOver(long usedTime);

	/**
	 * ����Ļ���
	 * 
	 * @param canvas
	 * @param paint
	 * @param screenWidth
	 * @param screenHeight
	 */
	public abstract void childDraw(Canvas canvas, Paint paint, int screenWidth, int screenHeight);

	/**
	 * ������߼�
	 */
	public abstract void childLogic();

	@Override
	public void draw(Canvas canvas, Paint paint, int screenWidth, int screenHeight)
	{
		// ������ (Ϊÿ��������ɫ)// ���ƴ��2-3ms
		for (Node node : mList)
		{
			paint.setColor(node.getColor());
			canvas.drawCircle(node.getX(), node.getY(), mSnake.getRadius(), paint);
		}
		// ����Ļ���
		childDraw(canvas, paint, screenWidth, screenHeight);
		
		// ���ƴ��1-2ms
		paint.setColor(MyConstant.COLOR_RED);
		// ����Ѫ��
		Utils.drawHp(canvas, paint, radius / 2, mSnake.getCurrentHp());
		// �����ռ�
		Utils.drawCollection(canvas, paint, mCollectionNUM);
		// ����ʱ��
		Utils.drawTime(canvas, paint, screenWidth, System.currentTimeMillis() - oldTime);
		
		// ��������//���ƴ��8-10ms
		for (PieceParticle particle : mVector)
		{
			paint.setColor(particle.getColor());
			canvas.drawCircle(particle.getX(), particle.getY(), particle.getRadius(), paint);
		}
	}

	@Override
	public void logic()
	{
		// ����Ƿ�Խ��,Խ�����޷������ƶ�(���ڿ�������̽��)
		checkIsOutofView();
		// �ߵ��ƶ�, ������д�����moveSnake����,�ı��ƶ���ʽ
		mSnake.moveSnake(xm, ym);
		// ��⵱ǰ��һ���ڵ��Ƿ����κε���ײ
		Vector<PieceParticle> vector = getAllCrossParticle(mVector);
		// ����ߴ���ĳ���������������¼�
		mSnake.crossParticle(vector);
		// �Ƴ������ĵ�
		removeAllCrossParticle(vector);
		// �����ʱ ,��������ʾ
		if ((System.currentTimeMillis() - oldTime) > timeLimite)
		{
			timeIsOver((System.currentTimeMillis() - oldTime));
		}
		// ��ȡ��ǰ��Ѫ��, ����Ƿ������Ϸ������ȥ
		if (mSnake.getCurrentHp() <= 0)
		{
			// Ѫ��Ϊ��ʱ, ֪ͨ��������߼�����
			hpIsOver();
		}
		childLogic();
	}

	/**
	 * ����Ƿ�Խ��
	 * 
	 * @param g
	 * @param f
	 */
	private void checkIsOutofView()
	{

		// ���Խ��
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
	 * ����Ҫ��ʱ��
	 */
	public long getUsedTime()
	{
		return (System.currentTimeMillis() - oldTime) / 1000;
	}

	/**
	 * ��ȡ��ײ��
	 * 
	 * @param mVector2
	 * @return
	 */
	public Vector<PieceParticle> getAllCrossParticle(Vector<PieceParticle> mVector2)
	{

		// ˼������Ż��㷨

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
				// ��ײ
				vector.add(particle);
			}
		}
		return vector;
	}


	/**
	 * �Ƴ����д�����
	 */
	private void removeAllCrossParticle(Vector<PieceParticle> vector)
	{
		for (PieceParticle particle : vector)
		{
			mVector.remove(particle);
		}
	}


	/**
	 * ��ȡ����
	 */
	protected Vector<PieceParticle> createPartice()
	{
		ParticleManager manager = ParticleManager.newInstance();
		return manager.createParticle(100);
	}

	/**
	 * ������������(Ĭ�ϲ�����, ���������д)
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
	 * �ƶ������ӿ�
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
