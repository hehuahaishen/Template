package com.example.common.util;

/**
 * 随机数工具类
 */
public class RandomUtil {

    /**
     * 传入最小值，最大值，返回范围内的一个整数
     *
     * @param min       最大值
     * @param max       最大值
     * @return          随机数
     */
    private int getRandom(int min, int max) {
        int result = (int) (min + 1 + Math.random() * (max + 1));
        return result;
    }
}
