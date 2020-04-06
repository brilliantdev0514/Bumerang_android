package com.tur.bumerang.Utils;
import android.content.Context;

import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.callback.IFooterCallBack;

public class CustomFooterView extends LinearLayout implements IFooterCallBack {
    private Context mContext;

    private View mContentView;
    private View mProgressBar;
    private TextView mHintView;
    private TextView mClickView;
    private boolean showing = false;

    public CustomFooterView(Context context) {
        super(context);
        initView(context);
    }

    public CustomFooterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }


    @Override
    public void callWhenNotAutoLoadMore(final XRefreshView xRefreshView) {

        mClickView.setText("Click to load more");
        mClickView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                xRefreshView.notifyLoadMore();
            }
        });
    }

    @Override
    public void onStateReady() {
        mHintView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.GONE);
        mClickView.setVisibility(View.VISIBLE);
        mClickView.setText("Click to load more");
    }

    @Override
    public void onStateRefreshing() {
        mHintView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
        mClickView.setVisibility(View.GONE);
        show(true);
    }

    @Override
    public void onReleaseToLoadMore() {
        mHintView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.GONE);
        mClickView.setVisibility(View.VISIBLE);
        mClickView.setText("Release and load more");
    }

    @Override
    public void onStateFinish(boolean hideFooter) {
        if (hideFooter) {
            mHintView.setText("");
        } else {
            //处理数据加载失败时ui显示的逻辑，也可以不处理，看自己的需求
            mHintView.setText("Loading failed, please try again");
        }
        mHintView.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
        mClickView.setVisibility(View.GONE);
    }

    @Override
    public void onStateComplete() {
        mHintView.setText("No more data");
        mHintView.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
        show(true);
    }

    @Override
    public void show(boolean show) {
        showing = show;
        LayoutParams lp = (LayoutParams) mContentView
                .getLayoutParams();
        lp.height = show ? LayoutParams.WRAP_CONTENT : 0;
        mContentView.setLayoutParams(lp);
    }

    @Override
    public boolean isShowing() {
        return showing;
    }

    private void initView(Context context) {
        mContext = context;
        LinearLayout moreView = (LinearLayout) LayoutInflater.from(mContext).inflate(com.andview.refreshview.R.layout.xrefreshview_footer, this);
        LinearLayout.LayoutParams layoutParams= new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, 0, 100);
        moreView.setLayoutParams(layoutParams);


        mContentView = moreView.findViewById(com.andview.refreshview.R.id.xrefreshview_footer_content);
        mProgressBar = moreView
                .findViewById(com.andview.refreshview.R.id.xrefreshview_footer_progressbar);
        mHintView = (TextView) moreView
                .findViewById(com.andview.refreshview.R.id.xrefreshview_footer_hint_textview);
        mClickView = (TextView) moreView
                .findViewById(com.andview.refreshview.R.id.xrefreshview_footer_click_textview);
    }

    @Override
    public int getFooterHeight() {
        return getMeasuredHeight();
    }
}
