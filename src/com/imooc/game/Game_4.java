package com.imooc.game;

import java.util.LinkedList;

import com.imooc.control.IMoveListener;
import com.imooc.gameMenu.SimpleGameMenuFail;
import com.imooc.gameMenu.SimpleGameMenuSuccess;
import com.imooc.myConstant.MyConstant;
import com.imooc.mySufaceView.MainActivity;
import com.imooc.mySufaceView.MyAplication;
import com.imooc.particle.PieceParticle;
import com.imooc.snake.Node;
import com.imooc.snake.Snake;
import com.imooc.utils.Utils;
import com.imooc.utils.Utils.Position;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Game_4 extends CommonGame implements IMoveListener{

	private float alpha = 255;
	private float decreaseAlpha;
	private String text;
	private boolean isStart;
	private boolean isSuccess;
	private boolean isOver;

	public Game_4() {
		decreaseAlpha = Utils.alphaDecreaseInNearBytime(6);
		isStart = false;
		isOver = false;
		text = "����ͣ��? ��! ����һ�´����������ײ���ƶ������Ҷ�,��;��ͣ��Ӵ~";
		setOnMoveListener(this);
	}

	@Override
	public long setTimeLimite() {
		return 111111110;
	}

	@Override
	public Snake getSnake() {
		return new Snake() {

			@Override
			public void initBody(LinkedList<Node> mLinkedList) {
				Node node = new Node(MyConstant.COLOR_BLACK, 0, MainActivity.screenHeight / 2);
				mLinkedList.add(node);
				mLinkedList.add(node);
				mLinkedList.add(node);
				mLinkedList.add(node);
				mLinkedList.add(node);
				mLinkedList.add(node);
			}

			@Override
			public int getInitHp() {
				return 0;
			}
		};
	}

	@Override
	public int gameOverPos() {
		return 4;
	}

	@Override
	public void childDraw(Canvas canvas, Paint paint, int screenWidth, int screenHeight) {
		Utils.drawAlphaText(Position.CEN, canvas, text, paint, alpha);
		if (alpha < 230) {
			isStart = true;
		}
	}

	@Override
	public void childLogic() {
		if (mCollectionNUM > 0) {
			isOver = true;
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			MyAplication.getSurfaceView()
					.setOnISurfaceViewCallBack(new SimpleGameMenuFail("��Ǹ, ����ߵ㲻�ڶ�����~~", "�������κΰߵ��нӴ�"));
		}
		if (alpha > 0) {
			alpha -= decreaseAlpha;
		}
		if (mSnake.getList().getFirst().getX() >= MainActivity.screenWidth) {
			isSuccess = true;
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			MyAplication.getSurfaceView()
					.setOnISurfaceViewCallBack(new SimpleGameMenuSuccess("��ɹ�����˰�.", "����ʱ�� : " + getUsedTime()));
		}
	}

	@Override
	public void sameColorCrossHandle(int color) {
		mCollectionNUM++;
	}

	@Override
	public void oppositeColorCrossHandle(int color) {
		mCollectionNUM++;
	}

	@Override
	public void differentColorCrossHandle(int color) {
		mCollectionNUM++;
	}

	@Override
	public void birthColorCrosshandle(int color) {
		mCollectionNUM++;
	}

	@Override
	public void actionMOVE(float xm, float ym) {
	}

	@Override
	public void actionDOWN(int x, int y) {
	}

	@Override
	public void actionUP(int x, int y) {
		if (isStart && !isSuccess) {
			if (!isOver) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				MyAplication.getSurfaceView().setOnISurfaceViewCallBack(
						new SimpleGameMenuFail("��Ǹ, ����;ͣ����~~", "����һ�´����������ײ���ƶ������Ҷ�,��;��ͣ��Ӵ~"));
			}
		}
	}

	@Override
	public void hpIsOver() {
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
