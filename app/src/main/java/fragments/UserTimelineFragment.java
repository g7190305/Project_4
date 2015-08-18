package fragments;

import android.os.Bundle;
import android.util.Log;

import com.codepath.apps.MySimpleTweets.TwitterApplication;
import com.codepath.apps.MySimpleTweets.TwitterClient;
import com.codepath.apps.MySimpleTweets.models.Tweet;

import org.json.JSONObject;

/**
 * Created by g7190305 on 2015/8/16.
 */
public class UserTimelineFragment extends TweetsListFragment {
    private TwitterClient client;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        client = TwitterApplication.getRestClient();

        populateTimeline(getSince_id());
    }

    public static UserTimelineFragment newInstance( String screenName ) {
        UserTimelineFragment userFragment = new UserTimelineFragment();
        Bundle args = new Bundle();
        args.putString("screen_name", screenName);
        userFragment.setArguments(args);
        return  userFragment;
    }

    public void populateTimeline(long since_id) {
        // client.getHomeTimeline();
        String screenName = getArguments().getString("screen_name", null);
        // String screenName = "g7190305";

        client.getUserTimeline(screenName, new com.loopj.android.http.JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, org.apache.http.Header[] headers, org.json.JSONArray jsonArray) {
                // super.onSuccess(statusCode, headers, jsonArray);
                Log.d("DEBUG", "Twitter Client get Success");
                addAll(Tweet.fromJSONArray(jsonArray));
                // swipeContainer.setRefreshing(false);
            }

            @Override
            public void onFailure(int statusCode, org.apache.http.Header[] headers, Throwable throwable, JSONObject Response) {
                //super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.d("DEBUG", Response.toString());
            }
        });
    }
}
