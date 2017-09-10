package com.java.seven.newsapp.util;

/**
 * Created by caibao on 17-9-10.
 */

public class SevenDecoder {
    public static boolean[] decodeSubscribeStatesStr(String subscribeStatesStr) {
        String[] stateStrs = subscribeStatesStr.trim().split(" ");
        boolean[] states = new boolean[stateStrs.length];
        for (int i = 0; i < states.length; ++i) {
            if (stateStrs[i].equals("1"))
                states[i] = true;
            else
                states[i] = false;
        }
        return states;
    }
}
