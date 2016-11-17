package com.imooc.mySufaceView;

import com.imooc.gameMenu.CommonGameMenu;
import com.imooc.gameMenu.MenuCallBack;
import com.imooc.myConstant.MyConstant;
import com.imooc.myDataBase.BeanGame;
import com.imooc.myDataBase.MySQLiteGame;
import com.imooc.utils.Utils;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
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
	
	//数据库
	private MySQLiteGame sqllite;


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
		int titleSize = Utils.loadDataInt(this, "titleSize");
		if (titleSize == 1)
		{
			titleSize = 70;
		}
		myAplication.setTextSize(textSize);
		myAplication.setTitleSize(titleSize);
		
		// 加载关卡
		currentRelevant = Utils.loadDataInt(this, "checkPoint");

		if (Utils.checkIsLocked(1))
		{
			new MySQLiteGame(this).insert(new BeanGame(1, 1, 100, 1, ""));
		}
		
		sqllite = new MySQLiteGame(this);
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		menu.add(0, 1, 0, "关于");
		menu.add(0, 2, 0, "规则");
		menu.add(0, 3, 0, "解锁");
		menu.add(0, 4, 0, "退出");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case 1:
				// 游戏介绍
				surfaceView.setOnISurfaceViewCallBack(new Game_introduce());
				break;
			case 2:
				// 游戏规则
				surfaceView.setOnISurfaceViewCallBack(new Game_rule());
				break;
			case 3:
				// 解锁关卡
				String imei = ((TelephonyManager)getSystemService(TELEPHONY_SERVICE)).getDeviceId();
				surfaceView.setOnISurfaceViewCallBack(new Game_Lock(imei));
				break;
			case 4:
				// 推出游戏
				android.os.Process.killProcess(android.os.Process.myPid());
				break;
		}
		return true;
	}

}
