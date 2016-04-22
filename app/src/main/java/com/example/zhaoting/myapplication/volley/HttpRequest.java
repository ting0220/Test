package com.example.zhaoting.myapplication.volley;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.zhaoting.myapplication.utils.SharedPManager;
import com.example.zhaoting.utils.Utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhaoting on 16/4/21.
 */
public class HttpRequest extends StringRequest {
    public HttpRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = super.getHeaders();
        if (headers == null || headers.equals(Collections.<String, String>emptyMap())) {
            headers = new HashMap<>();
        }
        headers.put("Accept", "Application/json");
        headers.put("app_key", Constants.App_KEY);
        headers.put("app_secret", Constants.APP_SECRET);
        headers.put("device", "android");
        headers.put("app_version", Utils.getInstance().getAppVersionName());
        headers.put("identifier", SharedPManager.getInstance().getDeviceId());
        headers.put("acccess_token", SharedPManager.getInstance().getAccessToken());
        headers.put("user_unique_key", SharedPManager.getInstance().getUserUniqueKey());
        return super.getHeaders();
    }
}
