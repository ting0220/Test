package com.example.zhaoting.myapplication.view.main;

import android.os.Bundle;
import android.os.Handler;
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
import com.example.zhaoting.myapplication.view.home.HomeFragment;
import com.example.zhaoting.myapplication.view.otherTheme.OtherThemeFragment;
import com.example.zhaoting.myapplication.view.setting.SettingActivity;
import com.example.zhaoting.utils.Utils;

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


    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0: {
//                    Utils.getInstance().ToastShort(String.valueOf(Utils.getInstance().isBackground()));
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
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white_day));
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open_string, R.string.close_string);
        actionBarDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
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
//            case 2: {
//                menu.clear();
//                getMenuInflater().inflate(R.menu.toolbar_menu_article_content, menu);
//                mToolbar.setNavigationIcon(R.drawable.back);
//                mToolbar.setTitle("");
//                mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        getSupportFragmentManager().popBackStack();
//                        isChangeMenu = 1;
//                        invalidateOptionsMenu();
//                    }
//                });
//            }
//            break;
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
        });
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

    public void setItemBackgournd(int position) {
        LinearLayoutManager mManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
        int firstItemPosition = mManager.findFirstVisibleItemPosition();
        int lastItemPosition = mManager.findLastVisibleItemPosition();
        for (int i = 1; i < lastItemPosition; i++) {
            View v = mRecyclerView.getChildAt(i);
            DrawerItemAdapter.DrawerItemHolder holder = (DrawerItemAdapter.DrawerItemHolder) mRecyclerView.getChildViewHolder(v);
            holder.itemView.setBackgroundResource(R.color.white_day);
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
                Utils.getInstance().ToastShort("click menu_mode");
            }
            break;
            case R.id.id_setting: {
                jumpActivity(SettingActivity.class,false);
                Utils.getInstance().ToastShort("click menu_setting");

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
