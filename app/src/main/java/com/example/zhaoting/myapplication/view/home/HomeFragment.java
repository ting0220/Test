package com.example.zhaoting.myapplication.view.home;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.example.zhaoting.myapplication.R;
import com.example.zhaoting.myapplication.adapter.HomeListAdapter;
import com.example.zhaoting.myapplication.adapter.HomeTopViewPagerAdapter;
import com.example.zhaoting.myapplication.app.BaseFragment;
import com.example.zhaoting.myapplication.bean.HomeBean;
import com.example.zhaoting.myapplication.bean.StoriesBean;
import com.example.zhaoting.myapplication.bean.TopStoriesBean;
import com.example.zhaoting.myapplication.events.ChangeToolbarTextEvent;
import com.example.zhaoting.myapplication.presenter.HomePresenter;
import com.example.zhaoting.myapplication.view.HomeEndlessScrollListener;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhaoting on 16/5/3.
 */
public class HomeFragment extends BaseFragment implements HomeView, SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ViewPager mViewPager;
    private RecyclerView mRecyclerView;
    private RecyclerViewHeader mRecyclerViewHeader;
    private LinearLayout llPointLinear;
    private View viewOrangePoint;
    private int mPointWidth;

    private boolean isStop = false;
    private int currentItem = 0;
    private static final int MSG_UPDATE_IMAGE = 0;// 更新图片
    private static final int MSG_STOP_UPDATE_IMAGE = 1;//停止更新图片
    private static final int MSG_RECOVER_UPDATE_IMAGE = 2;//恢复更新图片
    private static final int MSG_PAGE_CHANGED = 3;//记录手动滑动时的页数变化
    private static final int MSG_DELAY = 5000;//记录换页的时间间隔
    private static int SIZE = 0;
    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mHandler.hasMessages(MSG_UPDATE_IMAGE)) {
                mHandler.removeMessages(MSG_UPDATE_IMAGE);
            }
            switch (msg.what) {
                case MSG_UPDATE_IMAGE: {
                    int n = (currentItem + 1) % SIZE;
                    mViewPager.setCurrentItem(n);
                    if (!isStop) {
                        mHandler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                    }
                }
                break;
                case MSG_STOP_UPDATE_IMAGE: {

                }
                break;
                case MSG_RECOVER_UPDATE_IMAGE: {
                    if (!isStop) {
                        mHandler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                    }
                }
                break;
                case MSG_PAGE_CHANGED: {
                    currentItem = msg.arg1;
                }
                break;
            }
        }
    };


    private HomeListAdapter mAdapter;

    private HomePresenter mHomePresenter = new HomePresenter(this);

    private boolean isRefresh = false;
    private List<StoriesBean> mList = new ArrayList<>();


    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_home2;
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void initViews() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.id_swipe_refresh_layout);
        mRecyclerViewHeader = (RecyclerViewHeader) mRootView.findViewById(R.id.id_view_pager_layout);
        mViewPager = (ViewPager) mRootView.findViewById(R.id.id_view_pager);
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.id_recycler_view);
        llPointLinear = (LinearLayout) mRootView.findViewById(R.id.id_point_group);
        viewOrangePoint = mRootView.findViewById(R.id.id_orange_point);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.BLUE);
        mRecyclerView.addOnScrollListener(new HomeEndlessScrollListener(mRecyclerView) {
            @Override
            public void onLoadMore(int currentPage) {
                Date date = new Date(new Date().getTime() - 1000 * 60 * 60 * 24 *(currentPage-1));
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                String num = sdf.format(date);
                String url = "http://news.at.zhihu.com/api/4/news/before/" + num;
                mHomePresenter.getHomeList(url);
            }


        });
        mRecyclerViewHeader.attachTo(mRecyclerView);
        mAdapter = new HomeListAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initDatas() {
        mHomePresenter.getHomeList("http://news-at.zhihu.com/api/4/news/latest");

    }

    public void setTopView(List<TopStoriesBean> list) {
        final List<View> mList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            View v = LayoutInflater.from(getActivity()).inflate(R.layout.item_home_top, null);
            ImageView img = (ImageView) v.findViewById(R.id.id_home_top_img);
            TextView text = (TextView) v.findViewById(R.id.id_home_top_text);
            ImageView imgMeng = (ImageView) v.findViewById(R.id.id_home_top_img_meng);
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) img.getLayoutParams();
            imgMeng.setLayoutParams(params);
            Picasso.with(getActivity()).load(list.get(i).getImage()).into(img);
            text.setText(list.get(i).getTitle());
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            mList.add(v);
        }

        for (int i = 0; i < list.size(); i++) {
            View point = new View(getActivity());
            point.setBackgroundResource(R.color.gray_day);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10, 10);
            if (i > 0) {
                params.leftMargin = 20;
            }
            point.setLayoutParams(params);
            llPointLinear.addView(point);
        }

        //获取圆点间的间距
        llPointLinear.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                llPointLinear.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                mPointWidth = llPointLinear.getChildAt(1).getLeft() - llPointLinear.getChildAt(0).getLeft();

            }
        });


        HomeTopViewPagerAdapter adapter = new HomeTopViewPagerAdapter(mList);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(currentItem);
        mHandler.sendEmptyMessage(MSG_RECOVER_UPDATE_IMAGE);


        SIZE = mList.size();
        mHandler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int len = (int) ((mPointWidth * positionOffset) + position * mPointWidth);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) viewOrangePoint.getLayoutParams();
                params.leftMargin = len;
                viewOrangePoint.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {
                Message msg = new Message();
                msg.what = MSG_PAGE_CHANGED;
                msg.arg1 = position;
                msg.arg2 = 0;
                mHandler.sendMessage(msg);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_IDLE: {
                        mHandler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                    }
                    break;
                    case ViewPager.SCROLL_STATE_DRAGGING: {
                        mHandler.sendEmptyMessage(MSG_STOP_UPDATE_IMAGE);
                    }
                    break;
                }

            }
        });


    }

    public void setListView(List<StoriesBean> list) {
        mAdapter.setList(list);
    }

    @Override
    public void onRefreshList() {

    }

    @Override
    public void setData(HomeBean data) {
        isRefresh = false;
        mSwipeRefreshLayout.setRefreshing(isRefresh);
        mList = new ArrayList<>();
        if (data.getTop_stories() != null) {
            setTopView(data.getTop_stories());
        }
        for (int i = 0; i < data.getStories().size(); i++) {
            StoriesBean bean = data.getStories().get(i);
            bean.setDate(data.getDate());
            mList.add(bean);
        }
        setListView(mList);
    }

    @Override
    public void onError() {

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        isStop = true;
    }

    @Override
    public void onRefresh() {
        if (!isRefresh) {
            isRefresh = true;
            mHomePresenter.getHomeList("http://news-at.zhihu.com/api/4/news/latest");
            llPointLinear.removeAllViews();
            EventBus.getDefault().post(new ChangeToolbarTextEvent(getResources().getString(R.string.drawer_home)));
        }
    }



}
