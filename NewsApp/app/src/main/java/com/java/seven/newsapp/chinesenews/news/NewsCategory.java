package com.java.seven.newsapp.chinesenews.news;

import com.java.seven.newsapp.R;

/**
 * Created by caibao on 17-9-8.
 */

public class NewsCategory {
    public static final int ALL = 0;
    public static final int SCIENCE = 1;
    public static final int EDU = 2;
    public static final int MILITARY = 3;
    public static final int DOMESTIC = 4;
    public static final int SOCIETY = 5;
    public static final int CULTURE = 6;
    public static final int CAR = 7;
    public static final int INTERNATIONAL = 8;
    public static final int SPORTS = 9;
    public static final int ECONOMY = 10;
    public static final int HEALTH = 11;
    public static final int ENTERTAINMENT = 12;

    public static final int CATEGORY_CNT = 12;

    public static int[] getCategoryCodesButAll() {
        return new int[] {SCIENCE, EDU, MILITARY, DOMESTIC, SOCIETY, CULTURE,
                CAR, INTERNATIONAL, SPORTS, ECONOMY, HEALTH, ENTERTAINMENT};
    }

    public static String[] getAllCategoryNamesButAll() {
        int[] categoryCodes = getCategoryCodesButAll();
        String[] categoryNames = new String[categoryCodes.length];
        for (int i = 0; i < categoryNames.length; ++i) {
            categoryNames[i] = codeToName(categoryCodes[i]);
        }
        return categoryNames;
    }

    public static int[] getAllCategoryCodes() {
        return new int[] {ALL, SCIENCE, EDU, MILITARY, DOMESTIC, SOCIETY, CULTURE,
         CAR, INTERNATIONAL, SPORTS, ECONOMY, HEALTH, ENTERTAINMENT};
    }

    public static String[] getAllCategoryNames() {
        int[] categoryCodes = getAllCategoryCodes();
        String[] categoryNames = new String[categoryCodes.length];
        for (int i = 0; i < categoryNames.length; ++i) {
            categoryNames[i] = codeToName(categoryCodes[i]);
        }
        return categoryNames;
    }

    public static String codeToName(int code) {
        switch (code) {
            case ALL:           return "首页";
            case SCIENCE:       return "科技";
            case EDU:           return "教育";
            case MILITARY:      return "军事";
            case DOMESTIC:      return "国内";
            case SOCIETY:       return "社会";
            case CULTURE:       return "文化";
            case CAR:           return "汽车";
            case INTERNATIONAL: return "国际";
            case SPORTS:        return "体育";
            case ECONOMY:       return "财经";
            case HEALTH:        return "健康";
            case ENTERTAINMENT: return "娱乐";
            default:            return "";
        }
    }

    public static int codeToId(int code) {
        switch (code) {
            case SCIENCE:
                return R.id.category1;
            case EDU:
                return R.id.category2;
            case MILITARY:
                return R.id.category3;
            case DOMESTIC:
                return R.id.category4;
            case SOCIETY:
                return R.id.category5;
            case CULTURE:
                return R.id.category6;
            case CAR:
                return R.id.category7;
            case INTERNATIONAL:
                return R.id.category8;
            case SPORTS:
                return R.id.category9;
            case ECONOMY:
                return R.id.category10;
            case HEALTH:
                return R.id.category11;
            case ENTERTAINMENT:
                return R.id.category12;
            default:
                return -1;
        }
    }

    public static int nameToCode(String name) {
        switch (name) {
            case "首页":           return ALL;
            case "科技":           return SCIENCE;
            case "教育":           return EDU;
            case "军事":           return MILITARY;
            case "国内":           return DOMESTIC;
            case "社会":           return SOCIETY;
            case "文化":           return CULTURE;
            case "汽车":           return CAR;
            case "国际":           return INTERNATIONAL;
            case "体育":           return SPORTS;
            case "财经":           return ECONOMY;
            case "健康":           return HEALTH;
            case "娱乐":           return ENTERTAINMENT;
            default:            return 0;

        }
    }

}
