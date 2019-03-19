package com.source.sdk.common.rightlinearalyout.listener;

import android.view.View;
import android.view.ViewGroup;

import com.source.sdk.common.rightlinearalyout.RightLinearLayout;

/**
 * Created by yangjian on 2018/8/7.
 */

public interface ITabLayout {

    /**
     * item count
     * @return
     */
    int getItemCount();

    /**
     * getview
     * @param parentView
     * @param position
     * @return
     */
    View getView(ViewGroup parentView, int position);

    /**
     * set the TabLayout
     * @param tabLayout
     */
    void setTabLayout(RightLinearLayout tabLayout);
}
