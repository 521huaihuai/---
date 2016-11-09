package com.imooc.block;

public interface ICrossBlockListener
{

	void onSameColorCrossBlock(Block block, int color);

	void onOppositeColorCrossBlock(Block block, int color);

	void onBirthColorCrossBlock(Block block, int color);

	void onDifferentColorCrossBlock(Block block, int color);

}
