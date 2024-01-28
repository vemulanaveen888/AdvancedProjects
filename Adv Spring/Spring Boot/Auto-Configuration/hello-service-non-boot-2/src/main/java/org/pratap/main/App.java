package org.pratap.main;

import com.pratap.ConsoleHelloService;
import com.pratap.HelloService;

public class App {

	public static void main(String[] args) {
		HelloService service = new ConsoleHelloService();
		service.sayHello("pratap");
		
		service = new ConsoleHelloService("Howdy", "?");
		service.sayHello("java");
	}

}
