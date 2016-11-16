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
	
	
	
	// 游戏id号
	public static final String GAMEID = "gameId";
	// 游戏排名
	public static final String RANKING = "ranking";
	// 游戏星级认定(最高3星 ,最低无)
	public static final String STAR = "star";
	// 是否锁定
	public static final String LOCK = "lock";
	// 创建时间
	public static final String CREATED = "created";
	// 默认排列方式
	public static final String DEFAULT_SORT_ORDER = "created DESC";
	
	
	
	public static final float STROKE_WIDTH = 5.0f;
	public static final float SNAKE_RADIUS = 21.0f;
	public static final int PARTICLE_RADIUS = 5;
	public static final int POWERFULPARTICLE_RADIUS = 30;
	public static final int BIG_PARTICLE_RADIUS = 25;
	public static final int MID_PARTICLE_RADIUS = 15;
	public static final int LOCK_ALPHA = 140;
}
