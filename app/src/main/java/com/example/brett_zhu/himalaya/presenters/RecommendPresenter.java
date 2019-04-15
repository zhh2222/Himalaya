package com.example.brett_zhu.himalaya.presenters;

import android.support.annotation.Nullable;
import android.util.Log;

import com.example.brett_zhu.himalaya.interfaces.IRecommendPresenter;
import com.example.brett_zhu.himalaya.interfaces.IRecommendViewCallBack;
import com.example.brett_zhu.himalaya.utils.Constants;
import com.example.brett_zhu.himalaya.utils.LogUtil;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.model.album.Album;
import com.ximalaya.ting.android.opensdk.model.album.GussLikeAlbumList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author brett-zhu
 * created at 2019/4/15 13:23
 */
public class RecommendPresenter implements IRecommendPresenter {
    private static final String TAG = RecommendPresenter.class.getSimpleName();

    private List<IRecommendViewCallBack> mCallBacks = new ArrayList<>();

    private RecommendPresenter() {
    }

    private static final class Holder {
        private static final RecommendPresenter INSTANCE = new RecommendPresenter();
    }

    public static RecommendPresenter getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 获取推荐内容，其实就是猜你喜欢
     */
    @Override
    public void getRecommendList() {
        //封装参数
        updateLoading();
        Map<String, String> map = new HashMap<>();
        //这个参数表示一页数据返回多少条
        map.put(DTransferConstants.LIKE_COUNT, Constants.RECOMMEND_COUNT + "");
        CommonRequest.getGuessLikeAlbum(map, new IDataCallBack<GussLikeAlbumList>() {
            @Override
            public void onSuccess(@Nullable GussLikeAlbumList gussLikeAlbumList) {
                //数据获取成功
                if (gussLikeAlbumList != null) {
                    List<Album> albumList = gussLikeAlbumList.getAlbumList();
                    //更新UI
//                    updateRecommendUI(albumList);
                    handlerRecommendResult(albumList);
                }
            }

            @Override
            public void onError(int i, String s) {
                //数据获取出错
                LogUtil.e(TAG, "error:" + i);
                LogUtil.e(TAG, "error:msg=" + s);
                handlerError();
            }
        });

    }

    private void handlerError() {
        if (mCallBacks != null) {
            for (IRecommendViewCallBack callBack : mCallBacks) {
                callBack.onNetworkError();
            }
        }
    }

    private void handlerRecommendResult(List<Album> albumList) {
        //通知UI更新
        if (albumList != null) {
            //测试，清空一下，让页面显示空
        //       albumList.clear();
            final int size = albumList.size();
            if (size == 0) {
                for (IRecommendViewCallBack callBack : mCallBacks) {
                    callBack.onEmpty();
                }
            } else {
                for (IRecommendViewCallBack callBack : mCallBacks) {
                    callBack.onRecommendListLoaded(albumList);
                }
            }
        }
    }

    private void updateLoading() {
        for (IRecommendViewCallBack callBack : mCallBacks) {
            callBack.onLoading();
        }
    }

    @Override
    public void registerViewCallback(IRecommendViewCallBack callBack) {
        if (mCallBacks != null && !mCallBacks.contains(callBack)) {
            mCallBacks.add(callBack);
        }
    }

    @Override
    public void unRegisterViewCallback(IRecommendViewCallBack callBack) {

    }
}
