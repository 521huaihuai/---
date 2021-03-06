package com.imooc.myColorState;

import java.util.Vector;

import com.imooc.block.Block;
import com.imooc.block.DenseFog;
import com.imooc.block.HideTask;
import com.imooc.myConstant.MyConstant;
import com.imooc.myCrossListener.ICrossBlockListener;
import com.imooc.myCrossListener.ICrossHidePosListener;
import com.imooc.myCrossListener.ICrossParticleListener;
import com.imooc.myParticle.PieceParticle;

import android.util.Log;

/**
 * 当前为蓝色状态
 * 
 * @author Administrator
 *
 */
public class BlueState implements State
{

	private ICrossParticleListener mCrossListener = null;
	private ICrossBlockListener mCrossBlockListener = null;
	private ICrossHidePosListener mCrossHidePosListener = null;


	@Override
	public void handle_01()
	{

	}

	/**
	 * 当蛇穿过粒子时所处理的事件
	 */
	@Override
	public void handle_02(Vector<PieceParticle> vector)
	{
		for (PieceParticle particle : vector)
		{
			if (particle.getColor() == MyConstant.COLOR_BLUE)
			{
				Log.e("521huaihuai", "sameColorCrossHandle");
				// 如果是同色
				if (mCrossListener != null)
				{
					mCrossListener.sameColorCrossHandle(MyConstant.COLOR_BLUE);
				}
			}
			else if (particle.getColor() == MyConstant.COLOR_BLACK)
			{
				Log.e("521huaihuai", "oppositeColorCrossHandle");
				// 如果是黑色
				if (mCrossListener != null)
				{
					mCrossListener.oppositeColorCrossHandle(MyConstant.COLOR_BLACK);
				}
			}
			else if (particle.getColor() == MyConstant.COLOR_GREEN)
			{
				Log.e("521huaihuai", "birthColorCrosshandle");
				if (mCrossListener != null)
				{
					mCrossListener.birthColorCrosshandle(particle.getColor());
				}
			}
			else
			{
				if (mCrossListener != null)
				{
					mCrossListener.differentColorCrossHandle(particle.getColor());
				}
			}

		}
	}

	@Override
	public void handle_03(DenseFog denseFog)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void handle_04(Block block)
	{
		if (block.getColor() == MyConstant.COLOR_BLUE)
		{
			Log.e("521huaihuai", "sameColorCrossHandle");
			// 如果是同色
			if (mCrossBlockListener != null)
			{
				mCrossBlockListener.onSameColorCrossBlock(block, MyConstant.COLOR_BLUE);
			}
		}
		else if (block.getColor() == MyConstant.COLOR_BLACK)
		{
			Log.e("521huaihuai", "oppositeColorCrossHandle");
			// 如果是
			if (mCrossBlockListener != null)
			{
				mCrossBlockListener.onOppositeColorCrossBlock(block, MyConstant.COLOR_BLACK);
			}
		}
		else if (block.getColor() == MyConstant.COLOR_GREEN)
		{
			Log.e("521huaihuai", "birthColorCrosshandle");
			if (mCrossListener != null)
			{
				mCrossBlockListener.onDifferentColorCrossBlock(block, block.getColor());
			}
		}
		else
		{
			if (mCrossBlockListener != null)
			{
				mCrossBlockListener.onBirthColorCrossBlock(block, block.getColor());
			}
		}
	}

	@Override
	public void handle_05(HideTask hTask)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setOnCrossListener(ICrossParticleListener mCrossListener)
	{
		if (mCrossListener != null)
		{
			this.mCrossListener = mCrossListener;
		}

	}

	@Override
	public void setOnCrossBlockListener(ICrossBlockListener mCrossBlockListener)
	{
		if (mCrossBlockListener != null)
		{
			this.mCrossBlockListener = mCrossBlockListener;
		}
	}

	@Override
	public void setOnCrossHidePosListener(ICrossHidePosListener mCrossHidePosListener)
	{
		if (mCrossHidePosListener != null)
		{
			this.mCrossHidePosListener = mCrossHidePosListener;
		}
	}

}
