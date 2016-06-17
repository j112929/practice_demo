package com.anhouse.analyse.util;

/**   
 * @Title: HDFS配置项的Key 
 * @Description: conf.properties配置文件中关于HDFS的配置项
 * @Team: 技术1部Java开发小组
 * @Author Andy-ZhichengYuan   
 * @Date 2016年4月5日
 * @Version V1.0   */
public interface HdfsCfgKeys {
	
	public static final String HDFS_Root = "hdfs://";
	
	/** Developer HDFS configurations: */
	public static final String K_FSDefName = "fs.default.name";
	public static final String K_FSImplCache = "fs.hdfs.impl.disable.cache";
	
	public static final String K_DayLogPath = "hdfs.log.path";
	public static final String K_FSOutput = "hdfs.output.path";
	public static final String K_NameNodes = "hdfs.namenode.clusters";//集群IP地址，多个以“|”分隔
	public static final String K_LogDateFormat = "hdfs.log.path.date.format";
	public static final String K_LogFileName = "hdfs.log.file.name";
	
	
	/** MySQL Log configuration: */
	public static final String K_MysqlLogPath = "mysql.log.path";
	public static final String K_MysqlOutPath = "mysql.out.path";
	
	
	/** Hive db configuration:   */
	String K_HiveIp = "hive.server.ssh.ip";
	String K_HiveUser = "hive.server.ssh.user";
	String K_HivePswd = "hive.server.ssh.pswd";
	String K_HiveRoot = "hive.server.hdfs.root";
	String K_HivePageUrl = "hive.page.t.hdfs.url";
	String K_HivePageField = "hive.page.t.partition.field";
	String K_HiveSqoop2Url = "hive.sqoop2.url";

	

	
	
}
