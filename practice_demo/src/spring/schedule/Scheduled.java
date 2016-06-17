package spring.schedule;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
 * @Title: 
 * @Description: 
 * @Team: 技术1部Java开发小组
 * @Author zhuolin ji
 * @Date 2016年6月7日 下午2:08:48
 * @Version V1.0
 * @param <T>   */
@Target({java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.ANNOTATION_TYPE})  
@Retention(RetentionPolicy.RUNTIME)  
@Documented  
public @interface Scheduled  
{  
  public abstract String cron();  
  
  /**
   * An interval-based trigger where the interval is measured from the completion time of the previous task. The time unit value is measured in milliseconds.即表示从上一个任务完成开始到下一个任务开始的间隔，单位是毫秒。
   * @return
   */
public abstract long fixedDelay();  
  
  /**
   * An interval-based trigger where the interval is measured from the start time of the previous task. The time unit value is measured in milliseconds.即从上一个任务开始到下一个任务开始的间隔，单位是毫秒。
 * @return
 */
public abstract long fixedRate();  
}  