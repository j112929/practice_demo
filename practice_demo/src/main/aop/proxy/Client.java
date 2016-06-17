package main.aop.proxy;

import java.lang.reflect.Proxy;

public class Client {
public static void main(String[] args) {
	Account account = (Account) Proxy.newProxyInstance( 
			 Account.class.getClassLoader(), 
			 new Class[] { Account.class }, 
			 new SecurityProxyInvocationHandler(new AccountImpl()) 
		 ); 
		 account.function(); 
}
}
