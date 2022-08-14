package com.yanwu.design.metrics.write.impl;

import com.google.gson.Gson;
import com.yanwu.design.metrics.domain.RequestStat;
import com.yanwu.design.metrics.write.StatViewer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @ClassName ConsoleView * @Description TODO
 * @Author tako
 * @Date 18:16 2022/8/14
 * @Version 1.0
 **/
public class ConsoleViewer implements StatViewer {

    private static final Logger logger = LoggerFactory.getLogger(ConsoleViewer.class);

    @Override
    public void output(Map<String, RequestStat> requestStats, long startTimeInMills, long endTimeInMills) {
        System.out.println("Span Time: [" + startTimeInMills + ", " + endTimeInMills+ "]");
        Gson gson = new Gson();
        logger.info("requestStats : {}", gson.toJson(requestStats));
    }
}
