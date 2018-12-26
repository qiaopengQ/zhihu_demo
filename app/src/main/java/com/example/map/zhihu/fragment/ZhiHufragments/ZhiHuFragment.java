package com.example.map.zhihu.fragment.ZhiHufragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.map.zhihu.R;
import com.example.map.zhihu.adapter.ZhiHuiFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZhiHuFragment extends Fragment {

    private TabLayout tab;
    private ViewPager vp;
    private List<String> list = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zhi_hu, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        tab = (TabLayout) view.findViewById(R.id.tab);
        vp = (ViewPager) view.findViewById(R.id.vp);

        list.add("日报");
        list.add("主题");
        list.add("专栏");
        list.add("热门");
        ZhiHu_AFragment zhiHu_aFragment = new ZhiHu_AFragment();
        ZhiHu_BFragment zhiHu_bFragment = new ZhiHu_BFragment();
        ZhiHu_CFragment zhiHu_cFragment = new ZhiHu_CFragment();
        ZhiHu_DFragment zhiHu_dFragment = new ZhiHu_DFragment();
        fragments.add(zhiHu_aFragment);
        fragments.add(zhiHu_bFragment);
        fragments.add(zhiHu_cFragment);
        fragments.add(zhiHu_dFragment);
        ZhiHuiFragmentAdapter zhiHuiFragmentAdapter = new ZhiHuiFragmentAdapter(getChildFragmentManager(),fragments,list);
        vp.setAdapter(zhiHuiFragmentAdapter);
        tab.setupWithViewPager(vp);
    }
}
