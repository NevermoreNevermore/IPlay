package com.burning.iplay.ui.out;

import com.burning.iplay.R;
import com.burning.iplay.base.BaseFragment;
import com.burning.iplay.ui.forum.iplay.IplayFragment;
import com.burning.iplay.ui.forum.kaien.KaienFragment;
import com.burning.iplay.ui.forum.recommend.HotFragment;

import java.util.List;

/**
 * @author Kiven
 * @time 2017-2-16  18:27
 * Email f842728368@163.com
 * @desc 社区Fragment
 */
public class ForumFragment extends OutFragment {


    @Override
    protected int getFragmentTitleArr() {
        return R.array.forum_arr;
    }

    @Override
    protected void initFragments(List<BaseFragment> fragments) {
        //修改社区(热门推荐)
        //fragments.add(new NewsItemFragment(Constants.TOPIC));
        fragments.add(new HotFragment());
        fragments.add(new IplayFragment());
        fragments.add(new KaienFragment());
    }

}
