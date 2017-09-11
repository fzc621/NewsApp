package com.java.seven.newsapp.util;

import java.util.ArrayList;

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

    public static String[] decodeShieldKeyWords(String shieldKeywordsStr) {
        String[] temp = shieldKeywordsStr.split(";");
        for (int i = 0; i < temp.length; ++i) {
            temp[i] = temp[i].trim();
        }

        ArrayList<String> shieldKeywordsList = new ArrayList<>();
        for (int i = 0; i < temp.length; ++i) {
            if (!temp[i].equals(""))
                shieldKeywordsList.add(temp[i]);
        }

        String[] shieldKeywords = new String[shieldKeywordsList.size()];
        for (int i = 0; i < shieldKeywords.length; ++i) {
            shieldKeywords[i] = shieldKeywordsList.get(i);
        }
        return shieldKeywords;
    }
}
