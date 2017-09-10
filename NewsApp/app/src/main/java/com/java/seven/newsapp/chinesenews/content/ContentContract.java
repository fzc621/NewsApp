package com.java.seven.newsapp.chinesenews.content;

/**
 * Created by linsawako on 2017/1/30.
 */

public interface ContentContract {

    interface View{
        void setTitleImage(String url);

        void setContent(String text);

        void setTitle(String title);

        void setFabVisible();

        void setUrl(String url);
    }

    interface Presenter{
        void getNews(String id);
    }

    interface Model{
        void getNews(CallBackNewsContent callback, String id);
    }

}
