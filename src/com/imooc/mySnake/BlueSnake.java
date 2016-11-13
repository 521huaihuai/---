package com.imooc.mySnake;

import java.util.LinkedList;

import com.imooc.myConstant.MyConstant;
import com.imooc.mySufaceView.MainActivity;

public class BlueSnake extends Snake {

	@Override
	public void initBody(LinkedList<Node> mLinkedList) {
		mLinkedList.add(new Node(MyConstant.COLOR_BLUE, MainActivity.screenWidth / 2, MainActivity.screenHeight / 2));
		mLinkedList.add(new Node(MyConstant.COLOR_BLUE, MainActivity.screenWidth / 2, MainActivity.screenHeight / 2));
		mLinkedList.add(new Node(MyConstant.COLOR_BLUE, MainActivity.screenWidth / 2, MainActivity.screenHeight / 2));
		mLinkedList.add(new Node(MyConstant.COLOR_BLUE, MainActivity.screenWidth / 2, MainActivity.screenHeight / 2));
		mLinkedList.add(new Node(MyConstant.COLOR_BLUE, MainActivity.screenWidth / 2, MainActivity.screenHeight / 2));
		mLinkedList.add(new Node(MyConstant.COLOR_BLUE, MainActivity.screenWidth / 2, MainActivity.screenHeight / 2));
		mLinkedList.add(new Node(MyConstant.COLOR_BLUE, MainActivity.screenWidth / 2, MainActivity.screenHeight / 2));
		mLinkedList.add(new Node(MyConstant.COLOR_BLUE, MainActivity.screenWidth / 2, MainActivity.screenHeight / 2));
		mLinkedList.add(new Node(MyConstant.COLOR_BLUE, MainActivity.screenWidth / 2, MainActivity.screenHeight / 2));
		mLinkedList.add(new Node(MyConstant.COLOR_BLUE, MainActivity.screenWidth / 2, MainActivity.screenHeight / 2));
	}

	@Override
	public int getInitHp() {
		return 15;
	}

}
