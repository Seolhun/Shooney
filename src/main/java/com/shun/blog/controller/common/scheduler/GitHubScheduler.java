package com.shun.blog.controller.common.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Component
public class GitHubScheduler {
	@Autowired
	private static final Logger LOG = LoggerFactory.getLogger(GitHubScheduler.class);

	@Scheduled(cron="0 0 8 * * ?")
	public void getNaverMusics() throws IOException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.KOREA);
		Date currentTime = new Date();
	}
}