package com.pms.comm.quartz.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.time.LocalTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class RefreshPriceJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//        log.info(LocalTime.now() + ": RefreshPriceJob Execute");
//
//        // 스케줄러에 의해 돌아갈 코드 작성
//        List<Room> roomList = roomService.selectAll();
//        priceService.crawlingProcess(roomList);
//
//        log.info(LocalTime.now() + ": RefreshPriceJob Exit");
    }
}