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
		{ "���Ǹ���Ϸ����һЩ�Ѷ�", "������˲���������ͬ����ɫ������˵���ɫ", "���ͻ�ת��Ϊ���Ե���ɫ", "��Ȼ��, ��������Ҳ��֮�ı�!", "����ת��10����ɫ��~" };
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
			Utils.enterNextCheckPoint("�е㲻��Ӧ��?", "����, ������������һ��������ˣ��~");
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
		Utils.reStartCheckPoint("ʧ����", "С��ʾ", "������������", "֮�����Ϸ�������ɫ�����������ֵ�½�");
	}

	@Override
	public void onRemoveParticleCallBack(PieceParticle particle)
	{
		// TODO Auto-generated method stub
		
	}

}
