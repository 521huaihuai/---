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
import com.imooc.mySufaceView.MainActivity;
import com.imooc.mySufaceView.MyAplication;
import com.imooc.mySufaceView.Pos;
import com.imooc.particle.PieceParticle;

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
 * @author Administrator
 *
 */
public class Utils
{

	public enum Position
	{
		CEN, LEFT, RIGHT, CEN_UP, CEN_DOEN, UP_RIGHT, UP_LEFT, CEN_UP_UP
	}


	private static long currentTime;


	public static void setStartTime()
	{
		currentTime = System.currentTimeMillis();
	}

	public static void setEndTime(String Tag)
	{
		Log.e("Time", Tag + " Time = " + (System.currentTimeMillis() - currentTime));
		currentTime = System.currentTimeMillis();
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
	 * @param canvas
	 * @param text
	 * @param paint
	 */
	public static void drawMessageText(String[] messages, Canvas canvas, int size, Paint paint)
	{
		paint.setTextSize(size);
		for (int i = 0; i < messages.length; i++)
		{
			int length = messages[i].length();
			int realLength = length;
			for (int j = 0; j < length; j++)
			{
				if (!isChineseByBlock(messages[i].charAt(j)))
				{
					realLength--;
				}
			}
			int width = realLength * size;
			canvas.drawText(messages[i], MainActivity.screenWidth / 2 - width / 2, MainActivity.screenHeight / 3.5f + (1 + 0.35f) * size * i, paint);
		}
		paint.setTextSize(MyAplication.getTextSize());
	}

	public static void drawText(Position position, Canvas canvas, String text, Paint paint)
	{
		drawText(position, canvas, text, MyAplication.getTextSize(), paint);
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
	 * 是否越界
	 */
	public static boolean isOutOfBunds(int x, int y)
	{
		if (x < 0 || x > MainActivity.screenWidth) { return true; }
		if (y < 0 || y > MainActivity.screenHeight) { return true; }
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

	/**
	 * 绘制血量
	 * 
	 * @param hp
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
	 * 
	 * @param time
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
	 * 
	 * @param canvas
	 * @param paint
	 * @param radius
	 * @param hp
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
	 * 
	 * @param canvas
	 * @param particles
	 * @param paint
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
	 * @param message
	 */
	public static void enterNextCheckPoint(String title, String... message)
	{
		try
		{
			Thread.sleep(500);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		MyAplication.getSurfaceView().setOnISurfaceViewCallBack(new SimpleGameMenuSuccess(title, message));
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

	public static float getAdapterMenuRadius()
	{
		float radius = MainActivity.screenWidth / 13;
		return radius;
	}

	/**
	 * 保存简单的 信息
	 * 
	 * @param context
	 * @param string
	 * @return
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
	 * 保存游戏状态
	 * 
	 * @param surfaceViewBack
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
	 * 获取不同的颜色
	 * 
	 * @param color
	 * @return
	 */
	public static int getDifferentColor(int color)
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
