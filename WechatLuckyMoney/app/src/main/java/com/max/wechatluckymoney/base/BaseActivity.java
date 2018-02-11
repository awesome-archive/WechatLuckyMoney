package com.max.wechatluckymoney.base;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import butterknife.ButterKnife;

/**
 * Created by max on 2018/2/9.
 */
public abstract class BaseActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        try
        {
            setContentView(getLayoutResId());
            onInitialize();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID)
    {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    public void setContentView(View view)
    {
        super.setContentView(view);
        ButterKnife.bind(this);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params)
    {
        super.setContentView(view, params);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy()
    {
        ButterKnife.unbind(this);
        super.onDestroy();
    }


    /**
     * 初始化
     */
    protected abstract void onInitialize();

    /**
     * 布局资源ID
     *
     * @return
     */
    protected abstract int getLayoutResId();


    /**
     * 设置 状态栏 颜色
     * @param colorResId
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected void setStatusBarColor(@ColorRes int colorResId)
    {
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)
        {
            return;
        }

        Window window = this.getWindow();

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        window.setStatusBarColor(getResources().getColor(colorResId));
    }

}
