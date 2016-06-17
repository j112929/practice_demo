package main.aop.javassist;

public class ServiceImpl implements Service{

	@Override
	public void Action(int a) {
		System.out.println("do Action"+a);
	}
} 
