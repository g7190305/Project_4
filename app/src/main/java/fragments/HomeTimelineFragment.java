package fragments;

import android.os.Bundle;
import android.util.Log;

import com.codepath.apps.MySimpleTweets.TwitterApplication;
import com.codepath.apps.MySimpleTweets.TwitterClient;
import com.codepath.apps.MySimpleTweets.models.Tweet;

import org.json.JSONArray;

/**
 * Created by g7190305 on 2015/8/15.
 */
public class HomeTimelineFragment extends TweetsListFragment {

    private TwitterClient client;

    private final int TWEET_ACTIVATE_ID=1;
    public static final String POSTED_STATUS="g7190305_project_4_POSTED_STATUS";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ((TimelineActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        // ((TimelineActivity)getActivity()).getSupportActionBar().setLogo(R.drawable.ic_launcher);
        // ((TimelineActivity)getActivity()).getSupportActionBar().setDisplayUseLogoEnabled(true);

        client = TwitterApplication.getRestClient();

        populateTimeline(getSince_id());
    }

    public void populateTimeline(long since_id) {
        // client.getHomeTimeline();

        client.getHomeTimeline(since_id, new com.loopj.android.http.JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, org.apache.http.Header[] headers, org.json.JSONArray jsonArray) {
                // super.onSuccess(statusCode, headers, jsonArray);
                Log.d("DEBUG", "Twitter Client get Success");
                addAll(Tweet.fromJSONArray(jsonArray));
                // swipeContainer.setRefreshing(false);
            }

            @Override
            public void onFailure(int statusCode, org.apache.http.Header[] headers, Throwable throwable, JSONArray Response) {
                //super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.d("DEBUG", Response.toString());
            }
        });
    }

    // @Override
    // public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // super.onCreateOptionsMenu(menu, inflater);
        // inflater.inflate(R.menu.menu_timeline, menu);
    //     return;
    // }

    // @Override
    // public boolean onOptionsItemSelected(MenuItem item) {
    //     int id = item.getItemId();


        //noinspection SimplifiableIfStatement
    //     if (id == R.id.mi_compose) {
    //         // Intent i = new Intent(TimelineActivity.this, TweetComposeActivity.class);
    //         Intent i = new Intent(getActivity(), TweetComposeActivity.class);
    //         i.putExtra(POSTED_STATUS, "");
    //         startActivityForResult(i, TWEET_ACTIVATE_ID);
    //         return true;
    //     }

    //     return super.onOptionsItemSelected(item);
    // }

    // @Override
    // public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // super.onActivityResult(requestCode, resultCode, data);

    //     if ( resultCode == getActivity().RESULT_OK && requestCode == TWEET_ACTIVATE_ID ) {
    //         Tweet tweet = (Tweet) data.getSerializableExtra(POSTED_STATUS);
    //         // aTweets.add(tweet);
    //         insert(tweet, 0);

            // Log.d("DEBUG", data.toString());
            // setupInfo = (SetupInfo) data.getSerializableExtra("setupInfo");
            // Log.i("DEBUG", setupInfo.toString());
    //     }
    // }
}
