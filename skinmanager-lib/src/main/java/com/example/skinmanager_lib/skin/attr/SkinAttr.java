package com.example.skinmanager_lib.skin.attr;

import android.view.View;

/**
 * Created by guoyang on 2017/9/28.
 * github https://github.com/GuoYangGit
 * QQ:352391291
 */

public class SkinAttr {
    private String mResName;
    private SkinType mSkinType;

    public SkinAttr(String resName, SkinType skinType) {
        this.mResName = resName;
        this.mSkinType = skinType;
    }

    public void skin(View view) {
        mSkinType.skin(view,mResName);
    }
}
