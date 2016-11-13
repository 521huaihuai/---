package com.imooc.game;

import com.imooc.myBaseGame.CommonGuideGame_20_40;
import com.imooc.myParticle.PieceParticle;
import com.imooc.mySnake.RedSnake;
import com.imooc.mySnake.Snake;
import com.imooc.utils.Utils;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Game_21 extends CommonGuideGame_20_40
{


	@Override
	public Snake getSnake()
	{
		return new RedSnake();
	}

	@Override
	public String[] getGuideString()
	{
		return new String[]
		{ "我们给游戏增加一些难度", "当你吃了不是自身相同的颜色或者相克的颜色", "它就会转变为所吃的颜色", "当然了, 它的属性也随之改变!", "尝试转变10次颜色吧~" };
	}

	@Override
	public int[] getGuideIndexTime()
	{
		return new int[]
		{ 2, 3, 3, 2, 10 };
	}

	@Override
	public void detailDraw(Canvas canvas, Paint paint, int screenWidth, int screenHeight)
	{
	}

	@Override
	public void detailLogic()
	{
		if (mColorChangeTimes == 10)
		{
			Utils.enterNextCheckPoint("有点不适应吧?", "放心, 接下来让我们一起愉快地玩耍吧~");
		}

	}

	@Override
	public int gameOverPos()
	{
		return 21;
	}

	@Override
	public void hpIsOver()
	{
		Utils.reStartCheckPoint("失败了", "小提示", "除非特殊声明", "之后的游戏吃相克颜色都会造成生命值下降");
	}

	@Override
	public void onRemoveParticleCallBack(PieceParticle particle)
	{
		// TODO Auto-generated method stub
		
	}

}
