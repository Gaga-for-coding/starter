package com.guissisoftware.starter.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app.sbip.ct")
public class AppProperties {

	private final String name;
	private final String ip;
	private final int port;
	private final Security security;

	public AppProperties(String name, String ip, int port, Security security) {
		this.name = name;
		this.ip = ip;
		this.port = port;
		this.security = security;
	}

	public String name() {
		return name;
	}

	public String ip() {
		return ip;
	}

	public int port() {
		return port;
	}

	public Security security() {
		return security;
	}

	@Override
	public String toString() {
		return "AppProperties{" +
				"name='" + name + '\'' +
				", ip='" + ip + '\'' +
				", port=" + port +
				", security=" + security +
				'}';
	}
}

