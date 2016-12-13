package com.example.control.utils;


import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Point;


/**
 * ����ϵͳ���õĲ���
 * 
 * 
 */
public class ProjectEnvironment {

	public static float FLOAT_BUTTON_RADIUS = 50f;// ��ť�뾶
	public static float FLOAT_MOUSE_RADIUS = 10f;// ��괥��뾶
	public static String STRING_PROJECT_RADIUS_KEY = "radius_key";
	public static String STRING_LOCATION_KEY = "location";// ����λ�õ�key

	public static int INT_DEFAULT_PORT_COMMAND = 1987;// Ĭ�϶˿�
	public static int INT_DEFAULT_PORT_IMG = 1988;// ����ͼƬ�Ķ˿ں���
	public static int INT_MOUSE_SENSE = 10;
	public static String STRING_HOST_IP = "192.168.0.104";// Ĭ��ip

	public static final String STRING_IP_REGX = "(0|[1-9]|[1-9][0-9]|(1[0-9][0-9]|(2[0-4][0-9]|25[0-5]))).(0|[1-9]|[1-9][0-9]|(1[0-9][0-9]|(2[0-4][0-9]|25[0-5]))).(0|[1-9]|[1-9][0-9]|(1[0-9][0-9]|(2[0-4][0-9]|25[0-5]))).(0|[1-9]|[1-9][0-9]|(1[0-9][0-9]|(2[0-4][0-9]|25[0-5])))";

	public static int INT_LOCATIONS_SIZE = 3;// ���������message����ֵ
	public static int INT_LOCATIONS_X = 0;
	public static int INT_LOCATIONS_Y = 1;
	public static int INT_LOCATIONS_KEY_STRING = 2;

	public static int INT_AFTER_SCALE_X = -1;
	public static int INT_AFTER_SCALE_Y = -1;

	public static boolean BOOLEAN_LOCK_KEYBOAED = false;// ��ǰ�����Ƿ�������״̬
	public static Point pointHostScreen = null;
	public static String STRING_CASE_KEY = "case";
	public static String ACTION_FILTER_SHUT_DOWN = "shutdown_filter_action";
	public static String STRING_KEY_SHUTDOWN = "shutdown_key";
	public static float FLOAT_SCALE = 1.0f;

	public static String STRING_IP_KEY = "ip_key";

	public static String STRING_COMMAND_ENTER = "enter";
	public static String STRING_COMMAND_CLOSE = "close_current";
	public static String STRING_COMMAND_RIGHT_CLICK = "right_click";
	public static String STRING_COMMAND_STOP_IMG_SEND = "stop_send_img";
	public static String STRING_COMMMAND_START_IMG_SEND = "start_send_img";
	public static String STRING_COMMAND_DOUBLE_CLICK = "doubleClick";

	public static String STRING_COMMAND_PPT_F5 = "F5";
	public static String STRING_COMMAND_PPT_ESC = "ESC";
	public static String STRING_COMMAND_PPT_PRE = "pre";
	public static String STRING_COMMAND_PPT_NEX = "nex";
	public static String STRING_COMMAND_PPT_UP = "up";
	public static String STRING_COMMAND_PPT_DOWN = "down";
	public static String STRING_COMMAND_LOCK_SCREEN = "lock_screen";

	public static byte[] BYTE_ENDDATA = new byte[] { 19, 87, 11 };// ����ͼƬ�Ľ�����־����
	public static String STRING_AD_URL = "http://www.jjandroid.com/ad/main!getAllList.action";

	public static String STRING_KEY_NOTIFY = "key_notify";
	public static String STRING_VALUE_NOTIFY = "1";

	public static String STRING_KEY_EUI = "eui_key";
	public static String STRING_VALUE_EUI = "yes";

	public static String YOUR_PHONE_DIMEN = "screen";

	public static ArrayList<Activity> globalActivitys = new ArrayList<Activity>();

	public static boolean isExit=false;
//	static {
//
//		AdBean defaultAdBeanSina = new AdBean();
//		defaultAdBeanSina.setAdUrl("http://weibo.com/1272669533?");
//		defaultAdBeanSina.setAdTitle("������������΢��");
//		arrayListAdBeanDefault.add(defaultAdBeanSina);
//
//		AdBean defaultAdBeanTencent = new AdBean();
//		defaultAdBeanTencent.setAdUrl("http://t.qq.com/woshiwzyn");
//		defaultAdBeanTencent.setAdTitle("�������ߵ���Ѷ΢��");
//		arrayListAdBeanDefault.add(defaultAdBeanTencent);
//	}

}
