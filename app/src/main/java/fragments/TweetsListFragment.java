package fragments;

// import android.app.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.codepath.apps.MySimpleTweets.R;
import com.codepath.apps.MySimpleTweets.TweetsArrayAdapter;
import com.codepath.apps.MySimpleTweets.models.Tweet;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by g7190305 on 2015/8/15.
 */
public class TweetsListFragment extends Fragment {
    private ListView lvTweets;
    private ArrayList<Tweet> tweets;
    private TweetsArrayAdapter aTweets;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_tweets_list, parent, false);

        lvTweets = (ListView) v.findViewById(R.id.lvTweets);
        lvTweets.setAdapter(aTweets);
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tweets = new ArrayList<>();
        aTweets = new TweetsArrayAdapter(getActivity(), tweets);
    }

    public void addAll(List<Tweet> tweets) {
        aTweets.addAll(tweets);
    }

    public long getSince_id() {
        return aTweets.getSince_id();
    }

    public void insert(Tweet tweet, int index) {
        aTweets.insert(tweet, index);
    }
}
