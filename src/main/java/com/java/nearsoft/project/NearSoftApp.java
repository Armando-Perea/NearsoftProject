package com.java.nearsoft.project;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import lombok.extern.slf4j.Slf4j;

/**
 * Hello Vitar System!
 *
 */
@SpringBootApplication
@EnableScheduling
public class NearSoftApp {
	
	public static  Logger logger = LogManager.getLogger(NearSoftApp.class);
	
	public static Map<String,String> aliasMap = new HashMap<String,String>();

	public static void main( String[] args )
    {
        SpringApplication.run(NearSoftApp.class, args);
    }
	
//	@ManagedOperation
//	//@Scheduled(cron= "0 0/5 * * * *")
//	@Scheduled(cron = "0 * * * * *")
//	public void testConnection() {
//		
//	}

}
