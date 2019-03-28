package com.example.rahul.alert_haiti.Api;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

public class HttpRequest {


    String url="https://alerthaiti.com/ews/";
    String data;
    Map<String,Object> params;
    Map<String,String> header;
    Activity activity;
    AsyncResponse responseActivity;
    public boolean isPost=false;



    public HttpRequest()
        {
             params= new LinkedHashMap<>();
             header=new LinkedHashMap<>();
        }

        public HttpRequest addParameter(String key,String value)
        {
            params.put(key,value);
            return this;
        }

        public HttpRequest addParameter(String key, Object value)
        {
            params.put(key,value);
            return this;
        }

        public HttpRequest addUrl(String url)
        {
            this.url=this.url+url;
            return this;
        }

        public HttpRequest addHeader(String name,String value)
        {
            header.put(name,value);
            return this;
        }



        public void post(AsyncResponse activity)
        {
            isPost=true;
            responseActivity=activity;
        }

        public void get(AsyncResponse activity)
        {
            isPost=false;
            responseActivity=activity;
            new Downloader().execute();


        }

        private String makeGet(){

        StringBuilder postData = new StringBuilder();
        try {
            Log.e("Downloadinig",this.url);
            URL url = new URL(this.url);

            for (Map.Entry<String, Object> param : params.entrySet()) {
                Log.e("paramter",param.getKey()+":"+param.getValue());
                if (postData.length() != 0) postData.append('&');

                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            for (Map.Entry<String, String> param : header.entrySet()) {
                conn.setRequestProperty(param.getKey(),param.getValue());
            }
                conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setDoOutput(true);

            conn.getOutputStream().write(postDataBytes);

            Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            StringBuilder sb = new StringBuilder();
            for (int c; (c = in.read()) >= 0; )
                sb.append((char) c);
            String response = sb.toString();
            Log.e("HTTP Request", response);
            return response;

        }
        catch(Exception e)
        {
            Log.e("HttpRequest","exception occured"+e.getMessage());
            JSONObject obj=new JSONObject();
            try {
                obj.put("result","fail");
                obj.put("description","Unable to connect to server. Please check your Internet");
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
            return obj.toString();


        }
        }

    private String makePost(){

        StringBuilder postData = new StringBuilder();
        try {
            Log.e("Downloadinig",this.url);
            URL url = new URL(this.url);

            for (Map.Entry<String, Object> param : params.entrySet()) {
                Log.e("paramter",param.getKey()+":"+param.getValue());
                if (postData.length() != 0) postData.append('&');

                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            for (Map.Entry<String, String> param : header.entrySet()) {
                conn.setRequestProperty(param.getKey(),param.getValue());
            }
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setDoOutput(true);

            conn.getOutputStream().write(postDataBytes);

            Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            StringBuilder sb = new StringBuilder();
            for (int c; (c = in.read()) >= 0; )
                sb.append((char) c);
            String response = sb.toString();
            Log.e("HTTP Request", response);
            return response;

        }
        catch(Exception e)
        {
            Log.e("HttpRequest","exception occured"+e.getMessage());
            JSONObject obj=new JSONObject();
            try {
                obj.put("result","fail");
                obj.put("description","Unable to connect to server. Please check your Internet");
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
            return obj.toString();


        }
    }
        class Downloader extends AsyncTask<Void,String,String>
        {
            @Override
            protected String doInBackground(Void... voids) {
                String out;
                if(isPost) {
                     out = makePost();
                }
                else
                {
                     out=makeGet();
                }
                return out;
            }

            @Override
            protected void onPostExecute(String response) {
                responseActivity.postResponse(response);
                }
        }


        public void postImage()
        {}



}



