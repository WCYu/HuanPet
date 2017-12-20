package com.example.huanpet.view.pet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.huanpet.R;
import com.example.huanpet.view.pet.View.WheelView;
import com.example.huanpet.view.pet.Wheel.OnWheelScrollListener;
import com.example.huanpet.view.pet.Wheel.WheelViewAdapter;
import com.example.huanpet.view.user.adpter.NumericWheelAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class SeclectorDateActivity extends AppCompatActivity implements View.OnClickListener{
    private WheelView year;
    private WheelView month;
    private WheelView day;

    private int mYear = Integer.valueOf(new SimpleDateFormat("yyyy")
            .format(new Date()));
    private int mMonth = 0;
    private int mDay = 1;
    private Button btnCancel;
    private Button btnSubmit;
    private TextView tvTitle;
    private RelativeLayout rl;
    private static OnGetDateListener listener;
    private static String title;

    LinearLayout ll;

    View view = null;

    boolean isMonthSetted = false, isDaySetted = false;

    private String date = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getDataPick());


    }
    private View getDataPick() {
        Calendar c = Calendar.getInstance();
        int norYear = c.get(Calendar.YEAR);
        int curMonth = c.get(Calendar.MONTH) + 1;// 通过Calendar算出的月数要+1
        int curDate = c.get(Calendar.DATE);

        int curYear = mYear;

        view = getLayoutInflater().inflate(R.layout.wheel_date_picker, null);
        rl = (RelativeLayout) view.findViewById(R.id.date_root);
        year = (WheelView) view.findViewById(R.id.year);
        NumericWheelAdapter numericWheelAdapter1 = new NumericWheelAdapter(
                this, 1975, 2099);
        numericWheelAdapter1.setLabel("年");
        year.setViewAdapter((WheelViewAdapter) numericWheelAdapter1);
        year.setCyclic(true);// 是否可循环滑动
        year.addScrollingListener(scrollListener);
        rl.setOnClickListener(SeclectorDateActivity.this);

        month = (WheelView) view.findViewById(R.id.month);
        NumericWheelAdapter numericWheelAdapter2 = new NumericWheelAdapter(
                this, 1, 12, "%02d");
        numericWheelAdapter2.setLabel("月");
        month.setViewAdapter((WheelViewAdapter) numericWheelAdapter2);
        month.setCyclic(true);
        month.addScrollingListener(scrollListener);

        day = (WheelView) view.findViewById(R.id.day);
        initDay(curYear, curMonth);
        day.setCyclic(true);
        day.addScrollingListener(scrollListener);

        year.setVisibleItems(7);// 设置显示行数
        month.setVisibleItems(7);
        day.setVisibleItems(7);

        year.setCurrentItem(curYear - 1975);
        Log.e("CXP", "=======" + (curYear - 1975));
        month.setCurrentItem(curMonth - 1);
        day.setCurrentItem(curDate - 1);
        btnCancel = (Button) view.findViewById(R.id.btnCancel);
        tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        btnSubmit = (Button) view.findViewById(R.id.btnSubmit);
        tvTitle.setText(TextUtils.isEmpty(title) ? "选择时间" : title);
        btnCancel.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        scrollListener.onScrollingFinished(year);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.date_root:
            case R.id.btnCancel:
                finish();
                break;
            case R.id.btnSubmit:
                listener.dateChange(date);
                finish();
                break;
        }
    }
    OnWheelScrollListener scrollListener = new OnWheelScrollListener() {
        @Override
        public void onScrollingStarted(WheelView wheel) {
            date = new StringBuilder()
                    .append((year.getCurrentItem() + 1975))
                    .append("-")
                    .append((month.getCurrentItem() + 1) < 10 ? "0"
                            + (month.getCurrentItem() + 1) : (month
                            .getCurrentItem() + 1))
                    .append("-")
                    .append(((day.getCurrentItem() + 1) < 10) ? "0"
                            + (day.getCurrentItem() + 1) : (day
                            .getCurrentItem() + 1)).toString();
        }

        @Override
        public void onScrollingFinished(WheelView wheel) {
            int n_year = year.getCurrentItem() + 1975;// 年
            int n_month = month.getCurrentItem() + 1;// 月
            initDay(n_year, n_month);
            date = new StringBuilder()
                    .append((year.getCurrentItem() + 1975))
                    .append("-")
                    .append((month.getCurrentItem() + 1) < 10 ? "0"
                            + (month.getCurrentItem() + 1) : (month
                            .getCurrentItem() + 1))
                    .append("-")
                    .append(((day.getCurrentItem() + 1) < 10) ? "0"
                            + (day.getCurrentItem() + 1) : (day
                            .getCurrentItem() + 1)).toString();
            // int y = year.getCurrentItem()+1975;
            // int m = month.getCurrentItem();
            // int d = day.getCurrentItem();
            // date = y + "年" + m + "月" + d + "日";
        }
    };

    /**
     *
     * @param year
     * @param month
     * @return
     */
    private int getDay(int year, int month) {
        int day = 30;
        boolean flag = false;
        switch (year % 4) {
            case 0:
                flag = true;
                break;
            default:
                flag = false;
                break;
        }
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                day = 31;
                break;
            case 2:
                day = flag ? 29 : 28;
                break;
            default:
                day = 30;
                break;
        }
        return day;
    }

    /**
     */
    private void initDay(int arg1, int arg2) {
        NumericWheelAdapter numericWheelAdapter = new NumericWheelAdapter(this,
                1, getDay(arg1, arg2), "%02d");
        numericWheelAdapter.setLabel("日");
        day.setViewAdapter((WheelViewAdapter) numericWheelAdapter);
    }

    public static void startActivity(Context context, String tit,
                                     OnGetDateListener dateListener) {
        listener = dateListener;
        title = tit;
        context.startActivity(new Intent(context, SeclectorDateActivity.class));
    }

    public interface OnGetDateListener {
        void dateChange(String date);
    }
}
