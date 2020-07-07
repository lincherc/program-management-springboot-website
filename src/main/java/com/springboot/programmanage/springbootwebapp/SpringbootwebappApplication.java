package com.springboot.programmanage.springbootwebapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;

@MapperScan("com.springboot.programmanage.springbootwebapp")
@SpringBootApplication
public class SpringbootwebappApplication {


	@Autowired
	private Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootwebappApplication.class, args);
	}
	@Bean
	public MessageSource messageSource(){
		ResourceBundleMessageSource messageSource=new ResourceBundleMessageSource();
		messageSource.setBasename(environment.getRequiredProperty("message.source.basename"));
		messageSource.setUseCodeAsDefaultMessage(
				Boolean.parseBoolean(environment.getRequiredProperty("message.source.use.code.as.default.message")));
		return messageSource;
	}

}
