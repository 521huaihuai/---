package com.imooc.game;

import java.util.List;

import com.imooc.myBaseGame.CommonGame_21_40;
import com.imooc.particle.PieceParticle;
import com.imooc.snake.Node;
import com.imooc.snake.RedSnake;
import com.imooc.snake.Snake;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

public class Game_27 extends CommonGame_21_40{

	private Snake mSnake;

	@Override
	public Snake getSnake() {
		mSnake = new RedSnake();
		return mSnake;
	}

	@Override
	public void childDraw(Canvas canvas, Paint paint, int screenWidth, int screenHeight) {
		paint.setColor(mSnake.getColor());
		canvas.drawText("���Ǹ���Ϸ����һЩ�Ѷ�,", screenWidth / 3, screenHeight / 4, paint);
		canvas.drawText("������˲���������ͬ����ɫ������˵���ɫ, ���ͻ�ת��Ϊ���Ե���ɫ", screenWidth / 7, screenHeight / 3, paint);
		canvas.drawText("��Ȼ��, ��������Ҳ��ı�!", screenWidth / 3, screenHeight / 2, paint);
	}

	@Override
	public void childLogic() {
		// TODO Auto-generated method stub

	}

	@Override
	public void sameColorCrossHandle(int color) {
		// TODO Auto-generated method stub

	}

	@Override
	public void oppositeColorCrossHandle(int color) {
		mSnake.setHp(mSnake.getCurrentHp() - 5);
		if (mSnake.getCurrentHp() <= 0) {
			Log.e("521huaihuai", "you have die");
		}
		Log.e("521huaihuai", "Ѫ���۳�5��");
	}

	@Override
	public void differentColorCrossHandle(int color) {
		List<Node> mList = mSnake.getList();
		for (Node node : mList) {
			node.setColor(color);
		}
	}

	@Override
	public long setTimeLimite() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int gameOverPos() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void birthColorCrosshandle(int color) {
		// TODO Auto-generated method stub

	}

	@Override
	public void hpIsOver() {
		// TODO Auto-generated method stub

	}

	@Override
	public void timeIsOver(long usedTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRemoveParticleCallBack(PieceParticle particle) {
		// TODO Auto-generated method stub
		
	}

}
