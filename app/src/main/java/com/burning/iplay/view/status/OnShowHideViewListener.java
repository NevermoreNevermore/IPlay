package com.burning.iplay.view.status;

import android.view.View;

/**
 * Created by Kiven on 2017-4-6.
 * Desc: 状态切换的监听器
 */

public interface OnShowHideViewListener {

    void onShowView(View view, int id);

    void onHideView(View view, int id);
}
