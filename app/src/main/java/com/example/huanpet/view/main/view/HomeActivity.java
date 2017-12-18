package com.example.huanpet.view.main.view;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huanpet.R;
import com.example.huanpet.app.BaseActivity;
import com.example.huanpet.entity.HomeBean;
import com.example.huanpet.utils.CustomTool;
import com.example.huanpet.utils.PreferencesUtil;
import com.example.huanpet.view.know.KnowActivity;
import com.example.huanpet.view.main.adapter.HomeListAdapter;
import com.example.huanpet.view.main.presenter.HomePresenter;
import com.example.huanpet.view.news.NewsActivity;
import com.example.huanpet.view.order.view.OrderActivity;
import com.example.huanpet.view.pet.PetActivity;
import com.example.huanpet.view.purse.PurseActivity;
import com.example.huanpet.view.set.SetActivity;
import com.example.huanpet.view.user.SignIn.SignInActivity;
import com.example.huanpet.view.user.activity.UserActivity;
import com.google.gson.Gson;
import com.zaaach.citypicker.CityPickerActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeActivity extends BaseActivity implements View.OnClickListener, IHomeView {

    private static final int REQUEST_CODE_PICK_CITY = 233;
    private LinearLayout main_user;
    private LinearLayout main_news;
    private LinearLayout main_pet;
    private LinearLayout main_order;
    private LinearLayout main_purse;
    private LinearLayout main_know;
    private LinearLayout main_set;
    private LinearLayout main_adduser;
    private Button main_btn;
    private CustomTool customTool;
    private SearchView searchView;
    private ImageView imageView;
    private DrawerLayout drawerLayout;
    private LinearLayout linearLayout;

    private RadioButton home_vicinity;
    private RadioButton home_pettype;
    private RadioButton home_screening;
    private RadioGroup home_group;
    private ListView home_list;
    private CheckBox screening_cityName;
    private HomePresenter homePresenter;
    private String userId;

    @Override
    public int initLayoutID() {
        return R.layout.activity_home;
    }

    //初始化空间
    @Override
    public void initView() {
        main_user = findViewById(R.id.main_user);
        main_news = findViewById(R.id.main_news);
        main_pet = findViewById(R.id.main_pet);
        main_order = findViewById(R.id.main_order);
        main_purse = findViewById(R.id.main_purse);
        main_know = findViewById(R.id.main_know);
        main_set = findViewById(R.id.main_set);
        main_adduser = findViewById(R.id.main_adduser);
        main_btn = findViewById(R.id.main_btn);
        customTool = findViewById(R.id.home_custom);
        searchView = findViewById(R.id.home_search);
        imageView = findViewById(R.id.home_spreads);
        drawerLayout = findViewById(R.id.home_drawer);
        linearLayout = findViewById(R.id.home_hides);
        home_group = findViewById(R.id.home_group);
        home_vicinity = findViewById(R.id.home_vicinity);
        home_pettype = findViewById(R.id.home_pettype);
        home_screening = findViewById(R.id.home_screening);
        home_list = findViewById(R.id.home_list);
        homePresenter = new HomePresenter(this);
        int mPage = 1;
        Map param = new HashMap();
        param.put("orderBy", "distance asc");
        param.put("coordX", 40.22);
        param.put("coordY", 116.23);
        param.put("beginIndex", (mPage - 1) * 10);
        param.put("endIndex", mPage * 10);
        homePresenter.getData("http://123.56.150.230:8885/dog_family/" + "users/getUsersInfoByVO.jhtml", param,0);

//        Log.e("Tat-------------",userId);

    }

    @Override
    protected void onResume() {
        super.onResume();
        userId = PreferencesUtil.getInstance().getUserId();
        if (!TextUtils.isEmpty(userId)) {
            main_adduser.setVisibility(View.GONE);
        } else {
            main_adduser.setVisibility(View.VISIBLE);
        }
    }

    //初始化适配器
    @Override
    public void initAdapter() {

    }

    //初始化数据
    @Override
    public void initData() {

    }

    //主页面监听
    @Override
    public void initListener() {
        main_user.setOnClickListener(this);
        main_news.setOnClickListener(this);
        main_pet.setOnClickListener(this);
        main_order.setOnClickListener(this);
        main_purse.setOnClickListener(this);
        main_know.setOnClickListener(this);
        main_set.setOnClickListener(this);
        main_adduser.setOnClickListener(this);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(linearLayout);
            }
        });
        home_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.home_vicinity:
                        if (home_vicinity.isChecked()) {
                            View vicinityView = LayoutInflater.from(HomeActivity.this).inflate(R.layout.vicinity_item, null);
                            initVicinityView(vicinityView);
                            initPopu(vicinityView);
                        }
                        break;
                    case R.id.home_pettype:
                        if (home_pettype.isChecked()) {
                            View pettypeView = LayoutInflater.from(HomeActivity.this).inflate(R.layout.pettype_item, null);
                            initPettypeView(pettypeView);
                            initPopu(pettypeView);
                        }
                        break;
                    case R.id.home_screening:
                        if (home_screening.isChecked()) {
                            View screeningView = LayoutInflater.from(HomeActivity.this).inflate(R.layout.screening_item, null);
                            initScreeningView(screeningView);
                            initPopu(screeningView);
                        }
                        break;
                }
            }
        });
    }

    //筛选布局控件初始化
    private void initScreeningView(View screeningView) {
        Button screening_determine = screeningView.findViewById(R.id.screening_determine);
        Button screening_reset = screeningView.findViewById(R.id.screening_reset);
        final CheckBox screening_guo_qing = screeningView.findViewById(R.id.screening_guo_qing);
        final CheckBox screening_zhong_qiu = screeningView.findViewById(R.id.screening_zhong_qiu);
        final CheckBox screening_duan_wu = screeningView.findViewById(R.id.screening_duan_wu);
        final CheckBox screening_lao_dong = screeningView.findViewById(R.id.screening_lao_dong);
        final CheckBox screening_qing_ming = screeningView.findViewById(R.id.screening_qing_ming);
        final CheckBox screening_chun_jie = screeningView.findViewById(R.id.screening_chun_jie);
        final CheckBox screening_yuan_dan = screeningView.findViewById(R.id.screening_yuan_dan);
        final CheckBox screening_pick_up = screeningView.findViewById(R.id.screening_pick_up);
        final CheckBox screening_bathe = screeningView.findViewById(R.id.screening_bathe);
        screening_cityName = screeningView.findViewById(R.id.screening_cityName);
        TextView screening_city = screeningView.findViewById(R.id.screening_city);
        initScreeningListener(screening_guo_qing);
        initScreeningListener(screening_zhong_qiu);
        initScreeningListener(screening_duan_wu);
        initScreeningListener(screening_lao_dong);
        initScreeningListener(screening_qing_ming);
        initScreeningListener(screening_chun_jie);
        initScreeningListener(screening_yuan_dan);
        initScreeningListener(screening_pick_up);
        initScreeningListener(screening_bathe);
        initScreeningListener(screening_cityName);

        //城市选择监听
        screening_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(HomeActivity.this, CityPickerActivity.class),
                        REQUEST_CODE_PICK_CITY);
            }
        });

        //确定监听
        screening_determine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //重置监听
        screening_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screening_guo_qing.setChecked(false);
                screening_zhong_qiu.setChecked(false);
                screening_duan_wu.setChecked(false);
                screening_lao_dong.setChecked(false);
                screening_qing_ming.setChecked(false);
                screening_chun_jie.setChecked(false);
                screening_yuan_dan.setChecked(false);
                screening_pick_up.setChecked(false);
                screening_bathe.setChecked(false);
                screening_cityName.setChecked(false);
            }
        });
    }

    private void initScreeningListener(CheckBox checkBox) {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(HomeActivity.this, buttonView.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //宠物类型布局控件初始化
    private void initPettypeView(View pettypeView) {
        RadioGroup pettype_group = pettypeView.findViewById(R.id.pettype_group);
        final RadioButton pettype_small = pettypeView.findViewById(R.id.pettype_small);
        RadioButton pettype_medium = pettypeView.findViewById(R.id.pettype_medium);
        RadioButton pettype_big = pettypeView.findViewById(R.id.pettype_big);
        RadioButton pettype_cat = pettypeView.findViewById(R.id.pettype_cat);
        RadioButton pettype_small_pet = pettypeView.findViewById(R.id.pettype_small_pet);
        RadioButton pettype_puppy = pettypeView.findViewById(R.id.pettype_puppy);
        pettype_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.pettype_small:
                        break;
                    case R.id.pettype_medium:
                        break;
                    case R.id.pettype_big:
                        break;
                    case R.id.pettype_cat:
                        break;
                    case R.id.pettype_small_pet:
                        break;
                    case R.id.pettype_puppy:
                        break;
                }
            }
        });
    }

    //附近布局控件初始化
    private void initVicinityView(View vicinityView) {
        RadioGroup vicinity_group = vicinityView.findViewById(R.id.vicinity_group);
        final RadioButton vicinity_near = vicinityView.findViewById(R.id.vicinity_near);
        final RadioButton vicinity_praise = vicinityView.findViewById(R.id.vicinity_praise);
        final RadioButton vicinity_order = vicinityView.findViewById(R.id.vicinity_order);
        final RadioButton vicinity_high = vicinityView.findViewById(R.id.vicinity_high);
        final RadioButton vicinity_low = vicinityView.findViewById(R.id.vicinity_low);
        vicinity_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.vicinity_near:
                        home_vicinity.setText(vicinity_near.getText());
                        break;
                    case R.id.vicinity_praise:
                        home_vicinity.setText(vicinity_praise.getText());
                        break;
                    case R.id.vicinity_order:
                        home_vicinity.setText(vicinity_order.getText());
                        break;
                    case R.id.vicinity_high:
                        home_vicinity.setText(vicinity_high.getText());
                        break;
                    case R.id.vicinity_low:
                        home_vicinity.setText(vicinity_low.getText());
                        break;
                }
            }
        });
    }

    //初始化PopupWindow
    private void initPopu(View vicinityView) {
//        vicinityView.getBackground().setAlpha(200);
        final PopupWindow popupWindow = new PopupWindow(vicinityView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        vicinityView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        popupWindow.setFocusable(true);
        popupWindow.setWidth(ViewGroup.LayoutParams.FILL_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.FILL_PARENT);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAsDropDown(home_vicinity, 0, 20);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        home_group.clearCheck();
                    }
                });
            }
        });
    }

    //设置自定义Toolbar
    @Override
    public void setMyAppTitle() {
        customTool.setRightIcon(R.drawable.map);
        customTool.initViewsVisible(false, false, true, false);
        customTool.setOnRightImgClickLisrener(new CustomTool.OnRightImgClickListener() {
            @Override
            public void onRightImgClick(View v) {

            }
        });
    }

    //侧滑界面监听
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        String userId = PreferencesUtil.getInstance().getUserId();
        int i = 0;

        switch (v.getId()) {

            case R.id.main_user:

                intent.setClass(HomeActivity.this, UserActivity.class);
                break;
            case R.id.main_news:
                intent.setClass(HomeActivity.this, NewsActivity.class);
                break;
            case R.id.main_adduser:
                i = 1;
                intent.setClass(HomeActivity.this, SignInActivity.class);
                break;
            case R.id.main_pet:
                intent.setClass(HomeActivity.this, PetActivity.class);
                break;
            case R.id.main_order:

                intent.setClass(HomeActivity.this, OrderActivity.class);
                break;

            case R.id.main_purse:
                intent.setClass(HomeActivity.this, PurseActivity.class);
                break;
            case R.id.main_know:
                i = 2;
                intent.setClass(HomeActivity.this, KnowActivity.class);
                break;
            case R.id.main_set:
                i = 3;
                intent.setClass(HomeActivity.this, SetActivity.class);
                break;
        }
        if (userId == null && i == 0) {
            Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
        } else if (i == 1 || i == 2 || i == 3) {
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PICK_CITY && resultCode == RESULT_OK) {
            if (data != null) {
                String city = data.getStringExtra(CityPickerActivity.KEY_PICKED_CITY);
                screening_cityName.setText(city);
            }
        }
    }


    @Override
    public void getData(String data, int i) {
        final String str = data;
        Log.e("TAG-----------", data);
        Gson gs = new Gson();
        HomeBean homeBean = gs.fromJson(str, HomeBean.class);
        List<HomeBean.DescBean> desc = homeBean.getDesc();
        HomeListAdapter homeListAdapter = new HomeListAdapter((ArrayList<HomeBean.DescBean>) desc, this);
        home_list.setAdapter(homeListAdapter);
    }
}
