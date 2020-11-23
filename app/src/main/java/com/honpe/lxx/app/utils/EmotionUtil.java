package com.honpe.lxx.app.utils;

import java.util.LinkedHashMap;

/**
 * @ProjectName: Honpe
 * @CreateDate: 2020/7/6 15:51
 * @Author: 李熙祥
 * @Description: java类作用描述:表情加载类,可自己添加多种表情，分别建立不同的map存放和不同的标志符即可
 */
public class EmotionUtil {
    /**
     * key-表情文字;
     * value-表情图片资源
     */
    public static LinkedHashMap<String, Integer> EMPTY_GIF_MAP;
    public static LinkedHashMap<String, Integer> EMOTION_STATIC_MAP;


    static {
        EMPTY_GIF_MAP = new LinkedHashMap<>();

    }
}
