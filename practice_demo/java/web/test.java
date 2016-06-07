package web;


import org.jzl.com.dao.UserMapper;
import org.jzl.com.entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class test {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext ctx=new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
		UserMapper userMapper=(UserMapper)ctx.getBean("userMapper");
		User u=new User();
		u.setUsername("admin");
		u.setPassword("admin");
		System.out.println(userMapper.selectUser(u));
		//插入（去掉下面的注释进行调试）
		/*
		User insertUser=new User();
		insertUser.setUsername("testUsername");
		insertUser.setPassword("testPassword");
		userMapper.insertUser(insertUser);
		*/
		//更新（去掉下面的注释进行调试）
		/*
		u.setId(1);
		u.setPassword("updatePassword");
		userMapper.updateUser(u);
		*/
		//删除（去掉下面的注释进行调试）
		/*
		userMapper.deleteUser(9);
		*/
	}
}
