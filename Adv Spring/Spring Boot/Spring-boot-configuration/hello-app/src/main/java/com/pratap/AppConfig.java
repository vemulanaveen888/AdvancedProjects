package com.pratap;

import org.pratap.BazzConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configuration
@Import({BazzConfig.class})
@ImportResource("classpath:org/pratap/xml/sample-beans.xml")
public class AppConfig {

}
