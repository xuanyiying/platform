package com.platform.common.enumation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangying
 * Created on 2019/10/23.
 */
public enum UserPriority {
    /**
     * 普通用户
     */
    ORDINARY("ORDINARY",0),
    /**
     *  二级代理
     */
    SECONDARY("SECONDARY",2),
    /**
     * 特级代理
     */
    SPECIAL("SPECIAL",4),
    /**
     * 超级代理
     */
    SUPER("SUPER",6);

    public int getValue() {
        return value;
    }

    public String getPriority() {
        return priority;
    }

    private int value;
    private String priority;
    UserPriority(String priority, int value) {
        this.value = value;
        this.priority = priority;
    }
    public static List<Integer> getValues(){
        List<Integer> values = new ArrayList <>(4);
        Arrays.stream(UserPriority.values()).map(item -> values.add(item.getValue()));
        return values;
    }


}
