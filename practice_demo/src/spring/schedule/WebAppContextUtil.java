package spring.schedule;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author alvin
 */

@SuppressWarnings("all")
public class WebAppContextUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

   
	@Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

}
