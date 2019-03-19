package com.source.sdk.common.rightlinearalyout.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.source.sdk.common.array.data.BaseTabModel;
import com.source.sdk.common.array.holder.BaseArrayHolder;
import com.source.sdk.common.rightlinearalyout.RightLinearLayout;
import com.source.sdk.common.rightlinearalyout.listener.ITabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangjian on 2018/8/7.
 */

public abstract class BaseTabLayoutAdapter <T extends BaseTabModel,H extends BaseArrayHolder<T>> implements ITabLayout {

    private List<T> mDatas;

    private RightLinearLayout mTargetLayout;

    public void setData(List<T> datas){
        if(mDatas == null){
            mDatas = new ArrayList<>();
        }
        mDatas.clear();
        if(datas != null){
            mDatas.addAll(datas);
        }
        notifyDataSetChanged();

    }

    public void addData(T ...ts){

        if(ts != null && ts.length > 0){
            if(mDatas == null){
                mDatas = new ArrayList<>();
            }

            int[] positions = new int[ts.length];
            int dex = 0;
            for(T t: ts){
                positions[dex] = mDatas.size();
                this.mDatas.add(t);
                dex++;
            }
            notifyDataSetChanged();
        }
    }

    public void addData(List<T> ts){

        if(ts != null && ts.size() > 0){
            if(mDatas == null){
                mDatas = new ArrayList<>();
            }

            int[] positions = new int[ts.size()];
            int dex = 0;
            for(T t: ts){
                positions[dex] = mDatas.size();
                this.mDatas.add(t);
                dex++;
            }
            notifyDataSetChanged();
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public List<T> getDatas(){

        return mDatas;
    }

    public void notifyDataSetChanged(){

        if(mTargetLayout != null){
            mTargetLayout.request();
        }
    }


    public abstract H onCreateViewHolder(ViewGroup parent, int type);

    public abstract void onBindViewHolder(H holder , int position);

    public int getItemType(int position){

        if(getDatas() != null &&  position < mDatas.size()){

            return getDatas().get(position).getType();
        }
        return 0;
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public View getView(ViewGroup parentView, int position) {
        final H holder = onCreateViewHolder(parentView,getItemType(position));
        holder.setType(getItemType(position));
        holder.setPosition(position);
        onBindViewHolder(holder,position);
        View contextView = holder.getContentView();
        if(contextView == null){
            throw new NullPointerException("item view is null");
        }
        return contextView;
    }

    @Override
    public void setTabLayout(RightLinearLayout tabLayout) {

        this.mTargetLayout = tabLayout;
    }

    public RightLinearLayout getTargetLayout(){

        return mTargetLayout;
    }
}
