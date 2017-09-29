package com.example.skinmanager_lib.skin.attr;

import android.view.View;

import java.util.List;

/**
 * Created by guoyang on 2017/9/28.
 * github https://github.com/GuoYangGit
 * QQ:352391291
 */

public class SkinView {
    private View mView;

    private List<SkinAttr> mSkinAttrs;

    public SkinView(View view, List<SkinAttr> skinAttrs) {
        this.mView = view;
        this.mSkinAttrs = skinAttrs;
    }

    public void skin(){
        for (SkinAttr attr : mSkinAttrs) {
            attr.skin(mView);
        }
    }
}
