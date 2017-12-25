package com.example.huanpet.view.pet;


import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.example.huanpet.R;
import com.example.huanpet.app.BaseActivity;
import com.example.huanpet.utils.OkhttpUtil;
import com.example.huanpet.utils.PreferencesUtil;
import com.example.huanpet.utils.util.AppUtils;
import com.example.huanpet.utils.util.CJSON;
import com.example.huanpet.utils.util.FileUtil;
import com.example.huanpet.utils.util.PetInfo;
import com.example.huanpet.utils.util.TableUtils;
import com.example.huanpet.utils.util.ToastUtil;
import com.example.huanpet.view.pet.UTILS.UploadUtil;
import com.example.huanpet.view.pet.View.PetImmuneInfo;

import com.example.huanpet.view.user.activity.UserActivity;
import com.example.huanpet.view.user.adpter.NumericWheelAdapter;
import com.example.huanpet.view.user.widget.WheelView;
import com.lzy.okhttputils.callback.StringCallback;
import com.lzy.okhttputils.request.PostRequest;

import java.io.File;

import java.io.IOException;
import java.text.BreakIterator;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import cn.qqtheme.framework.picker.DatePicker;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.example.huanpet.view.pet.bean.UserManager;

public class PetAddActivity extends BaseActivity implements View.OnClickListener {
    private PopupWindow popupWindow;
    private static final int PHOTO_REQUEST_CAREMA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果
    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";
    private View Mypup;
    private TextView phono_Album;
    private TextView take_photo;
    private Button dismiss;
    private CustomTextLayout App_title;
    private RelativeLayout pet;
    private RelativeLayout pet_name;
    private RelativeLayout pet_kind;
    private RelativeLayout sterilization;
    private RelativeLayout pet_Dateofbirth;
    private RelativeLayout pet_weight;
    private RelativeLayout pet_sick;
    private EditText pet_info;
    private ImageView imageView;
    private UserManager userManager;
    //宠物头像路径

    private View sexpup;
    /* 请求识别码 */
    private static final int CODE_GALLERY_REQUEST = 0;
    private static final int CODE_CAMERA_REQUEST = 1;
    private static final int CODE_RESULT_REQUEST = 2;
    //是否绝育


    //宠物信息
    private String petname;
    private String pettype;
    private String petimage;
    private String petweigth;
    private String petbirthDate;
    private String typename;
    //上传文件


    private Map<String, Object> map = new HashMap<>();
    private Map<String, File> iconMap;

    private String path;
    private View mPhotoView;
    private PopupWindow mPhotoPopWindow;
    private View mSelectPhoto;
    private View mMakePhoto;
    private View mCancelPhoto;
    private LinearLayout quan;
    private File tempFile;
    private ImageView iv_pet_head;
    private String yesOrNo;
    private boolean issterilization = false;
    private boolean mmune;
    private TextView mTV_pet_name, mTV_pet_kind, mTV_pet_sterilization, mTV_pet_birthDate, mTV_pet_Weigth, mTV_pet_Immune;
    private String petDate;
    private boolean isSterilization = false;
    private TextView bc;
    private PopupWindow window;
    private WheelView year;
    private WheelView month;
    private WheelView day;
    private TextView mBirth;
    private RelativeLayout aaa;
    private ImageView fh;
    private String aa;
    private Map<String, Object> mapp;
    private String PetCode;


    @Override
    public int initLayoutID() {
        return R.layout.activity_pet_add;
    }

    @Override
    public void initView() {

        iconMap = new HashMap<>();

        mBirth = (TextView) findViewById(R.id.mBirth);
        mTV_pet_name = (TextView) findViewById(R.id.tv_pet_nick);
        mTV_pet_kind = (TextView) findViewById(R.id.tv_pet_type);
        mTV_pet_sterilization = (TextView) findViewById(R.id.tv_pet_isPass);
        mTV_pet_birthDate = (TextView) findViewById(R.id.mBirth);
        mTV_pet_Weigth = (TextView) findViewById(R.id.tv_pet_weight);
        mTV_pet_Immune = (TextView) findViewById(R.id.immune_info);
        fh = (ImageView) findViewById(R.id.fh);

        //相册


        ;
        iv_pet_head = (ImageView) findViewById(R.id.iv_pet_head);


        quan = (LinearLayout) findViewById(R.id.quan);
        pet = (RelativeLayout) findViewById(R.id.bbb);
        aaa = (RelativeLayout) findViewById(R.id.aaa);
        pet_kind = (RelativeLayout) findViewById(R.id.add_pet_type);
        sterilization = (RelativeLayout) findViewById(R.id.add_pet_ispass);
        pet_Dateofbirth = (RelativeLayout) findViewById(R.id.add_pet_date);
        pet_weight = (RelativeLayout) findViewById(R.id.add_pet_weight);
        pet_sick = (RelativeLayout) findViewById(R.id.add_pet_immune);
        pet_info = (EditText) findViewById(R.id.pet_info);
        imageView = (ImageView) findViewById(R.id.iv_to_pet_head);
        bc = (TextView) findViewById(R.id.bc);
        pet.setOnClickListener(this);

        aaa.setOnClickListener(this);
        pet_kind.setOnClickListener(this);
        sterilization.setOnClickListener(this);
        pet_Dateofbirth.setOnClickListener(this);
        pet_weight.setOnClickListener(this);
        pet_sick.setOnClickListener(this);


    }



    @Override
    public void initAdapter() {

    }

    @Override
    public void initData() {

    }

    public void initListener() {

        bc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                PetInfo petinfo = new PetInfo();
                petinfo.setPetName(petname);
                petinfo.setPetBirthTime(petbirthDate);
                petinfo.setPetType(pettype);
                petinfo.setPetWeight(petweigth);
                petinfo.setPetInfo(pet_info.getText().toString().trim());
                petinfo.setPetType(typename);
                petinfo.setIsSterilization(issterilization ? 1 : 2);
                petinfo.setIsimmune(
                        mmune ? 1 : 0);
                petinfo.setCreateTime(new SimpleDateFormat("yyyy-MM-dd")
                        .format(new Date()));
                petinfo.setUserId(PreferencesUtil.getInstance().getUserId());
                petinfo.setUserName(PreferencesUtil.getInstance().getUserName());
                petinfo.setPetCode(PetCode);
                map.put("petInfo" + PreferencesUtil.getInstance().getUserId(), petinfo);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final String string = UploadUtil.uploadFile(iconMap,
                                CJSON.URL_STRING
                                        + "petInfo/savePetInfo.jhtml", map);
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(PetAddActivity.this,
                                        "修改成功", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(PetAddActivity.this, PetActivity.class);
                                startActivity(intent);


                            }
                        });


                    }
                }).start();
            }
        });


    }


    @Override
    public void setMyAppTitle() {

    }


    public void initData(String a) {
        String str = mTV_pet_Immune.getText().toString().trim();
        if (str == null) {
            mmune = false;
        } else {
            mmune = true;
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bbb:
                // addpeticon();
                tx();
                break;

            case R.id.aaa:
                Intent in = new Intent(PetAddActivity.this, nicknameActivity.class);
                startActivityForResult(in, 33);
                break;

            case R.id.add_pet_type:
                Intent intent = new Intent(this, TypeofpetActivity.class);
                startActivityForResult(intent, 44);
                break;
            case R.id.add_pet_ispass:
                //是否绝育
                addisSterilization();
                break;
            case R.id.add_pet_date:
                data();
                break;
            case R.id.add_pet_weight:
                Intent iii = new Intent(this, WeightActivity.class);
                startActivityForResult(iii, 55);
                break;
            case R.id.add_pet_immune:
                Intent iiii = new Intent(PetAddActivity.this, ImmuneActivity.class);
                startActivityForResult(iiii, 66);
                break;

        }
    }

    private void tx() {
        mPhotoView = LayoutInflater.from(PetAddActivity.this).inflate(R.layout.pop, null);
        mPhotoPopWindow = new PopupWindow(mPhotoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mSelectPhoto = mPhotoView.findViewById(R.id.btn_selectphoto);
        mMakePhoto = mPhotoView.findViewById(R.id.btn_makephoto);

        mCancelPhoto = mPhotoView.findViewById(R.id.btn_photocancel);

        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.5f;
        getWindow().setAttributes(params);

        mPhotoPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams params = getWindow().getAttributes();
                params.alpha = 1f;
                getWindow().setAttributes(params);
            }
        });

        mPhotoPopWindow.setBackgroundDrawable(new BitmapDrawable());

        mPhotoPopWindow.setFocusable(true);

        mPhotoPopWindow.showAtLocation(quan, Gravity.BOTTOM, 0, 0);

        mSelectPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gallery(v);

            }
        });

        mMakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                camera(v);
            }
        });

        mCancelPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CancelPhoto();
            }
        });
    }


    /*
     * 从相册获取
     */
    public void gallery(View view) {
        // 激活系统图库，选择一张图片
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
    }

    /*
     * 从相机获取
     */
    public void camera(View view) {
        // 激活相机
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        // 判断存储卡是否可以用，可用进行存储
        if (hasSdcard()) {
            tempFile = new File(Environment.getExternalStorageDirectory(), PHOTO_FILE_NAME);
            // 从文件中创建uri
            Uri uri = Uri.fromFile(tempFile);

            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CAREMA
        startActivityForResult(intent, PHOTO_REQUEST_CAREMA);
    }

    /*
     * 剪切图片
     */
    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);

        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    /*
     *   判断sdcard是否被挂载
     */
    private boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    private void CancelPhoto() {
        mPhotoPopWindow.dismiss();
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 1f;
        getWindow().setAttributes(params);
    }


    private void data() {
        View views = View.inflate(this, R.layout.datepicker_layout, null);

        window = new PopupWindow(views, android.support.v7.app.ActionBar.LayoutParams.MATCH_PARENT,
                ViewPager.LayoutParams.WRAP_CONTENT,
                true);
        window.setAnimationStyle(R.style.style_dialog);
//        views.getBackground().setAlpha(140);
        window.setBackgroundDrawable(new BitmapDrawable());
        //出现位置
        window.showAtLocation(pet, Gravity.BOTTOM, 0, 0);
        // 设置PopupWindow以外部分的背景颜色  有一种变暗的效果
        final WindowManager.LayoutParams wlBackground = getWindow().getAttributes();
        wlBackground.alpha = 0.5f;  // 0.0 完全不透明,1.0完全透明
        getWindow().setAttributes(wlBackground);
        //popuwindow消失时，恢复原来的颜色
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                wlBackground.alpha = 1.0f;
                getWindow().setAttributes(wlBackground);
            }
        });


        Calendar c = Calendar.getInstance();
        int curYear = c.get(Calendar.YEAR);
        int curMonth = c.get(Calendar.MONTH) + 1;//通过Calendar算出的月数要+1
        int curDate = c.get(Calendar.DATE);
        year = (WheelView) views.findViewById(R.id.year);
        initYear();
        month = (WheelView) views.findViewById(R.id.month);
        initMonth();
        day = (WheelView) views.findViewById(R.id.day);
        initDay(curYear, curMonth);


        year.setCurrentItem(curYear - 1950);
        month.setCurrentItem(curMonth - 1);
        day.setCurrentItem(curDate - 1);
        year.setVisibleItems(7);
        month.setVisibleItems(7);
        day.setVisibleItems(7);

        // 设置监听
        Button ok = (Button) views.findViewById(R.id.set);
        Button cancel = (Button) views.findViewById(R.id.cancel);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String str = String.format(Locale.CHINA, "%4d年%2d月%2d日", year.getCurrentItem() + 1950, month.getCurrentItem() + 1, day.getCurrentItem() + 1);
                mBirth.setText(str);
                String data = PreferencesUtil.getInstance().getBirthday();
                UpdateDate(data);
                window.dismiss();
            }

            private void UpdateDate(String data) {

                Map<String, Object> paramdata = new HashMap<>();
                paramdata.put(TableUtils.UserInfo.USERID, PreferencesUtil.getInstance().getUserId());
                paramdata.put(TableUtils.UserInfo.BIRTHDAY, data);
                // 生成提交服务器的JSON字符串
                String json = CJSON.toJSONMap(paramdata);
                FormBody.Builder builder = new FormBody.Builder();
//        builder.add(CJSON.DATA, data);
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder()
                        .post(builder.build())
                        .url("http://123.56.150.230:8885/dog_family/user/updateUserInfo.jhtml")
                        .build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String string = response.body().string();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.e("TAG", string);
                                if (CJSON.getRET(string)) {
                                    Toast.makeText(PetAddActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(PetAddActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });

    }

    /**
     * 初始化年
     */
    private void initYear() {
        NumericWheelAdapter numericWheelAdapter = new NumericWheelAdapter(this, 1950, 2050);
        numericWheelAdapter.setLabel(" 年");
        //		numericWheelAdapter.setTextSize(15);  设置字体大小
        year.setViewAdapter(numericWheelAdapter);
        year.setCyclic(true);
    }

    /**
     * 初始化月
     */
    private void initMonth() {
        NumericWheelAdapter numericWheelAdapter = new NumericWheelAdapter(this, 1, 12, "%02d");
        numericWheelAdapter.setLabel(" 月");
        //		numericWheelAdapter.setTextSize(15);  设置字体大小
        month.setViewAdapter(numericWheelAdapter);
        month.setCyclic(true);
    }

    /**
     * 初始化天
     */
    private void initDay(int arg1, int arg2) {
        NumericWheelAdapter numericWheelAdapter = new NumericWheelAdapter(this, 1, getDay(arg1, arg2), "%02d");
        numericWheelAdapter.setLabel(" 日");
        //		numericWheelAdapter.setTextSize(15);  设置字体大小
        day.setViewAdapter(numericWheelAdapter);
        day.setCyclic(true);
    }

    /**
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


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) { // 如果返回码是可以用的
            switch (requestCode) {
                case CODE_GALLERY_REQUEST:
                    // 开始对图片进行裁剪处理  //从相册中获取
                    crop(data.getData());

                    break;
                case CODE_CAMERA_REQUEST:
                    File temp = new File(Environment.getExternalStorageDirectory()
                            + "/xiaoma.jpg");

                    crop(Uri.fromFile(temp));

                    break;
                case CODE_RESULT_REQUEST:
                    if (data != null) {
                        // 让刚才选择裁剪得到的图片显示在界面上
                        setPicToView(data);
                        Toast.makeText(PetAddActivity.this, "214142", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
            if (requestCode == PHOTO_REQUEST_GALLERY) {
                // 从相册返回的数据
                if (data != null) {
                    // 得到图片的全路径
                    Uri uri = data.getData();
                    crop(uri);
                }

            } else if (requestCode == PHOTO_REQUEST_CAREMA) {
                // 从相机返回的数据
                if (hasSdcard()) {
                    crop(Uri.fromFile(tempFile));
                } else {
                    Toast.makeText(PetAddActivity.this, "未找到存储卡，无法存储照片！", Toast.LENGTH_SHORT).show();
                }

            } else if (requestCode == PHOTO_REQUEST_CUT) {
                // 从剪切图片返回的数据
                if (data != null) {
                    //获取Bitmap图片
                    Bitmap bitmap = data.getParcelableExtra("data");
                    //剪切图片
                    uploadPic(bitmap);
                    Bitmap photo = ImageUtils.toRoundBitmap(bitmap);// 这个时候的图片已经被处理成圆形的了
                    //设置图片
                    iv_pet_head.setImageBitmap(photo);
                    //popupwindow消失,系统背景颜色改变
                    mPhotoPopWindow.dismiss();
                    WindowManager.LayoutParams params = getWindow().getAttributes();
                    params.alpha = 1f;
                    getWindow().setAttributes(params);
                    Toast.makeText(this, "已提交申请，待审核", Toast.LENGTH_SHORT).show();
                }
                /*try {
                    // 将临时文件删除
                    tempFile.delete();
                } catch (Exception e) {
                    e.printStackTrace();*/
            }

        }

        if (requestCode == 33 && resultCode == RESULT_OK) {
            String name = data.getStringExtra("name");
            mTV_pet_name.setText(name);
            petname = name;
        }
        if (requestCode == 44 && resultCode == RESULT_OK) {
            typename = data.getStringExtra(TableUtils.PetInfo.PETNAME);
            pettype = data.getStringExtra(TableUtils.PetInfo.PETTYPE);
            mTV_pet_kind.setText(typename);

        }
        if (requestCode == 55 && resultCode == RESULT_OK) {
            petweigth = data.getStringExtra("name");
            mTV_pet_Weigth.setText(petweigth);
        }
        if (requestCode == 66 && resultCode == RESULT_OK) {

            aa = data.getStringExtra("list");

            mTV_pet_Immune.setText("已免疫");
        }
        super.onActivityResult(requestCode, resultCode, data);


    }

    private void uploadPic(Bitmap bitmap) {

    }

    /*
       添加体重
     */


    /*
       是否绝育
     */
    private void addisSterilization() {
        View contentView = LayoutInflater.from(this).inflate(
                R.layout.pop_window_ispass, null);
        final PopupWindow popupWindow = new PopupWindow(contentView,
                ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT, true);
        // 绑定布局
        popupWindow.setContentView(contentView);

        TextView tv_yes = (TextView) contentView.findViewById(R.id.pop_yes);
        TextView tv_no = (TextView) contentView.findViewById(R.id.pop_no);

        View rootview = LayoutInflater.from(this).inflate(
                R.layout.activity_pet_add, null);
        popupWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
        tv_yes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                yesOrNo = "是";
                isSterilization = true;
                mTV_pet_sterilization.setText(yesOrNo);
                popupWindow.dismiss();
            }
        });
        tv_no.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                yesOrNo = "否";
                isSterilization = false;
                mTV_pet_sterilization.setText(yesOrNo);
                popupWindow.dismiss();
            }
        });
        fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    public void setPicToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            //图片路径
            Bitmap bitmap = ImageUtils.toRoundBitmap(photo);
            imageView.setImageBitmap(bitmap);
        }
    }
}