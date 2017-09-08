package com.java.seven.newsapp.bean;

import java.util.List;

/**
 * Created by zzy on 17-9-8.
 */

public class SearchNews {
    /**
     * list : [{"lang_Type":"zh-CN","newsClassTag":"文化","news_Author":"三联生活周刊","news_ID":"201601220712e3081db0bde94949a0e0b1ccd49f1343","news_Pictures":"http://upload.lifeweek.com.cn/2016/0121/1453361407809.jpg","news_Source":"其他","news_Time":"20160121153000","news_Title":"南山南，北京北","news_URL":"http://www.lifeweek.com.cn/2016/0121/47144.shtml","news_Video":"No Match","news_Intro":"\u201c你在南方的艳阳里大雪纷飞，我在北方的寒夜里四季如春。\u201d苍凉磁性的女声在唱，..."},{"lang_Type":"zh-CN","newsClassTag":"财经","news_Author":"中华网","news_ID":"2015110607142a2e486060f44c14bc620fd6a1e58c26","news_Pictures":"http://img.gmw.cn/pic/content_logo.png","news_Source":"光明网","news_Time":"202605121600","news_Title":"京北众筹股权融资成功率100%","news_URL":"http://economy.gmw.cn/2015-11/05/content_17618090.htm","news_Video":"No Match","news_Intro":"京北众筹股权融资成功率100%京北众212468经营快讯/enpproper..."},{"lang_Type":"zh-CN","newsClassTag":"文化","news_Author":"新浪文化","news_ID":"2015103007146d40f1fb7a964f178279b871fb29593a","news_Pictures":"http://i3.sinaimg.cn/history/2015/1029/U6715P1488DT20151029151503.jpg","news_Source":"新浪新闻","news_Time":"201501029151900","news_Title":"北京出版集团参展第十届北京文博会","news_URL":"http://history.sina.com.cn/cul/fx/2015-10-29/1519127627.shtml","news_Video":"No Match","news_Intro":"　　第十届中国北京国际文化创意产业博览会于今日在中国国际展览中心(老馆)正式..."},{"lang_Type":"zh-CN","newsClassTag":"军事","news_Author":"　","news_ID":"201508010712d68f0a903a0e4fd8b51ef07938c9aa5d","news_Pictures":"","news_Source":"","news_Time":"20150731122800","news_Title":"盘点北京申奥历程","news_URL":"http://www.yangtse.com/yaowen/2015-07-31/598229.html","news_Video":"No Match","news_Intro":"北京时间7月31日，国际奥委会第128次全会在马来西亚吉隆坡继续举行。傍晚时..."},{"lang_Type":"zh-CN","newsClassTag":"国际","news_Author":"","news_ID":"201507230918ef4267e43c554ffbb5a594bd70b85f95","news_Pictures":"128046875_14375424056361n.jpg","news_Source":"","news_Time":"20150722132300","news_Title":"北京各界送别万里","news_URL":"http://news.xinhuanet.com/photo/2015-07/22/c_128046875_17.htm","news_Video":"No Match","news_Intro":"年月日，一代改革闯将万里与世长辞，享年岁。月日，万里遗体在北京火化，天安门下..."},{"lang_Type":"zh-CN","newsClassTag":"财经","news_Author":"人民网-房产频道","news_ID":"20150826071391eb0f23a3e54940ad61bf309cfbacdd","news_Pictures":"","news_Source":"人民网","news_Time":"20150825164900","news_Title":"全北京仰止于此","news_URL":"http://house.people.com.cn/n/2015/0825/c164220-27515296.html","news_Video":"No Match","news_Intro":"（注：此文属于人民网登载的商业信息，文章内容不代表本网观点，仅供参考。）\t　..."},{"lang_Type":"zh-CN","newsClassTag":"体育","news_Author":"搜狐体育","news_ID":"201511040714a1722c44064e48029b5a11b98cfcbaef","news_Pictures":"","news_Source":"搜狐新闻","news_Time":"202603221200","news_Title":"女排联赛北京2","news_URL":"http://sports.sohu.com/20151103/n425153696.shtml","news_Video":"No Match","news_Intro":"　　11月3日，2015-2016赛季第20届全国女排联赛战至第2轮，II组..."},{"lang_Type":"zh-CN","newsClassTag":"体育","news_Author":"人民网－人民日报","news_ID":"201512080714431fd1835f4e42d0b84141aa704ba54d","news_Pictures":"","news_Source":"人民网","news_Time":"202707034300","news_Title":"北京队遭遇三连败","news_URL":"http://sports.people.com.cn/n/2015/1207/c14820-27894464.html","news_Video":"No Match","news_Intro":"　　扫描二维码　　看更多内容　　12月6日，CBA（中国男篮职业联赛）常规赛..."},{"lang_Type":"zh-CN","newsClassTag":"国内","news_Author":"人民网－人民日报","news_ID":"20151121071396ff2de6d8d64da38ce768bd1f26ff5c","news_Pictures":"","news_Source":"人民网","news_Time":"2046033800","news_Title":"习近平回到北京","news_URL":"http://politics.people.com.cn/n/2015/1120/c1024-27835131.html","news_Video":"No Match","news_Intro":"　　新华社北京11月19日电　19日晚上，在结束出席二十国集团领导人第十次峰..."},{"lang_Type":"zh-CN","newsClassTag":"体育","news_Author":"搜狐体育","news_ID":"2015121807130222089e01764737bac4cd3f5946965d","news_Pictures":"","news_Source":"搜狐新闻","news_Time":"210700","news_Title":"北京男排3","news_URL":"http://sports.sohu.com/20151217/n431693566.shtml","news_Video":"No Match","news_Intro":"　　12月17日，2015-2016赛季第20届全国男排联赛战罢第10轮，I..."},{"lang_Type":"zh-CN","newsClassTag":"军事","news_Author":"","news_ID":"20151030071475c58df1ee314238a0de3b59539ab836","news_Pictures":"http://img03.imgcdc.com/smallpic/articleLogo.png","news_Source":"中华网","news_Time":"201501029154300","news_Title":"北京人权论坛","news_URL":"http://news.china.com/focus/zgms/11174612/20151029/20654301.html","news_Video":"No Match","news_Intro":"北京人权论坛从2008年开始，每年举办一届。几年来，论坛始终坚持坦诚交流、互..."},{"lang_Type":"zh-CN","newsClassTag":"健康","news_Author":"新浪体育","news_ID":"201509210711506625fe46f040e3b90c072299f8befd","news_Pictures":"http://news.fjsen.com/images/attachement/jpg/site2/20150920/00248c733720176877cf4b.jpg http://news.fjsen.com/images/attachement/jpg/site2/20150920/00248c733720176877cf4c.jpg http://news.fjsen.com/images/attachement/jpg/site2/20150920/00248c733720176877cf4d.jpg http://news.fjsen.com/images/attachement/jpg/site2/20150920/00248c733720176877cf4e.jpg http://news.fjsen.com/images/attachement/jpg/site2/20150920/00248c733720176877cf4f.jpg","news_Source":"东南网","news_Time":"20150920121300","news_Title":"北京马拉松奇装异服","news_URL":"http://news.fjsen.com/2015-09/20/content_16652984.htm","news_Video":"No Match","news_Intro":"北京时间2015年9月20日，北京马拉松开跑，每年都有穿着奇装异服的跑者吸引..."},{"lang_Type":"zh-CN","newsClassTag":"国内","news_Author":"新华网","news_ID":"201512070711017abf518fac4d61aac22a534995a304","news_Pictures":"","news_Source":"新华网","news_Time":"202706172100","news_Title":"习近平回到北京","news_URL":"http://news.xinhuanet.com/politics/2015-12/06/c_1117370498.htm","news_Video":"No Match","news_Intro":"　　新华网北京月日电  日下午，在结束出席气候变化巴黎大会，对津巴布韦和南非..."},{"lang_Type":"zh-CN","newsClassTag":"国内","news_Author":"","news_ID":"2015072309180ed40e15fb6e4db3bd34bd6df5ed50c8","news_Pictures":"128046875_14375424040861n.jpg","news_Source":"","news_Time":"20150722132300","news_Title":"北京各界送别万里","news_URL":"http://news.xinhuanet.com/photo/2015-07/22/c_128046875_11.htm","news_Video":"No Match","news_Intro":"月日，万里同志追悼会在北京八宝山举行，各界人士前来送别这位中国的改革先锋。贾..."},{"lang_Type":"zh-CN","newsClassTag":"体育","news_Author":"人民网-体育频道　","news_ID":"201508020711090d67df33ad4d03af36009e4cac8036","news_Pictures":"","news_Source":"","news_Time":"20150801140200","news_Title":"北京再启程","news_URL":"http://www.yangtse.com/yaowen/2015-08-01/599264.html","news_Video":"No Match","news_Intro":"今天，从马来西亚吉隆坡传来喜讯，北京张家口联合申办2022年冬奥会成功。这不..."},{"lang_Type":"zh-CN","newsClassTag":"体育","news_Author":"","news_ID":"201509210711b208198a39de4c709ce0ebfc0d530e45","news_Pictures":"http://news.xinhuanet.com/local/2015-09/20/1116618925_14427506193981n.jpg","news_Source":"新华网","news_Time":"20150920201600","news_Title":"奔跑吧！北京","news_URL":"http://news.xinhuanet.com/local/2015-09/20/c_1116618925.htm","news_Video":"No Match","news_Intro":"　　月日，参赛选手在比赛前留影。当日，年北京马拉松在北京天安门广场开跑。 新..."},{"lang_Type":"zh-CN","newsClassTag":"国际","news_Author":"","news_ID":"201507230918f393b721f56a4863ac47e9d2248f854b","news_Pictures":"128046875_14375424055081n.jpg","news_Source":"","news_Time":"20150722132300","news_Title":"北京各界送别万里","news_URL":"http://news.xinhuanet.com/photo/2015-07/22/c_128046875_15.htm","news_Video":"No Match","news_Intro":"月日，万里遗体在北京火化，民众自发在八宝山殡仪馆外悼念。中新社发盛佳鹏摄Co..."},{"lang_Type":"zh-CN","newsClassTag":"国内","news_Author":"","news_ID":"201507230918dfa5575d4b06420e9a3a3b62be2a4a5f","news_Pictures":"128046875_14375424045041n.jpg","news_Source":"","news_Time":"20150722132300","news_Title":"北京各界送别万里","news_URL":"http://news.xinhuanet.com/photo/2015-07/22/c_128046875_12.htm","news_Video":"No Match","news_Intro":"月日，万里同志追悼会在北京八宝山举行，来自农村改革发祥地的安徽省小岗村的农民..."},{"lang_Type":"zh-CN","newsClassTag":"财经","news_Author":"","news_ID":"201507230918f65d8ef024964b8795290bf1d50a7f95","news_Pictures":"128046875_14375424020981n.jpg","news_Source":"","news_Time":"20150722132300","news_Title":"北京各界送别万里","news_URL":"http://news.xinhuanet.com/photo/2015-07/22/c_128046875_2.htm","news_Video":"No Match","news_Intro":"月日，万里遗体在北京火化，社会各界人士前往八宝山殡仪馆进行悼念。中新社发盛佳..."},{"lang_Type":"zh-CN","newsClassTag":"体育","news_Author":"新华网","news_ID":"201509210711da5d97088d764b67afe26bda3674af07","news_Pictures":"","news_Source":"光明网","news_Time":"20150920111000","news_Title":"北京马拉松开跑","news_URL":"http://sports.gmw.cn/2015-09/20/content_17101830.htm","news_Video":"No Match","news_Intro":"北京马拉松开跑(1)_综合体育_光明网开跑,参赛选手,比赛,李明,马拉松29..."}]
     * pageNo : 1
     * pageSize : 20
     * totalPages : 1229
     * totalRecords : 24576
     */

    private int pageNo;
    private int pageSize;
    private int totalPages;
    private int totalRecords;
    private List<ListBean> list;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * lang_Type : zh-CN
         * newsClassTag : 文化
         * news_Author : 三联生活周刊
         * news_ID : 201601220712e3081db0bde94949a0e0b1ccd49f1343
         * news_Pictures : http://upload.lifeweek.com.cn/2016/0121/1453361407809.jpg
         * news_Source : 其他
         * news_Time : 20160121153000
         * news_Title : 南山南，北京北
         * news_URL : http://www.lifeweek.com.cn/2016/0121/47144.shtml
         * news_Video : No Match
         * news_Intro : “你在南方的艳阳里大雪纷飞，我在北方的寒夜里四季如春。”苍凉磁性的女声在唱，...
         */

        private String lang_Type;
        private String newsClassTag;
        private String news_Author;
        private String news_ID;
        private String news_Pictures;
        private String news_Source;
        private String news_Time;
        private String news_Title;
        private String news_URL;
        private String news_Video;
        private String news_Intro;

        public String getLang_Type() {
            return lang_Type;
        }

        public void setLang_Type(String lang_Type) {
            this.lang_Type = lang_Type;
        }

        public String getNewsClassTag() {
            return newsClassTag;
        }

        public void setNewsClassTag(String newsClassTag) {
            this.newsClassTag = newsClassTag;
        }

        public String getNews_Author() {
            return news_Author;
        }

        public void setNews_Author(String news_Author) {
            this.news_Author = news_Author;
        }

        public String getNews_ID() {
            return news_ID;
        }

        public void setNews_ID(String news_ID) {
            this.news_ID = news_ID;
        }

        public String getNews_Pictures() {
            return news_Pictures;
        }

        public void setNews_Pictures(String news_Pictures) {
            this.news_Pictures = news_Pictures;
        }

        public String getNews_Source() {
            return news_Source;
        }

        public void setNews_Source(String news_Source) {
            this.news_Source = news_Source;
        }

        public String getNews_Time() {
            return news_Time;
        }

        public void setNews_Time(String news_Time) {
            this.news_Time = news_Time;
        }

        public String getNews_Title() {
            return news_Title;
        }

        public void setNews_Title(String news_Title) {
            this.news_Title = news_Title;
        }

        public String getNews_URL() {
            return news_URL;
        }

        public void setNews_URL(String news_URL) {
            this.news_URL = news_URL;
        }

        public String getNews_Video() {
            return news_Video;
        }

        public void setNews_Video(String news_Video) {
            this.news_Video = news_Video;
        }

        public String getNews_Intro() {
            return news_Intro;
        }

        public void setNews_Intro(String news_Intro) {
            this.news_Intro = news_Intro;
        }
    }
}
