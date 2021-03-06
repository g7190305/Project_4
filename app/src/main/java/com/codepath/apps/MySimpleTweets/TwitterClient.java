package com.codepath.apps.MySimpleTweets;

import android.content.Context;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;
import org.scribe.exceptions.OAuthException;

import java.io.UnsupportedEncodingException;

/*
 * 
 * This is the object responsible for communicating with a REST API. 
 * Specify the constants below to change the API being communicated with.
 * See a full list of supported API classes: 
 *   https://github.com/fernandezpablo85/scribe-java/tree/master/src/main/java/org/scribe/builder/api
 * Key and Secret are provided by the developer site for the given API i.e dev.twitter.com
 * Add methods for each relevant endpoint in the API.
 * 
 * NOTE: You may want to rename this object based on the service i.e TwitterClient or FlickrClient
 * 
 */
public class TwitterClient extends OAuthBaseClient {
	public static final Class<? extends Api> REST_API_CLASS = TwitterApi.class; // Change this
	public static final String REST_URL = "https://api.twitter.com/1.1"; // Change this, base API URL
	public static final String REST_CONSUMER_KEY = "pZc90FxAO8tX2nURdUGtBIM9T";       // Change this
	public static final String REST_CONSUMER_SECRET = "veBTHiXca94jnDyxsN15IEnoaNIuCzUonUPcdhp8EqvcsnJ5To"; // Change this
	public static final String REST_CALLBACK_URL = "oauth://g7190305_CPTwitterApp"; // Change this (here and in manifest)

	private static final int COUNT = 10;

	public TwitterClient(Context context) {
		super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
	}

	// CHANGE THIS
	// DEFINE METHODS for different API endpoints here

	public void getHomeTimeline(long id, AsyncHttpResponseHandler handler) {
		String apiUrl = getApiUrl("statuses/home_timeline.json");

		RequestParams params = new RequestParams();
		params.put("count",COUNT);

		if (id > 1) {
			params.put("max_id", id);
		} else {
			params.put("since_id", 1);
		}

		try {
			getClient().get(apiUrl, params, handler);
		} catch (OAuthException e) {
			e.printStackTrace();
		}
	}

	public void getUserTimeline( String screenName, AsyncHttpResponseHandler handler ) {
		String apiUrl = getApiUrl("statuses/user_timeline.json");

		RequestParams params = new RequestParams();
		params.put("count",COUNT);
		params.put("screen_name", screenName);

		try {
			getClient().get(apiUrl, params, handler);
		} catch (OAuthException e) {
			e.printStackTrace();
		}
	}

	public void getUsersLookup( String screen_name, AsyncHttpResponseHandler handler) {
		String apiUrl = getApiUrl("users/lookup.json");

		RequestParams params = new RequestParams();
		params.put("screen_name", screen_name);

		try {
			getClient().get(apiUrl, params, handler);
		} catch (OAuthException e) {
			e.printStackTrace();
		}
	}

	public void getVerifyCredentials( AsyncHttpResponseHandler handler) {
		String apiUrl = getApiUrl("account/verify_credentials.json");

		try {
			getClient().get(apiUrl, null, handler);
		} catch (OAuthException e) {
			e.printStackTrace();
		}
	}

	public void updateStatuses( String status, AsyncHttpResponseHandler handler) throws UnsupportedEncodingException {
		String apiUrl = getApiUrl("statuses/update.json");

		RequestParams params = new RequestParams();
		// params.put("status", URLEncoder.encode(status, "UTF-8") ); //  the asyncHttpClient will do encoding for you
		params.put("status", status );

		try {
			getClient().post(apiUrl, params, handler);
		} catch (OAuthException e) {
			e.printStackTrace();
		}
	}

	public void getMentionTimeline(long id, AsyncHttpResponseHandler handler) {
		String apiUrl = getApiUrl("statuses/mentions_timeline.json");

		RequestParams params = new RequestParams();
		params.put("count",COUNT);

		if (id > 1) {
			params.put("max_id", id);
		} else {
			params.put("since_id", 1);
		}

		try {
			getClient().get(apiUrl, params, handler);
		} catch (OAuthException e) {
			e.printStackTrace();
		}
	}

	/* 1. Define the endpoint URL with getApiUrl and pass a relative path to the endpoint
	 * 	  i.e getApiUrl("statuses/home_timeline.json");
	 * 2. Define the parameters to pass to the request (query or body)
	 *    i.e RequestParams params = new RequestParams("foo", "bar");
	 * 3. Define the request method and make a call to the client
	 *    i.e client.get(apiUrl, params, handler);
	 *    i.e client.post(apiUrl, params, handler);
	 */
}