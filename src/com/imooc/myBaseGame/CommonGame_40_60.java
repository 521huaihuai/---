package com.imooc.myBaseGame;

import java.util.LinkedList;
import java.util.Vector;

import com.imooc.block.Block;
import com.imooc.block.DenseFog;
import com.imooc.control.IMoveListener;
import com.imooc.myConstant.MyConstant;
import com.imooc.myCrossListener.ICrossBlockListener;
import com.imooc.myCrossListener.ICrossDenfogListener;
import com.imooc.myCrossListener.ICrossParticleListener;
import com.imooc.myParticle.IPowerfulParticleListener;
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
import android.util.Log;
import android.view.MotionEvent;

public abstract class CommonGame_40_60 implements ISurfaceViewCallBack, ICrossParticleListener, IPowerfulParticleListener, ICrossBlockListener, ICrossDenfogListener
{

	// ����
	protected Vector<PieceParticle> mVector;
	// ��������
	protected Vector<PowerfulParticleAbstract> mPowfularticles;
	// ��
	protected Snake mSnake;
	// �谭��
	private Vector<Block> mBlocks;
	private Vector<DenseFog> mDenfogs;
	// �ռ�����
	protected int mCollectionNUM = 0;
	// �߽ڵ�Ĺ���
	private LinkedList<Node> mList;
	// �ߵ�ͷ���;
	private Node firstNode;
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
	private int powParticleIndex = 0;
	private boolean isReadyRelerasePow;

	// �����ͷŵļ���
	private PowerfulParticleAbstract powerfulParticle;


	// ��ʼ��
	public CommonGame_40_60()
	{
		mSnake = getSnake();
		if (mSnake == null)
		{
			mSnake = new RedSnake();
		}
		mBlocks = getBlock();
		mDenfogs = getDenseFog();
		mList = mSnake.getList();
		firstNode = mList.getFirst();
		radius = mSnake.getRadius();
		timeLimite = setTimeLimite();
		// �����������ʱ��,��Ĭ������Ϊһ���Ƚϳ���ʱ��
		if (timeLimite == 0)
		{
			timeLimite = 99999999;
		}
		oldTime = System.currentTimeMillis();
		mVector = createPartice();
		mPowfularticles = createPowfulPartice();
		if (mPowfularticles != null)
		{
			for (PowerfulParticleAbstract pAbstract : mPowfularticles)
			{
				pAbstract.setOnPowerfulParticleListener(this);
			}
		}
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
	 * ��ȡ�谭��
	 */
	public abstract Vector<Block> getBlock();

	/**
	 * ��ȡ����
	 */
	public abstract Vector<DenseFog> getDenseFog();

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
		// ������ (Ϊÿ��������ɫ)
		Node node = null;
		for (int i = mList.size() - 1; i >= 0; i--)
		{
			node = mList.get(i);
			paint.setColor(node.getColor());
			canvas.drawCircle(node.getX(), node.getY(), mSnake.getRadius(), paint);
		}
		// ����Ļ���
		childDraw(canvas, paint, screenWidth, screenHeight);
		paint.setColor(MyConstant.COLOR_RED);
		// ����Ѫ��
		Utils.drawHp(canvas, paint, radius / 2, mSnake.getCurrentHp());
		// �����ռ�
		Utils.drawCollection(canvas, paint, mCollectionNUM);
		// ����ʱ��
		Utils.drawTime(canvas, paint, screenWidth, System.currentTimeMillis() - oldTime);
		// ��������
		for (PieceParticle particle : mVector)
		{
			paint.setColor(particle.getColor());
			canvas.drawCircle(particle.getX(), particle.getY(), particle.getRadius(), paint);
		}
		// �����ϰ�
		if (mBlocks != null)
		{
			for (Block block : mBlocks)
			{
				block.drawBlock(canvas, paint, screenWidth, screenHeight);
			}
		}
		// ��������
		if (mDenfogs != null)
		{
			for (DenseFog denseFog : mDenfogs)
			{
				denseFog.drawDenseFog(canvas, paint, screenWidth, screenHeight);
			}
		}
		// ���Ƽ�������
		if (mPowfularticles != null)
		{
			for (PowerfulParticleAbstract powerfulParticle : mPowfularticles)
			{
				powerfulParticle.drawPowerfulParticle(canvas, paint);
			}
			// ��⵱ǰ��һ���ڵ��Ƿ����κμ��ܻ��
			// ������κεļ��ܻ�ȡ,���б�־
			if (getAllPowerfulParticle(mPowfularticles) != null)
			{
				powerfulParticle = getAllPowerfulParticle(mPowfularticles);
				isReadyRelerasePow = true;
				powParticleIndex = 0;
			}
			// �Ƴ�������������
			removeCrossPowerfulParticle(powerfulParticle);
		}
		// �������׼����Ͼ��ͷ�
		if (isReadyRelerasePow)
		{
			if (powParticleIndex >= (PowerfulParticleAbstract.effectTime * 1000 / MyConstant.SLEEPTIME))
			{
				isReadyRelerasePow = false;
			}
			powParticleIndex++;
			Log.e("521huaihuai", "powParticleIndex = " + powParticleIndex);
			mVector = powerfulParticle.releaseEffectParticle(mVector, mSnake, canvas, paint, powParticleIndex);
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

		mSnake.crossBlock(getTouchBlock());
		mSnake.crossDenfog(getTouchDenseFog());
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
		firstNode = mList.getFirst();
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
		float currentx = firstNode.getX();
		float currentY = firstNode.getY();
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
	 * ��ȡ�谭����ײ��
	 * 
	 * @return Block
	 */
	private Block getTouchBlock()
	{
		if (mBlocks != null)
		{
			for (Block block : mBlocks)
			{
				if (block.judgeIsInBlock(firstNode)) { return block; }
			}
		}
		return null;
	}

	/**
	 * ��ȡ������ײ��
	 * 
	 * @return Block
	 */
	private DenseFog getTouchDenseFog()
	{
		if (mDenfogs != null)
		{
			for (DenseFog denseFog : mDenfogs)
			{
				if (denseFog.judgeIsInDenseFog(firstNode)) { return denseFog; }
			}
		}
		return null;
	}

	/**
	 * ��ȡ��ײ��
	 * 
	 * @param mVector2
	 * @return Vector
	 */
	public Vector<PieceParticle> getAllCrossParticle(Vector<PieceParticle> mVector2)
	{

		// ˼������Ż��㷨

		Vector<PieceParticle> vector = new Vector<PieceParticle>();
		int cenX = firstNode.getX();
		int cenY = firstNode.getY();
		int currentX;
		int currentY;
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
	 * �ж��Ƿ��ȡ����
	 * 
	 * @param mPowfularticles2
	 * @return
	 */
	private PowerfulParticleAbstract getAllPowerfulParticle(Vector<PowerfulParticleAbstract> mPowfularticles2)
	{
		if (mPowfularticles2 == null) { return null; }
		int cenX = firstNode.getX();
		int cenY = firstNode.getY();
		int currentX;
		int currentY;
		for (PowerfulParticleAbstract iPowerfulParticle : mPowfularticles2)
		{
			currentX = iPowerfulParticle.getX();
			currentY = iPowerfulParticle.getY();
			int distance = (int) Math.sqrt(Math.pow((cenX - currentX), 2) + Math.pow((cenY - currentY), 2));
			if (distance < (MyConstant.SNAKE_RADIUS + iPowerfulParticle.getRadius()))
			{
				// ��ײ
				return iPowerfulParticle;
			}
		}
		return null;
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
	 * �Ƴ������ļ��ܵ�
	 * 
	 * @param powerfulParticle
	 */
	private void removeCrossPowerfulParticle(PowerfulParticleAbstract powerfulParticle)
	{
		if (mPowfularticles != null)
		{
			mPowfularticles.remove(powerfulParticle);
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
