package com.source.sdk.common.holder.holder.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.source.sdk.common.R;
import com.source.sdk.common.holder.holder.BaseHolder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by yangjian on 2017/12/19.
 */

public abstract class BaseListViewAdapter<T, H extends BaseHolder<T>> extends BaseAdapter {

    private List<T> mData;

    private OnListViewItemClick<H> mListener;

    public void setData(List<T> dataList) {

        if (mData == null) {

            mData = new ArrayList<>();
        }
        mData.clear();
        if (dataList == null || dataList.size() <= 0) {
            notifyDataSetChanged();
            return;
        }
        mData.addAll(dataList);
        notifyDataSetChanged();
    }

    public void addData(List<T> dataList) {

        if (dataList == null || dataList.size() <= 0) {

            return;
        }

        if (mData == null) {

            mData = new ArrayList<>();
        }
        mData.addAll(dataList);
        notifyDataSetChanged();
    }

    public void setOnItemClick(OnListViewItemClick<H> listener) {

        this.mListener = listener;
    }

    public List<T> getData() {
        return mData;
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData == null ? null : mData.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    public OnListViewItemClick<H> getItemClickListener(){

        return mListener;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        H holder = null;
        if (convertView == null) {

            holder = onCreateViewHolder(parent);
            convertView = holder.getContentView();
            convertView.setTag(R.id.common_orderlist_product_detail_list_view_item_view_id, holder);
            if (mListener != null) {
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        H itemHolder = (H) v.getTag(R.id.common_orderlist_product_detail_list_view_item_view_id);
                        if (mListener != null) {
                            mListener.onListViewItemClick(position, itemHolder);
                        }
                    }
                });
            }
        } else {

            holder = (H) convertView.getTag(R.id.common_orderlist_product_detail_list_view_item_view_id);
        }
        holder.setPosition(position);
        onBindViewHolder(holder, position);
        return convertView;
    }

    /**
     * 清楚数据
     */
    public void clearData(){
        if(mData != null &&mData.size() > 0){
            mData.clear();
            notifyDataSetChanged();
        }
    }
    /**
     * 创建holder
     * @param parent
     * @return
     */
    public abstract H onCreateViewHolder(ViewGroup parent);

    /**
     * 赋值
     * @param holder
     * @param position
     */
    public abstract void onBindViewHolder(H holder, int position);

    /**
     * 点击事件
     * @param <H>
     */
    public static interface OnListViewItemClick<H> {

        /**
         * 点击事件
         * @param position
         * @param holder
         */
        void onListViewItemClick(int position, H holder);
    }

}
