package com.java.seven.newsapp.util;

/**
 * Created by caibao on 17-9-10.
 */

public class SevenEncoder {
    public static String encodeSubscribeStates(boolean[] subscribeStates) {
        String subscribeStatesStr = "";
        for (int i = 0; i < subscribeStates.length; ++i) {
            if (subscribeStates[i] == true) {
                subscribeStatesStr += "1";
            }
            else {
                subscribeStatesStr += "0";
            }
            if (i < subscribeStates.length - 1) {
                subscribeStatesStr += " ";
            }
        }
        return subscribeStatesStr;
    }
}
