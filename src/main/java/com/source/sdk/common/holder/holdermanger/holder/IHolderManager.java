package com.source.sdk.common.holder.holdermanger.holder;

import com.source.sdk.common.holder.holdermanger.holder.context.SubscriberContext;

/**
 * @author Created by yangjian-ds3 on 2018/4/14.
 */

public interface IHolderManager {

    /**
     * 得到holder
     * @return
     */
    SubscriberContext getContextHolder();

    /**
     * 添加holder
     * @param holder
     */
    void addSubscriberHolder(BaseSubscriberHolder<?> holder);

}
