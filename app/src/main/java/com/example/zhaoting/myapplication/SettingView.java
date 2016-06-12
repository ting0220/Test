package com.example.zhaoting.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by zhaoting on 16/6/8.
 */
public class SettingView extends RelativeLayout {
    private TextView title;
    private TextView sig;
    private CheckBox check;
    private TextView line;

    private String titleS;
    private String sigS;
    private boolean checkB;
    private String hasCheckB;
    private boolean hasLine;


    public SettingView(Context context) {
        super(context);
        initViews(context);
    }

    public SettingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context);
        TypedArray attrArray = context.obtainStyledAttributes(attrs, R.styleable.SettingView);
        int count = attrArray.getIndexCount();
        for (int i = 0; i < count; i++) {
            int attrName = attrArray.getIndex(i);
            switch (attrName) {
                case R.styleable.SettingView_titleS: {
                    titleS = attrArray.getString(R.styleable.SettingView_titleS);
                }
                break;
                case R.styleable.SettingView_sigS: {
                    sigS = attrArray.getString(R.styleable.SettingView_sigS);
                }
                break;
                case R.styleable.SettingView_checkB: {
                    checkB = attrArray.getBoolean(R.styleable.SettingView_checkB, false);
                }
                break;
                case R.styleable.SettingView_hasCheckB: {
                    hasCheckB = attrArray.getString(R.styleable.SettingView_hasCheckB);
                }
                break;
                case R.styleable.SettingView_hasLine: {
                    hasLine = attrArray.getBoolean(R.styleable.SettingView_hasLine, false);
                }
                break;
            }
        }
        attrArray.recycle();
        initDatas();
    }

    private void initDatas() {
        if (titleS != null) {
            title.setText(titleS);
        } else {
            throw new NullPointerException("title text is null");
        }
        if (sigS != null) {
            sig.setText(sigS);
            sig.setVisibility(View.VISIBLE);
        } else {
            sig.setVisibility(View.GONE);
            LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.addRule(CENTER_VERTICAL);
            title.setLayoutParams(params);
        }
        if (hasCheckB != null) {
            if (hasCheckB.equals("Y") || hasCheckB.equals("y")) {
                check.setChecked(checkB);
                check.setVisibility(View.VISIBLE);
            } else if (hasCheckB.equals("N") || hasCheckB.equals("n")) {
                check.setVisibility(View.GONE);
            } else {
                throw new UnsupportedOperationException("data for hasCheckB is wrong");
            }
        } else {
            throw new NullPointerException("hasCheckB is null");
        }
        if (hasLine) {
            line.setVisibility(View.VISIBLE);
        } else {
            line.setVisibility(View.GONE);
        }

    }

    public SettingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context);
    }

    private void initViews(Context context) {
        View.inflate(context, R.layout.item_setting, this);
        title = (TextView) findViewById(R.id.id_title);
        sig = (TextView) findViewById(R.id.id_sig);
        check = (CheckBox) findViewById(R.id.id_checkbox);
        line = (TextView) findViewById(R.id.id_line);
    }

    public void setTitle(String s) {
        titleS = s;
        title.setText(titleS);
        invalidate();
    }

    public void setSig(String s) {
        sigS = s;
        sig.setText(sigS);
        invalidate();
    }

    public void setCheck(boolean s) {
        check.setChecked(s);
        invalidate();
    }


}
