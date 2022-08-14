package com.yanwu.design.generate.common;

import com.yanwu.design.generate.common.exception.IdGenerationFailureException;

/**
 * @ClassName IDGenerator * @Description TODO
 * @Author tako
 * @Date 14:11 2022/8/14
 * @Version 1.0
 **/
public interface IdGenerator {

    void generate() throws IdGenerationFailureException;
}
