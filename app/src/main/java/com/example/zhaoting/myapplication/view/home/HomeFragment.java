package com.example.zhaoting.myapplication.view.home;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.example.zhaoting.myapplication.R;
import com.example.zhaoting.myapplication.adapter.HomeListAdapter;
import com.example.zhaoting.myapplication.app.BaseFragment;
import com.example.zhaoting.myapplication.bean.HomeBean;
import com.example.zhaoting.myapplication.bean.StoriesBean;
import com.example.zhaoting.myapplication.bean.TopStoriesBean;
import com.example.zhaoting.myapplication.events.ChangeToolbarTextEvent;
import com.example.zhaoting.myapplication.presenter.HomePresenter;
import com.example.zhaoting.myapplication.view.article.ArticleCActivity;
import com.example.zhaoting.myapplication.view.main.MainActivity;
import com.example.zhaoting.myapplication.widget.AutoScrollViewPagerLayout;
import com.example.zhaoting.myapplication.widget.HomeEndlessScrollListener;
import com.example.zhaoting.myapplication.widget.OnRecyclerItemClickListener;
import com.example.zhaoting.utils.Utils;
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
    private AutoScrollViewPagerLayout mViewPager;
    private RecyclerView mRecyclerView;
    private RecyclerViewHeader mRecyclerViewHeader;

    private HomeListAdapter mAdapter;

    private HomePresenter mHomePresenter = new HomePresenter(this);

    private boolean isRefresh = false;
    private List<StoriesBean> mList = new ArrayList<>();
    private List<Integer> mListForId = new ArrayList<>();


    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_home2;
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void initViews() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.id_swipe_refresh_layout);
        mRecyclerViewHeader = (RecyclerViewHeader) mRootView.findViewById(R.id.id_view_pager_layout);
        mViewPager = (AutoScrollViewPagerLayout) mRootView.findViewById(R.id.id_auto_scroll_viewpager_layout);
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.id_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        mSwipeRefreshLayout.setOnRefreshListener(this);
        if (Build.VERSION.SDK_INT >= 23) {
            mSwipeRefreshLayout.setColorSchemeColors(getActivity().getResources().getColor(R.color.blue, getActivity().getTheme()));
            mSwipeRefreshLayout.setProgressBackgroundColorSchemeColor(getActivity().getResources().getColor(R.color.white, getActivity().getTheme()));
        } else {
            mSwipeRefreshLayout.setColorSchemeColors(getActivity().getResources().getColor(R.color.blue));
            mSwipeRefreshLayout.setProgressBackgroundColorSchemeColor(getActivity().getResources().getColor(R.color.white));

        }
        mRecyclerView.addOnScrollListener(new HomeEndlessScrollListener(mRecyclerView) {
            @Override
            public void onLoadMore(int currentPage) {
                Date date = new Date(new Date().getTime() - 1000 * 60 * 60 * 24 * (currentPage - 1));
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                String num = sdf.format(date);
                String url = "http://news.at.zhihu.com/api/4/news/before/" + num;
                mHomePresenter.getHomeList(url);
            }


        });
        mRecyclerViewHeader.attachTo(mRecyclerView);
        mAdapter = new HomeListAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(mRecyclerView) {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", mList.get(position).getId());
                ((MainActivity) getActivity()).jumpActivity(ArticleCActivity.class, bundle, false);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Utils.getInstance().ToastShort(String.valueOf(position));

            }
        });
    }

    @Override
    protected void initDatas() {
        mHomePresenter.getHomeList("http://news-at.zhihu.com/api/4/news/latest");
    }

    public void setTopView(final List<TopStoriesBean> list) {
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
            final int finalI = i;
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", list.get(finalI).getId());
                    Intent intent = new Intent(getActivity(), ArticleCActivity.class);
                    intent.putExtras(bundle);
                    getActivity().startActivity(intent);
                }
            });
            mList.add(v);
        }
        mViewPager.setListViews(mList);


    }

    public void setListView(List<StoriesBean> list) {
        mAdapter.setList(list);
    }

    @Override
    public void onRefreshList() {

    }

    @Override
    public void setData(HomeBean data) {
        if (data.getTop_stories() != null) {
            isRefresh = false;
            mSwipeRefreshLayout.setRefreshing(isRefresh);
            setTopView(data.getTop_stories());
            for (int i = 0; i < data.getTop_stories().size(); i++) {
                mListForId.add(data.getTop_stories().get(i).getId());
            }
        }
        for (int i = 0; i < data.getStories().size(); i++) {
            StoriesBean bean = data.getStories().get(i);
            bean.setDate(data.getDate());
            mList.add(bean);
            mListForId.add(data.getStories().get(i).getId());
        }
        setListView(mList);
    }

    @Override
    public void onError() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mViewPager.setStop(true);
    }

    @Override
    public void onRefresh() {
        if (!isRefresh) {
            isRefresh = true;
            mViewPager.clearLinearLayout();
            mList = new ArrayList<>();
            mListForId = new ArrayList<>();
            mHomePresenter.getHomeList("http://news-at.zhihu.com/api/4/news/latest");
            EventBus.getDefault().post(new ChangeToolbarTextEvent(getResources().getString(R.string.drawer_home)));
        }
    }

    public List<Integer> getListForId() {
        return mListForId;
    }
}
