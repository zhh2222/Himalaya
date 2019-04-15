package com.example.brett_zhu.himalaya.presenters;

import android.util.Log;

import com.example.brett_zhu.himalaya.interfaces.IAlbumDetailPresenter;
import com.example.brett_zhu.himalaya.interfaces.IAlbumDetailViewCallback;
import com.ximalaya.ting.android.opensdk.model.album.Album;

import java.util.ArrayList;
import java.util.List;

/**
 * @author brett-zhu
 * created at 2019/4/15 17:42
 */
public class AlbumDetailPresenterImpl implements IAlbumDetailPresenter {

    private static final String TAG = AlbumDetailPresenterImpl.class.getSimpleName();
    private Album mTargetAlbum = null;

    private List<IAlbumDetailViewCallback> mCallbacks = new ArrayList<>();

    private AlbumDetailPresenterImpl() {
    }

    private static final class Holder {
        private static final AlbumDetailPresenterImpl INSTANCE = new AlbumDetailPresenterImpl();
    }

    public static AlbumDetailPresenterImpl getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public void pull2RefreshMore() {

    }

    @Override
    public void loadMore() {

    }

    @Override
    public void getAlbumDetail(int albumId, int page) {

    }

    @Override
    public void registerViewCallback(IAlbumDetailViewCallback detailViewCallback) {
        if (mCallbacks != null && !mCallbacks.contains(detailViewCallback)) {
            mCallbacks.add(detailViewCallback);
            if (mTargetAlbum != null) {
                detailViewCallback.onAlbumLoaded(mTargetAlbum);
            }
        }
    }

    @Override
    public void unRegisterViewCallback(IAlbumDetailViewCallback detailViewCallback) {
        mCallbacks.remove(detailViewCallback);
    }

    public void setTargetAlbum(Album targetAlbum) {
        this.mTargetAlbum = targetAlbum;
    }
}
