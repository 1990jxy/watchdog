package com.example.control.utils;


import java.io.UnsupportedEncodingException;

public class StringUtils {

    /**
     * �ж��ַ����Ƿ�Ϊ��
     * ps:�˷�����android���д��棬TextUtils.isEmpty
     * @param str
     * @return
     */
	public static boolean isEmpty(String str) {
		return str == null || "".equals(str.trim());
	}
	
	/**
     * �ж��ַ����Ƿ�Ϊ�ջ���null�ַ���
     * @param str
     * @return
     */
	public static boolean isEmptyNull(String str) {
        return str == null || "".equals(str.trim()) || "null".equals(str);
    }

	

	/**
	 * ������תΪ�ַ���
	 * 
	 * @param array
	 * @param encode
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String convertByteArrayToString(byte[] array, String encode) throws UnsupportedEncodingException {
		if (array == null || array.length == 0) {
			return null;
		}
		return new String(array, encode);
	}

    /**
     * ����int�Ͳ�����stringֵ
     * @param param
     * @return
     */
    public static String nvl(int param) {
        return String.valueOf(param);
    }
    
    //---------------------����ķ���Ŀǰδʹ��----------------------------//
    
    
    public static String convertByteArrayToString(byte[] array) throws UnsupportedEncodingException {
        return convertByteArrayToString(array, "utf-8");
    }

    

}

