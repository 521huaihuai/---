package com.imooc.game;

import java.util.LinkedList;

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

public class Game_3 extends CommonGame{

	private float alpha = 255;
	private float decreaseAlpha;
	private String text;

	public Game_3() {
		decreaseAlpha = Utils.alphaDecreaseInNearBytime(5);
		text = "那就尝试一下从最左侧无碰撞的移动到最右端~";
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
		return 3;
	}

	@Override
	public void childDraw(Canvas canvas, Paint paint, int screenWidth, int screenHeight) {
		Utils.drawAlphaText(Position.CEN, canvas, text, paint, alpha);
	}

	@Override
	public void childLogic() {
		if (mCollectionNUM > 0) {
			MyAplication.getSurfaceView().setOnISurfaceViewCallBack(new SimpleGameMenuFail("抱歉, 你与斑点不期而遇了~~", ""));
		}
		if (alpha > 0) {
			alpha -= decreaseAlpha;
		}
		if (mSnake.getList().getFirst().getX() >= MainActivity.screenWidth) {
			MyAplication.getSurfaceView().setOnISurfaceViewCallBack(new SimpleGameMenuSuccess("你成功到达彼岸.", ""));
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
	public void hpIsOver() {
	}

	@Override
	public void timeIsOver(long usedTime) {
	}

	@Override
	public void onRemoveParticleCallBack(PieceParticle particle) {
		// TODO Auto-generated method stub
		
	}

}
