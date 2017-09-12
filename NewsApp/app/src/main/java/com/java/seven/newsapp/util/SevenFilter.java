package com.java.seven.newsapp.util;

import com.java.seven.newsapp.bean.LatestNews;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caibao on 17-9-11.
 */

public class SevenFilter {
    public static List<LatestNews.ListBean> filter(List<LatestNews.ListBean> list, String[] ignores) {
        List<LatestNews.ListBean> filtered = new ArrayList<>();
        for (int i = 0; i < list.size(); ++i) {
            if (keep(list.get(i), ignores))
                filtered.add(list.get(i));
        }
        return filtered;
    }

    public static boolean keep(LatestNews.ListBean item, String[] ignores) {
        String title = item.getNews_Title();
        for (int i = 0; i < ignores.length; ++i) {
            if (title.toLowerCase().indexOf(ignores[i].toLowerCase()) != -1)
                return false;
        }
        String intro = item.getNews_Intro();
        for (int i = 0; i < ignores.length; ++i) {
            if (intro.toLowerCase().indexOf(ignores[i].toLowerCase()) != -1)
                return false;
        }
        return true;
    }


}
