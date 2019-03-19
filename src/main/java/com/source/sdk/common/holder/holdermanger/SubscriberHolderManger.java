package com.source.sdk.common.holder.holdermanger;

import android.content.Context;

import com.source.sdk.common.holder.eventmanger.EventManager;
import com.source.sdk.common.holder.holdermanger.holder.BaseSubscriberHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by yangjian-ds3 on 2018/4/10.
 */

public class SubscriberHolderManger {

    private List<BaseSubscriberHolder<?>> mHolders;

    public SubscriberHolderManger(){

        mHolders = new ArrayList<>();
    }

    public void registerObserver(Context context, BaseSubscriberHolder<?> holder){
        EventManager.getDefault().registerObserver(context,holder);
        mHolders.add(holder);
    }

    public void removeObserver(Context context){

        for(BaseSubscriberHolder<?> holder : mHolders){
            EventManager.getDefault().removeObserver(context,holder);
        }
        mHolders.clear();
    }
}
