package com.java.seven.newsapp.chinesenews.content;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.java.seven.newsapp.R;
import com.java.seven.newsapp.adapter.NewsSummaryAdapter;
import com.java.seven.newsapp.util.AppConstants;
import com.java.seven.newsapp.util.HtmlFormat;
import com.java.seven.newsapp.util.SharedPreferencesUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ContentActivity extends AppCompatActivity implements ContentContract.View {

    @Bind(R.id.toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @Bind(R.id.news_image)
    ImageView newsImage;
    @Bind(R.id.news_content)
    WebView newsContent;
    private String id;

    private FloatingActionButton fab;
    private ContentContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenter = new ContentPresenter(this);

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
        Glide.with(this).load(url).into(newsImage);
    }

    @Override
    public void setContent(String text) {
        newsContent.loadDataWithBaseURL(null, HtmlFormat.getNewContent(text), "text/html", "utf-8", null);
        newsContent.getSettings().setJavaScriptEnabled(true);
        WebSettings settings = newsContent.getSettings();

        newsContent.setWebChromeClient(new WebChromeClient());
    }

    @Override
    public void setTitle(String title) {
        collapsingToolbarLayout.setTitle(title);
    }

    @Override
    public void setFabVisible() {
        fab.setVisibility(View.VISIBLE);
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
            case R.id.item_share:
                Toast.makeText(this, "share被选择了", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_speak:
                Toast.makeText(this, "speak被选择了", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
