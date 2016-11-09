package com.imooc.control;

public class Control {

	public static Move move(float xm, float ym, float speed) {
		float realSpeed = (float) Math.sqrt(xm * xm + ym * ym);
		int dx = 0;
		int dy = 0;
		if (realSpeed < 140) {
			// 缓慢移动
			dx = (int) (speed * xm / realSpeed / 2);
			dy = (int) (speed * ym / realSpeed / 2);
		} else {
			// 快速移动
			dx = (int) (speed * xm / realSpeed);
			dy = (int) (speed * ym / realSpeed);
		}
		return new Move(dx, dy);
	}

}
