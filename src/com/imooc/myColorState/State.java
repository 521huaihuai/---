package com.imooc.myColorState;

import java.util.Vector;

import com.imooc.block.Block;
import com.imooc.block.DenseFog;
import com.imooc.block.HideTask;
import com.imooc.myCrossListener.ICrossBlockListener;
import com.imooc.myCrossListener.ICrossHidePosListener;
import com.imooc.myCrossListener.ICrossParticleListener;
import com.imooc.myParticle.PieceParticle;

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
