package com.source.sdk.common.rightlinearalyout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.source.sdk.common.rightlinearalyout.listener.ITabLayout;

/**
 * Created by yangjian on 2018/9/10.
 */

public class RightLinearLayout extends ViewGroup {

    private ITabLayout mTabLayout;

    private int mHeight;

    private int everyCount = 5;

    public RightLinearLayout(Context context) {
        this(context,null);
    }

    public RightLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RightLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if(getChildCount() <= 0){

            return;
        }
        int countSize = getMeasuredWidth();
        int everyWidth = (int)((float)countSize / everyCount);
        int height = 0;
        for(int i = 0 ; i < getChildCount() ; i ++){
            getChildAt(i).measure(0,heightMeasureSpec);
            if(getChildAt(i) instanceof ViewGroup){

                ViewGroup group = (ViewGroup) getChildAt(i);

            }
            height = getChildAt(i).getMeasuredHeight() > height ? getChildAt(i).getMeasuredHeight() : height;
        }
        mHeight = mHeight > getMeasuredHeight() ? mHeight : getMeasuredHeight();
        setMeasuredDimension(widthMeasureSpec,height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int countSize = getMeasuredWidth();
        int everyWidth = (int)((float)countSize / everyCount);
        //右边
       // int width = getMeasuredWidth();
        //左边
        int width = 0;
        for(int i = 0 ; i < getChildCount() ; i ++){

//            getChildAt(i).layout(width - everyWidth + (int)((float)everyWidth - getChildAt(i).getMeasuredWidth())/2,
//                    (int)((float)getMeasuredHeight() - getChildAt(i).getMeasuredWidth())/2,
//                    width - everyWidth + (int)((float)everyWidth + getChildAt(i).getMeasuredWidth())/2,
//                    (int)((float)getMeasuredHeight() + getChildAt(i).getMeasuredWidth())/2);

            //右排序
//            getChildAt(i).layout(width - everyWidth ,
//                    0,
//                    width ,
//                    getMeasuredHeight());
//            width -= everyWidth;

            //左排序
            getChildAt(i).layout(width ,
                    0,
                    width + everyWidth,
                    getMeasuredHeight());
            width += everyWidth;
        }
    }

    public void setAdapter(ITabLayout tabLayout){
        this.mTabLayout = tabLayout;
        this.mTabLayout.setTabLayout(this);
        request();
    }
    public void request(){
        removeAllViews();
        if(this.mTabLayout == null ||
                this.mTabLayout.getItemCount() <= 0){
            return;
        }
        for(int i = 0 ; i < this.mTabLayout.getItemCount() ; i++){
            View view = this.mTabLayout.getView(this,i);
            ViewGroup.LayoutParams params = view.getLayoutParams();
            int everyWidth = (int)((float)getMeasuredWidth() / everyCount);
            if(params == null){
                params = new ViewGroup.LayoutParams(everyWidth, LayoutParams.MATCH_PARENT);
            }else{
                params.width = everyWidth;
            }
            view.setLayoutParams(params);
            addView(view);
        }
        requestLayout();
    }
}
