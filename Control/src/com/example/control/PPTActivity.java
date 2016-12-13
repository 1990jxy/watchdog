package com.example.control;

import com.example.control.net.Connecter;
import com.example.control.net.ConnecterPool;
import com.example.control.utils.INetCallBack;
import com.example.control.utils.MediaTool;
import com.example.control.utils.ProjectEnvironment;
import com.example.control.utils.RemoteOperateImpl;
import com.example.control.utils.T;
import com.example.control.utils.Utils;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class PPTActivity extends Activity implements OnClickListener, INetCallBack {

	private ImageView ivPlayOrEscape;
	private ImageView ivUp;
	private ImageView ivDown;
	private boolean isPlay = false;
	
	private RemoteOperateImpl mRemoteOperateImpl;
	private Connecter mConnector;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ppt);
		
		initView();
	}
	private void initView() {
		// TODO Auto-generated method stub
		mConnector = ConnecterPool.getConnectorByKey(ConnecterPool.STRING_CKEY);
		if (null != mConnector) {
			mRemoteOperateImpl = new RemoteOperateImpl(mConnector, this);
		} else {
			Toast.makeText(this,
					getResources().getString(R.string.app_lost_host),
					Toast.LENGTH_SHORT).show();
		}
		ProjectEnvironment.BOOLEAN_LOCK_KEYBOAED = true;
		
		ivPlayOrEscape=(ImageView)findViewById(R.id.iv_ppt_PlayOrEscape);
		ivUp=(ImageView)findViewById(R.id.iv_ppt_up);
		ivDown=(ImageView)findViewById(R.id.iv_ppt_down);
		
		ivPlayOrEscape.setOnClickListener(this);
		ivUp.setOnClickListener(this);
		ivDown.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.iv_ppt_PlayOrEscape:
			if (null==mRemoteOperateImpl) {
				T.showLong(PPTActivity.this, "123");
			}else {
//				MediaTool.load(PPTActivity.this, R.raw.open_close);
				if (isPlay) {// Í£Ö¹×´Ì¬
					isPlay = false;
					mRemoteOperateImpl.sendCommand(ProjectEnvironment.STRING_COMMAND_PPT_ESC);
					ivPlayOrEscape.setImageResource(R.drawable.video_pause);
				} else {// ²¥·Å×´Ì¬
					isPlay = true;
					mRemoteOperateImpl.sendCommand(ProjectEnvironment.STRING_COMMAND_PPT_F5);
					ivPlayOrEscape.setImageResource(R.drawable.video_start);
				}
			}
			break;
		case R.id.iv_ppt_up:
			mRemoteOperateImpl
			.sendCommand(ProjectEnvironment.STRING_COMMAND_PPT_UP);
//			MediaTool.load(PPTActivity.this, R.raw.ken_down);
			break;
		case R.id.iv_ppt_down:
			mRemoteOperateImpl
			.sendCommand(ProjectEnvironment.STRING_COMMAND_PPT_DOWN);
//			MediaTool.load(PPTActivity.this, R.raw.ken_down);
			break;

		default:
			break;
		}
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
}
