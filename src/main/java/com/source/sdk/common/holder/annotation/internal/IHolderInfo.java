package com.source.sdk.common.holder.annotation.internal;

import android.support.annotation.IdRes;

import com.source.sdk.common.holder.holdermanger.holder.BaseSubscriberHolder;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by yangjian on 2018/5/25.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface IHolderInfo {

    @IdRes int resId();

    Class<? extends BaseSubscriberHolder<?>> holderClass();
}
