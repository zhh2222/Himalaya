package com.example.brett_zhu.himalaya.base;

import android.app.Application;

import com.example.brett_zhu.himalaya.utils.LogUtil;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;

/**
 * @author brett-zhu
 * created at 2019/4/14 16:01
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CommonRequest mXimalaya = CommonRequest.getInstanse();
        if(DTransferConstants.isRelease) {
            String mAppSecret = "b49c525cefef254bdc2b6590d8aa3ffd";
            mXimalaya.setAppkey("1b740ccca78c7bbd99a3e3f9994837f3");
            mXimalaya.setPackid("com.ivi.audiobook");
            mXimalaya.init(this ,mAppSecret);
        } else {
            String mAppSecret = "0a09d7093bff3d4947a5c4da0125972e";
            mXimalaya.setAppkey("f4d8f65918d9878e1702d49a8cdf0183");
            mXimalaya.setPackid("com.ximalaya.qunfeng");
            mXimalaya.init(this ,mAppSecret);
        }
        //初始化LogUtil
        LogUtil.init(this.getPackageName(),false);
    }
}
