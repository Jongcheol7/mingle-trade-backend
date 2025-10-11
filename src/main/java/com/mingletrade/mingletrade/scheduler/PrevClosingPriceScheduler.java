package com.mingletrade.mingletrade.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mingletrade.mingletrade.service.PrevClosingPriceService;

@Component
public class PrevClosingPriceScheduler {

	private final PrevClosingPriceService service;

	public PrevClosingPriceScheduler(PrevClosingPriceService service) {
		super();
		this.service = service;
	}
	
	// 매일 새벽 00시 30분 실행
	@Scheduled(cron= "0 0 0 * * *")
	public void runDailyJob() {
		System.out.println("배치 시작");
		service.fetchAndInsertFromBinance();
		System.out.println("배치 종료");
	}
}
