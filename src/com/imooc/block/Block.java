package com.imooc.block;

import com.imooc.mySnake.Node;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class Block
{

	// ’œ∞≠ŒÔ—’…´
	private int color;
	private Bitmap smallBitmap;
	private int mPadding = 10;


	public int getColor()
	{
		return color;
	}

	public abstract void crossSafeBlock(int color2);

	public abstract void touchDengerousBlock(int color2);

	public abstract void crossNiceBlock(int color2);

	public abstract void touchNormalBlock(int color2);

	/**
	 * ≈–∂œ «∑Ò¥•≈ˆ±ﬂΩÁ¡À
	 * 
	 * @param firstNode
	 * @return <code>True</code> if it touchs block
	 */
	public boolean judgeIsInBlock(Node firstNode)
	{
		
		return false;
	}

	public void drawBlock(Canvas canvas, Paint paint, int screenWidth, int screenHeight)
	{
		// TODO Auto-generated method stub
		
	}

}
