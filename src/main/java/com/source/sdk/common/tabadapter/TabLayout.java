package com.source.sdk.common.tabadapter;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.HorizontalScrollView;

import com.source.sdk.common.R;
import com.source.sdk.common.tabadapter.listener.ITabLayout;
import com.source.sdk.common.tabadapter.widget.TabStripLayout;

/**
 * TabLayout for title tab.
 *
 *@author Created by yangjian-ds3 on 2018/4/10.
 * 
 */
public class TabLayout extends HorizontalScrollView implements OnClickListener {

	private int mTabWidthPrelong = getDipSize(20);

	private TabStripLayout mTabStripLayout;
	private int mSelectedColor = Color.parseColor("#01C990");

	private OnSelectedCallBack mOnSelectedCallBack;

	private ITabLayout mAdapter;

	private View mLastSelectedTab;

	public interface OnSelectedCallBack {

		/**
		 * 点击的回调
		 * @param position 点击的位置
		 */
		void selected(int position);
	}

	public TabLayout(Context context) {
		this(context, null);
	}

	public TabLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public TabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context);
	}

	private void init(Context context) {
		TabStripLayout tabStrip = new TabStripLayout(context);
		// Initialise paint.
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setDither(true);
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(mSelectedColor);
		tabStrip.setPaint(paint);
		// Add tab strip view.
		addTabStrip(tabStrip);
	}

	public void setAdapter(ITabLayout adapter){

		this.mAdapter = adapter;
		this.mAdapter.setTabLayout(this);
		request(null);
	}
	private void addTabStrip(TabStripLayout tabStrip) {
		if (getChildCount() > 0) {
			removeAllViews();
		}
		int width = LayoutParams.WRAP_CONTENT;
		int height = LayoutParams.MATCH_PARENT;
		addView(tabStrip, width, height);
		mTabStripLayout = tabStrip;
	}



	/**
	 * Set tab selected color.
	 * 
	 * @param selectedColor
	 */
	public void setSelectedColor(int selectedColor) {
		mSelectedColor = selectedColor;
	}

	public void setMinSize(int minSize){
	    if(mTabStripLayout != null){
			mTabStripLayout.setMinSize(minSize);
		}
	}


	public void initStrip(){
		initStrip(0);
	}
	/**
	 * Initialise strip since tabs added.
	 */
	public void initStrip(int position) {
		if (mTabStripLayout.getChildCount() <= position) {
			mTabStripLayout.setRectF(new RectF());
			return;
		}
		// Select the first tab.
		mLastSelectedTab = mTabStripLayout.getChildAt(position);
		post(new Runnable() {
			@Override
			public void run() {
				int w = mLastSelectedTab.getMeasuredWidth();
				int h = getHeight();
				float left = mLastSelectedTab.getLeft() + mTabWidthPrelong / 2;
				float top = h - getDipSize(2);
				float right = left + w - mTabWidthPrelong ;
				float bottom = h;
				RectF rectF = new RectF(left, top, right, bottom);
				// Initialise the first strip.
				mTabStripLayout.setRectF(rectF);
			}
		});
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		if (widthMode == MeasureSpec.AT_MOST) {
			int specWidth = MeasureSpec.getSize(widthMeasureSpec);
			widthMeasureSpec = MeasureSpec.makeMeasureSpec(specWidth,
					MeasureSpec.EXACTLY);
		}
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		if (heightMode == MeasureSpec.AT_MOST) {
			int minSize = getDipSize(50);
			heightMeasureSpec = MeasureSpec.makeMeasureSpec(minSize,
					MeasureSpec.EXACTLY);
		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	private int getDipSize(int value) {
		int unit = TypedValue.COMPLEX_UNIT_DIP;
		DisplayMetrics metrics = getResources().getDisplayMetrics();
		value = (int) TypedValue.applyDimension(unit, value, metrics);
		return value;
	}

	@Override
	public void onClick(View view) {
		if (mLastSelectedTab == view) {
			return;
		}
		// Set selected.
		View currentTab = view;
		mLastSelectedTab = currentTab;
		// Call back.
		int position = (Integer) (view.getTag(R.id.spearch_tab_item_id));
		mTabStripLayout.updateHorizontalStrip(position,mTabWidthPrelong);
		if (mOnSelectedCallBack != null) {
			mOnSelectedCallBack.selected(position);
		}
	}

	public void clearChildView(){

		if(mTabStripLayout != null){
			mTabStripLayout.removeAllViews();
		}
	}
	/**
	 * Select tab by position.
	 * 
	 * @param position
	 *            The index of selected tab.
	 */
	public void selectTab(int position) {
		View view = mTabStripLayout.getChildAt(position);
		view.performClick();
	}

	/**
	 * Select tab by position and position offset.
	 * 
	 * @param position
	 *            The index of selected tab.
	 * @param positionOffset
	 *            The position offset.
	 */
	public void selectTab(int position, float positionOffset) {
		int lastPosition = mTabStripLayout.indexOfChild(mLastSelectedTab);
		if (lastPosition < 0) {
			return;
		}
		int nextPosition = -1;
		float nextP = 0;
		float offset = 0;
		if (lastPosition == position) {
			nextPosition = lastPosition + 1;
			nextP = positionOffset;
			offset = mLastSelectedTab.getWidth() * nextP;
		} else if (lastPosition > position) {
			nextPosition = lastPosition - 1;
			nextP = 1f - positionOffset;
			offset = -mLastSelectedTab.getWidth() * nextP;
		}
		View nextTab = mTabStripLayout.getChildAt(nextPosition);
		if (nextTab != null) {
			float left = mLastSelectedTab.getLeft() + offset;
			int width = nextTab.getMeasuredWidth();
			int parentWidth = getWidth();
			float newPosition = left - parentWidth / 2 + width / 2;
			// Update position.
			scrollTo((int) newPosition, 0);
		}
	}

	public void request(int... positions){
		mTabStripLayout.removeAllViews();
		if(this.mAdapter != null && this.mAdapter.getItemCount() > 0){
			for(int i = 0 ; i < this.mAdapter.getItemCount() ; i ++){
				final View view = this.mAdapter.getView(this,i);
				view.setTag(R.id.spearch_tab_item_id,i);
				mTabStripLayout.addView(view);
				view.setOnClickListener(this);
				if(isAddView(positions,i)){
					addItemView(view);
				}
			}
		}
	}

	private boolean isAddView(int[] positions , int position){

		if(positions == null){
			return false;
		}

		for(int pos : positions){

			if(pos == position)
				return true;
		}
		return false;
	}

	public void addItemView(View view){

	}

	/**
	 * Set outside callback.
	 * 
	 * @param callBack
	 */
	public void setOnSelectedCallBack(OnSelectedCallBack callBack) {
		mOnSelectedCallBack = callBack;
	}
}
