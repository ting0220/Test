package com.example.zhaoting.myapplication.events;

/**
 * Created by zhaoting on 16/5/10.
 */
public class ChangeToolbarTextEvent {
    private String msg;

    public ChangeToolbarTextEvent(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
