package com.example.brett_zhu.himalaya.fragment;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.brett_zhu.himalaya.R;
import com.example.brett_zhu.himalaya.base.BaseFragment;
import com.example.brett_zhu.himalaya.utils.Constants;
import com.example.brett_zhu.himalaya.utils.LogUtil;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.model.album.Album;
import com.ximalaya.ting.android.opensdk.model.album.GussLikeAlbumList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author brett-zhu
 * created at 2019/4/14 20:03
 */
public class RecommendFragment extends BaseFragment {
    private static final String TAG = RecommendFragment.class.getSimpleName();

    @Override
    protected View onSubViewLoaded(LayoutInflater layoutInflater, ViewGroup container) {
        //已经拿到View
        View view = layoutInflater.inflate(R.layout.fragment_recommend, container, false);
        //获取喜马拉雅接口数据
        getRecommendData();
        return view;
    }

    /**
     * 获取推荐内容，其实就是猜你喜欢
     */
    private void getRecommendData() {
        //封装参数
        Map<String, String> map = new HashMap<>();
        //这个参数表示一页数据返回多少条
        map.put(DTransferConstants.LIKE_COUNT, Constants.RECOMMEND_COUNT + "");
        CommonRequest.getGuessLikeAlbum(map, new IDataCallBack<GussLikeAlbumList>() {
            @Override
            public void onSuccess(@Nullable GussLikeAlbumList gussLikeAlbumList) {
                //数据获取成功
                if (gussLikeAlbumList != null) {
                    List<Album> albumList = gussLikeAlbumList.getAlbumList();
                    if (albumList != null) {
                        LogUtil.d(TAG, "size=" + albumList.size());
                    }
                }
            }

            @Override
            public void onError(int i, String s) {
                //数据获取出错
                LogUtil.e(TAG, "error:" + i);
                LogUtil.e(TAG, "error:msg=" + s);

            }
        });

    }
}
