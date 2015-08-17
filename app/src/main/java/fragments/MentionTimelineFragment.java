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
public class MentionTimelineFragment extends TweetsListFragment {
    private TwitterClient client;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ((TimelineActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        // ((TimelineActivity)getActivity()).getSupportActionBar().setLogo(R.drawable.ic_launcher);
        // ((TimelineActivity)getActivity()).getSupportActionBar().setDisplayUseLogoEnabled(true);

        client = TwitterApplication.getRestClient();

        populateTimeline(getSince_id());
    }

    private void populateTimeline(long since_id) {
        // client.getHomeTimeline();

        client.getMentionTimeline(since_id, new com.loopj.android.http.JsonHttpResponseHandler() {

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
}
