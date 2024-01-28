package com.pratap;

public class ConsoleHelloService implements HelloService {
	private String prefix;
	private String suffix;

	public ConsoleHelloService() {
		this.prefix = "hello";
		this.suffix = "!";
	}
	public ConsoleHelloService(String preifx, String suffix) {
		this.prefix = preifx !=null ? preifx : "hello";
		this.suffix = suffix !=null ? suffix : "!";
	}

	public void sayHello(String name) {
		System.out.println(this.prefix + " " + name + " " + this.suffix);

	}

}