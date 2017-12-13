package com.example.huanpet.view.pet;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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

import com.example.huanpet.R;

import java.io.File;

import cn.qqtheme.framework.picker.DatePicker;

public class PetAddActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout mLine, mLineIcon, mLineName, mLineSex, mLineBirth, mLinePhone;
    private LinearLayout mLineWeiXin, mLineQQ, mLineAddress;
    private View mPhotoView, mSexView;
    private PopupWindow mPhotoPopWindow, mSexPopWindow;
    private Button mSelectPhoto, mMakePhoto, mCancelPhoto;
    private static final int PHOTO_REQUEST_CAREMA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果
    /* 头像名称 */
    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";
    private File tempFile;
    private ImageView mIcon;
    TextView mSex, mBirth;
    private RadioGroup mRgSex;
    private RadioButton mBoy, mGirl;
    private TextView tz;
    private ImageView userinfo_img;
    private RelativeLayout tx;
    private RelativeLayout nc;
    private RelativeLayout cwlx;
    private RelativeLayout jy;
    private RelativeLayout rq;
    private RelativeLayout tiz;
    private RelativeLayout mianyi;
    private EditText edit;
    private LinearLayout quan;
    private TextView nccc;
    private TextView tzcc;
    private TextView cwcc;
    private ImageView fanzhuan;
    private TextView tv_mSex;
    private ImageView fh;
    private RelativeLayout title_view;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_add);
        initView();
        getIntehnt();


    }

    protected void initView() {


        tz = (TextView) findViewById(R.id.tz);
        tz.setOnClickListener(this);
        cwcc = (TextView) findViewById(R.id.cwcc);
        cwcc.setOnClickListener(this);
        nccc = (TextView) findViewById(R.id.nccc);
        nccc.setOnClickListener(this);
        tzcc = (TextView) findViewById(R.id.tzcc);
        tzcc.setOnClickListener(this);
        userinfo_img = (ImageView) findViewById(R.id.userinfo_img);
        userinfo_img.setOnClickListener(this);
        tx = (RelativeLayout) findViewById(R.id.tx);
        tx.setOnClickListener(this);
        nc = (RelativeLayout) findViewById(R.id.nc);
        nc.setOnClickListener(this);
        cwlx = (RelativeLayout) findViewById(R.id.cwlx);
        cwlx.setOnClickListener(this);
        jy = (RelativeLayout) findViewById(R.id.jy);
        jy.setOnClickListener(this);
        rq = (RelativeLayout) findViewById(R.id.rq);
        rq.setOnClickListener(this);
        tiz = (RelativeLayout) findViewById(R.id.tiz);
        tiz.setOnClickListener(this);
        mianyi = (RelativeLayout) findViewById(R.id.mianyi);
        mianyi.setOnClickListener(this);
        edit = (EditText) findViewById(R.id.edit);
        edit.setOnClickListener(this);
        quan = (LinearLayout) findViewById(R.id.quan);
        quan.setOnClickListener(this);
        fh = (ImageView) findViewById(R.id.fh);
        fh.setOnClickListener(this);
        title_view = (RelativeLayout) findViewById(R.id.title_view);
        title_view.setOnClickListener(this);
        mBirth = (TextView) findViewById(R.id.mBirth);
        mSex = (TextView) findViewById(R.id.tv_mSex);

        tv_mSex = (TextView) findViewById(R.id.tv_mSex);
        tv_mSex.setOnClickListener(this);
        initListener();




    }

    private void initListener() {
        /**
         * 头像的监听
         */
        tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopWindow();
            }
        });
        /**
         * 名称的监听
         */
        nc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PetAddActivity.this, nicknameActivity.class);
                startActivity(intent);
            }
        });


        cwlx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(PetAddActivity.this, TypeofpetActivity.class);
                startActivityForResult(in, 4);
            }
        });
        mianyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(PetAddActivity.this, ImmuneActivity.class);
                startActivity(in);
            }
        });
        fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(PetAddActivity.this, PetActivity.class);
                startActivity(in);
            }
        });

        rq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });
        tiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(PetAddActivity.this, WeightActivity.class);
                startActivity(in);
            }
        });

        jy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSexPopWindow();
            }
        });


    }

    private void showSexPopWindow() {
        mSexView = LayoutInflater.from(PetAddActivity.this).inflate(R.layout.sex_pop, null);
        mSexPopWindow = new PopupWindow(mSexView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mRgSex = mSexView.findViewById(R.id.rg_sex);
        mBoy = mSexView.findViewById(R.id.btn_boy);
        mGirl = mSexView.findViewById(R.id.btn_girl);

        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);


        //设置背景
        mSexPopWindow.setBackgroundDrawable(new BitmapDrawable());
        // 设置点击其他地方 就消失 (只设置这个，没有效果) 必须设置背景
        mSexPopWindow.setOutsideTouchable(true);
        mSexPopWindow.setTouchable(true);
        mSexPopWindow.setFocusable(true);
        mSexPopWindow.showAtLocation(quan, Gravity.BOTTOM, 0, 0);

        // 如果popupWindow显示，改变系统背景透明度

        if (mSexPopWindow.isShowing()) {
            WindowManager.LayoutParams params = getWindow().getAttributes();
            params.alpha = 0.5f;
            getWindow().setAttributes(params);
        }

        // 如果popupWindow消失，改变系统背景透明度

        mSexPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams params = getWindow().getAttributes();
                params.alpha = 1f;
                getWindow().setAttributes(params);
            }
        });

        mRgSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.btn_boy:

                        String boyString = mBoy.getText().toString();
                        if (!boyString.isEmpty()) {
                            Toast.makeText(PetAddActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                            mSex.setText(boyString);
                            mSexPopWindow.dismiss();
                        } else {
                            Toast.makeText(PetAddActivity.this, "网络不佳,请稍后重试", Toast.LENGTH_SHORT).show();
                            mSexPopWindow.dismiss();
                        }


                        break;
                    case R.id.btn_girl:

                        String girlString = mGirl.getText().toString();
                        if (!girlString.isEmpty()) {
                            Toast.makeText(PetAddActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                            mSex.setText(girlString);
                            mSexPopWindow.dismiss();
                        } else {
                            Toast.makeText(PetAddActivity.this, "网络不佳,请稍后重试", Toast.LENGTH_SHORT).show();
                            mSexPopWindow.dismiss();
                        }

                        break;
                }
            }
        });

    }


    private void showPopWindow() {
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
                userinfo_img.setImageBitmap(photo);
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

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void uploadPic(Bitmap bitmap) {
    }

    private void submit() {
        // validate
        String editString = edit.getText().toString().trim();
        if (TextUtils.isEmpty(editString)) {
            Toast.makeText(this, "宠物简介描述", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }

    @Override
    public void onClick(View v) {

    }

    public void getIntehnt() {


        Intent intent = getIntent();
        String aa = intent.getStringExtra("bb");
        nccc.setText(aa);


        Intent intent1 = getIntent();
        String cc = intent1.getStringExtra("cc");
        tzcc.setText(cc);

        Intent intent2 = getIntent();
        String aa1 = intent2.getStringExtra("aa");
        cwcc.setText(aa1);

    }
}