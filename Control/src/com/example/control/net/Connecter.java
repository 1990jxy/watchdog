package com.example.control.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.example.control.utils.ProjectEnvironment;

import android.graphics.Point;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

public class Connecter {

	private Socket mSocketReceive;
	private String mStringRemoteHost;
	private int mIntRemotePort;
	private DataInputStream dataInputStream;
	private DataOutputStream dataOutputStream;
	// private boolean mBooleanAlive = true;
	private String tag = "Connecter";

	public Connecter(Socket socket) {
		try {

			this.mSocketReceive = socket;
			this.mSocketReceive.setSendBufferSize(4);
			this.mStringRemoteHost = socket.getInetAddress().getHostAddress();
			this.mIntRemotePort = socket.getPort();
			this.dataInputStream = new DataInputStream(
					this.mSocketReceive.getInputStream());
			this.dataOutputStream = new DataOutputStream(
					this.mSocketReceive.getOutputStream());

			byte[] buffer = new byte[1024];
			this.dataInputStream.read(buffer);
			// �������ĸ�ʽΪ:screen:width:height
			String utf = new String(buffer, 0, buffer.length);
			String[] datas = utf.trim().split(":");
			int width = Integer.parseInt(datas[1]);
			int height = Integer.parseInt(datas[2]);
			Point pointScreen = new Point(width, height);
			ProjectEnvironment.pointHostScreen = pointScreen;
			this.dataOutputStream.write(Build.MODEL.getBytes());// �����ֻ��ͺ�:

		} catch (Exception e) {
			e.printStackTrace();
			Log.e(tag, "e:" + e.getMessage());

		}
	}

	public void killSelf() {
		// mBooleanAlive = false;
		try {
			if (null != mSocketReceive && mSocketReceive.isClosed() == false) {
				mSocketReceive.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeMessage(String utf) throws IOException {
		if (ProjectEnvironment.BOOLEAN_LOCK_KEYBOAED) {
			// ����״̬�ŷ�������
			// this.dataOutputStream.writeUTF(utf);
			this.dataOutputStream.write(utf.getBytes());
			this.dataOutputStream.flush();
		}

	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		killSelf();
	}

	public Socket getmSocketReceive() {
		return mSocketReceive;
	}

	public void setmSocketReceive(Socket mSocketReceive) {
		this.mSocketReceive = mSocketReceive;
	}

	public String getmStringRemoteHost() {
		return mStringRemoteHost;
	}

	public void setmStringRemoteHost(String mStringRemoteHost) {
		this.mStringRemoteHost = mStringRemoteHost;
	}

	public int getmIntRemotePort() {
		return mIntRemotePort;
	}

	public void setmIntRemotePort(int mIntRemotePort) {
		this.mIntRemotePort = mIntRemotePort;
	}

	public DataInputStream getDataInputStream() {
		return dataInputStream;
	}

	public void setDataInputStream(DataInputStream dataInputStream) {
		this.dataInputStream = dataInputStream;
	}

	public DataOutputStream getDataOutputStream() {
		return dataOutputStream;
	}

	public void setDataOutputStream(DataOutputStream dataOutputStream) {
		this.dataOutputStream = dataOutputStream;
	}

}
