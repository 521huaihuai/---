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
 * ��Ҫ����ͬ��ɫ��������ͬЧ������� (״̬ģʽ)
 * 
 * @author Administrator
 *
 */
public interface State {

	public void setOnCrossListener(ICrossParticleListener mCrossListener);

	public void setOnCrossBlockListener(ICrossBlockListener mCrossBlockListener);

	public void setOnCrossHidePosListener(ICrossHidePosListener mCrossHidePosListener);

	/**
	 * �����¼�1(����ļ���,��ڵ���ɫ����Ϊ��)
	 */
	void handle_01();

	/**
	 * �����¼�2(��ɫ֮���ת��ӳ�����Ե�ת��)
	 */
	void handle_02(Vector<PieceParticle> vector);

	/**
	 * ��������
	 */
	void handle_03(DenseFog denseFog);

	/**
	 * �����ϰ���
	 */
	void handle_04(Block block);

	/**
	 * ������������
	 */
	void handle_05(HideTask hTask);
}
