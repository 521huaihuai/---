package com.imooc.snake;

import java.util.LinkedList;
import java.util.Vector;

import com.imooc.block.Block;
import com.imooc.block.DenseFog;
import com.imooc.block.HideTask;
import com.imooc.block.ICrossBlockListener;
import com.imooc.block.ICrossDenfogListener;
import com.imooc.block.ICrossHidePosListener;
import com.imooc.block.ICrossParticleListener;
import com.imooc.control.Control;
import com.imooc.control.Move;
import com.imooc.myConstant.MyConstant;
import com.imooc.mySufaceView.MainActivity;
import com.imooc.particle.PieceParticle;
import com.imooc.state.BlackState;
import com.imooc.state.BlueState;
import com.imooc.state.GoldState;
import com.imooc.state.GreenState;
import com.imooc.state.RedState;
import com.imooc.state.State;

import android.graphics.Color;

public abstract class Snake {

	// 速度
	private float speed = MyConstant.SPEED;
	// 蛇的宽度
	private float radius;
	// 每条蛇由一个一个节点组成
	private LinkedList<Node> mList;
	// 每条蛇的能力
	private State mState;
	// 生命值
	private int hp;
	// 收集数量
	private int collectionNum;
	// 穿过迷雾回调接口
	private ICrossDenfogListener mDenfogListener;
	// 穿过障碍时回调接口
	private ICrossBlockListener blockListener;
	// 穿过隐藏任务时回调接口
	private ICrossHidePosListener mHidePosListener;
	// 穿过粒子的回调接口
	private ICrossParticleListener mCrossParticleListener;

	public void setOnCrossParticleListener(ICrossParticleListener mCrossParticleListener) {
		this.mCrossParticleListener = mCrossParticleListener;
	}

	public void setOnCrossDenfogListener(ICrossDenfogListener mDenfogListener) {
		this.mDenfogListener = mDenfogListener;
	}

	public void setOnCrossBlockListener(ICrossBlockListener blockListener) {
		this.blockListener = blockListener;
	}

	public void setOnCrossHidePosListener(ICrossHidePosListener mHidePosListener) {
		this.mHidePosListener = mHidePosListener;
	}

	/**
	 * 初始化蛇
	 */
	public Snake() {
		mList = new LinkedList<Node>();
		initBody(mList);
		hp = getInitHp();
		radius = mList.getFirst().getRadius();
	}

	/**
	 * 移动
	 * 
	 * @param xm
	 * @param ym
	 */
	public void moveSnake(float xm, float ym) {
		if (mList.size() > 0) {
			Node pos = mList.get(0);
			Move move = Control.move(xm, ym, speed);
			int x = (int) (pos.getX() + move.getX());
			int y = (int) (pos.getY() + move.getY());
			mList.addFirst(new Node(pos.getColor(), x, y, pos.getRadius()));
			mList.removeLast();
		}
	}

	/**
	 * 蛇拥有的能力
	 */
	public void fire() {
		setCurrentState();
		mState.handle_01();
	}

	/**
	 * 蛇穿过粒子时所产生的结果, 具体处理方法交给game的监听者处理
	 */
	public void crossParticle(Vector<PieceParticle> vector) {
		setCurrentState();
		if (mCrossParticleListener != null) {
			mState.setOnCrossListener(mCrossParticleListener);
		}
		if (vector != null) {
			mState.handle_02(vector);
		}
	}

	/**
	 * 蛇穿过迷雾时所产生的结果, 具体处理方法交给game的监听者处理
	 */
	public void crossDenfog(DenseFog denseFog) {
		if (mDenfogListener != null) {
			mDenfogListener.denfogHandle(denseFog, mList.getFirst().getColor());
		}
	}

	/**
	 * 蛇穿过障碍时所产生的结果, 具体处理方法交给game的监听者处理
	 */
	public void crossBlock(Block block) {
		setCurrentState();
		if (blockListener != null) {
			mState.setOnCrossBlockListener(blockListener);
		}
		if (block != null) {
			mState.handle_04(block);
		}
	}

	/**
	 * 蛇穿过隐藏任务时所产生的结果, 具体处理方法交给game的监听者处理
	 */
	public void crossHidePos(HideTask task) {
		setCurrentState();
		if (mHidePosListener != null) {
			mState.setOnCrossHidePosListener(mHidePosListener);
		}
		if (task != null) {
			mState.handle_05(task);
		}
	}

	/**
	 * 设置当前的状态
	 */
	private void setCurrentState() {
		if (mList.size() > 0) {
			int color = mList.getFirst().getColor();
			if (color == MyConstant.COLOR_BLUE) {
				color = Color.BLUE;
			}
			switch (color) {
			case MyConstant.COLOR_RED:
				mState = new RedState();
				break;
			case MyConstant.COLOR_BLACK:
				mState = new BlackState();
				break;
			case Color.BLUE:
				mState = new BlueState();
				break;
			case MyConstant.COLOR_GREEN:
				mState = new GreenState();
				break;
			default:
				mState = new GoldState();
				break;
			}
		} else {
			mState = new RedState();
		}
	}

	/**
	 * 初始化节点(身体)
	 */
	public abstract void initBody(LinkedList<Node> mLinkedList);

	/**
	 * 生命值
	 */
	public abstract int getInitHp();

	public static class Factory {
		public static Snake createSnake(final int hp, final Node... nodes) {
			Snake snake = new Snake() {

				@Override
				public void initBody(LinkedList<Node> mLinkedList) {
					for (Node node : nodes) {
						mLinkedList.add(node);
					}
				}

				@Override
				public int getInitHp() {
					return hp;
				}
			};
			return snake;
		}

		public static Snake createSnake(final int hp, final int x, final int y, final int color, final int length) {
			Snake snake = new Snake() {

				@Override
				public void initBody(LinkedList<Node> mLinkedList) {
					for (int i = 0; i < length; i++) {
						mLinkedList.add(new Node(color, x, y));
					}
				}

				@Override
				public int getInitHp() {
					return hp;
				}
			};
			return snake;
		}

		/**
		 * 创建默认的4节点长度蛇
		 * 
		 * @param list
		 * @param color
		 */
		public static void createPeerNode(LinkedList<Node> list, int color) {
			for (int i = 0; i < 4; i++) {
				list.add(new Node(color, MainActivity.screenWidth / 2, MainActivity.screenHeight / 2));
			}
		}
	}

	public int getColor() {
		return mList.getFirst().getColor();
	}

	public int getLengthCount() {
		return mList.size();
	}

	public float getSpeed() {
		return speed;
	}

	public float getRadius() {
		return radius;
	}

	public LinkedList<Node> getList() {
		return mList;
	}

	public void setSnakeColor(int color) {
		for (Node node : mList) {
			node.setColor(color);
		}
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setRadius(float radius) {
		mList.getFirst().setRadius(radius);
	}

	public void setLinkedList(LinkedList<Node> mLinkedList) {
		this.mList = mLinkedList;
	}

	public int getCollectionNum() {
		return collectionNum;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getCurrentHp() {
		return hp;
	}

	public void setCollectionNum(int collectionNum) {
		this.collectionNum = collectionNum;
	}
}
