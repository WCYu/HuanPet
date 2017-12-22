package com.example.huanpet.view.main.view.foster_families;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huanpet.R;
import com.example.huanpet.app.BaseActivity;
import com.example.huanpet.utils.CustomTool;
import com.example.huanpet.view.main.view.IHomeView;
import com.example.huanpet.view.user.activity.UserActivity;
import com.example.huanpet.view.user.image.ImageUtils;

import java.io.File;

public class FosterHomeActivity extends BaseActivity implements View.OnClickListener,IHomeView {

    private static final int PHOTO_REQUEST_CAREMA = 7;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 8;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 9;// 结果
    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";
    private File tempFile;

    private CustomTool fosterhome_custom;
    private EditText fosterhome_name;
    private EditText fosterhome_phone;
    private EditText fosterhome_address;
    private EditText fosterhome_Identificationcard;
    private ImageView fosterhome_Identificationcardimg;
    private CheckBox fosterhome_check1;
    private EditText fosterhome_check_et1;
    private CheckBox fosterhome_check2;
    private EditText fosterhome_check_et2;
    private CheckBox fosterhome_check3;
    private EditText fosterhome_check_et3;
    private CheckBox fosterhome_check4;
    private EditText fosterhome_check_et4;
    private CheckBox fosterhome_check5;
    private EditText fosterhome_check_et5;
    private CheckBox fosterhome_check6;
    private EditText fosterhome_check_et6;
    private CheckBox fosterhome_check7;
    private EditText fosterhome_check_et7;
    private CheckBox fosterhome_check8;
    private EditText fosterhome_check_et8;
    private EditText fosterhome_checkname;
    private ImageView fosterhome_fosterimg;
    private ImageView fosterhome_environmentimg;
    private CheckBox fosterhome_isconsent;
    private TextView fosterhome_userprotocol;
    private Button fosterhome_determine;
    private PopupWindow window;

    @Override
    public int initLayoutID() {
        return R.layout.activity_foster_home;
    }

    public void initView() {
        fosterhome_custom = (CustomTool) findViewById(R.id.fosterhome_custom);
        fosterhome_name = (EditText) findViewById(R.id.fosterhome_name);
        fosterhome_phone = (EditText) findViewById(R.id.fosterhome_phone);
        fosterhome_address = (EditText) findViewById(R.id.fosterhome_address);
        fosterhome_Identificationcard = (EditText) findViewById(R.id.fosterhome_Identificationcard);
        fosterhome_Identificationcardimg = (ImageView) findViewById(R.id.fosterhome_Identificationcardimg);
        fosterhome_check1 = (CheckBox) findViewById(R.id.fosterhome_check1);
        fosterhome_check_et1 = (EditText) findViewById(R.id.fosterhome_check_et1);
        fosterhome_check2 = (CheckBox) findViewById(R.id.fosterhome_check2);
        fosterhome_check_et2 = (EditText) findViewById(R.id.fosterhome_check_et2);
        fosterhome_check3 = (CheckBox) findViewById(R.id.fosterhome_check3);
        fosterhome_check_et3 = (EditText) findViewById(R.id.fosterhome_check_et3);
        fosterhome_check4 = (CheckBox) findViewById(R.id.fosterhome_check4);
        fosterhome_check_et4 = (EditText) findViewById(R.id.fosterhome_check_et4);
        fosterhome_check5 = (CheckBox) findViewById(R.id.fosterhome_check5);
        fosterhome_check_et5 = (EditText) findViewById(R.id.fosterhome_check_et5);
        fosterhome_check6 = (CheckBox) findViewById(R.id.fosterhome_check6);
        fosterhome_check_et6 = (EditText) findViewById(R.id.fosterhome_check_et6);
        fosterhome_check7 = (CheckBox) findViewById(R.id.fosterhome_check7);
        fosterhome_check_et7 = (EditText) findViewById(R.id.fosterhome_check_et7);
        fosterhome_check8 = (CheckBox) findViewById(R.id.fosterhome_check8);
        fosterhome_check_et8 = (EditText) findViewById(R.id.fosterhome_check_et8);
        fosterhome_checkname = (EditText) findViewById(R.id.fosterhome_checkname);
        fosterhome_fosterimg = (ImageView) findViewById(R.id.fosterhome_fosterimg);
        fosterhome_environmentimg = (ImageView) findViewById(R.id.fosterhome_environmentimg);
        fosterhome_isconsent = (CheckBox) findViewById(R.id.fosterhome_isconsent);
        fosterhome_userprotocol = (TextView) findViewById(R.id.fosterhome_userprotocol);
        fosterhome_determine = (Button) findViewById(R.id.fosterhome_determine);

        fosterhome_determine.setOnClickListener(this);
        fosterhome_Identificationcardimg.setOnClickListener(this);
    }

    @Override
    public void initAdapter() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        fosterhome_determine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fosterhome_isconsent.isChecked()){

                }
            }
        });
    }

    @Override
    public void setMyAppTitle() {
        fosterhome_custom.setAppTitle("申请成为寄养家庭");
        fosterhome_custom.initViewsVisible(true, true, false, false);
        fosterhome_custom.setOnRightImgClickLisrener(new CustomTool.OnRightImgClickListener() {
            @Override
            public void onRightImgClick(View v) {

            }
        });
        fosterhome_custom.setOnLeftButtonClickListener(new CustomTool.OnLeftButtonClickListener() {
            @Override
            public void onLeftButtonClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fosterhome_determine:

                break;
            case R.id.fosterhome_Identificationcardimg:
                showTipPop(fosterhome_Identificationcardimg,"上传身份证照片");
                break;
        }
    }

    private void showTipPop(ImageView imageView,String title) {
        Toast.makeText(this, "选择身份证", Toast.LENGTH_SHORT).show();
        View view = View.inflate(this, R.layout.user_popiuwindow, null);
        window = new PopupWindow(view, ActionBar.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.WRAP_CONTENT, true);
        window.setAnimationStyle(R.style.style_dialog);
        view.getBackground().setAlpha(140);
        window.setBackgroundDrawable(new BitmapDrawable());
        //出现位置
        window.showAtLocation(imageView, Gravity.BOTTOM, 0, 0);
        // 设置PopupWindow以外部分的背景颜色  有一种变暗的效果
        final WindowManager.LayoutParams wlBackground = getWindow().getAttributes();
        wlBackground.alpha = 0.5f;  // 0.0 完全不透明,1.0完全透明
        getWindow().setAttributes(wlBackground);

        //标题
        TextView user_poputitle=view.findViewById(R.id.user_poputitle);
        user_poputitle.setText(title);
        //拍照
        TextView mTake_pictures = view.findViewById(R.id.but_Take_pictures);
        mTake_pictures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImageBenDi();
                window.dismiss();

            }
        });
        //相册
        TextView mAlbum = view.findViewById(R.id.but_album);
        mAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choseHeadImageFromGallery();
                window.dismiss();
            }
        });
        //取消
        TextView mCancel = view.findViewById(R.id.but_cancel);
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });

        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                wlBackground.alpha = 1.0f;
                getWindow().setAttributes(wlBackground);
            }
        });
    }

    //拍照作为头像
    private void getImageBenDi() {
        // 激活相机
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        // 判断存储卡是否可以用，可用进行存储
        if (hasSdcard()) {
            tempFile = new File(Environment.getExternalStorageDirectory(),PHOTO_FILE_NAME);
            // 从文件中创建uri
            Uri uri = Uri.fromFile(tempFile);

            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CAREMA
        startActivityForResult(intent, PHOTO_REQUEST_CAREMA);
    }

    // 从本地相册选取图片作为头像
    private void choseHeadImageFromGallery() {
        // 激活系统图库，选择一张图片
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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
                Toast.makeText(FosterHomeActivity.this, "未找到存储卡，无法存储照片！", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == PHOTO_REQUEST_CUT) {
            // 从剪切图片返回的数据
            if (data != null) {
                //获取Bitmap图片
                Bitmap bitmap = data.getParcelableExtra("data");
                //剪切图片
//                uploadPic(bitmap);
                Bitmap photo = ImageUtils.toRoundBitmap(bitmap);// 这个时候的图片已经被处理成圆形的了
                //设置图片
                fosterhome_Identificationcardimg.setImageBitmap(photo);
                //popupwindow消失,系统背景颜色改变
                window.dismiss();
                WindowManager.LayoutParams params = getWindow().getAttributes();
                params.alpha = 1f;
                getWindow().setAttributes(params);
                Toast.makeText(this, "上传成功", Toast.LENGTH_SHORT).show();
            }
            try {
                // 将临时文件删除
                tempFile.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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

    private void submit() {
        // validate
        String name = fosterhome_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "输入寄养姓名", Toast.LENGTH_SHORT).show();
            return;
        }

        String phone = fosterhome_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "输入寄养电话", Toast.LENGTH_SHORT).show();
            return;
        }

        String address = fosterhome_address.getText().toString().trim();
        if (TextUtils.isEmpty(address)) {
            Toast.makeText(this, "输入寄养家庭地址", Toast.LENGTH_SHORT).show();
            return;
        }

        String Identificationcard = fosterhome_Identificationcard.getText().toString().trim();
        if (TextUtils.isEmpty(Identificationcard)) {
            Toast.makeText(this, "输入有效身份证号", Toast.LENGTH_SHORT).show();
            return;
        }

        String et1 = fosterhome_check_et1.getText().toString().trim();
        if (TextUtils.isEmpty(et1)) {
            Toast.makeText(this, "请输入价格", Toast.LENGTH_SHORT).show();
            return;
        }

        String et2 = fosterhome_check_et2.getText().toString().trim();
        if (TextUtils.isEmpty(et2)) {
            Toast.makeText(this, "请输入价格", Toast.LENGTH_SHORT).show();
            return;
        }

        String et3 = fosterhome_check_et3.getText().toString().trim();
        if (TextUtils.isEmpty(et3)) {
            Toast.makeText(this, "请输入价格", Toast.LENGTH_SHORT).show();
            return;
        }

        String et4 = fosterhome_check_et4.getText().toString().trim();
        if (TextUtils.isEmpty(et4)) {
            Toast.makeText(this, "请输入价格", Toast.LENGTH_SHORT).show();
            return;
        }

        String et5 = fosterhome_check_et5.getText().toString().trim();
        if (TextUtils.isEmpty(et5)) {
            Toast.makeText(this, "请输入价格", Toast.LENGTH_SHORT).show();
            return;
        }

        String et6 = fosterhome_check_et6.getText().toString().trim();
        if (TextUtils.isEmpty(et6)) {
            Toast.makeText(this, "请输入价格", Toast.LENGTH_SHORT).show();
            return;
        }

        String et7 = fosterhome_check_et7.getText().toString().trim();
        if (TextUtils.isEmpty(et7)) {
            Toast.makeText(this, "请输入价格", Toast.LENGTH_SHORT).show();
            return;
        }

        String et8 = fosterhome_check_et8.getText().toString().trim();
        if (TextUtils.isEmpty(et8)) {
            Toast.makeText(this, "请输入价格", Toast.LENGTH_SHORT).show();
            return;
        }

        String checkname = fosterhome_checkname.getText().toString().trim();
        if (TextUtils.isEmpty(checkname)) {
            Toast.makeText(this, "输入寄养昵称", Toast.LENGTH_SHORT).show();
            return;
        }

    }

    @Override
    public void getData(String data, int i) {

    }
}
