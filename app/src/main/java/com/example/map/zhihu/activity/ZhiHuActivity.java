package com.example.map.zhihu.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.map.zhihu.R;
import com.example.map.zhihu.base.activity.SimpleActivity;
import com.example.map.zhihu.utils.DateUtil;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZhiHuActivity extends SimpleActivity {

    @BindView(R.id.view_calender)
    MaterialCalendarView viewCalender;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_calender_enter)
    TextView tvCalenderEnter;
    CalendarDay data;
    @Override
    protected void initData() {
        setToolBar(toolbar, "选择日期");
        viewCalender.state().edit()
                .setFirstDayOfWeek(Calendar.WEDNESDAY)
                .setMinimumDate(CalendarDay.from(2013, 5, 20))
                .setMaximumDate(CalendarDay.from(DateUtil.getCurrentYear(), DateUtil.getCurrentMonth(), DateUtil.getCurrentDay()))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();
        viewCalender.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                data= date;
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(this, "回退按钮", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int createLayoutId() {
        return R.layout.activity_zhi_hu;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_calender_enter)
    public void onViewClicked() {
        if (data!=null){
            EventBus.getDefault().post(data);
        }
        finish();
    }
}
