package com.imooc.myCrossListener;

import com.imooc.block.HideTask;

public interface ICrossHidePosListener
{

	void onSameColorCrossHideTask(HideTask hTask, int color);

	void onOppositeColorCrossHideTask(HideTask hTask, int color);

	void onDifferentColorCrossHideTask(HideTask hTask, int color);

	void onBirthColorCrossHideTask(HideTask hTask, int color);
}
