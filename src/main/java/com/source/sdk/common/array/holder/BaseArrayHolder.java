package com.source.sdk.common.array.holder;

import android.view.View;

import com.source.sdk.common.holder.holder.BaseHolder;

/**
 * Created by yangjian on 2018/8/6.
 */

public class BaseArrayHolder<T> extends BaseHolder<T> {

    private int mType;

    public BaseArrayHolder(View view) {
        super(view);
    }

    public int getType(){

        return mType;
    }

    public void setType(int type){

        this.mType = type;
    }
}
