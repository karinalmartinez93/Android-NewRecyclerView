package com.example.rkjc.news_app_2;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    // Declare variables that will be used to store the news article string
    private static String newsArticles = "";
    // Declare variable that will hold the newsRecyclerView
    private RecyclerView newsRecyclerView;
    // Declare variable that will hold the news recycler view adapter
    private NewsRecyclerViewAdapter newsRecyclerViewAdapter;
    // Declare variable that will hold the NewsItems objects returned in API call
    private ArrayList<NewsItem> articleList = new ArrayList<NewsItem>();

    // Called when activity becomes visible to the user
    @Override
    protected void onStart() {
        super.onStart();
    }

    // Called when activity is first created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Sets the current look of the activity
        setContentView(R.layout.activity_main);

        // Finds the recycler view in activity_main and stores it in newsRecyclerView
        newsRecyclerView = (RecyclerView) findViewById(R.id.news_recyclerview);

        // Instantiates the newsRecyclerViewAdapter
        newsRecyclerViewAdapter = new NewsRecyclerViewAdapter(this, articleList);

        // Sets the data behind the newsRecyclerView
        newsRecyclerView.setAdapter(newsRecyclerViewAdapter);

        // Sets the layout that the newsRecyclerView will use
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        new NewsQueryTask().execute();
    }

    public void setNewsRecyclerView(String newsArticles) {
        // Parse the API call results to a list of NewsItems
        articleList = JsonUtils.parseNews(newsArticles);

        // Send NewsItem list to newsRecyclerViewAdapter
        newsRecyclerViewAdapter.allArticles = articleList;

        // runs newsRecyclerViewAdapter on UI thread
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                newsRecyclerViewAdapter.notifyDataSetChanged();
            }
        });
    }

    class NewsQueryTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try {

                // Get string of news articles from API call
                newsArticles = NetworkUtils.getResponseFromHttpUrl(NetworkUtils.buildURL());

                // Pass newsArticles to newsRecyclerView
                setNewsRecyclerView(newsArticles);

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Get item that was clicked
        int itemThatWasClickedId = item.getItemId();

        // Check if item clicked was refresh button
        if (itemThatWasClickedId == R.id.action_search) {
            // If refresh button clicked get new articles
            new NewsQueryTask().execute();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
