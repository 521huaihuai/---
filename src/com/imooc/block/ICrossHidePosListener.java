package com.imooc.block;

public interface ICrossHidePosListener
{

	void crossHidePos(HideTask hideTask);

	void onSameColorCrossHideTask(HideTask hTask, int colorBlack);

	void onOppositeColorCrossHideTask(HideTask hTask, int colorGreen);

	void onDifferentColorCrossHideTask(HideTask hTask, int color);

	void onBirthColorCrossHideTask(HideTask hTask, int color);
}
