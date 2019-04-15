package com.example.brett_zhu.himalaya.interfaces;

import com.ximalaya.ting.android.opensdk.model.album.Album;
import com.ximalaya.ting.android.opensdk.model.track.Track;

import java.util.List;

/**
 * @author brett-zhu
 * created at 2019/4/15 17:46
 */
public interface IAlbumDetailViewCallback {
    /**
     * 专辑详情内容加载出来
     * @param tracks
     */
    void onDetailListLoaded(List<Track> tracks);

    /**
     * 把album传给UI
     * @param album 专辑封面
     */
    void onAlbumLoaded(Album album);
}
