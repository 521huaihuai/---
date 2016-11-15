package com.imooc.mySufaceView;

import com.imooc.gameMenu.CommonGameMenu;
import com.imooc.gameMenu.MenuCallBack;
import com.imooc.myConstant.MyConstant;
import com.imooc.myDataBase.BeanGame;
import com.imooc.myDataBase.MySQLiteGame;
import com.imooc.utils.Utils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * 游戏主入口
 * 
 * @author zjm 23
 *
 */
public class MainActivity extends Activity
{

	private MyAplication myAplication;
	private MySurfaceView surfaceView;
	private long currentTime;
	public static int screenWidth;
	public static int screenHeight;
	public static int currentRelevant;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// 设置全屏
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		myAplication = (MyAplication) getApplication();
		myAplication.setContext(this);

		// 加载字体大小textSize
		int textSize = Utils.loadDataInt(this, "textSize");
		if (textSize == 1)
		{
			textSize = 50;
		}
		myAplication.setTextSize(textSize);
		// 加载关卡
		currentRelevant = Utils.loadDataInt(this, "checkPoint");

		if (Utils.checkIsLocked(1))
		{
			new MySQLiteGame(this).insert(new BeanGame(1, 1, 0, 1, ""));
		}

		// 显示自定义的SurfaceView视图
		surfaceView = new MySurfaceView(this);
		surfaceView.setSleepTime(MyConstant.SLEEPTIME);
		surfaceView.setOnISurfaceViewCallBack(new MenuCallBack());
		myAplication.setSurfaceView(surfaceView);

		setContentView(surfaceView);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if ((keyCode == KeyEvent.KEYCODE_BACK))
		{
			Log.e("521huaihuai", "time = " + (System.currentTimeMillis() - currentTime));
			if (System.currentTimeMillis() - currentTime > 800)
			{
				surfaceView.setOnISurfaceViewCallBack(new CommonGameMenu(currentRelevant));
				Toast.makeText(MainActivity.this, "双击退出", Toast.LENGTH_SHORT).show();
				currentTime = System.currentTimeMillis();
				return false;
			}
		}
		return super.onKeyDown(keyCode, event);
	}

	public static long getCurrentTime()
	{
		return System.currentTimeMillis();
	}

}
