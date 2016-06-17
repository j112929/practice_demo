package main.aop.javassist;

import java.lang.reflect.Method;

public interface InterceptorInterface extends org.aopalliance.intercept.Interceptor{
	public int intercept(Object instance, Method method, Object[] Args);
}
