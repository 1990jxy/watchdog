package com.example.control;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import com.example.control.custom.ImageBtn;
import com.example.control.net.Connecter;
import com.example.control.net.ConnecterPool;
import com.example.control.utils.INetCallBack;
import com.example.control.utils.MediaTool;
import com.example.control.utils.ProjectEnvironment;
import com.example.control.utils.RemoteOperateImpl;
import com.example.control.utils.SharePersistent;
import com.example.control.utils.T;
import com.example.control.utils.Utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Administrator
 *
 */
public class MainActivity extends Activity implements OnClickListener, INetCallBack, OnLoadCompleteListener {

	private Context _context;
	private TextView tvConnect;
	private TextView tvPC;
	
	private ImageBtn imageBtn00;
	private ImageBtn imageBtn01;
	private ImageBtn imageBtn02;
	private ImageBtn imageBtn03;
	private ImageBtn imageBtn04;
	private ImageBtn imageBtn05;
	private ImageBtn imageBtn06;
	private ImageBtn imageBtn07;
	private ImageBtn imageBtn08;
	private ImageBtn imageBtn12;
	private ImageBtn imageBtn13;
	private ImageBtn imageBtn14;
	
	private RemoteOperateImpl mRemoteOperateImpl;
	private Connecter mConnector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MediaTool.addLoadListener(this);
        _context=MainActivity.this;
        
        initView();
    }

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mConnector = ConnecterPool.getConnectorByKey(ConnecterPool.STRING_CKEY);
		if (null != mConnector) {
			mRemoteOperateImpl = new RemoteOperateImpl(mConnector, this);
		} else {
			Toast.makeText(this,
					getResources().getString(R.string.app_lost_host),
					Toast.LENGTH_SHORT).show();
		}
		ProjectEnvironment.BOOLEAN_LOCK_KEYBOAED = true;
	}

	private void initView() {
		// TODO Auto-generated method stub
		tvConnect=(TextView)findViewById(R.id.tvbtnConnent);
		tvPC=(TextView)findViewById(R.id.tvbtnPC);
		
		tvConnect.setOnClickListener(this);
		tvPC.setOnClickListener(this);
		initImageBtn();

		MediaTool.load(MainActivity.this, R.raw.in);
	}

	private void initImageBtn() {
		imageBtn00=(ImageBtn)findViewById(R.id.imagebtn_main_00);
		imageBtn01=(ImageBtn)findViewById(R.id.imagebtn_main_01);
		imageBtn02=(ImageBtn)findViewById(R.id.imagebtn_main_02);
		imageBtn03=(ImageBtn)findViewById(R.id.imagebtn_main_03);
		imageBtn04=(ImageBtn)findViewById(R.id.imagebtn_main_04);
		imageBtn05=(ImageBtn)findViewById(R.id.imagebtn_main_05);
		imageBtn06=(ImageBtn)findViewById(R.id.imagebtn_main_06);
		imageBtn07=(ImageBtn)findViewById(R.id.imagebtn_main_07);
		imageBtn08=(ImageBtn)findViewById(R.id.imagebtn_main_08);
		
		imageBtn12=(ImageBtn)findViewById(R.id.imagebtn_main_12);
		imageBtn13=(ImageBtn)findViewById(R.id.imagebtn_main_13);
		imageBtn14=(ImageBtn)findViewById(R.id.imagebtn_main_14);
		
		imageBtn00.setImageResource(R.drawable.computer);
		imageBtn00.setTextViewText("锁屏");
		imageBtn00.setOnClickListener(this);
		imageBtn01.setImageResource(R.drawable.write);
		imageBtn01.setTextViewText("写字板");
		imageBtn01.setOnClickListener(this);
		imageBtn02.setImageResource(R.drawable.math);
		imageBtn02.setTextViewText("计算器");
		imageBtn02.setOnClickListener(this);
		imageBtn03.setImageResource(R.drawable.check_mark);
		imageBtn03.setTextViewText("打开");
		imageBtn03.setOnClickListener(this);
		imageBtn04.setImageResource(R.drawable.nocheck_mark);
		imageBtn04.setTextViewText("关闭");
		imageBtn04.setOnClickListener(this);
		imageBtn05.setImageResource(R.drawable.picture);
		imageBtn05.setTextViewText("画图");
		imageBtn05.setOnClickListener(this);
		imageBtn06.setImageResource(R.drawable.clock);
		imageBtn06.setTextViewText("关机");
		imageBtn06.setOnClickListener(this);
		imageBtn07.setImageResource(R.drawable.remove);
		imageBtn07.setTextViewText("取消关机");
		imageBtn07.setOnClickListener(this);
		imageBtn08.setImageResource(R.drawable.ppt);
		imageBtn08.setTextViewText("PPT");
		imageBtn08.setOnClickListener(this);
		
		imageBtn12.setImageResource(R.drawable.cogwheel);
		imageBtn12.setTextViewText("设置选项");
		imageBtn12.setOnClickListener(this);
		imageBtn13.setImageResource(R.drawable.smart_phone);
		imageBtn13.setTextViewText("关于程序");
		imageBtn13.setOnClickListener(this);
		imageBtn14.setImageResource(R.drawable.send);
		imageBtn14.setTextViewText("退出程序");
		imageBtn14.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Connecter ckm = ConnecterPool.getConnectorByKey(ConnecterPool.STRING_CKEY);
		
		MediaTool.load(MainActivity.this, R.raw.bi);
		if (null == ckm&&!(v.getId()==R.id.tvbtnConnent||v.getId()==R.id.tvbtnPC
				||v.getId()==R.id.imagebtn_main_12||v.getId()==R.id.imagebtn_main_13||v.getId()==R.id.imagebtn_main_14)) {
			notifyNoNet();
			return;
		} else {
			switch (v.getId()) {
			case R.id.tvbtnConnent:
				Utils.startOtherActivity(MainActivity.this, SettingActivity.class);
				break;
			case R.id.tvbtnPC:
				try {
					Utils.mkdir("/sdcard/Download");
					Utils.copyAssetFile2Sdcard(MainActivity.this, "pc.zip",
							"/sdcard/Download/");
					Toast.makeText(MainActivity.this,
							"拷贝完成,请把sd卡'Download'文件下面内容拷贝到电脑后使用!",
							Toast.LENGTH_LONG).show();
				} catch (IOException e) {
					e.printStackTrace();
					Log.e("main", "e:" + e.toString());
					Toast.makeText(MainActivity.this, "拷贝失败，请检查是否插好sd卡！",
							Toast.LENGTH_SHORT).show();
				}
				break;
			case R.id.imagebtn_main_00:
				T.showShort(MainActivity.this, "00");
				mRemoteOperateImpl.sendCommand(ProjectEnvironment.STRING_COMMAND_LOCK_SCREEN);
				break;
			case R.id.imagebtn_main_01:
//				T.showShort(MainActivity.this, "01");
				mRemoteOperateImpl.sendCommandDos("notepad");
				break;
			case R.id.imagebtn_main_02:
//				T.showShort(MainActivity.this, "02");
				mRemoteOperateImpl.sendCommandDos("calc");
				break;
			case R.id.imagebtn_main_03:
//				T.showShort(MainActivity.this, "03");
				mRemoteOperateImpl.sendCommand(ProjectEnvironment.STRING_COMMAND_ENTER);
				break;
			case R.id.imagebtn_main_04:
//				T.showShort(MainActivity.this, "04");
				mRemoteOperateImpl.sendCommand(ProjectEnvironment.STRING_COMMAND_CLOSE);
				break;
			case R.id.imagebtn_main_05:
				T.showShort(MainActivity.this, ProjectEnvironment.BOOLEAN_LOCK_KEYBOAED+"");
				mRemoteOperateImpl.sendCommandDos("mspaint");
				break;
			case R.id.imagebtn_main_06:
//				T.showShort(MainActivity.this, "06");
				mRemoteOperateImpl.sendCommandDos("shutdown -s -t 30");
				break;
			case R.id.imagebtn_main_07:
//				T.showShort(MainActivity.this, "07");
				mRemoteOperateImpl.sendCommandDos("shutdown -a");
				break;
			case R.id.imagebtn_main_08:
//				T.showShort(MainActivity.this, "08");
				Utils.startOtherActivity(MainActivity.this, PPTActivity.class);
				break;
			case R.id.imagebtn_main_12:
				T.showShort(_context, "LOL");
				break;
			case R.id.imagebtn_main_13:
				T.showShort(_context, "ALL BY:JXY");
				break;
			case R.id.imagebtn_main_14:
				System.exit(0);
				break;
	
			default:
				break;
			}
		}
	}

	/**
	 * 提醒没有网络
	 */
	public void notifyNoNet() {
		Toast.makeText(MainActivity.this,
				getResources().getString(R.string.mouse_activity_net),
				Toast.LENGTH_LONG).show();
//		Animation animation = AnimationUtils.loadAnimation(this, R.anim.shake);
//		mButtonLinkHost.startAnimation(animation);
	}
	@Override
	public void OnStart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void OnFinish() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void OnIntercepted(String source) {
		// TODO Auto-generated method stub
		Utils.doNetLost(this);
	}

	@Override
	public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
		// TODO Auto-generated method stub
		MediaTool.play(sampleId);
	}
}
