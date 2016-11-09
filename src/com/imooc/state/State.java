package com.imooc.state;

import java.util.Vector;

import com.imooc.block.Block;
import com.imooc.block.DenseFog;
import com.imooc.block.HideTask;
import com.imooc.block.ICrossBlockListener;
import com.imooc.block.ICrossHidePosListener;
import com.imooc.block.ICrossParticleListener;
import com.imooc.particle.PieceParticle;

/**
 * 主要处理不同颜色所产生不同效果而设计 (状态模式)
 * 
 * @author Administrator
 *
 */
public interface State {

	public void setOnCrossListener(ICrossParticleListener mCrossListener);

	public void setOnCrossBlockListener(ICrossBlockListener mCrossBlockListener);

	public void setOnCrossHidePosListener(ICrossHidePosListener mCrossHidePosListener);

	/**
	 * 处理事件1(自身的技能,与节点颜色排序为主)
	 */
	void handle_01();

	/**
	 * 处理事件2(颜色之间的转化映射属性的转化)
	 */
	void handle_02(Vector<PieceParticle> vector);

	/**
	 * 处理迷雾
	 */
	void handle_03(DenseFog denseFog);

	/**
	 * 处理障碍物
	 */
	void handle_04(Block block);

	/**
	 * 处理隐藏任务
	 */
	void handle_05(HideTask hTask);
}
