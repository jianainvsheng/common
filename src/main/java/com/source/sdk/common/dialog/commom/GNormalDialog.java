package com.source.sdk.common.dialog.commom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.View;

import com.source.sdk.common.dialog.base.BaseDialog;
import com.source.sdk.common.dialog.builder.BaseBuilder;
import com.source.sdk.common.dialog.builder.Normal.NormalBuilder;
import com.source.sdk.common.dialog.helper.BaseDialogHelper;
import com.source.sdk.common.dialog.helper.usually.UsuallyDialogHelper;

/**
 * Created by yangjian-ds3 on 2018/3/21.
 */

public class GNormalDialog< N extends BaseBuilder<N>> extends BaseDialog<N> {

    public GNormalDialog(@NonNull Context context) {
        super(context);
    }

    public GNormalDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    @Override
    public BaseDialogHelper onCreateDefaultHelp(Context context, N data) {
        return new UsuallyDialogHelper(context);
    }

    public static NormalBuilder onCreateBuiler(Context context){

        return new NormalBuilder(context);
    }

    public static < N extends BaseBuilder<N>> N onCreateBuiler(N n){

        return n;
    }


    public interface PositiveCallBack {
        void onClick(View v);
    }

    public interface NegativeCallBack {
        void onClick(View v);
    }
}
