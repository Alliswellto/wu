package com.yanwu.design.generate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

/**
 * @ClassName IDGeneratorV1 * @Description TODO
 * @Author tako
 * @Date 13:59 2022/8/14
 * @Version 1.0
 **/
public class IdGeneratorV1 {

    private static final Logger logger = LoggerFactory.getLogger(IdGeneratorV1.class);

    public static String generate() {
        String id = "";
        try {
            String hostName = InetAddress.getLocalHost().getHostName();
            String[] tokens = hostName.split("\\.");
            if (tokens.length > 0) {
                hostName = tokens[tokens.length - 1];
            }
            char[] randomChars = new char[8];
            int count = 0;
            Random random = new Random();
            while (count < 8) {
                int randomAscii = random.nextInt(122);
                if (randomAscii >= 48 && randomAscii <= 57) {
                    randomChars[count] = (char)('0' + (randomAscii - 48));
                    count++;
                } else if (randomAscii >= 65 && randomAscii <= 90) {
                    randomChars[count] = (char)('A' + (randomAscii - 65));
                    count++;
                } else if (randomAscii >= 97 && randomAscii <= 122) {
                    randomChars[count] = (char)('a' + (randomAscii - 97));
                    count++;
                }
            }
            id = String.format("%s-%s-%s", hostName, System.currentTimeMillis(), random);
        } catch (UnknownHostException e) {
            logger.error("Failed to get the hostname!");
        }
        return id;
    }
}
