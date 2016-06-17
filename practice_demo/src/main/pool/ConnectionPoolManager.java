package main.pool;


public class ConnectionPoolManager {
	public static ConnectionPoolManager instance;
	public ConnectionPoolManager(){
		this.init();
	}
	public static synchronized ConnectionPoolManager getInstance(){
		if(instance == null){
			instance = new ConnectionPoolManager();
		}
		return instance;
	}
	/**
	 * 得到连接
	 */
	public void getConnection(){
		
	}
	/**
	 * 释放连接到空闲池中
	 */
	public void freeConnection(){
		
	}
	/**
	 * 释放所有连接
	 */
	public synchronized void release(){
		
	}
	/**
	 * 创建连接池
	 * @param dsc
	 */
	private void createPools(DSConfig dsc){
		
	}
	/**
	 * 初始化连接池管理类的唯一实例，由构造函数调用
	 */
	private void init(){
		
	}
	/**
	 * 加载xml配置文件
	 */
	private void loadDrivers(){
		
	}
}
