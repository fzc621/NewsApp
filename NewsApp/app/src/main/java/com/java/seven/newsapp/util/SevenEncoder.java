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

    public static String encodeShieldKeyWords(String[] shieldKeywords) {
        String shieldKeywordsStr = "";
        for (int i = 0; i < shieldKeywords.length; ++i) {
                shieldKeywordsStr += shieldKeywords[i];
            if (i < shieldKeywords.length - 1) {
                shieldKeywordsStr += "; ";
            }
        }
        return shieldKeywordsStr;
    }
}
