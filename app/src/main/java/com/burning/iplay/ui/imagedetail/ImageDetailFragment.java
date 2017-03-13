package com.burning.iplay.ui.imagedetail;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.burning.iplay.R;
import com.burning.iplay.adapter.ImageDetailAdapter;
import com.burning.iplay.base.BaseFragment;
import com.burning.iplay.bean.ImageDetail;

import java.util.List;

import butterknife.BindView;

/**
 * @author lam
 * @time 2017-03-08  10:28
 * @desc ${TODD}
 */
public class ImageDetailFragment extends BaseFragment<IImageDetailContract.Presenter>
        implements IImageDetailContract.View, IImageOnClickListener {

    @BindView(R.id.vp_image_detail_frag)
    ViewPager mVpImageDetailFrag;
    @BindView(R.id.tv_page_no)
    TextView mTvPageNo;
    @BindView(R.id.tv_page_count)
    TextView mTvPageCount;
    @BindView(R.id.tv_note_image_detail)
    TextView mTvNoteImageDetail;
    @BindView(R.id.rl_container)

    RelativeLayout mRlContainer;
    @BindView(R.id.iv_back2)
    ImageView mIvBack2;
    @BindView(R.id.ll_top_container)
    LinearLayout mLlTopContainer;
    private String mSetid;
    private ImageDetailAdapter mAdapter;
    private List<ImageDetail> mImageDetails;
    private Animation mAnimInUp2down;
    private Animation mAnimDown2up;
    private Animation bottomDown2up;
    private Animation bottomUp2Down;
    private WindowManager mWm;
    private float mDefaultY; //底部容器的默认位置
    private int mDefaultHeight;//底部容器的高度

    @Override
    protected int getLayoutResId() {
        return R.layout.frag_image_detail;
    }

    @Override
    protected void initParams() {
        mParams.id = mSetid;
    }

    @Override
    protected void initialize() {
        mPresenter.start();
        initUi();
        initEven();
    }

    private void initEven() {
        mRlContainer.setOnTouchListener(new View.OnTouchListener() {
            private float mStartY;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float mSumDy=0;
                switch (event.getAction()) {
                                  case MotionEvent.ACTION_DOWN :
                                      mStartY = event.getY();
                                      break;
                                  case MotionEvent.ACTION_MOVE :
                                      float newY = event.getY();
                                      float dY = newY - mStartY;
                                       mSumDy += dY;
                                      mStartY = newY;
                                      float y = mRlContainer.getY() + mSumDy;
                                      if(y<mDefaultY-(mDefaultY-mDefaultHeight/2)||y>mDefaultY) {
                                        Log.i("tag", "mDefaultY-mDefaultY-mDefaultHeight/2||y>mDefaultY   "+y);
                                      }else {
                                          Log.i("tag", "else    "+y);
                                      }
                                      mRlContainer.setY(y);
                                      break;
                                  case MotionEvent.ACTION_UP :

                                      break;
                              }
                            mRlContainer.invalidate();
                              return true;
            }
        });
    }

    private void initUi() {
        mAdapter = new ImageDetailAdapter();
        mAdapter.setImageOnClickListener(this);
        mVpImageDetailFrag.setAdapter(mAdapter);
        mVpImageDetailFrag.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ImageDetail imageDetail = mImageDetails.get(position);
                mTvPageNo.setText(imageDetail.no);
                mTvNoteImageDetail.setText(imageDetail.note);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        // initListener();
        //请求ImageDetail
        mPresenter.requestData(mParams);
        //获取动画
        mAnimInUp2down = AnimationUtils.loadAnimation(getContext(), R.anim.translate_up2down);
        mAnimDown2up = AnimationUtils.loadAnimation(getContext(), R.anim.translate_down2up);
        bottomDown2up = AnimationUtils.loadAnimation(getContext(), R.anim.bottom_down2up);
        bottomUp2Down = AnimationUtils.loadAnimation(getContext(), R.anim.bottom_up2down);
    }

    @Override
    protected IImageDetailContract.Presenter createPresenter() {
        return new ImageDetailPresenter();
    }

    @Override
    protected void requestPermissionSucceed() {

    }

    public void setPhotoId(String setid) {
        mSetid = setid;
    }


    @Override
    public void showImageAndInfo(List<ImageDetail> imageDetails) {
        mImageDetails = imageDetails;
        mAdapter.setData(mImageDetails);
        mTvPageNo.setText("1");

        mTvPageCount.setText("/" + imageDetails.size());
        mTvNoteImageDetail.setText(imageDetails.get(0).note);
        mDefaultY = mRlContainer.getY();
        mDefaultHeight = mRlContainer.getHeight();
        Log.i("tag", "y="+mRlContainer.getY());
        Log.i("tag", "ImageDetailFragment onTouch()mDefaultHeight="+mDefaultHeight);
    }

    private boolean show=true;//动画的状态
    @Override
    public void onClick() {
        if(show) {
            mLlTopContainer.startAnimation(mAnimDown2up);
            mRlContainer.startAnimation(bottomUp2Down);
        }else {
            mLlTopContainer.startAnimation(mAnimInUp2down);
            mRlContainer.startAnimation(bottomDown2up);
        }
        show=!show;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mAnimInUp2down != null) {
            mAnimInUp2down = null;
        }
        if (mAnimDown2up != null) {
            mAnimDown2up=null;
        }
        if (bottomDown2up != null) {
            bottomDown2up = null;
        }
        if (bottomUp2Down != null) {
            bottomUp2Down=null;
        }
        if (mAdapter != null) {
            mAdapter = null;
        }
    }
}
