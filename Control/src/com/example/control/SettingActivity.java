package com.example.control;

import java.net.InetAddress;
import java.net.Socket;

import com.example.control.net.Connecter;
import com.example.control.net.ConnecterPool;
import com.example.control.utils.ProjectEnvironment;
import com.example.control.utils.SharePersistent;
import com.example.control.utils.StringUtils;
import com.example.control.utils.T;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingActivity extends Activity implements OnClickListener {

	private Button btnButton;
	private EditText mEditTextIpInput;
	private EditText mEditTextPortInput;
	private LinkHostTask linkHostTask;
	private ProgressDialog mProgressDialog = null;
	private int mIntPort = ProjectEnvironment.INT_DEFAULT_PORT_COMMAND;
	private String hostIp;
	private SharedPreferences mySharedPreferences;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		
		initView();
	}
	
	private void initView() {
		// TODO Auto-generated method stub
		mySharedPreferences = getSharedPreferences("ShareData",Activity.MODE_PRIVATE);
		mEditTextIpInput=(EditText)findViewById(R.id.editTextIp);
		mEditTextPortInput=(EditText)findViewById(R.id.editTextPort);
		btnButton=(Button)findViewById(R.id.btn_setting_OK);
		
		btnButton.setOnClickListener(this);
		mEditTextIpInput.setText(mySharedPreferences.getString("Ip", ProjectEnvironment.STRING_HOST_IP));
		mEditTextPortInput.setText(StringUtils.nvl(mySharedPreferences.getInt("Port", ProjectEnvironment.INT_DEFAULT_PORT_COMMAND)));
		
		mProgressDialog = new ProgressDialog(SettingActivity.this);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mProgressDialog.setTitle(getString(R.string.content));
		mProgressDialog.setCancelable(true);
		mProgressDialog.setMessage(getString(R.string.contenting));
		mProgressDialog.setOnDismissListener(new OnDismissListener() {
			@Override
			public void onDismiss(DialogInterface dialog) {
				btnButton.setEnabled(true);
				if(null!=linkHostTask){
					linkHostTask.cancel(true);
				}
			}
		});
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_setting_OK:
			linkHostTask = new LinkHostTask();
			
			String protString = mEditTextPortInput.getText().toString();
			if (protString.matches("//^\\d+$//")) {
				try {
					mIntPort = Integer.parseInt(protString);
				} catch (Exception e) {
					// 端口输入错误
					Toast.makeText(
							SettingActivity.this,getResources().getString(
									R.string.setting_activity_setting_port),
							Toast.LENGTH_SHORT).show();
					return;
				}
			}
			
			hostIp = mEditTextIpInput.getText().toString();
			if (StringUtils.isEmpty(hostIp)
					|| !hostIp.matches(ProjectEnvironment.STRING_IP_REGX)) {
				// ip输入错误
				Toast.makeText(
							SettingActivity.this,getResources().getString(
									R.string.setting_activity_setting_inputrtip),
						Toast.LENGTH_SHORT).show();
			}
			
			linkHostTask.execute(hostIp);
			ProjectEnvironment.STRING_HOST_IP = hostIp;// 修改默认的ip
			SharePersistent.savePerference(SettingActivity.this,
			ProjectEnvironment.STRING_IP_KEY, hostIp);
			break;

		default:
			break;
		}
	}
	
	class LinkHostTask extends AsyncTask<String, Integer, String> {

		@Override
		protected void onPreExecute() {
			// 预处理
			btnButton.setEnabled(false);

			mProgressDialog.show();
			super.onPreExecute();
		}

		@Override
		protected String doInBackground(String... param) {
			// 后台计算
			String host = param[0];
			try {
				ConnecterPool.clearPool();
				mIntPort =Integer.parseInt(mEditTextPortInput.getText()
						.toString().trim());
				Socket socket = new Socket(InetAddress.getByName(host),
						mIntPort);
				Connecter conector = new Connecter(socket);
				ConnecterPool.putConnecter(conector.getmStringRemoteHost(),
						conector);
			} catch (Exception e) {
//				Log.e(tag, "message:" + e.getMessage());
				cancel(true);
				e.printStackTrace();
			}
			// publishProgress(10);
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// 处理结果
			btnButton.setEnabled(true);
			mProgressDialog.dismiss();

			if (ConnecterPool.getConnectorPoolSize() > 0) {
				// 连接成功
				Toast.makeText(SettingActivity.this, R.string.content_success, Toast.LENGTH_SHORT).show();
				Editor editor = mySharedPreferences.edit();
				editor.putString("Ip", hostIp); 
				editor.putInt("Port", mIntPort);
				editor.commit();
				finish();
			} else {
				// 连接失败
//				MediaTool.load(MainActivity.this, R.raw.done);
				T.showShort(SettingActivity.this, R.string.content_fail);
			}

		}

	};
}
