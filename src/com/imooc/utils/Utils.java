package com.imooc.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import com.imooc.gameMenu.SimpleGameMenuFail;
import com.imooc.gameMenu.SimpleGameMenuSuccess;
import com.imooc.myConstant.MyConstant;
import com.imooc.myDataBase.MySQLiteGame;
import com.imooc.myParticle.BigPieceParticle;
import com.imooc.myParticle.PieceParticle;
import com.imooc.mySufaceView.MainActivity;
import com.imooc.mySufaceView.MyAplication;
import com.imooc.mySufaceView.Pos;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

/**
 * 工具包
 * 
 * @author zjm
 *
 */
public class Utils
{

	public enum Position
	{
		CEN, LEFT, RIGHT, CEN_UP, CEN_DOEN, UP_RIGHT, UP_LEFT, CEN_UP_UP, CEN_DOWN_DOWN
	}


	private static long currentTime;


	/**
	 * 用于测试时间的开始
	 */
	public static void setStartTime()
	{
		currentTime = System.currentTimeMillis();
	}

	/**
	 * 用于测试代码用时
	 */
	public static void setEndTime(String Tag)
	{
		Log.e("Time", Tag + " Time = " + (System.currentTimeMillis() - currentTime));
		currentTime = System.currentTimeMillis();
	}

	/**
	 * 获取游戏得分
	 */
	public static int judgeScores(long time, int hp, int colloction, float x1, float x2, float x3, int baseScore)
	{

		int score = (int) (baseScore + hp * x2 + colloction * x3 - time * x1);
		Log.e("521huaihuai", "time = " + time + " hp =" + hp + " colloction = " + colloction);
		Log.e("521huaihuai", "score = " + score);
		return score < 0 ? 9 : score;
	}

	/**
	 * 检测该关卡是否被锁定
	 * 
	 * @param checkPoint
	 * @return <code>true</code> 代表被锁定
	 */
	public static boolean checkIsLocked(int checkPoint)
	{
		return new MySQLiteGame(MyAplication.getContext()).findIsLock(checkPoint) == 0 ? true : false;
	}

	/**
	 * 绘制随时间隐藏的字体
	 * 
	 * @param canvas
	 * @param text
	 * @param paint
	 * @param alpha
	 */
	public static void drawAlphaText(Canvas canvas, String text, Paint paint, float alpha)
	{
		drawAlphaText(Position.CEN, canvas, text, paint, alpha);
	}

	public static void drawAlphaText(Position position, Canvas canvas, String text, Paint paint, float alpha)
	{
		if (alpha < 0)
		{
			alpha = 0;
		}
		if (alpha > 255)
		{
			alpha = 255;
		}
		canvas.save();
		paint.setAlpha((int) alpha);
		drawText(position, canvas, text, paint);
		paint.setAlpha(100);
		canvas.restore();
	}

	public static void drawText(Position position, Canvas canvas, String text, int textSize, Paint paint)
	{
		int length = text.length();
		int realLength = length;
		for (int i = 0; i < length; i++)
		{
			if (!isChineseByBlock(text.charAt(i)))
			{
				realLength--;
			}
		}
		int size = textSize;
		int width = realLength * size;
		paint.setTextSize(textSize);
		switch (position)
		{
			case CEN:
				canvas.drawText(text, MainActivity.screenWidth / 2 - width / 2, MainActivity.screenHeight / 2, paint);
				break;
			case LEFT:
				canvas.drawText(text, 0, MainActivity.screenHeight / 2, paint);
				break;
			case CEN_UP_UP:
				canvas.drawText(text, MainActivity.screenWidth / 2 - width / 2, MainActivity.screenHeight / 5, paint);
				break;
			case CEN_UP:
				canvas.drawText(text, MainActivity.screenWidth / 2 - width / 2, MainActivity.screenHeight / 3, paint);
				break;
			case CEN_DOEN:
				canvas.drawText(text, MainActivity.screenWidth / 2 - width / 2, 2 * MainActivity.screenHeight / 3, paint);
				break;
			case CEN_DOWN_DOWN:
				canvas.drawText(text, MainActivity.screenWidth / 2 - width / 2, 4 * MainActivity.screenHeight / 5, paint);
				break;
			case UP_RIGHT:
				canvas.drawText(text, MainActivity.screenWidth - width, MainActivity.screenHeight / 3, paint);
				break;
			case UP_LEFT:
				canvas.drawText(text, MainActivity.screenWidth / 2 - width / 2, MainActivity.screenHeight / 3, paint);
				break;

			case RIGHT:
				canvas.drawText(text, MainActivity.screenWidth - width, MainActivity.screenHeight / 2, paint);
				break;
		}
		paint.setTextSize(MyAplication.getTextSize());
	}

	/**
	 * 用于绘制多条消息
	 * 
	 * @param position
	 *            位置
	 * @param canvas
	 *            画布
	 * @param text
	 *            文字
	 * @param paint
	 *            画笔
	 */
	public static void drawMessageText(String[] messages, Canvas canvas, int size, Paint paint)
	{
		paint.setTextSize(size);
		int index = 0;
		for (int i = 0; i < messages.length; i++)
		{
			index = 0;
			int length = messages[i].length();
			int realLength = length;
			for (int j = 0; j < length; j++)
			{
				if (!isChineseByBlock(messages[i].charAt(j)))
				{
					realLength--;
					index ++;
				}
			}
			int width = (int) (realLength * size + index * 1.0f / 3 * size);
			canvas.drawText(messages[i], MainActivity.screenWidth / 2 - width / 2, MainActivity.screenHeight / 3.5f + (1 + 0.35f) * size * i, paint);
		}
		paint.setTextSize(MyAplication.getTextSize());
	}

	public static void drawText(Position position, Canvas canvas, String text, Paint paint)
	{
		drawText(position, canvas, text, MyAplication.getTextSize(), paint);
	}

	/**
	 * 绘制血量
	 */
	public static void drawHp(Canvas canvas, Paint paint, float radius, int hp)
	{
		float screenWidth = MainActivity.screenWidth;
		float screenHeight = MainActivity.screenHeight;
		// 绘制血量
		canvas.drawText("HP : ", (int) (screenWidth - 0.12 * screenWidth), screenHeight / 25, paint);
		canvas.drawRoundRect(new RectF(screenWidth - 1.0f / 240 * hp * screenWidth, 0.5f / 108 * screenHeight, screenWidth, 4.5f / 108 * screenHeight), radius,
				radius, paint);
	}

	/**
	 * 绘制时间
	 */
	public static void drawTime(Canvas canvas, Paint paint, float radius, long time)
	{
		float screenWidth = MainActivity.screenWidth;
		float screenHeight = MainActivity.screenHeight;
		// 绘制时间
		canvas.drawText("T : ", (int) (0.01 * screenWidth), screenHeight / 25, paint);
		canvas.drawText("" + time / 1000, (int) (0.05 * screenWidth), screenHeight / 25, paint);
	}

	/**
	 * 绘制收集数
	 */
	public static void drawCollection(Canvas canvas, Paint paint, int collectionNum)
	{
		float screenWidth = MainActivity.screenWidth;
		float screenHeight = MainActivity.screenHeight;
		// 绘制血量
		canvas.drawText("DG : ", (int) (screenWidth - 0.12 * screenWidth), 2.5f * screenHeight / 25, paint);
		canvas.drawText("" + collectionNum, (int) (screenWidth - 0.05 * screenWidth), 2.5f * screenHeight / 25, paint);
	}

	/**
	 * 绘制粒子
	 */
	public static void drawParticle(Canvas canvas, Vector<PieceParticle> particles, Paint paint)
	{
		for (PieceParticle particle : particles)
		{
			paint.setColor(particle.getColor());
			canvas.drawCircle(particle.getX(), particle.getY(), particle.getRadius(), paint);
		}
	}

	/**
	 * 将3维的转化到ParticleList中
	 */
	public static Vector<BigPieceParticle> convertData2ParticleVector(int[] data)
	{
		Vector<BigPieceParticle> bigPieceParticles = new Vector<BigPieceParticle>();
		if (data.length < 3)
		{
			return null;
		}
		if (data.length % 3 != 0)
		{
			throw new IllegalArgumentException("数据不规范");
		}
		int size = data.length / 3;
		BigPieceParticle bParticle = null;
		for (int i = 0; i < size; i++)
		{
			bParticle = new BigPieceParticle(data[3 * i], data[3 * i + 1], data[3 * i + 2]);
			bigPieceParticles.add(bParticle);
		}
		return bigPieceParticles;
	}

	/**
	 * 是否越界
	 */
	public static boolean isOutOfBunds(int x, int y)
	{
		if (x < 0 || x > MainActivity.screenWidth)
		{
			return true;
		}
		if (y < 0 || y > MainActivity.screenHeight)
		{
			return true;
		}
		return false;
	}

	/**
	 * 是否在园内
	 */
	public static boolean isInRound(PieceParticle particle, float x, float y, float radius)
	{
		float distance = (float) Math.sqrt(Math.pow(x - particle.getX(), 2) + Math.pow(y - particle.getY(), 2));
		return distance <= radius;
	}

	public static void reStartCheckPoint(String title, String... message)
	{
		try
		{
			Thread.sleep(500);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		MyAplication.getSurfaceView().setOnISurfaceViewCallBack(new SimpleGameMenuFail(title, message));
	}

	/**
	 * 获取该方向移动的下一步移动距离
	 * 
	 * @param direction
	 *            0 - 360
	 * @return
	 */
	public static Pos getMoveDistance(double direction, int currentX, int currentY, float speed)
	{
		double x = speed * Math.cos(Math.PI / 180 * direction) + currentX;
		double y = speed * Math.sin(Math.PI / 180 * direction) + currentY;
		return new Pos((int) Math.round(x), (int) Math.round(y));
	}

	/**
	 * 进入下一关
	 * 
	 * @param string
	 *            标题
	 * @param message
	 *            展示内容
	 */
	public static void enterNextCheckPoint(String title, int score, String... message)
	{
		try
		{
			Thread.sleep(500);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		MyAplication.getSurfaceView().setOnISurfaceViewCallBack(new SimpleGameMenuSuccess(title, score, message));
	}

	/**
	 * alpha 在time内时间衰减255 - 0
	 * 
	 * @param time
	 * @return
	 */
	public static float alphaDecreaseInNearBytime(int time)
	{
		return (255 * MyConstant.SLEEPTIME) * 1.0f / (1000 * time);
	}

	/**
	 * 保存简单的 信息
	 */
	public static void saveDataString(Context context, String key, String string)
	{
		SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString("content", string);
		editor.commit();
	}

	/**
	 * 保存简单的 信息
	 * 
	 * @param context
	 * @param string
	 */
	public static void saveDataInt(Context context, String key, int num)
	{
		SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putInt(key, num);
		editor.commit();
	}

	/**
	 * 保存游戏状态
	 */
	public static void saveGameState(Object object)
	{
		File file = new File(MyConstant.OBJECT_PATH);
		if (!file.exists())
		{
			file.mkdir();
		}
		Log.e("521huaihuai", "mkdir");
		File storeFile = new File(file, "game.dat");
		FileOutputStream outputStream = null;
		ObjectOutputStream objOut = null;
		if (storeFile.exists())
		{
			storeFile.delete();
		}
		try
		{
			storeFile.createNewFile();
			Log.e("521huaihuai", "createNewFile");
			outputStream = new FileOutputStream(storeFile);
			objOut = new ObjectOutputStream(outputStream);
			objOut.writeObject(object);
			objOut.flush();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (objOut != null)
				{
					objOut.close();
					outputStream.close();
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * 读取游戏状态
	 */
	public static Object readGameState()
	{
		Object temp = null;
		File file = new File(MyConstant.OBJECT_PATH, "game.dat");
		ObjectInputStream objIn = null;
		FileInputStream in = null;
		try
		{
			in = new FileInputStream(file);
			objIn = new ObjectInputStream(in);
			temp = objIn.readObject();
			Log.e("521huaihuai", "read object success!");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (in != null)
			{
				try
				{
					in.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			if (objIn != null)
			{
				try
				{
					objIn.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}

		}
		return temp;
	}

	/**
	 * 读取SharedPreferences数据
	 * 
	 * @param context
	 */
	public static int loadDataInt(Context context, String key)
	{
		SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		return sp.getInt(key, 1);
	}

	/**
	 * 读取SharedPreferences数据
	 * 
	 * @param context
	 */
	public static String loadDataString(Context context, String key)
	{
		SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		return sp.getString(key, "");
	}

	/**
	 * 获取相克的颜色
	 */
	public static int getOppsiteColor(int color)
	{
		if (color == MyConstant.COLOR_BLUE)
		{
			color = Color.BLUE;
		}
		switch (color)
		{
			case MyConstant.COLOR_RED:
				return MyConstant.COLOR_BLUE;
			case MyConstant.COLOR_BLACK:
				return MyConstant.COLOR_GREEN;
			case Color.BLUE:
				return MyConstant.COLOR_BLACK;
			case MyConstant.COLOR_GREEN:
				return MyConstant.COLOR_GOLD;
			default:
				return MyConstant.COLOR_RED;
		}
	}

	/**
	 * 获取适配的菜单半径大小
	 */
	public static float getAdapterMenuRadius()
	{
		float radius = MainActivity.screenWidth / 13;
		return radius;
	}

	/**
	 * 获取适配的大型粒子半径大小
	 */
	public static float getAdapterBigParticleRadius()
	{
		float radius = MainActivity.screenWidth / 34;
		return radius;
	}

	/**
	 * 获取适配的Node半径大小
	 */
	public static float getAdapterSnakeRadius()
	{
		float radius = MainActivity.screenWidth / 68;
		return radius;
	}

	// 使用UnicodeBlock方法判断
	public static boolean isChineseByBlock(char c)
	{
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_C
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_D || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
