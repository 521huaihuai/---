package com.imooc.myCrossListener;

/**
 * �������Ӽ���
 * 
 * @author Administrator
 *
 */
public interface ICrossParticleListener {
	void sameColorCrossHandle(int color);

	void oppositeColorCrossHandle(int color);

	void birthColorCrosshandle(int color);

	void differentColorCrossHandle(int color);
}