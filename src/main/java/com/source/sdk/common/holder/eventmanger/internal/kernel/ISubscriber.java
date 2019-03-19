package com.source.sdk.common.holder.eventmanger.internal.kernel;

import com.source.sdk.common.holder.eventmanger.internal.event.BaseEvent;

/**
 * @author Created by yangjian-ds3 on 2018/4/4.
 */

public interface ISubscriber {

    /**
     * receive messages
     * @param event
     */
    void onMessageEvent(BaseEvent event);
}
