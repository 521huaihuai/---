package com.imooc.myParticle;

import java.util.Vector;

import com.imooc.mySnake.Snake;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * 当蛇吃了该粒子时会产生特殊效果</br>
 * <itme>xia</item> <itme></item>
 * 
 * @author Administrator
 *
 */
public interface IPowerfulParticle {

	/**
	 * 粒子产生的效果
	 * 
	 * @author zjm
	 *
	 */
	public enum Pattern {
		VERTICAL_LINE, HORIZONTAL_LINE, VERTICAL_HORIZONTAL_LINE, CIRCLE, ALL_SAME_COLOR, ALL_OPPSITE_COLOR, ALL_COLOR, ADD_HP, ADD_GD, CUT_TIME
	}

	/**
	 * 依据颜色释放相应效果
	 * @param paint 
	 * @param canvas 
	 * 
	 * @param color
	 */
	Vector<PieceParticle> releaseEffectParticle(Vector<PieceParticle>vector,  Snake snake, Canvas canvas, Paint paint, int times);

}
