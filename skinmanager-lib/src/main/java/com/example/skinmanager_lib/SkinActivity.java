package com.example.skinmanager_lib;

import android.content.Context;
import android.os.Build;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;

import com.example.skinmanager_lib.skin.SkinManager;
import com.example.skinmanager_lib.skin.SkinResource;
import com.example.skinmanager_lib.skin.attr.SkinAttr;
import com.example.skinmanager_lib.skin.attr.SkinView;
import com.example.skinmanager_lib.skin.callback.ISkinChangeListener;
import com.example.skinmanager_lib.skin.support.SkinAppCompatViewInflater;
import com.example.skinmanager_lib.skin.support.SkinAttrSupport;

import java.util.ArrayList;
import java.util.List;

public abstract class SkinActivity extends AppCompatActivity implements LayoutInflaterFactory,ISkinChangeListener{
    private SkinAppCompatViewInflater mAppCompatViewInflater;

    private static final String TAG = "BaseSkinActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        LayoutInflaterCompat.setFactory(layoutInflater,this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(View parent,String name, Context context, AttributeSet attrs) {
        View view = createview(parent,name,context,attrs);
        if (view != null){
            List<SkinAttr> skinAttrs = SkinAttrSupport.getSkinAttrs(context,attrs);
            SkinView skinView = new SkinView(view,skinAttrs);
            managerSkinView(skinView);
            SkinManager.getInstance().checkChangeSkin(skinView);
        }
        return view;
    }

    private void managerSkinView(SkinView skinView) {
        List<SkinView> skinViews = SkinManager.getInstance().getSkinViews(this);
        if (skinViews == null){
            skinViews = new ArrayList<>();
            SkinManager.getInstance().register(this,skinViews);
        }
        skinViews.add(skinView);
    }

    private View createview(View parent, String name, Context context, AttributeSet attrs) {
        final boolean isPre21 = Build.VERSION.SDK_INT < 21;

        if (mAppCompatViewInflater == null) {
            mAppCompatViewInflater = new SkinAppCompatViewInflater();
        }
        final boolean inheritContext = isPre21 && true
                && shouldInheritContext((ViewParent) parent);

        return mAppCompatViewInflater.createView(parent, name, context, attrs, inheritContext,
                isPre21,
                true
        );
    }

    private boolean shouldInheritContext(ViewParent parent) {
        if (parent == null) {
            return false;
        }
        while (true) {
            if (parent == null) {
                return true;
            } else if (parent == getWindow().getDecorView() || !(parent instanceof View)
                    || ViewCompat.isAttachedToWindow((View) parent)) {
                return false;
            }
            parent = parent.getParent();
        }
    }

    @Override
    public void changeSkin(SkinResource skinResource) {

    }

    @Override
    protected void onDestroy() {
        SkinManager.getInstance().unregister(this);
        super.onDestroy();
    }
}
