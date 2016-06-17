package main.aop.javassist;

import javassist.CtMethod;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;

import javassist.CannotCompileException;
import javassist.NotFoundException;

/**
 * 基于Javassist动态生成字节码实现简单的动态代理
 * Dynamic Proxy based on Javassist
 * @author:godsong
 * @version:1.01 2012/3/16
 * */
public class DProxy {
	/**
	 * 动态生成的代理类名前缀 prefix name for Proxy
	 */
	private static final String PROXY_CLASS_NAME = ".Gproxy$";
	/**
	 * 代理类名索引 用于标示一个唯一的代理类（具体的代理类名为Gproxy$n） index for generate a unique proxy
	 * class
	 */
	private static int proxyIndex = 1;
	/**
	 * 代理拦截器(利用继承减少动态构造的字节码) Proxy interceptor(desingn for inherit)
	 */
	protected InterceptorInterface interceptor;
	protected DProxy(InterceptorInterface interceptor) {
		this.interceptor = interceptor;
	}

	/**
	 * 创建动态代理的工厂方法 static factory method for create proxy
	 * 
	 * @param targetClass
	 *            :被代理的类型
	 * @param interceptor
	 *            拦截器实例
	 * @return 返回动态代理实例 它实现了targerClass的所有接口。 因此可以向上转型为这些之中的任意接口
	 */
	@SuppressWarnings("unchecked")
	public static Object createProxy(Class<?> targetClass, InterceptorInterface interceptor) {
		int index = 0;
		/* 获得运行时类的上下文 */
		ClassPool pool = ClassPool.getDefault();
		/* 动态创建代理类 */
		CtClass proxy = pool.makeClass(targetClass.getPackage().getName() + PROXY_CLASS_NAME + proxyIndex++);

		try {
			/* 获得DProxy类作为代理类的父类 */
			CtClass superclass = pool.get("main.aop.javassist.DProxy");
			proxy.setSuperclass(superclass);
			/* 获得被代理类的所有接口 */
			CtClass[] interfaces = pool.get(targetClass.getName()).getInterfaces();
			for (CtClass i : interfaces) {
				/* 动态代理实现这些接口 */
				proxy.addInterface(i);
				/* 获得结构中的所有方法 */
				CtMethod[] methods = i.getDeclaredMethods();
				for (int n = 0; n < methods.length; n++) {
					CtMethod m = methods[n];
					/* 构造这些Method参数 以便传递给拦截器的interceptor方法 */
					StringBuilder fields = new StringBuilder();
					fields.append("private static java.lang.reflect.Method method" + index);
					fields.append("=Class.forName(\"");
					fields.append(i.getName());
					fields.append("\").getDeclaredMethods()[");
					fields.append(n);
					fields.append("];");
					/* 动态编译之 */
					CtField cf = CtField.make(fields.toString(), proxy);
					proxy.addField(cf);
					GenerateMethods(pool, proxy, m, index);
					index++;
				}
			}
			/* 创建构造方法以便注入拦截器 */
			CtConstructor cc = new CtConstructor(new CtClass[] { pool.get("main.aop.javassist.InterceptorInterface") }, proxy);
			cc.setBody("{super($1);}");
			proxy.addConstructor(cc);
			// proxy.writeFile();
			return proxy.toClass().getConstructor(InterceptorInterface.class).newInstance(interceptor);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	* 动态生成方法实现（内部调用）
	* */
	private static void GenerateMethods(ClassPool pool,CtClass proxy,CtMethod method,int index){

		try {
			CtMethod cm = new CtMethod(method.getReturnType(), method.getName(), method.getParameterTypes(), proxy);
			/* 构造方法体 */
			StringBuilder mbody = new StringBuilder();
			mbody.append("{super.interceptor.intercept(this,method");
			mbody.append(index);
			mbody.append(",$args);}");
			cm.setBody(mbody.toString());
			proxy.addMethod(cm);
		} catch (CannotCompileException e) {
			e.printStackTrace();
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
	}
}



