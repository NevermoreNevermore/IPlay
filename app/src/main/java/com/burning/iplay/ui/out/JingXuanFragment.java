package com.burning.iplay.ui.out;

import com.burning.iplay.R;
import com.burning.iplay.base.BaseFragment;
import com.burning.iplay.ui.jingxuan.recommend.RecommendFragment;
import com.burning.iplay.ui.jingxuan.zhuanlan.ZhuanlanFragment;

import java.util.List;

/**
 * @author Kiven
 * @time 2017-2-16  18:27
 * Email f842728368@163.com
 * @desc 精选Fragment
 */
public class JingXuanFragment extends OutFragment {


    @Override
    protected int getFragmentTitleArr() {
        return R.array.jingxuan_arr;
    }

    @Override
    protected void initFragments(List<BaseFragment> fragments) {
        fragments.add(new RecommendFragment());
        fragments.add(new ZhuanlanFragment());
    }

}
