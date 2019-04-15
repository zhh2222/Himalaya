package com.example.brett_zhu.himalaya.interfaces;

import com.ximalaya.ting.android.opensdk.model.album.Album;

import java.util.List;

/**
 * @author brett-zhu
 * created at 2019/4/15 13:20
 */
public interface IRecommendViewCallBack {
    /**
     * 获取推荐内容的结果
     * @param result
     */
    void onRecommendListLoaded(List<Album> result);

    /**
     * 网络错误
     */
    void onNetworkError();

    /**
     * 页面内容为空
     */
    void onEmpty();

    /**
     * 正在加载
     */
    void onLoading();
}
