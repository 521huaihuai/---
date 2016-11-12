package com.imooc.myBaseGame;

import com.imooc.block.HideTask;
import com.imooc.game.Game_22;
import com.imooc.mySufaceView.MyAplication;
import com.imooc.snake.Snake;
import com.imooc.utils.Utils;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Òþ²Ø¹Ø¿¨
 * 
 * @author Administrator
 *
 */
public class CommonHideGame extends CommonGuideGame_00_20
{
	
	private long oldTime;

	public CommonHideGame(HideTask hideTask, int color)
	{
		oldTime = System.currentTimeMillis();
	}

	@Override
	public String[] getGuideString()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getGuideIndexTime()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void detailDraw(Canvas canvas, Paint paint, int screenWidth, int screenHeight)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void detailLogic()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public int gameOverPos()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void hpIsOver()
	{
		Game_22 game_22 = (Game_22) Utils.readGameState();
		game_22.initSaveData(System.currentTimeMillis() - oldTime);
		//game_22.getOldVetor();
		MyAplication.getSurfaceView().setOnISurfaceViewCallBack(game_22);
	}

	@Override
	public Snake getSnake()
	{
		return null;
	}

}
