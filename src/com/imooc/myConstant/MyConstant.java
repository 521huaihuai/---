package com.imooc.myConstant;

import android.graphics.Color;
import android.os.Environment;

public class MyConstant {

	public static final int COLOR_BLUE =  Color.rgb(36, 169, 255);
	public static final int COLOR_RED = Color.RED;
	public static final int COLOR_GREEN = Color.GREEN;
	public static final int COLOR_GOLD = Color.rgb(251, 178, 24);
	public static final int COLOR_BLACK = Color.BLACK;
	public static final int[] COLORS = new int[] { COLOR_RED, COLOR_GOLD, COLOR_GREEN, COLOR_BLUE, COLOR_BLACK };
	public static final float SPEED = 9.0f;
	public static final int SLEEPTIME = 25;
	public static final String OBJECT_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/huaihuai/";
	
	
	public static final float STROKE_WIDTH = 5.0f;
	public static final float SNAKE_RADIUS = 21.0f;
	public static final int PARTICLE_RADIUS = 5;
	public static final int POWERFULPARTICLE_RADIUS = 30;
	public static final int BIG_PARTICLE_RADIUS = 25;
	public static final int MID_PARTICLE_RADIUS = 15;
}
