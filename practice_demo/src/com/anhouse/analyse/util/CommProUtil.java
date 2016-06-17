package com.anhouse.analyse.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**   
 * @Title: TODO
 * @Description: TODO(用一句话描述该类文件做什么)
 * @Team: 技术1部Java开发小组
 * @Author Andy-ZhichengYuan   
 * @Date 2016年02月02日
 * @Version V1.0   */
public class CommProUtil implements HdfsCfgKeys {
	private static final Logger LOGGER = LoggerFactory.getLogger(CommProUtil.class);
	
	private static PropertiesCfg proCfg;
	private static volatile CommProUtil instance = null;
	public static CommProUtil getInstance() {
		if (instance == null) {
			synchronized (CommProUtil.class) {
				if (instance == null) {
					instance = new CommProUtil();
					// instance.init();
				}
			}
		}
		return instance;
	}


	private CommProUtil() {
		init();
	}

	private void init() {
		proCfg = new PropertiesCfg();
		proCfg.setProCfg("conf.properties");
	}

	public String getProperty(String key) {
		return proCfg.getValue(key);
	}
	
	
////////////////////////////////////////////////////////////////////////////////
	
	/** 获取HDFS配置的PIWIK log日志文件目录   */
	public static String getHdfsLogDir() {
		return CommProUtil.getInstance().getProperty(K_DayLogPath);
	}
	
	/** 获取HDFS配置的MapReduce结果输出路径    */
	public static String getHdfsOutputPath() {
		return CommProUtil.getInstance().getProperty(K_FSOutput);
	}
	
	/** 将HDFS路径，更换为备份NameNode节点上   */
	public static String replaceBackupNN(String hdfsPath, int index) {
		if( StringUtils.isNotBlank(hdfsPath) ) {
			String backupNN = null;
			String[] nnodes = null;
			String nameNodes = CommProUtil.getInstance().getProperty(K_NameNodes);
			if(StringUtils.contains(nameNodes, "|")) {
				nnodes = StringUtils.split(nameNodes, "|");
			} else {//仅1个NameNode节点
				nnodes = new String[1];
				nnodes[0] = nameNodes;
			}
			if(index >= 0 && index < nnodes.length) {
				backupNN = nnodes[index];
			} else {//默认
				backupNN = nnodes[0];
			}
			nnodes = null;
			nameNodes = null;
			//先判断配置有端口号的情况，再处理缺省端口号的情况
			String masterNN = StringUtils.substringBetween(hdfsPath, "://", ":");//保留端口号：8020
			if( StringUtils.isBlank(masterNN) ) {
				masterNN = StringUtils.substringBetween(hdfsPath, "://", "/");
			}
			LOGGER.warn("The NameNode has changed："+ masterNN +" >>> "+ backupNN);
			LOGGER.info("Before the change, HDFS path: "+ hdfsPath);
			hdfsPath = StringUtils.replaceOnce(hdfsPath, masterNN, backupNN);
			LOGGER.info("After the replacement, HDFS path: "+ hdfsPath);
		}
		return hdfsPath;
	}
	
	
	
	/** 获取HDFS配置文件的类型
	 * @return int : 1=线上的，2=测试的    */
	public static Integer getHdfsCfgType() {
		String hdfsLogDir = getHdfsLogDir();
		if( StringUtils.contains(hdfsLogDir, "10.10.100.") ) {
			return 1;//线上的
		}else {//192.168.11.236、10.59.72.62
			return 2;//测试的
		}
	}
	
	/** 获取HDFS配置的PIWIK log日志文件目录的日期格式   */
	public static String getHdfsLogDateFormat() {
		return CommProUtil.getInstance().getProperty(K_LogDateFormat);
	}
	
	/** 获取HDFS配置的PIWIK log日志文件的名称    */
	public static String getHdfsLogFileName() {
		return CommProUtil.getInstance().getProperty(K_LogFileName);
	}
	
	
////////////////////////////////////////////////////////////////////////////////
	
	/** 获取昨天log日志文件夹  */
	public static String getYestLogPath() {
		//@param dateFormat: yyyyMMdd(默认) 或 "yyyy-MM-dd"
		String dateFormat = getHdfsLogDateFormat();
		if( StringUtils.isBlank(dateFormat) ) dateFormat = "yyyyMMdd";
		String yesterday = DateUtil.getAgoBackDate(-1, dateFormat);
	    return (getHdfsLogDir() + yesterday +"/");
	}
	
	/** 获取今天以前的第{agoDays}天的log日志文件夹
	 * @param agoDays: 今天以前的第n天，为负整数。  */
	public static String getAgoDaysLogPath(int agoDays) {
		int days = (agoDays <= 0) ? agoDays : -agoDays;
		//@param dateFormat: yyyyMMdd(默认) 或 "yyyy-MM-dd"
		String dateFormat = getHdfsLogDateFormat();
		if( StringUtils.isBlank(dateFormat) ) dateFormat = "yyyyMMdd";
		String yesterday = DateUtil.getAgoBackDate(days, dateFormat);
		return (getHdfsLogDir() + yesterday +"/");
	}
	
/////////////////////////////////////////////////////////////////////////////////
	
	/** 获取昨天结果输出的文件夹  */
	public static String getYestOutPath() {
		//@param dateFormat: yyyyMMdd(默认) 或 "yyyy-MM-dd"
		String dateFormat = getHdfsLogDateFormat();
		if( StringUtils.isBlank(dateFormat) ) dateFormat = "yyyyMMdd";
		String yesterday = DateUtil.getAgoBackDate(-1, dateFormat);
	    return (getHdfsOutputPath() + yesterday +"/");
	}
	
	/** 获取今天以前的第{agoDays}天的结果输出文件夹
	 * @param agoDays: 今天以前的第n天，为负整数。   */
	public static String getAgoDaysOutPath(int agoDays) {
		int days = (agoDays <= 0) ? agoDays : -agoDays;
		//@param dateFormat: yyyyMMdd(默认) 或 "yyyy-MM-dd"
		String dateFormat = getHdfsLogDateFormat();
		if( StringUtils.isBlank(dateFormat) ) dateFormat = "yyyyMMdd";
		String yesterday = DateUtil.getAgoBackDate(days, dateFormat);
		return (getHdfsOutputPath() + yesterday +"/");
	}
	
	
	
}
