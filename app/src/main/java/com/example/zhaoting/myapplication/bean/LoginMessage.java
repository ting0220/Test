package com.example.zhaoting.myapplication.bean;

/**
 * Created by zhaoting on 16/4/21.
 */
public class LoginMessage {
    /**
     * "access_token":"0186114ea30bc5b5178301f206eb0bbc",
     * "user_unique_key":"9986114ea30bc5e5178301f206eb0bab",
     * "expiration": 23435123232
     */

    private String accessToken;
    private String userUniqueKey;
    private long expiration;

    public LoginMessage() {
    }

    public LoginMessage(String accessToken, String userUniqueKey, long expiration) {
        this.accessToken = accessToken;
        this.userUniqueKey = userUniqueKey;
        this.expiration = expiration;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUserUniqueKey() {
        return userUniqueKey;
    }

    public void setUserUniqueKey(String userUniqueKey) {
        this.userUniqueKey = userUniqueKey;
    }

    public long getExpiration() {
        return expiration;
    }

    public void setExpiration(long expiration) {
        this.expiration = expiration;
    }


}
