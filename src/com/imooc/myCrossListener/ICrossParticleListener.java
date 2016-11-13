package com.imooc.myCrossListener;

/**
 * ´©¹ýÁ£×Ó¼àÌý
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