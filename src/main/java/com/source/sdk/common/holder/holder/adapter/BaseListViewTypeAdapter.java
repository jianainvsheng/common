package com.source.sdk.common.holder.holder.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.source.sdk.common.R;
import com.source.sdk.common.holder.holder.BaseHolder;

/**
 * @author Created by yangjian on 2017/12/19.
 */

public abstract class BaseListViewTypeAdapter<T, H extends BaseHolder<T>> extends BaseListViewAdapter<T,H> {

    @Override
    public H onCreateViewHolder(ViewGroup parent) {
        return onCreateViewHolder(parent,0);
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(getViewTypeCount() <= 1){
            return super.getView(position,convertView,parent);
        }else{
            H holder;
            if (convertView == null) {
                holder = onCreateViewHolder(parent,getItemViewType(position));
                convertView = holder.getContentView();
                convertView.setTag(R.id.common_orderlist_product_detail_list_view_item_view_id, holder);
                if (getItemClickListener() != null) {
                    convertView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            H itemHolder = (H) v.getTag(R.id.common_orderlist_product_detail_list_view_item_view_id);
                            if (getItemClickListener() != null) {
                                getItemClickListener().onListViewItemClick(position, itemHolder);
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
    }
    @Override
    public int getItemViewType(int position) {
        return getItemType(position);
    }

    @Override
    public int getViewTypeCount() {
        return getItemCount();
    }


    /**
     * 创建holder
     * @param parent
     * @param viewType
     * @return
     */
    public abstract H onCreateViewHolder(ViewGroup parent, int viewType);

    /**
     * 得到当前holder的type
     * @param position
     * @return
     */
    public abstract int getItemType(int position);

    /**
     * 获得item的个数
     * @return
     */
    public abstract int getItemCount();
}
