package com.example.guoyang.skinutils;

import android.app.Application;

import com.example.skinmanager_lib.skin.SkinManager;

/**
 * Created by guoyang on 2017/9/28.
 * github https://github.com/GuoYangGit
 * QQ:352391291
 */

public class BaseApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        SkinManager.getInstance().init(this);
    }
}
