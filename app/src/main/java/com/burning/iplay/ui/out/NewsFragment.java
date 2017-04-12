package com.burning.iplay.ui.out;

import com.burning.iplay.R;
import com.burning.iplay.base.BaseFragment;
import com.burning.iplay.config.Constants;
import com.burning.iplay.ui.news.focuse.focuse.NewsFocusedFragment;
import com.burning.iplay.ui.news.item.NewsItemFragment;

import java.util.List;

/**
 * @author Kiven
 * @time 2017-2-16  18:27
 * Email f842728368@163.com
 * @desc 资讯Fragment
 */
public class NewsFragment extends OutFragment {


    @Override
    protected int getFragmentTitleArr() {
        return R.array.news_arr;
    }

    @Override
    protected void initFragments(List<BaseFragment> fragments) {
        fragments.add(new NewsItemFragment(Constants.TOPIC));
        fragments.add(new NewsFocusedFragment(""));
        fragments.add(new NewsItemFragment(Constants.ONLINE_GAME));
        fragments.add(new NewsItemFragment(Constants.MOBILE_GAME));
        fragments.add(new NewsItemFragment(Constants.PLAYSTATION));
        fragments.add(new NewsItemFragment(Constants.ELEC_SPORT));
    }

}
