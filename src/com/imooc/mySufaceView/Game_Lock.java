package com.imooc.mySufaceView;

import java.util.List;

import com.imooc.myConstant.MyConstant;
import com.imooc.myDataBase.BeanGame;
import com.imooc.myDataBase.MySQLiteGame;
import com.imooc.utils.Utils;
import com.imooc.utils.Utils.Position;

import android.app.Service;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

public class Game_Lock implements ISurfaceViewCallBack
{

	private String imie;
	private ClipboardManager myClipboard;
	private ClipData myClip;
	private RectF rect;
	private RectF rect2;


	public Game_Lock(String imei)
	{
		this.imie = imei;
		myClipboard = (ClipboardManager) MyAplication.getContext().getSystemService(Service.CLIPBOARD_SERVICE);
		rect = new RectF(MainActivity.screenWidth / 3 - Utils.getAdapterMenuRadius(), MainActivity.screenHeight / 2 - Utils.getAdapterMenuRadius() / 2,
				MainActivity.screenWidth / 3 + Utils.getAdapterMenuRadius(), MainActivity.screenHeight / 2 + Utils.getAdapterMenuRadius() / 2);
		rect2 = new RectF(2 * MainActivity.screenWidth / 3 - Utils.getAdapterMenuRadius(), MainActivity.screenHeight / 2 - Utils.getAdapterMenuRadius() / 2,
				2 * MainActivity.screenWidth / 3 + Utils.getAdapterMenuRadius(), MainActivity.screenHeight / 2 + Utils.getAdapterMenuRadius() / 2);
	}

	@Override
	public void draw(Canvas canvas, Paint paint, int screenWidth, int screenHeight)
	{
		paint.setColor(MyConstant.COLOR_BLUE);
		Utils.drawText(Position.CEN_UP_UP, canvas, "加入我们, 即可解锁所有关卡", paint);
		Utils.drawText(Position.CEN_UP, canvas, "点击\"获取密钥\",把复制到剪切板的密钥发送到关注的群,即可获取相对密钥", paint);
		// canvas.drawCircle(screenWidth / 2, screenHeight / 2,
		// Utils.getAdapterMenuRadius(), paint);
		// 绘制按钮
		paint.setColor(MyConstant.COLOR_BLACK);
		paint.setShader(
				new RadialGradient(MainActivity.screenWidth / 3, screenHeight / 2, Utils.getAdapterMenuRadius(), Color.BLACK, Color.WHITE, TileMode.CLAMP));
		canvas.drawRoundRect(rect, 45, 45, paint);
		paint.setShader(
				new RadialGradient(2 * MainActivity.screenWidth / 3, screenHeight / 2, Utils.getAdapterMenuRadius(), Color.BLACK, Color.WHITE, TileMode.CLAMP));
		canvas.drawRoundRect(rect2, 45, 45, paint);

		// 绘制字体
		paint.setShader(null);
		paint.setColor(MyConstant.COLOR_RED);
		int weidth = MyAplication.getTextSize() * 4;
		canvas.drawText("获取密钥", MainActivity.screenWidth / 3 - weidth / 2, MainActivity.screenHeight / 2, paint);
		canvas.drawText("粘贴解锁", 2 * MainActivity.screenWidth / 3 - weidth / 2, MainActivity.screenHeight / 2, paint);
	}

	@Override
	public void logic()
	{

	}

	@Override
	public void surfaceCreatedCallBack(int screenW, int screenH)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onTouchEventCallBack(MotionEvent event)
	{
		if (event.getAction() == MotionEvent.ACTION_DOWN)
		{
			float x = event.getX();
			float y = event.getY();
			if (x > (MainActivity.screenWidth / 3 - Utils.getAdapterMenuRadius()) && x < (MainActivity.screenWidth / 3 + Utils.getAdapterMenuRadius()))
			{
				if (y > (MainActivity.screenHeight / 2 - Utils.getAdapterMenuRadius() / 2)
						&& y < (MainActivity.screenHeight / 2 + Utils.getAdapterMenuRadius() / 2))
				{
					//encode ways
//					int values = (Integer.parseInt(imie.substring(0, 5)) - 1000) * 2;
//					myClip = ClipData.newPlainText("text", values + "zjm");
					myClip = ClipData.newPlainText("text", imie);
					Log.e("521huaihuai", "values = " + (imie));
					myClipboard.setPrimaryClip(myClip);
					Toast.makeText(MyAplication.getContext(), "已复制到剪切板", Toast.LENGTH_LONG).show();
					return true;
				}
				// unlockAllGame();
			}

			if (x > (2 * MainActivity.screenWidth / 3 - Utils.getAdapterMenuRadius()) && x < (2 * MainActivity.screenWidth / 3 + Utils.getAdapterMenuRadius()))
			{
				if (y > (MainActivity.screenHeight / 2 - Utils.getAdapterMenuRadius() / 2)
						&& y < (MainActivity.screenHeight / 2 + Utils.getAdapterMenuRadius() / 2))
				{
					unlockAllGame();
					return true;
				}
				// unlockAllGame();
			}
		}
		return false;
	}

	private void unlockAllGame()
	{

		ClipData abc = myClipboard.getPrimaryClip();
		ClipData.Item item = abc.getItemAt(0);
		String text = item.getText().toString();
		if (!text.contains("zjm"))
		{
			Toast.makeText(MyAplication.getContext(), "您的密钥不正确, 请重新获取~", Toast.LENGTH_LONG).show();
			return;
		}
		String s = text.split("zjm")[0];
		Log.e("521huaihuai", "s = " + s);
		int ii = Integer.valueOf(s) / 2 + 1000;
		Log.e("521huaihuai", "ii = " + (ii));
		String newImie = imie.substring(0, 5);
		int iii = Integer.valueOf(newImie);
		Log.e("521huaihuai", "iii = " + iii);
		if (iii != ii)
		{
			Toast.makeText(MyAplication.getContext(), "您的密钥不正确, 请重新获取~", Toast.LENGTH_LONG).show();
		}
		else
		{
			MySQLiteGame sqllite = new MySQLiteGame(MyAplication.getContext());
			List<BeanGame> list = sqllite.findAll();
			int size = list.size();
			BeanGame beanGame = null;
			for (int i = 1; i <= 100 - size; i++)
			{
				beanGame = new BeanGame(size + i, 0, -11, 1, "");
				try
				{
					sqllite.insert(beanGame);
				}
				catch (Exception e)
				{
				}
			}
			Toast.makeText(MyAplication.getContext(), "成功解锁所有关卡~", Toast.LENGTH_LONG).show();
		}
	}

}
