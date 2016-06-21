package com.example.zhaoting.myapplication.view.main;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.zhaoting.myapplication.R;
import com.example.zhaoting.myapplication.adapter.DrawerItemAdapter;
import com.example.zhaoting.myapplication.app.BaseActivity;
import com.example.zhaoting.myapplication.bean.DrawerBean;
import com.example.zhaoting.myapplication.events.ChangeToolbarTextEvent;
import com.example.zhaoting.myapplication.presenter.MainPresenter;
import com.example.zhaoting.myapplication.utils.SharedPManager;
import com.example.zhaoting.myapplication.view.home.HomeFragment;
import com.example.zhaoting.myapplication.view.otherTheme.OtherThemeFragment;
import com.example.zhaoting.myapplication.view.setting.SettingActivity;
import com.example.zhaoting.utils.IOUtils;
import com.example.zhaoting.utils.Utils;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class MainActivity extends BaseActivity implements MainView, Toolbar.OnMenuItemClickListener {
    private DrawerLayout mDrawerLayout;
    private RecyclerView mRecyclerView;
    private Toolbar mToolbar;
    private HomeFragment mHomeFragment;
    private OtherThemeFragment mOtherThemeFragment;

    private DrawerItemAdapter mAdapter;

    private MainPresenter mMainPresenter = new MainPresenter(this);

    public int isChangeMenu = 0;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private String toolTitle = "首页";
    private boolean isFirst = true;//用于没网时不加载更多



    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0: {
                    mHandler.sendEmptyMessageDelayed(0, 3000);
                }
                break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);

        initViews();

        setUpDrawer();

    }

    @Override
    public int getFragmentContainerId() {
        return R.id.id_main_view;
    }

    private void initViews() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.id_main_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.id_main_drawer);
        mToolbar = (Toolbar) findViewById(R.id.id_main_toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle(getResources().getString(R.string.drawer_home));
        mToolbar.setOnMenuItemClickListener(this);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open_string, R.string.close_string);
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        mHomeFragment = newInstanceFragment(HomeFragment.class);
        changeFragment(mHomeFragment);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu_home, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        switch (isChangeMenu) {
            case 0: {
                menu.clear();
                getMenuInflater().inflate(R.menu.toolbar_menu_home, menu);
                actionBarDrawerToggle.syncState();
                mToolbar.setTitle(getToolTitle());
                mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
            }
            break;
            case 1: {
                menu.clear();
                getMenuInflater().inflate(R.menu.toolbar_menu_other_theme, menu);
                actionBarDrawerToggle.syncState();
                mToolbar.setTitle(getToolTitle());

            }
            break;
        }
        return true;
    }


    private void setUpDrawer() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new DrawerItemAdapter();
        mRecyclerView.setAdapter(mAdapter);
        setHeaderView(mRecyclerView);
        setFirstView(mRecyclerView);
        mMainPresenter.getDrawerList();
    }


    public void setHeaderView(RecyclerView view) {
        View header = LayoutInflater.from(this).inflate(R.layout.item_drawer_header, view, false);
        mAdapter.setHeaderView(header);
        mAdapter.setHeaderLineOneListener(new DrawerItemAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position, DrawerBean.OthersBean data) {
                Utils.getInstance().ToastShort("click_line_one");
            }
        });
        mAdapter.setHeaderCollectListener(new DrawerItemAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position, DrawerBean.OthersBean data) {
                Utils.getInstance().ToastShort("click_collect");

            }
        });
        mAdapter.setHeaderDownListener(new DrawerItemAdapter.onItemClickListener() {
                                           @Override
                                           public void onItemClick(int position, DrawerBean.OthersBean data) {
                                               Utils.getInstance().ToastShort("click_down");

                                           }
                                       }

        );
    }

    public void setFirstView(RecyclerView view) {
        View first = LayoutInflater.from(this).inflate(R.layout.item_drawer_first, view, false);
        mAdapter.setFirstView(first);
        mAdapter.setFirstListener(new DrawerItemAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position, DrawerBean.OthersBean data) {
                setItemBackgournd(position);
                setToolTitle(getResources().getString(R.string.drawer_home));
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                changeFragment(mHomeFragment);
                isChangeMenu = 0;
                invalidateOptionsMenu();
            }
        });
    }

    @Override
    public void setDrawer(final List<DrawerBean.OthersBean> mList) {
        mAdapter.setList(mList);
        mAdapter.setonItemClickListener(new DrawerItemAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position, DrawerBean.OthersBean data) {
                setItemBackgournd(position + 2);
                setToolTitle(mList.get(position).getName());
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                mOtherThemeFragment = newInstanceFragment(OtherThemeFragment.class);
                mOtherThemeFragment.setThemeId(mList.get(position).getId());
                changeFragment(mOtherThemeFragment, mList.get(position).getName());
                isChangeMenu = 1;
                invalidateOptionsMenu();
            }
        });
    }

    @Override
    public void onError() {

    }

    @Override
    public void onNoConnected() {
        if (IOUtils.getInstance().isFileIsExist("drawer")) {
            if (isFirst) {
                isFirst = false;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String s = IOUtils.getInstance().readSDFile("drawer");
                        Gson gson = new Gson();
                        DrawerBean drawerBean = gson.fromJson(s, DrawerBean.class);
                        final List<DrawerBean.OthersBean> list = drawerBean.getOthers();
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                setDrawer(list);
                            }
                        });
                    }
                }).start();
            }
        }

    }

    public void setItemBackgournd(int position) {
        LinearLayoutManager mManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
        int firstItemPosition = mManager.findFirstVisibleItemPosition();
        int lastItemPosition = mManager.findLastVisibleItemPosition();
        for (int i = 1; i < lastItemPosition; i++) {
            View v = mRecyclerView.getChildAt(i);
            DrawerItemAdapter.DrawerItemHolder holder = (DrawerItemAdapter.DrawerItemHolder) mRecyclerView.getChildViewHolder(v);
            holder.itemView.setBackgroundResource(R.color.white);
        }
        if (firstItemPosition == 0) {
            View v = mRecyclerView.getChildAt(position);
            DrawerItemAdapter.DrawerItemHolder holder = (DrawerItemAdapter.DrawerItemHolder) mRecyclerView.getChildViewHolder(v);
            holder.itemView.setBackgroundResource(R.color.light_gray);
        } else if (firstItemPosition == 1) {
            View v = mRecyclerView.getChildAt(position - 1);
            DrawerItemAdapter.DrawerItemHolder holder = (DrawerItemAdapter.DrawerItemHolder) mRecyclerView.getChildViewHolder(v);
            holder.itemView.setBackgroundResource(R.color.light_gray);
        } else {
            View v = mRecyclerView.getChildAt(position - 2);
            DrawerItemAdapter.DrawerItemHolder holder = (DrawerItemAdapter.DrawerItemHolder) mRecyclerView.getChildViewHolder(v);
            holder.itemView.setBackgroundResource(R.color.light_gray);
        }

    }

    @Subscribe
    public void onEvent(ChangeToolbarTextEvent event) {
        mToolbar.setTitle(event.getMsg());
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.id_info: {
                Utils.getInstance().ToastShort("click menu_message");
            }
            break;
            case R.id.id_mode: {
                SharedPManager.getInstance().setMode(this, R.layout.window_img, R.id.id_wm_img);
            }
            break;
            case R.id.id_setting: {
                jumpActivity(SettingActivity.class, false);
            }
            break;
            case R.id.id_focus: {
                Utils.getInstance().ToastShort("click menu_focus");
            }
            break;
            case R.id.id_share: {
                Utils.getInstance().ToastShort("click menu_share");
            }
            break;
            case R.id.id_collect: {
                Utils.getInstance().ToastShort("click menu_collect");

            }
            break;
            case R.id.id_comment: {
                Utils.getInstance().ToastShort("click menu_comment");

            }
            break;
            case R.id.id_like: {
                Utils.getInstance().ToastShort("click menu_like");

            }
            break;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        mHandler.sendEmptyMessageDelayed(0, 3000);
    }

    public void setToolTitle(String s) {
        toolTitle = s;
    }

    public String getToolTitle() {
        return toolTitle;
    }
}
