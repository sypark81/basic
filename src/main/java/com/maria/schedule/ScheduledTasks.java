package com.maria.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class ScheduledTasks {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	@Scheduled(fixedRate = 5000)
	public void helloWorldTask(){
		log.info("Fixed rate every 5 second " + dateFormat.format(new Date()));
	}
	
	@Scheduled(cron = "0/10 * * * * *")
	public void runEvery10Sec(){
		log.info("Cron expression - Run every 10 second - " + dateFormat.format(new Date()));
		
	}

}
