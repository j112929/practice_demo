package main.pool;
import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//import edu.shao.springJdbc.service.IUserService;
public class DbUtils {
	@SuppressWarnings("unused")
	private static ApplicationContext ctx = null;

	@BeforeClass //表示在所以测试方法之前执行，且只执行一次。
	public static void onlyOnce() {
	ctx = new ClassPathXmlApplicationContext("spring-datasource.xml");
	}
	public static void main(String[] args) {
		if(ctx!=null){
		System.out.println("sadad");
		}
	}
}
