package hello.pratap.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pratap.ConsoleHelloService;
import com.pratap.HelloService;

@Configuration
@ConditionalOnClass(HelloService.class)
@EnableConfigurationProperties(HelloProperties.class)
public class HelloServiceAutoConfiguration {
	
	public HelloServiceAutoConfiguration(){
		System.out.println("HelloServiceAutoConfiguration consturctor");
	}
	@Bean
	@ConditionalOnMissingBean
	public HelloService hs(HelloProperties prop) {
		return new ConsoleHelloService(prop.getPrefix() , prop.getSuffix());
	}
}
