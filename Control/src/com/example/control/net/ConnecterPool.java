package com.example.control.net;


import java.util.HashMap;
import java.util.Map.Entry;

/**
 * 这个连接池，备用为以后用来一个无线鼠盘控制多台电脑，现在我只进行一对一的控制
 * 
 * @author wangzy
 * 
 */
public class ConnecterPool {
	public static final String STRING_CKEY="ConnecterPool";

	public static HashMap<String, Connecter> mapConnectorPool = new HashMap<String, Connecter>();
	
	public static void putConnecter(String key,Connecter connector){
		//参数传进来的key，留待后用
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
