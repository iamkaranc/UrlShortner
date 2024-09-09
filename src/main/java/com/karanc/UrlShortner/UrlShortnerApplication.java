package com.karanc.UrlShortner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication(scanBasePackages =  {"com.karanc.UrlShortner.*"})
@EnableAutoConfiguration
@EnableScheduling
@Configuration
@EnableConfigurationProperties
@EntityScan(basePackages = {"com.karanc.UrlShortner.*"})
@EnableJpaRepositories(basePackages = {"com.karanc.UrlShortner.*"}, entityManagerFactoryRef = "entityManagerFactory")
public class UrlShortnerApplication {

	public static void main(String[] args) throws UnknownHostException {

		System.out.println("Hey, I am working fine.");
		System.setProperty("hostName", InetAddress.getLocalHost().getHostName());
		System.setProperty("applicationName", "UrlShortner");

		SpringApplication.run(UrlShortnerApplication.class, args);
	}

}
