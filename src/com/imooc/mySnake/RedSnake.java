package com.imooc.mySnake;

import java.util.LinkedList;

import com.imooc.mySufaceView.MainActivity;

public class RedSnake extends Snake {

	@Override
	public void initBody(LinkedList<Node> mLinkedList) {
		mLinkedList.add(new Node(MainActivity.screenWidth / 2, MainActivity.screenHeight / 2));
		mLinkedList.add(new Node(MainActivity.screenWidth / 2, MainActivity.screenHeight / 2));
		mLinkedList.add(new Node(MainActivity.screenWidth / 2, MainActivity.screenHeight / 2));
		mLinkedList.add(new Node(MainActivity.screenWidth / 2, MainActivity.screenHeight / 2));
		mLinkedList.add(new Node(MainActivity.screenWidth / 2, MainActivity.screenHeight / 2));
		mLinkedList.add(new Node(MainActivity.screenWidth / 2, MainActivity.screenHeight / 2));
		mLinkedList.add(new Node(MainActivity.screenWidth / 2, MainActivity.screenHeight / 2));
		mLinkedList.add(new Node(MainActivity.screenWidth / 2, MainActivity.screenHeight / 2));
		mLinkedList.add(new Node(MainActivity.screenWidth / 2, MainActivity.screenHeight / 2));
		mLinkedList.add(new Node(MainActivity.screenWidth / 2, MainActivity.screenHeight / 2));
		mLinkedList.add(new Node(MainActivity.screenWidth / 2, MainActivity.screenHeight / 2));
		mLinkedList.add(new Node(MainActivity.screenWidth / 2, MainActivity.screenHeight / 2));
		mLinkedList.add(new Node(MainActivity.screenWidth / 2, MainActivity.screenHeight / 2));
		mLinkedList.add(new Node(MainActivity.screenWidth / 2, MainActivity.screenHeight / 2));
	}

	@Override
	public int getInitHp() {
		return 5;
	}

}
