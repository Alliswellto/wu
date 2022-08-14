package com.yanwu.design.metrics.write.impl;

import com.yanwu.design.metrics.EmailSender;
import com.yanwu.design.metrics.domain.RequestStat;
import com.yanwu.design.metrics.write.StatViewer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName EmailViewer * @Description TODO
 * @Author tako
 * @Date 18:19 2022/8/14
 * @Version 1.0
 **/
public class EmailViewer implements StatViewer {

    private EmailSender emailSender;

    private List<String> toAddresses = new ArrayList<>();

    public EmailViewer() {
        this.emailSender = new EmailSender();
    }

    public EmailViewer(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    public EmailViewer(List<String> toAddresses) {
        this.toAddresses = toAddresses;
    }

    public void addToAddress(String address) {
        toAddresses.add(address);
    }

    @Override
    public void output(Map<String, RequestStat> requestStats, long startTimeInMills, long endTimeInMills) {
        // TODO: format the requestStats to HTML style.
        // TODO: send it to email toAddresses.
    }
}
