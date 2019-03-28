package com.example.rahul.alert_haiti.Api;

import android.util.Log;

import com.example.rahul.alert_haiti.Api.CustomCallBack.AsyncLoginResponse;

import org.json.JSONArray;
import org.json.JSONObject;


public class LoginApi implements AsyncResponse {
    String TAG="LOGIN";
    AsyncLoginResponse response;
    public LoginApi(AsyncLoginResponse response,String username,String password)
    {
        this.response=response;
        new HttpRequest()
                .addParameter("method","login")
                .addParameter("un",username)
                .addParameter("pwd",password)
                .get(this);
    }

    @Override
    public void postResponse(String response) {
        try{
            JSONArray arr=new JSONArray(response);
            JSONObject obj=(JSONObject) arr.get(0);
            if(obj.getString("code").equals("202"))
            {
             this.response.success(obj.getString("id"));
            }
            else{
                this.response.fail(obj.getString("msg"));
            }
        }
        catch (Exception e){}
        Log.e(TAG, "postResponse: "+response );
    }
}
