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
 * ��Ϸ�����
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
	
	//���ݿ�
	private MySQLiteGame sqllite;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// ����ȫ��
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		myAplication = (MyAplication) getApplication();
		myAplication.setContext(this);

		// ���������СtextSize
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
		
		// ���عؿ�
		currentRelevant = Utils.loadDataInt(this, "checkPoint");

		if (Utils.checkIsLocked(1))
		{
			new MySQLiteGame(this).insert(new BeanGame(1, 1, 100, 1, ""));
		}
		
		sqllite = new MySQLiteGame(this);
		// ��ʾ�Զ����SurfaceView��ͼ
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
				Toast.makeText(MainActivity.this, "˫���˳�", Toast.LENGTH_SHORT).show();
				currentTime = System.currentTimeMillis();
				return false;
			}
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		menu.add(0, 1, 0, "����");
		menu.add(0, 2, 0, "����");
		menu.add(0, 3, 0, "����");
		menu.add(0, 4, 0, "�˳�");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case 1:
				// ��Ϸ����
				surfaceView.setOnISurfaceViewCallBack(new Game_introduce());
				break;
			case 2:
				// ��Ϸ����
				surfaceView.setOnISurfaceViewCallBack(new Game_rule());
				break;
			case 3:
				// �����ؿ�
				String imei = ((TelephonyManager)getSystemService(TELEPHONY_SERVICE)).getDeviceId();
				surfaceView.setOnISurfaceViewCallBack(new Game_Lock(imei));
				break;
			case 4:
				// �Ƴ���Ϸ
				android.os.Process.killProcess(android.os.Process.myPid());
				break;
		}
		return true;
	}

}
