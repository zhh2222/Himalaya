package com.example.brett_zhu.himalaya.fragment;

import android.content.Intent;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.brett_zhu.himalaya.DetailActivity;
import com.example.brett_zhu.himalaya.R;
import com.example.brett_zhu.himalaya.adapter.RecommendListAdapter;
import com.example.brett_zhu.himalaya.base.BaseFragment;
import com.example.brett_zhu.himalaya.interfaces.IRecommendViewCallBack;
import com.example.brett_zhu.himalaya.presenters.AlbumDetailPresenterImpl;
import com.example.brett_zhu.himalaya.presenters.RecommendPresenter;
import com.example.brett_zhu.himalaya.views.UILoader;
import com.ximalaya.ting.android.opensdk.model.album.Album;

import net.lucode.hackware.magicindicator.buildins.UIUtil;

import java.util.List;

/**
 * @author brett-zhu
 * created at 2019/4/14 20:03
 */
public class RecommendFragment extends BaseFragment implements IRecommendViewCallBack, UILoader.OnRetryClickListener, RecommendListAdapter.OnClickItemListener {
    private static final String TAG = RecommendFragment.class.getSimpleName();
    private View mRootView;
    private RecyclerView mRecommendRv;
    private RecommendListAdapter mListAdapter;
    private RecommendPresenter mRecommendPresenter;
    private UILoader mUiLoader;

    @Override
    protected View onSubViewLoaded(final LayoutInflater layoutInflater, final ViewGroup container) {

        mUiLoader = new UILoader(getContext()) {
            @Override
            protected View getSuccessView(ViewGroup container) {
                return createSuccessView(layoutInflater,container);
            }
        };
        //获取到逻辑层的对象
        mRecommendPresenter = RecommendPresenter.getInstance();
        //先要设置通知接口的注册
        mRecommendPresenter.registerViewCallback(this);

        if (mUiLoader.getParent() instanceof ViewGroup) {
            ((ViewGroup) mUiLoader.getParent()).removeView(mUiLoader);
        }

        mUiLoader.setRetryClickListener(this);
        //
        mRecommendPresenter.getRecommendList();
        return mUiLoader;
    }

    private View createSuccessView(LayoutInflater layoutInflater, ViewGroup container) {
        //已经拿到View
        mRootView = layoutInflater.inflate(R.layout.fragment_recommend, container, false);
        mRecommendRv = mRootView.findViewById(R.id.recommend_list);
        mRecommendRv.setLayoutManager(new LinearLayoutManager(container.getContext(), LinearLayoutManager.VERTICAL, false));
        mListAdapter = new RecommendListAdapter();
        mRecommendRv.setAdapter(mListAdapter);
        mRecommendRv.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.top = UIUtil.dip2px(view.getContext(), 5);
                outRect.bottom = UIUtil.dip2px(view.getContext(), 5);
                outRect.left = UIUtil.dip2px(view.getContext(), 5);
                outRect.right = UIUtil.dip2px(view.getContext(), 5);
            }
        });
        mListAdapter.setClickItemListener(this);
        return mRootView;
    }

    @Override
    public void onRecommendListLoaded(List<Album> result) {
        //当我们获取到推荐内容的时候，这个方法就被调用（成功了）
        //数据拿到以后，就是更新UI了
        //把数据设置给适配器并且更新UI
        mListAdapter.setData(result);
        mUiLoader.updateStatus(UILoader.UIStatus.SUCCESS);
    }

    @Override
    public void onNetworkError() {
        mUiLoader.updateStatus(UILoader.UIStatus.NETWORK_ERROR);
    }

    @Override
    public void onEmpty() {
        mUiLoader.updateStatus(UILoader.UIStatus.EMPTY);
    }

    @Override
    public void onLoading() {
        mUiLoader.updateStatus(UILoader.UIStatus.LOADING);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //取消接口的注册
        if (mRecommendPresenter != null) {
            mRecommendPresenter.unRegisterViewCallback(this);
        }
    }

    @Override
    public void onRetryClick() {
        //表示网络不佳的时候用户点击了重试
        if (mRecommendPresenter != null) {
            mRecommendPresenter.getRecommendList();
        }
    }

    @Override
    public void onClickItem(int position, Album album) {
        AlbumDetailPresenterImpl.getInstance().setTargetAlbum(album);
        //根据位置拿到数据
        Intent intent = new Intent(getContext(), DetailActivity.class);
        startActivity(intent);
    }
}
