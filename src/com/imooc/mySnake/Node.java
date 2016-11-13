package com.imooc.mySnake;

import java.io.Serializable;

import android.graphics.Color;

public class Node implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 10L;
	private int color;
	private int x;
	private int y;

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
		color = Color.RED;
	}

	public Node(int color, int x, int y) {
		super();
		this.color = color;
		this.x = x;
		this.y = y;
	}

	public Node(int color, int x, int y, float radius) {
		this.color = color;
		this.x = x;
		this.y = y;
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

	public void setColor(int color) {
		this.color = color;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

}
