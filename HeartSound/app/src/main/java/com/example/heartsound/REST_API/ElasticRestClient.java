package com.example.heartsound.REST_API;
import android.content.Context;
import android.util.Log;

import com.example.heartsound.Result_analysis.ResultDepartment.ResultDepartment;
import com.example.heartsound.Result_analysis.ResultDepartment.ResultDepartment_1;
import com.example.heartsound.Result_analysis.ResultDepartment.ResultDepartment_2;
import com.example.heartsound.Result_analysis.ResultDepartment.ResultDepartment_3;
import com.example.heartsound.Result_analysis.ResultDepartment.ResultDepartment_4;
import com.example.heartsound.Result_analysis.ResultDepartment.ResultDepartment_5;
import com.example.heartsound.Result_analysis.ResultDepartment.ResultDepartment_6;
import com.example.heartsound.Result_analysis.ResultDepartment.ResultDepartment_7;
import com.example.heartsound.Result_analysis.ResultDepartment.ResultDepartment_8;
import com.example.heartsound.Result_analysis.ResultDepartment.ResultDepartment_9;
import com.github.mikephil.charting.data.Entry;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;

public class ElasticRestClient {

    private static final String BASE_URL = "http://140.137.41.81:9200/"; //http://localhost:9200/
    private static final String CLASS_NAME = ElasticRestClient.class.getSimpleName();


    private static AsyncHttpClient client = new AsyncHttpClient();

    public static ArrayList<Entry> get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        ArrayList<Entry> eArray = new ArrayList<>();
        client.get(getAbsoluteUrl(url), params, new JsonHttpResponseHandler() { // instead of 'get' use twitter/tweet/1

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                Log.i(CLASS_NAME, "onSuccess: " + response.toString());
                try {
                    JSONArray responses = response.getJSONObject("hits").getJSONArray("hits");

                    for(int i = 0 ;  i< responses.length() ; i++){

                        String s = responses.getJSONObject(i).getJSONObject("_source").get("pulse").toString();
                        Entry e = new Entry(i,Float.valueOf(s));
                        eArray.add(e);
                    }
                    if(url.contains("電機"))
                        ResultDepartment.enties = eArray;
                    if(url.contains("土木"))
                        ResultDepartment_1.enties = eArray;
                    if(url.contains("化工"))
                        ResultDepartment_2.enties = eArray;
                    if(url.contains("商業"))
                        ResultDepartment_3.enties = eArray;
                    if(url.contains("設計"))
                        ResultDepartment_4.enties = eArray;
                    if(url.contains("農業"))
                        ResultDepartment_5.enties = eArray;
                    if(url.contains("家政"))
                        ResultDepartment_6.enties = eArray;
                    if(url.contains("外語"))
                        ResultDepartment_7.enties = eArray;
                    if(url.contains("藝術"))
                        ResultDepartment_8.enties = eArray;
                    if(url.contains("醫學"))
                        ResultDepartment_9.enties = eArray;
                    System.out.println(eArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

//                System.out.println(response.toString());

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.e(CLASS_NAME, "onFailure");
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
            }

            @Override
            public void onRetry(int retryNo) {
                Log.i(CLASS_NAME, "onRetry " + retryNo);
                // called when request is retried
            }
        });
        return eArray;

    }

    public static void post(Context context, String url, HttpEntity entity, String contentType, ResponseHandlerInterface responseHandler) {
        client.post(context, getAbsoluteUrl(url), entity, contentType, responseHandler);
    }



    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

    public void getHttpRequest() {
        try {


            ElasticRestClient.get("get", null, new JsonHttpResponseHandler() { // instead of 'get' use twitter/tweet/1
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    // If the response is JSONObject instead of expected JSONArray
                    Log.i(CLASS_NAME, "onSuccess: " + response.toString());
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                    Log.i(CLASS_NAME, "onSuccess: " + response.toString());
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    super.onFailure(statusCode, headers, responseString, throwable);
                    Log.e(CLASS_NAME, "onFailure");
                    // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                }

                @Override
                public void onRetry(int retryNo) {
                    Log.i(CLASS_NAME, "onRetry " + retryNo);
                    // called when request is retried
                }
            });
        }
        catch (Exception e){
            Log.e(CLASS_NAME, e.getLocalizedMessage());
        }
    }
}