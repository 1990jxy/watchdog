package com.example.control.utils;

public interface IRemoteOperate {
	public String moveMouse(float x,float y);
	public String click(float x,float y);//����mouseDown��MouseClickò�ƿ��Բ��������
	public String doubleClick(float x,float y);
	public String rightClick(float x, float y);
	public String keyDown(int keyCode);
	public String keyUp(int keyCode);
	public String mouseDown(float x,float y);
	public String mouseUp(float x,float y);
	public String mouseWheel(int x);
	public String sendCommand(String command);
}
