package com.java.seven.newsapp.util;

/**
 * Created by caibao on 17-9-10.
 */

public class SevenPreprocessor {
    public static String preprocessIntro(String raw) {
        String intro = raw.replaceAll("\\s", " ").trim();
        if (intro.length() > 100)
            intro = intro.substring(0, 100);
        return intro;
    }

    public static String preprocessAuthor(String raw) {
        String author = raw.replaceAll("\\s", "");
        for (int i = 0; i < author.length(); ++i) {
            if (author.charAt(i) == ':' | author.charAt(i) == '：') {
                author = author.substring(i + 1);
            }
        }
        if (author.equals(""))
            author = "unknown";
        return author;
    }

    public static String preprocessTime(String raw) {
        String m = raw.substring(4, 6);
        String d = raw.substring(6, 8);
        return m + "-" + d;
    }

}
