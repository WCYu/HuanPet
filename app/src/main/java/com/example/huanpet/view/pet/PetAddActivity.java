package com.example.huanpet.view.pet;


import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
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
import com.example.huanpet.R;
import com.example.huanpet.app.BaseActivity;
import com.example.huanpet.utils.PreferencesUtil;
import com.example.huanpet.utils.util.AppUtils;
import com.example.huanpet.utils.util.CJSON;
import com.example.huanpet.utils.util.PetInfo;
import com.example.huanpet.utils.util.TableUtils;
import com.example.huanpet.utils.util.ToastUtil;
import com.example.huanpet.view.pet.UTILS.UploadUtil;
import com.example.huanpet.view.pet.View.PetImmuneInfo;
import com.lzy.okhttputils.callback.StringCallback;
import com.lzy.okhttputils.request.PostRequest;

import java.io.File;

import java.text.BreakIterator;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.qqtheme.framework.picker.DatePicker;
import okhttp3.Request;
import okhttp3.Response;


public class PetAddActivity extends BaseActivity implements View.OnClickListener {

    private Context context;
    private LinearLayout mLine, mLineIcon, mLineName, mLineSex, mLineBirth, mLinePhone;
    private LinearLayout mLineWeiXin, mLineQQ, mLineAddress;

    private PopupWindow mPhotoPopWindow, mSexPopWindow;
    private Button mSelectPhoto, mMakePhoto, mCancelPhoto;
    private static final int PHOTO_REQUEST_CAREMA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果
    /* 头像名称 */
    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";

    private ImageView mIcon;

    private RadioGroup mRgSex;
    private RadioButton mBoy, mGirl;
    private TextView mTv_immune, mTv_pet_nick, mTv_pet_weight, mTv_pet_date,
            mTv_pet_type, mTv_pet_sex, mTv_pet_isPass;
    private TextView btOk;

    // 得到昵称的返回值
    private String resultNick;
    // 得到体重的返回值
    private String resultWeight;
    // 宠物类型
    private String petType;
    // 出生日期
    private String petDate;
    // 是否绝育
    private String yesOrNo;
    // 宠物简介
    private String petInfo;
    // 免疫信息
    private boolean isImmune = false;
    // 是否绝育
    private boolean isSterilization = false;
    // 病毒类型
    private String str = "已免疫";
    // 图片路径
    private String imgPath;

    private Map<String, File> iconMap;

    private int isSex = 2;

    private String typeCode;

    private Map<String, Object> map = new HashMap<>();
    private EditText mEd_petinfo;
    private LinearLayout quan;
    private View mPhotoView;
    private File tempFile;
    private ImageView head;
    private TextView mBirth;

    // 病毒图片

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        btOk = (TextView) findViewById(R.id.bc);

        mTv_immune = (TextView) findViewById(R.id.immune_info);
        mTv_pet_nick = (TextView) findViewById(R.id.tv_pet_nick);
        mTv_pet_weight = (TextView) findViewById(R.id.tv_pet_weight);
        head = (ImageView) findViewById(R.id.iv_pet_head);
        mTv_pet_type = (TextView) findViewById(R.id.tv_pet_type);
        quan = (LinearLayout) findViewById(R.id.quan);
        mBirth = (TextView) findViewById(R.id.mBirth);
        mTv_pet_isPass = (TextView) findViewById(R.id.tv_pet_isPass);
        mEd_petinfo = (EditText) findViewById(R.id.pet_info);
        iconMap = new HashMap<String, File>();


        btOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                    PetInfo pet = new PetInfo();
                    pet.setPetName(resultNick);
                    pet.setPetType(typeCode);
                    Log.i("TAG", typeCode + "code3");
                    pet.setIsSterilization(isSterilization ? 1 : 2);
                    pet.setPetSex(isSex);
                    pet.setPetBirthTime(petDate);
                    pet.setCreateTime(new SimpleDateFormat("yyyy-MM-dd")
                            .format(new Date()));
                    pet.setPetWeight(Double.parseDouble(resultWeight));
                    pet.setPetInfo(petInfo);
                    pet.setPetTypeName(petType);
                    pet.setIsimmune(isImmune ? 1 : 0);
                    pet.setUserId(PreferencesUtil.getInstance().getUserId());
                    pet.setUserName(AppUtils.getUser().getUserName());

                    // final Map<String, Object> param = new HashMap<String,
                    // Object>();
                    map.put("petInfo-" + PreferencesUtil.getInstance().getUserId(), pet);
                    new Thread(new Runnable() {

                        @Override
                        public void run() {

                            String string = UploadUtil.uploadFile(iconMap,
                                    AppUtils.REQUESTURL
                                            + "petInfo/savePetInfo.jhtml", map);
                            Log.i("TAG", string);
                            runOnUiThread(new Runnable() {
                                @SuppressLint("WrongConstant")
                                public void run() {
                                    Toast.makeText(PetAddActivity.this, "添加成功",
                                            0).show();

                                }
                            });
                            finish();
                        }
                    }).start();
                }


        });
    }


 /*  public boolean noNull() {
        petInfo = mEd_petinfo.getText().toString().trim();
        String petImmune = mTv_immune.getText().toString().trim();
        if (petImmune.equals("未完善信息")) {
            isImmune = false;
        } else {
            isImmune = true;

        }
        if (resultNick == null || resultNick.isEmpty()) {
            ToastUtil.show("请填写宠物昵称");
            return false;
        }
        if (resultWeight == null || resultWeight.isEmpty()) {
            ToastUtil.show("请填写宠物体重");
            return false;
        }
        if (petType == null || petType.isEmpty()) {
            ToastUtil.show("请填写宠物类型");
            return false;
        }
        if (petDate == null || petDate.isEmpty()) {
            ToastUtil.show("请填写宠物生日");
            return false;
        }
        if (yesOrNo == null || yesOrNo.isEmpty()) {
            ToastUtil.show("请填写宠物是否绝育");
            return false;
        }
        if (petInfo == null || petInfo.isEmpty()) {
            ToastUtil.show("请填写宠物简介");
            return false;
        }
        if (isSex == 2) {
            ToastUtil.show("请填写宠物简介");
            return false;
        }
        return true;
    }*/

    public void click(View view) {
        switch (view.getId()) {
            // 选择图片
            case R.id.add_pet_icon:
                addpeticon();
                break;
            // 选择昵称
            case R.id.add_pet_nick:
                addpetnick();
                break;
            // 选择类型
            case R.id.add_pet_type:
                addPetType();
                break;
            // 是否绝育
            case R.id.add_pet_ispass:
                addpetispass();
                break;
            // 出生日期
            case R.id.add_pet_date:
                addpetdate();
                break;
            // 体重
            case R.id.add_pet_weight:
                addpetweight();
                break;
            // 免疫情况
            case R.id.add_pet_immune:
                addpetimmune();
                break;



        }
    }
    private void addpetimmune() {

        if ("".equals(mTv_pet_nick.getText().toString().trim())) {
            ToastUtil.show("请选输入宠物名称!");

        }
        Intent weightIntent = new Intent(this, ImmuneActivity.class);

        startActivityForResult(weightIntent, 33);
        return;
        }


    private void addPetType() {
        Intent weightIntent = new Intent(this, TypeofpetActivity.class);
        startActivityForResult(weightIntent, 443);

    }


    private void addpetweight() {
        Intent weightIntent = new Intent(this, WeightActivity.class);
        startActivityForResult(weightIntent, 22);
    }


    private void addpetdate() {
        DatePicker picker = new DatePicker(PetAddActivity.this);
        picker.setRange(1990, 2030);//年份范围
        picker.setSubmitTextColor(Color.BLUE);
        picker.setCancelTextColor(Color.BLUE);
        picker.setTextColor(Color.BLACK);
        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                if (!year.isEmpty() || !month.isEmpty() || !day.isEmpty()) {
                    Toast.makeText(PetAddActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                    mBirth.setText(year + "年" + month + "月" + day + "日");
                } else {
                    Toast.makeText(PetAddActivity.this, "网络不佳,请稍后重试", Toast.LENGTH_SHORT).show();
                }

            }
        });
        picker.show();
    }


    private void addpetnick() {
        Intent nickIntent = new Intent(this, nicknameActivity.class);
        startActivityForResult(nickIntent, 10);
    }


    private void addpeticon() {
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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
                head.setImageBitmap(photo);
                //popupwindow消失,系统背景颜色改变
                mPhotoPopWindow.dismiss();
                WindowManager.LayoutParams params = getWindow().getAttributes();
                params.alpha = 1f;
                getWindow().setAttributes(params);
                Toast.makeText(this, "已提交申请，待审核", Toast.LENGTH_SHORT).show();
            }

            try {
                // 将临时文件删除
                tempFile.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if (data != null) {

            if (requestCode == 10) {
                resultNick = data.getExtras().getString("nick");
                if (resultNick != null) {
                    mTv_pet_nick.setText(resultNick + "");
                }
            } else if (requestCode == 22) {
                resultWeight = data.getExtras().getString("weight");
                if (resultWeight != null) {
                    mTv_pet_weight.setText(resultWeight + "  KG");
                }

            } else if (requestCode == 33) {
                String strlistimm = data.getStringExtra("strlistimm");
                Log.d("TAG", strlistimm + "*******");
                List<Imm> listimm = CJSON.parseArray(strlistimm, Imm.class);
                if (listimm != null) {
                    for (Imm imm : listimm) {
                        if (iconMap == null) {
                            iconMap = new HashMap<>();
                        }
                        iconMap.put(imm.getImmuneCode() + "-"
                                        + AppUtils.getUser().getUserId(),
                                new File(imm.getPath()));

                        imm.setUserId(AppUtils.getUser().getUserId());
                        imm.setPetName(mTv_pet_nick.getText().toString().trim());
                        imm.setUserName(AppUtils.getUser().getUserName());

                        if (map == null) {
                            map = new HashMap<>();
                        }
                        map.put(imm.getImmuneCode() + "-"
                                + AppUtils.getUser().getUserId(), imm);
                        Log.i("TAG", "=======2=======" + imm.getImmuneCode()
                                + "+++++++++++" + imm.getImmuneName());
                    }
                }
                mTv_immune.setText("已提交");
            } else if (resultCode == 443) {
                if (data != null) {
                    petType = data.getStringExtra(TableUtils.PetInfo.PETNAME);
                    typeCode = data.getStringExtra(TableUtils.PetInfo.PETTYPE);
                    Log.i("TAG", petType + "name2");
                    Log.i("TAG", typeCode + "code2");
                    if (petType != null) {
                        mTv_pet_type.setText(petType);
                    }
                }
            }


            super.onActivityResult(requestCode, resultCode, data);

        }
    }

    private void uploadPic(Bitmap bitmap) {


    }

    private void addpetispass() {
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
                mTv_pet_isPass.setText(yesOrNo);
                popupWindow.dismiss();
            }
        });
        tv_no.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                yesOrNo = "否";
                isSterilization = false;
                mTv_pet_isPass.setText(yesOrNo);
                popupWindow.dismiss();
            }
        });


    }


    @Override
    public void onClick(View v) {


    }

    @Override
    public int initLayoutID() {
        return R.layout.activity_pet_add;
    }

    @Override
    public void initView() {

    }

    public void initAdapter() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }


    @Override
    public void setMyAppTitle() {

    }
}