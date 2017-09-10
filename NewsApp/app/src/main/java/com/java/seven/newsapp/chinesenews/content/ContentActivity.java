package com.java.seven.newsapp.chinesenews.content;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import com.baidu.tts.auth.AuthInfo;
import com.baidu.tts.client.SpeechError;
import com.baidu.tts.client.SpeechSynthesizer;
import com.baidu.tts.client.SpeechSynthesizerListener;
import com.baidu.tts.client.TtsMode;
import com.bumptech.glide.Glide;
import com.java.seven.newsapp.R;
import com.java.seven.newsapp.adapter.NewsSummaryAdapter;
import com.java.seven.newsapp.bean.FavorNews;
import com.java.seven.newsapp.util.HtmlFormat;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class ContentActivity extends AppCompatActivity implements ContentContract.View, SpeechSynthesizerListener {
    private static final String TAG = "ContentActivity";
    @Bind(R.id.toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @Bind(R.id.news_image)
    ImageView newsImage;
    @Bind(R.id.news_content)
    WebView newsContent;
    private String id;
    private String text;
    private String title;
    private String url;
    private String imageUrl;

    private FloatingActionButton fab;
    private ContentContract.Presenter presenter;
    private SpeechSynthesizer mSpeechSynthesizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenter = new ContentPresenter(this);

        // 获取语音合成对象实例
        mSpeechSynthesizer = SpeechSynthesizer.getInstance();
        
        final Intent intent = getIntent();
        id = intent.getStringExtra(NewsSummaryAdapter.NEWS_ID);

        fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent1 = new Intent(ContentActivity.this, CommentActivity.class);
//                intent1.putExtra("newsId", id);
//                startActivity(intent1);
//            }
//        });
        fab.setVisibility(View.INVISIBLE);
        presenter.getNews(id);
    }

    @Override
    public void setTitleImage(String url) {
        this.imageUrl = url;
        Glide.with(this).load(url).into(newsImage);
    }

    @Override
    public void setContent(String text) {
        if(text.length() < 500)
            this.text = text;
        else
            this.text = text.substring(0,500);
        newsContent.loadDataWithBaseURL(null, HtmlFormat.getNewContent(text), "text/html", "utf-8", null);
        newsContent.getSettings().setJavaScriptEnabled(true);
        WebSettings settings = newsContent.getSettings();

        newsContent.setWebChromeClient(new WebChromeClient());
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
        collapsingToolbarLayout.setTitle(title);
    }

    @Override
    public void setFabVisible() {
        fab.setVisibility(View.VISIBLE);
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.news_content, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/
        switch (item.getItemId()) {
            case R.id.item_favorite:
                List<FavorNews> list = DataSupport.where("news_id = ?", this.id).find(FavorNews.class);
                if (list.size() == 0) {
                    new FavorNews().setNewsId(this.id).save();
                    Toast.makeText(this, "favorite", Toast.LENGTH_SHORT).show();
                }
                else {
                    DataSupport.deleteAll(FavorNews.class, "news_id = ?", this.id);
                    Toast.makeText(this, "unfavorite", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.item_share:
                showShare();
                Toast.makeText(this, "share被选择了", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_speak:
                startTTS();
                Toast.makeText(this, "speak被选择了", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void startTTS() {

        // 设置context
        mSpeechSynthesizer.setContext(this);
        // 设置语音合成状态监听器
        mSpeechSynthesizer.setSpeechSynthesizerListener(this);

        // 获取语音合成授权信息
        AuthInfo authInfo = mSpeechSynthesizer.auth(TtsMode.MIX);
        // 判断授权信息是否正确，如果正确则初始化语音合成器并开始语音合成，如果失败则做错误处理
        if (authInfo.isSuccess()) {
            mSpeechSynthesizer.initTts(TtsMode.MIX);
            mSpeechSynthesizer.speak(text);
        } else {
            Log.d(TAG, "授权失败");
        }
    }

    public void onError(String arg0, SpeechError arg1) {
        // 监听到出错，在此添加相关操作
    }
    public void onSpeechFinish(String arg0) {
        // 监听到播放结束，在此添加相关操作
    }
    public void onSpeechProgressChanged(String arg0, int arg1) {
        // 监听到播放进度有变化，在此添加相关操作
    }
    public void onSpeechStart(String arg0) {
        // 监听到合成并播放开始，在此添加相关操作
    }
    public void onSynthesizeDataArrived(String arg0, byte[] arg1, int arg2) {
        // 监听到有合成数据到达，在此添加相关操作
    }
    public void onSynthesizeFinish(String arg0) {
        // 监听到合成结束，在此添加相关操作
    }
    public void onSynthesizeStart(String arg0) {
        // 监听到合成开始，在此添加相关操作
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mSpeechSynthesizer.stop();
    }

    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
//关闭sso授权
        oks.disableSSOWhenAuthorize();

    // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle(title);

        String shareText;
        if(text.length() < 100)
            shareText = text;
        else
            shareText = text.substring(0,100);
    // text是分享文本，所有平台都需要这个字段
        oks.setText(shareText + " " + url);
        
        oks.setImageUrl(imageUrl);

    // url仅在微信（包括好友和朋友圈）中使用
//        oks.setUrl(url);

// 启动分享GUI
        oks.show(this);
    }
}
