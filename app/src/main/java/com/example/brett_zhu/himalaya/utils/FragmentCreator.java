package com.example.brett_zhu.himalaya.utils;

import com.example.brett_zhu.himalaya.base.BaseFragment;
import com.example.brett_zhu.himalaya.fragment.HistoryFragment;
import com.example.brett_zhu.himalaya.fragment.RecommendFragment;
import com.example.brett_zhu.himalaya.fragment.SubscriptionFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * @author brett-zhu
 * created at 2019/4/14 20:23
 */
public class FragmentCreator {

    public final static int INDEX_RECOMMEND = 0;
    public final static int INDEX_SUBSCRIPTION = 1;
    public final static int INDEX_HISTORY = 2;
    public final static Map<Integer, BaseFragment> sCache = new HashMap<>();
    public final static int PAGE_COUNT = 3;

    public static BaseFragment getFragment(int index) {
        BaseFragment baseFragment = sCache.get(index);
        if (baseFragment != null) {
            return baseFragment;
        }
        switch (index) {
            case INDEX_RECOMMEND:
                baseFragment = new RecommendFragment();
                break;
            case INDEX_SUBSCRIPTION:
                baseFragment = new SubscriptionFragment();
                break;
            case INDEX_HISTORY:
                baseFragment = new HistoryFragment();
                break;
            default:
                break;
        }
        if (baseFragment != null) {
            sCache.put(index, baseFragment);
        }
        return baseFragment;
    }
}
