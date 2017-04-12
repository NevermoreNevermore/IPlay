package com.burning.iplay.ui.out;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.burning.iplay.R;
import com.burning.iplay.ui.me.set.SettingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Kiven
 * @time 2017-3-8  16:41
 * Email f842728368@163.com
 * @desc “我”Fragment界面
 */
public class MeFragment extends Fragment {

    @BindView(R.id.me_icon_iv)
    ImageView mMeIconIv;
    @BindView(R.id.me_login_btn)
    TextView mMeLoginBtn;
    @BindView(R.id.me_set_btn)
    ImageButton mMeSetBtn;


    private View thisView;
    private Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (thisView == null)
            thisView = inflater.inflate(R.layout.frag_me, container, false);
        mUnbinder = ButterKnife.bind(this, thisView);
        return thisView;

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 设置状态栏颜色
        if (Build.VERSION.SDK_INT >= 21) {
            getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.colorRed));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
    }


    @OnClick({R.id.me_icon_iv, R.id.me_login_btn, R.id.me_set_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.me_icon_iv:
                break;
            case R.id.me_login_btn:
                break;
            case R.id.me_set_btn: // 开启设置界面
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
                break;
        }
    }
}
