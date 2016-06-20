package com.example.zhaoting.myapplication.view.otherTheme;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.example.zhaoting.myapplication.R;
import com.example.zhaoting.myapplication.adapter.OtherThemeListAdapter;
import com.example.zhaoting.myapplication.app.BaseFragment;
import com.example.zhaoting.myapplication.bean.EditorsBean;
import com.example.zhaoting.myapplication.bean.OtherThemeBean;
import com.example.zhaoting.myapplication.bean.StoriesBean;
import com.example.zhaoting.myapplication.presenter.OtherThemePresenter;
import com.example.zhaoting.myapplication.view.article.ThemeArticleCActivity;
import com.example.zhaoting.myapplication.view.main.MainActivity;
import com.example.zhaoting.myapplication.widget.EndlessScrollListener;
import com.example.zhaoting.myapplication.widget.OnRecyclerItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zhaoting on 16/5/12.
 */
public class OtherThemeFragment extends BaseFragment implements OtherThemeView, SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private RecyclerViewHeader mRecyclerViewHeader;
    private ImageView mHeaderImg;
    private TextView mHeaderTitle;
    private TextView mHeaderName;
    private LinearLayout mHeaderPhoto;

    private OtherThemePresenter mOtherThemePresenter;
    private OtherThemeListAdapter mAdapter;
    private boolean isRefresh = false;
    private int themeId;


    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_other_theme;
    }

    @Override
    protected void initViews() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.id_swipe_refresh_layout);
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.id_recycler_view);
        mRecyclerViewHeader = (RecyclerViewHeader) mRootView.findViewById(R.id.id_view_pager_layout);
        mHeaderImg = (ImageView) mRootView.findViewById(R.id.id_other_theme_top_img);
        mHeaderTitle = (TextView) mRootView.findViewById(R.id.id_other_theme_top_title);
        mHeaderName = (TextView) mRootView.findViewById(R.id.id_other_theme_top_editor_name);
        mHeaderPhoto = (LinearLayout) mRootView.findViewById(R.id.id_other_theme_top_author_photo_linear);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerViewHeader.attachTo(mRecyclerView);

        mOtherThemePresenter = new OtherThemePresenter(this);

        mAdapter = new OtherThemeListAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        if (Build.VERSION.SDK_INT > 23) {
            mSwipeRefreshLayout.setProgressBackgroundColorSchemeColor(getActivity().getResources().getColor(R.color.white, getActivity().getTheme()));
            mSwipeRefreshLayout.setColorSchemeColors(getActivity().getResources().getColor(R.color.blue, getActivity().getTheme()));
        } else {
            mSwipeRefreshLayout.setProgressBackgroundColorSchemeColor(getActivity().getResources().getColor(R.color.white));
            mSwipeRefreshLayout.setColorSchemeColors(getActivity().getResources().getColor(R.color.blue));
        }


    }

    @Override
    protected void initDatas() {
//        mBundle = getArguments().putAll(mBundle);;
        mOtherThemePresenter.getOtherThemeList(String.valueOf(getThemeId()));


    }

    @Override
    public void setData(final OtherThemeBean bean) {
        isRefresh = false;
        mSwipeRefreshLayout.setRefreshing(isRefresh);

        if (bean.getDescription() != null) {
            mHeaderTitle.setText(bean.getDescription());
        }
        if (bean.getBackground() != null) {
            Picasso.with(getActivity()).load(bean.getImage()).into(mHeaderImg);
        }
        if (bean.getEditors() != null) {
            for (int i = 0; i < bean.getEditors().size(); i++) {
                CircleImageView image = new CircleImageView(getActivity());
                EditorsBean editorsBean = bean.getEditors().get(i);
                Picasso.with(getActivity()).load(editorsBean.getAvatar()).into(image);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.rightMargin = 20;
                image.setLayoutParams(params);
                mHeaderPhoto.addView(image);
            }
        }

        mAdapter.setList(bean.getStories());

        mRecyclerView.addOnScrollListener(new EndlessScrollListener(mRecyclerView) {
            @Override
            public void onLoadMore(int currentPage) {
                List<StoriesBean> list = bean.getStories();
                int id = list.get(list.size() - 1).getId();
                mOtherThemePresenter.getOtherThemeList(String.valueOf(getThemeId()) + "/before/" + String.valueOf(id));
            }
        });


        mRecyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(mRecyclerView) {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", bean.getStories().get(position).getId());
                ((MainActivity) getActivity()).jumpActivity(ThemeArticleCActivity.class, bundle, false);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    @Override
    public void onError() {

    }

    @Override
    public void onNoConnected() {

    }

    @Override
    public void onRefresh() {
        if (!isRefresh) {
            isRefresh = true;
            mSwipeRefreshLayout.setRefreshing(isRefresh);
            mOtherThemePresenter.getOtherThemeList(String.valueOf(getThemeId()));
            mHeaderPhoto.removeAllViews();
        }
    }

    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }
}
