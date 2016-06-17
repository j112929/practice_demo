package main.aop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class SecurityProxyInvocationHandler implements InvocationHandler {
	private Object proxyedObject; 
    public SecurityProxyInvocationHandler(Object o) { 
	   proxyedObject = o; 
    } 
	
	@Override
	public Object invoke(Object object, Method method, Object[] args) throws Throwable {
		if (object instanceof Account && method.getName().equals("opertaion")) { 
			 SecurityChecker.checkSecurity(); 
		 } 
		 return method.invoke(proxyedObject, args); 
	}

}
