package com.imooc.snake;

import com.imooc.myConstant.MyConstant;

import android.graphics.Color;

public class Node {

	private int color;
	private int x;
	private int y;
	private float radius;

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
		color = Color.RED;
		radius = MyConstant.SNAKE_RADIUS;
	}

	public Node(int color, int x, int y) {
		super();
		this.color = color;
		this.x = x;
		this.y = y;
		radius = MyConstant.SNAKE_RADIUS;
	}

	public Node(int color, int x, int y, float radius) {
		this.color = color;
		this.x = x;
		this.y = y;
		this.radius = radius;
	}

	public int getColor() {
		return color;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public float getRadius() {
		return radius;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

}
