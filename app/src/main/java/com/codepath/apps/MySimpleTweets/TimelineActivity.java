package com.codepath.apps.MySimpleTweets;

// import android.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.astuetz.PagerSlidingTabStrip;
import com.codepath.apps.MySimpleTweets.models.SmartFragmentStatePagerAdapter;
import com.codepath.apps.MySimpleTweets.models.Tweet;

import fragments.HomeTimelineFragment;
import fragments.MentionTimelineFragment;
import fragments.TweetsListFragment;

public class TimelineActivity extends ActionBarActivity {

    private TweetsListFragment fragmentTweetList;
    private ViewPager vpPager;
    private PagerSlidingTabStrip tabStrip;
    private TweetsPagerAdapter tweetsPagerAdapter;
    // private HomeTimelineFragment homeTimeline;

    // private SwipeRefreshLayout swipeContainer;
    private final int TWEET_ACTIVATE_ID=1;
    public static final String POSTED_STATUS="g7190305_project_4_POSTED_STATUS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);


        // display logo
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        // get he viewpager
        vpPager = (ViewPager) findViewById(R.id.viewpager);
        // set viewpaper adapter for the paper
        tweetsPagerAdapter = new TweetsPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(tweetsPagerAdapter);
        // Find the pager sliding tabs
        tabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        // Attach the paper tabs to the viewPper
        tabStrip.setViewPager(vpPager);

        //swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        //swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
        //     @Override
        //     public void onRefresh() {
        //         // Your code to refresh the list here.
        //         // Make sure you call swipeContainer.setRefreshing(false)
        //         // once the network request has completed successfully.
        //         populateTimeline(1);
        //     }
        // });
        // Configure the refreshing colors
        //swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
        //        android.R.color.holo_green_light,
        //        android.R.color.holo_orange_light,
        //        android.R.color.holo_red_light);

        // lvTweets.setOnScrollListener(new EndlessScrollListener() {
        //     @Override
        //     public void onLoadMore(int page, int totalItemsCount) {
        //         populateTimeline(aTweets.getSince_id());
        //     }
        // });

        // if ( savedInstanceState == null ) {
        //     fragmentTweetList = (TweetsListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_timeline);
        // }


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_timeline, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.mi_compose) {
            Intent i = new Intent(TimelineActivity.this, TweetComposeActivity.class);
            i.putExtra(POSTED_STATUS, "");
            startActivityForResult(i, TWEET_ACTIVATE_ID);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ( resultCode == RESULT_OK && requestCode == TWEET_ACTIVATE_ID ) {
            Tweet tweet = (Tweet) data.getSerializableExtra(POSTED_STATUS);

            HomeTimelineFragment homeTimelineFragment = (HomeTimelineFragment) tweetsPagerAdapter.getRegisteredFragment(0);
            homeTimelineFragment.insert(tweet, 0);

        //     HomeTimelineFragment homeTimelineFragment;
        //     homeTimelineFragment = (HomeTimelineFragment) getSupportFragmentManager().findFragmentById(R.id.swipeContainer);
        //     homeTimelineFragment.insert(tweet, 0);

            // aTweets.add(tweet);
            // homeTimeline.insert(tweet, 0);

            // Log.d("DEBUG", data.toString());
            // setupInfo = (SetupInfo) data.getSerializableExtra("setupInfo");
            // Log.i("DEBUG", setupInfo.toString());
        }
    }

    public void onProfileView(MenuItem mi) {
        // Launch the profile view
        Intent i = new Intent(TimelineActivity.this, ProfileActivity.class);
        startActivity(i);
    }

    public class TweetsPagerAdapter extends SmartFragmentStatePagerAdapter {
        private String tabTitles[] = {"Home","Mentions"};

        public TweetsPagerAdapter(FragmentManager fm ) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0 ) {
                return new HomeTimelineFragment();
            } else if( position == 1) {
                return new MentionTimelineFragment();
            } else {
                return null;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // return super.getPageTitle(position);
            return tabTitles[position];
        }

        @Override
        public int getCount() {
            return tabTitles.length;
        }
    }
}
