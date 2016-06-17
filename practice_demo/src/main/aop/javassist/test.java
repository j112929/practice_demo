package main.aop.javassist;

public class test {
	/**
	* @param args
	*/
	public static void main(String[] args) {
		ServiceImpl c = new ServiceImpl();
		Service i = (Service) DProxy.createProxy(ServiceImpl.class, new MyInterceptor(c));
		i.Action(123);
	}

}
