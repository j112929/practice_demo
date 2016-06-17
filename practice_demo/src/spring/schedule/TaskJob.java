package spring.schedule;
import org.springframework.scheduling.annotation.Scheduled;  
import org.springframework.stereotype.Component;  
//@Service
@Component("taskJob") 
public class TaskJob {
	 @Scheduled(cron = "0 0 3 * * ?")  
    public void job1() {  
        System.out.println("Taskjob-----------任务进行中。。。");  
    }  
}
