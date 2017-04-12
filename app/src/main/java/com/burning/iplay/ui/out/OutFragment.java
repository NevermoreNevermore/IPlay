package com.burning.iplay.ui.out;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.burning.iplay.R;
import com.burning.iplay.adapter.OutFragmentPagerAdapter;
import com.burning.iplay.base.BaseFragment;
import com.burning.iplay.utils.AppUtil;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Kiven
 * @time 2017-2-18  10:55
 * Email f842728368@163.com
 * @desc 外层Fragment的基类
 */
public abstract class OutFragment extends Fragment {


    @BindView(R.id.stl)
    SmartTabLayout mSmartTabLayout;
    @BindView(R.id.news_viewpager)
    ViewPager mViewPager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResId(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    protected int getLayoutResId() {
        return R.layout.frag_out;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 设置顶部状态栏的颜色
        if (Build.VERSION.SDK_INT >= 21) {
            getActivity().getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        initUI();
    }

    protected void initUI() {
        OutFragmentPagerAdapter adapter = new OutFragmentPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(adapter);

        List<BaseFragment> fragments = new ArrayList<>();
        // 获得子Fragment集合，由子类来实现
        initFragments(fragments);
        adapter.setData(fragments);

        // 获得子Fragment标题的集合，由子类来实现
        adapter.setTitles(Arrays.asList(AppUtil.getStringArr(getFragmentTitleArr())));
        mSmartTabLayout.setViewPager(mViewPager);
    }

    /**
     * 返回上面标题的字符串数组在String中的ID
     */
    protected abstract int getFragmentTitleArr();

    /**
     * 初始化子Fragment，往fragments集合中添加数据
     */
    protected abstract void initFragments(List<BaseFragment> fragments);


}
