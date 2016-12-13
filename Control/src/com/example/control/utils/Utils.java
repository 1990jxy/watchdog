package com.example.control.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Random;

import com.example.control.R;
import com.example.control.net.ConnecterPool;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.widget.Toast;

public class Utils {
	/**
	 * 启动一个新的Activity
	 * 
	 * @param ctx
	 * @param clasz
	 */
	public static void startOtherActivity(Activity ctx, Class clasz) {
		Intent intent = new Intent();
		intent.setClass(ctx, clasz);
		ctx.startActivity(intent);
	}

	/**
	 * 启动一个新的Activity
	 * 
	 * @param ctx
	 * @param clasz
	 */
	public static void startOtherActivity(Activity ctx, Class clasz,
			Serializable data) {
		Intent intent = new Intent();
		intent.putExtra("data", data);
		intent.setClass(ctx, clasz);
		ctx.startActivity(intent);
	}

	/**
	 * 获取屏幕高度宽度
	 * 
	 * @param ctx
	 * @return
	 */
	public static Point getDisplayMetrics(Context ctx) {
		DisplayMetrics metrcis = ctx.getResources().getDisplayMetrics();
		Point metricsPoint = new Point();
		metricsPoint.x = metrcis.widthPixels;
		metricsPoint.y = metrcis.heightPixels;
		return metricsPoint;
	}

	/**
	 * 参数必须为：getDisplayMetrics返回值
	 * 
	 * @param point
	 * @return
	 */
	public static Point getRandomPoint(Point point) {
		Random rand = new Random();
		int rx = rand.nextInt(point.x);
		int ry = rand.nextInt(point.y);
		return new Point(rx, ry);
	}
	
	/**
	 * 断网处理
	 * 
	 * @param context
	 */
	public static void doNetLost(Activity context) {
		Toast.makeText(context,
				context.getResources().getString(R.string.app_lost_host),
				Toast.LENGTH_SHORT).show();
		context.finish();
		ConnecterPool.mapConnectorPool.remove(ConnecterPool.STRING_CKEY);
	}
	/**
	 * 创建目录
	 * 
	 * @param dir
	 */
	public static void mkdir(String dir) {
		File file = new File(dir);
		file.mkdir();
	}

	/**
	 * 把asset下面的资源拷贝到sdcard下面
	 * 
	 * @param context
	 * @param destFile
	 * @throws IOException
	 */
	public static void copyAssetFile2Sdcard(Context context, String fname,
			String destFile) throws IOException {
		AssetManager asm = context.getAssets();
		File df = new File(destFile);
		if (df.exists() == false) {
			df.mkdir();
		} else {
			InputStream os = asm.open(fname);
			byte buf[] = new byte[256];
			int b = -1;
			FileOutputStream fout = new FileOutputStream(destFile + fname);
			while ((b = os.read(buf)) != -1) {
				fout.write(buf, 0, buf.length);
			}
			fout.flush();
			os.close();
			fout.close();
		}

	}
}
