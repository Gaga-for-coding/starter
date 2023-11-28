package com.guissisoftware.starter.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:dbconfig.properties")
public class DbConfiguration {
	private final Environment env;

	@Autowired
	public DbConfiguration(Environment env) {
		this.env = env;
	}

	@Override
	public String toString() {
		return "Username: "+env.getProperty("user") +", Password: "+env.getProperty("password");
	}
}
