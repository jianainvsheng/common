package com.source.sdk.common.dialog;

import android.content.Context;

import com.source.sdk.common.dialog.builder.IDialogBuilder;
import com.source.sdk.common.dialog.helper.BaseDialogHelper;

/**
 * Created by yangjian-ds3 on 2018/3/21.
 */

public interface IDialog<D extends IDialogBuilder<D>> {

    /**
     * init the dialog
     * @param context
     */
    void initDialog(Context context, D data);

    /**
     * create the helper
     * @return
     */
    BaseDialogHelper<D> onCreateHelper(Context context, D data) throws NoSuchMethodException;

}
