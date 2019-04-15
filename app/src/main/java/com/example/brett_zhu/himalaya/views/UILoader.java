package com.example.brett_zhu.himalaya.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.brett_zhu.himalaya.R;
import com.example.brett_zhu.himalaya.base.BaseApplication;

/**
 * @author brett-zhu
 * created at 2019/4/15 14:21
 */
public abstract class UILoader extends FrameLayout {

    private View mLoadingView;
    private View mSuccessView;
    private View mNetworkErrorView;
    private View mEmptyView;
    private OnRetryClickListener mRetryClickListener = null;

    public void setRetryClickListener(OnRetryClickListener retryClickListener) {
        mRetryClickListener = retryClickListener;
    }

    public enum UIStatus {
        LOADING, SUCCESS, NETWORK_ERROR, EMPTY, NONE
    }

    public UIStatus mCurrentStatus = UIStatus.NONE;

    protected abstract View getSuccessView(ViewGroup container);

    public UILoader(@NonNull Context context) {
        this(context, null);
    }

    public UILoader(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UILoader(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //
        init();
    }

    public void updateStatus(UIStatus status) {
        mCurrentStatus = status;
        //更新一定在主线程
        BaseApplication.getHandler().post(new Runnable() {
            @Override
            public void run() {
                switchUIByCurrentStatus();
            }
        });
    }

    /**
     * 初始化UI
     */
    private void init() {
        switchUIByCurrentStatus();
    }

    private void switchUIByCurrentStatus() {
        //加载中
        if (mLoadingView == null) {
            mLoadingView = getLoadView();
            addView(mLoadingView);
        }
        //设置状态是否可见
        mLoadingView.setVisibility(mCurrentStatus == UIStatus.LOADING ? VISIBLE : GONE);

        //成功
        if (mSuccessView == null) {
            mSuccessView = getSuccessView(this);
            addView(mSuccessView);
        }
        //设置状态是否可见
        mSuccessView.setVisibility(mCurrentStatus == UIStatus.SUCCESS ? VISIBLE : GONE);

        //网络错误页面
        if (mNetworkErrorView == null) {
            mNetworkErrorView = getNetWorkErrorView();
            addView(mNetworkErrorView);
        }
        //设置状态是否可见
        mNetworkErrorView.setVisibility(mCurrentStatus == UIStatus.NETWORK_ERROR ? VISIBLE : GONE);

        //数据为空的界面
        if (mEmptyView == null) {
            mEmptyView = getEmptyView();
            addView(mEmptyView);
        }
        //设置状态是否可见
        mEmptyView.setVisibility(mCurrentStatus == UIStatus.EMPTY ? VISIBLE : GONE);
    }

    private View getEmptyView() {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_empty_view, this, false);
    }

    private View getNetWorkErrorView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_error_view, this, false);
        view.findViewById(R.id.network_error_ll).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //去重新获取数据
                if (mRetryClickListener != null) {
                    mRetryClickListener.onRetryClick();
                }
            }
        });
        return view;
    }

    private View getLoadView() {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_loading__view, this, false);
    }

    public interface OnRetryClickListener {
        void onRetryClick();
    }
}
