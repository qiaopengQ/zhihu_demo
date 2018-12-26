package com.example.map.zhihu.base.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class SimpleActivity extends AppCompatActivity{

    private Unbinder bind;
    private Activity activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(createLayoutId());
        bind = ButterKnife.bind(this);
        activity = this;
        viewCreated();
        initData();
    }
    //初始化数据
    protected abstract void initData();

    public void viewCreated(){

    }
    protected void setToolBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
    //初始化布局
    public abstract int createLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bind !=null){
            bind.unbind();
        }
    }

}
