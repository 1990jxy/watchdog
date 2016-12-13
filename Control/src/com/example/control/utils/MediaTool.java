package com.example.control.utils;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;

public class MediaTool {

	public static SoundPool soudPool;

	public static void init() {
		soudPool = new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);
	}

	public static void load(Context context, int resId) {
		int hitOkSfx = soudPool.load(context, resId, 0);
	}
	public static void addLoadListener(OnLoadCompleteListener listener){
		getSoundPool().setOnLoadCompleteListener(listener);
	}

	public static void play(int rid) {
		int sid = soudPool.play(rid, 0.5f, 0.5f, 0, 0, 1);
		soudPool.setVolume(sid, 0.1f, 0.1f);
	}

	public static void release() {
		soudPool.release();
	}

	public static SoundPool getSoundPool() {
		if (null == soudPool) {
			soudPool = new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);
		}
		return soudPool;
	}
}