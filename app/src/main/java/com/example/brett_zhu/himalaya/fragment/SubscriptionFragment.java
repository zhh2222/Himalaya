package com.example.brett_zhu.himalaya.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.brett_zhu.himalaya.R;
import com.example.brett_zhu.himalaya.base.BaseFragment;

/**
 * @author brett-zhu
 * created at 2019/4/14 20:04
 */
public class SubscriptionFragment extends BaseFragment {
    @Override
    protected View onSubViewLoaded(LayoutInflater layoutInflater, ViewGroup container) {
        return layoutInflater.inflate(R.layout.fragment_subscription, container, false);
    }
}
