package com.example.control.net;


import java.util.HashMap;
import java.util.Map.Entry;

/**
 * ������ӳأ�����Ϊ�Ժ�����һ���������̿��ƶ�̨���ԣ�������ֻ����һ��һ�Ŀ���
 * 
 * @author wangzy
 * 
 */
public class ConnecterPool {
	public static final String STRING_CKEY="ConnecterPool";

	public static HashMap<String, Connecter> mapConnectorPool = new HashMap<String, Connecter>();
	
	public static void putConnecter(String key,Connecter connector){
		//������������key����������
		clearPool();
		mapConnectorPool.put(STRING_CKEY, connector);
	}
	
    public static Connecter getConnectorByKey(String key){
    	return mapConnectorPool.get(key);
    }
    
    public static int getConnectorPoolSize(){
    	return mapConnectorPool.size();
    }
    public static void clearPool(){
    	for(Entry<String,Connecter> entry:mapConnectorPool.entrySet()){
    		entry.getValue().killSelf();
    	}
    	mapConnectorPool.clear();
    }
}
