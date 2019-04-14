package com.example.brett_zhu.himalaya.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author brett-zhu
 * created at 2019/4/14 20:03
 */
public abstract class BaseFragment extends Fragment {

    private View mRootView;

    protected abstract View onSubViewLoaded(LayoutInflater layoutInflater, ViewGroup container);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = onSubViewLoaded(inflater,container);
        return mRootView;
    }
}
