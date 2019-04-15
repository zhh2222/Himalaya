package com.example.brett_zhu.himalaya.interfaces;

/**
 * @author brett-zhu
 * created at 2019/4/15 13:19
 */
public interface IRecommendPresenter {
    /**
     * 获取推荐内容
     */

    void getRecommendList();

    /**
     * 这个方法用于注册UI的回调
     * @param callBack
     */
    void registerViewCallback(IRecommendViewCallBack callBack);

    /**
     * 取消UI的回调注册
     * @param callBack
     */
    void unRegisterViewCallback(IRecommendViewCallBack callBack);
}
