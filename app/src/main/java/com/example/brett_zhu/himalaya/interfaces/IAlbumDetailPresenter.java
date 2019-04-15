package com.example.brett_zhu.himalaya.interfaces;

/**
 * @author brett-zhu
 * created at 2019/4/15 17:39
 */
public interface IAlbumDetailPresenter {
    /**
     * 下拉刷新
     */

    void pull2RefreshMore();

    /**
     * 上拉加载
     */
    void loadMore();

    /**
     * 获取专辑详情
     *
     * @param albumId 专辑id
     * @param page    当前第几页
     */
    void getAlbumDetail(int albumId, int page);

    /**
     * 注册UI通知的接口
     *
     * @param detailViewCallback 通知
     */
    void registerViewCallback(IAlbumDetailViewCallback detailViewCallback);

    /**
     * 删除UI通知的接口
     *
     * @param detailViewCallback 通知
     */
    void unRegisterViewCallback(IAlbumDetailViewCallback detailViewCallback);
}
