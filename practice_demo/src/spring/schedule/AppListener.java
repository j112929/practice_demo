package spring.schedule;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AppListener implements ServletContextListener {
	private final static Logger LOGGER = LoggerFactory.getLogger(AppListener.class);

	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
//		System.out.println("***************自定义监听器！！！***************");
		LOGGER.info("***************自定义监听器！！！***************");
	}


	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}
}
