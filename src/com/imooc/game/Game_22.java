package com.imooc.game;

import java.io.Serializable;
import java.util.Vector;

import com.imooc.block.HideTask;
import com.imooc.myBaseGame.CommonGuideGame_20_40;
import com.imooc.myBaseGame.HideGame_BirthColor;
import com.imooc.myBaseGame.HideGame_DifferColor;
import com.imooc.myBaseGame.HideGame_OppsiteColor;
import com.imooc.myBaseGame.HideGame_SameColor;
import com.imooc.myCrossListener.ICrossHidePosListener;
import com.imooc.myParticle.PieceParticle;
import com.imooc.mySnake.BlueSnake;
import com.imooc.mySnake.Snake;
import com.imooc.mySufaceView.MyAplication;
import com.imooc.utils.Utils;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Game_22 extends CommonGuideGame_20_40 implements ICrossHidePosListener, Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vector<PieceParticle> oldVector;
	private long currentTime;
	/**
	 * 
	 */
	private boolean isFirstCrossHideTask = true;


	public Game_22()
	{
		mSnake.setOnCrossHidePosListener(this);
		oldVector = mVector;
		currentTime = oldTime;
	}

	@Override
	public Snake getSnake()
	{
		return new BlueSnake();
	}

	@Override
	public String[] getGuideString()
	{
		return new String[]
		{ "考验你的反应能力?幸运也是不可或缺的", "偷偷地告诉你,本关有一定几率获得隐藏技能哦!", " 收集一定数量的斑点就可以释放技能!" };
	}

	@Override
	public int[] getGuideIndexTime()
	{
		return new int[]
		{ 3, 6, 7 };
	}

	@Override
	public void detailDraw(Canvas canvas, Paint paint, int screenWidth, int screenHeight)
	{

	}

	// 隐藏关卡,并不是每一关都会有, 并且设置比较低的概率获取隐藏技能
	private HideTask findHideTask()
	{
		return new HideTask()
		{
		};
	}

	@Override
	public void detailLogic()
	{
		if (mColorChangeTimes == 10)
		{
			enterNextCheckPoint("有点不适应吧?", "放心, 接下来让我们一起愉快地玩耍吧~");
		}
		if (isFirstCrossHideTask)
		{
			mSnake.crossHidePos(findHideTask());
		}

	}

	@Override
	public int gameOverPos()
	{
		return 22;
	}

	@Override
	public void hpIsOver()
	{
		Utils.reStartCheckPoint("失败了", "小提示", "除非特殊声明", "之后的游戏吃相克颜色都会造成生命值下降");
	}

	@Override
	public void onRemoveParticleCallBack(PieceParticle particle)
	{

	}

	@Override
	public void onSameColorCrossHideTask(HideTask hTask, int color)
	{
		isFirstCrossHideTask = false;
		Utils.saveGameState(this);
		MyAplication.getSurfaceView().setOnISurfaceViewCallBack(new HideGame_SameColor(hTask, color));
	}

	@Override
	public void onOppositeColorCrossHideTask(HideTask hTask, int color)
	{
		Utils.saveGameState(this);
		MyAplication.getSurfaceView().setOnISurfaceViewCallBack(new HideGame_OppsiteColor(hTask, color));
	}

	@Override
	public void onDifferentColorCrossHideTask(HideTask hTask, int color)
	{
		Utils.saveGameState(this);
		MyAplication.getSurfaceView().setOnISurfaceViewCallBack(new HideGame_DifferColor(hTask, color));
	}

	@Override
	public void onBirthColorCrossHideTask(HideTask hTask, int color)
	{
		Utils.saveGameState(this);
		MyAplication.getSurfaceView().setOnISurfaceViewCallBack(new HideGame_BirthColor(hTask, color));
	}

	/**
	 * 初始化保存的数据
	 */
	public void initSaveData(long usedTime)
	{
		mVector = oldVector;
		oldTime = currentTime + usedTime;
	}

}
