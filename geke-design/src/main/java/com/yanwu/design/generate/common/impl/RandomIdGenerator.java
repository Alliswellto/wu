package com.yanwu.design.generate.common.impl;

import com.yanwu.design.generate.common.LogTraceIdGenerator;
import com.yanwu.design.generate.common.exception.IdGenerationFailureException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

/**
 * Id Generator that is used to generate random IDs.
 *
 * <p>
 * The IDs generated by this class are not absolutely unique,
 * but the probability of duplication is very low.
 *
 * @ClassName RandomIdGenerator * @Description TODO
 * @Author tako
 * @Date 14:12 2022/8/14
 * @Version 1.0
 **/
public class RandomIdGenerator implements LogTraceIdGenerator {

    private static final Logger logger = LoggerFactory.getLogger(RandomIdGenerator.class);

    /**
     * Generate the random ID. The IDs may be duplicated only in extreme situatio
     *
     * @return an random ID
     */
    @Override
    public void generate() throws IdGenerationFailureException {
        String hostname = null;
        try {
            hostname = getLastFieldOfHostName();
        } catch (UnknownHostException e) {
            throw new IdGenerationFailureException();
        }
        long currentTimeMillis = System.currentTimeMillis();
        String randomString = generateRandomAlphameric(8);
        String id = String.format("%s-%s-%s", hostname, currentTimeMillis, randomString);
    }

    /**
     * Generate random string which
     * only contains digits, uppercase letters and lowercase letters.
     *
     * @param length should not be less than 0
     * @return the random string. Returns empty string if {@length} is 0
     */
    protected String generateRandomAlphameric(int length) throws IllegalArgumentException {
        if (length <= 0) {
            throw new IllegalArgumentException();
        }
        char[] randomChars = new char[length];
        int count = 0;
        Random random = new Random();
        while (count < length) {
            int maxAscii = 'z';
            int randomAscii = random.nextInt();
            boolean isDigit = randomAscii >= '0' && randomAscii <= '9';
            boolean isUppercase = randomAscii >= 'A' && randomAscii <= 'Z';
            boolean isLowercase = randomAscii >= 'a' && randomAscii <= 'z';
            if (isDigit || isUppercase || isLowercase) {
                randomChars[count] = (char) (randomAscii);
                ++count;
            }
        }
        return new String(randomChars);
    }

    protected String getLastSubstrSplittedByDot(String hostName) throws IllegalArgumentException {
        if (hostName == null || StringUtils.isBlank(hostName)) {
            throw new IllegalArgumentException();
        }
        String[] tokens = hostName.split("\\.");
        String substrOfHostName = tokens[tokens.length - 1];
        return substrOfHostName;
    }

    /**
     * Get the local hostname and
     * extract the last field of the name string splitted by delimiter '.'.
     *
     * @return the last field of hostname. Returns null if hostname is not obtain
     */
    protected String getLastFieldOfHostName() throws UnknownHostException {
        String substrOfHostName = null;
        String hostName = InetAddress.getLocalHost().getHostName();
        if (hostName == null || StringUtils.isBlank(hostName)) {
            throw new UnknownHostException("Unknown hostname!");
        }
        substrOfHostName = getLastSubstrSplittedByDot(hostName);
        return substrOfHostName;
    }
}
