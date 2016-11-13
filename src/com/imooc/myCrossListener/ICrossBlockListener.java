package com.imooc.myCrossListener;

import com.imooc.block.Block;

public interface ICrossBlockListener
{

	void onSameColorCrossBlock(Block block, int color);

	void onOppositeColorCrossBlock(Block block, int color);

	void onBirthColorCrossBlock(Block block, int color);

	void onDifferentColorCrossBlock(Block block, int color);

}
