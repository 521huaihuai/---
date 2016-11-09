package com.imooc.control;

public class Move {

	private int x;
	private int y;
	private float speed = 1f;

	public Move(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public float getSpeed() {
		return speed;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
}
