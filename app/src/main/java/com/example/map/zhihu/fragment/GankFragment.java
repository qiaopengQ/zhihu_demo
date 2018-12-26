package com.example.map.zhihu.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.map.zhihu.R;

/**
 * 干货集中营
 * A simple {@link Fragment} subclass.
 */
public class GankFragment extends Fragment {


    public GankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gank, container, false);
        return view;
    }

}
