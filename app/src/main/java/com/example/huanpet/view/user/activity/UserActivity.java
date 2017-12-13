package com.example.huanpet.view.user.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huanpet.R;
import com.example.huanpet.app.BaseActivity;
import com.example.huanpet.utils.CustomTool;
import com.example.huanpet.view.main.HomeActivity;
import com.example.huanpet.view.user.adpter.NumericWheelAdapter;
import com.example.huanpet.view.user.image.ImageUtils;
import com.example.huanpet.view.user.image.UserManagers;
import com.example.huanpet.view.user.widget.WheelView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UserActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout Head_portrait;
    private LinearLayout Name;
    private LinearLayout Gender;
    private LinearLayout Date_of_birth;
    private LinearLayout Mobile_phone;
    private LinearLayout WeChat;
    private LinearLayout QQ;
    private LinearLayout Contact_address;
    private ImageView imageview_head;
    private PopupWindow window;
    protected static Uri tempUri;
    private UserManagers userManagers;
    private String verifycode;
    private static final int PHOTO_REQUEST_CAREMA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果
    private File tempFile;
    /* 头像名称 */
    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";
    private CustomTool customtool_user;

    private TextView textview_name;
    private TextView textview_Gender;
    private TextView textview_Date_of_birth;
    private TextView textview_Phone;
    private TextView textview_WeChat;
    private TextView textview_Contact_address;
    private TextView textview_qq;

    private WheelView year;
    private WheelView month;
    private WheelView day;


    @Override
    public int initLayoutID() {
        return R.layout.activity_user;
    }

    @Override
    public void initView() {
        customtool_user = findViewById(R.id.customtool_user);
        customtool_user.setOnLeftButtonClickListener(new CustomTool.OnLeftButtonClickListener() {
            @Override
            public void onLeftButtonClick(View v) {
                Intent intentname = new Intent(UserActivity.this, HomeActivity.class);
                startActivity(intentname);
                finish();
            }
        });
        customtool_user.setAppTitle("个人信息");
        customtool_user.initViewsVisible(true, true, false, false);

        Head_portrait = (LinearLayout) findViewById(R.id.Head_portrait);
        Name = (LinearLayout) findViewById(R.id.Name);
        Gender = (LinearLayout) findViewById(R.id.Gender);
        Date_of_birth = (LinearLayout) findViewById(R.id.Date_of_birth);
        Mobile_phone = (LinearLayout) findViewById(R.id.Mobile_phone);
        WeChat = (LinearLayout) findViewById(R.id.WeChat);
        QQ = (LinearLayout) findViewById(R.id.QQ);
        Contact_address = (LinearLayout) findViewById(R.id.Contact_address);
        imageview_head = (ImageView) findViewById(R.id.imageview_head);

        Head_portrait.setOnClickListener(this);
        Name.setOnClickListener(this);
        Gender.setOnClickListener(this);
        Date_of_birth.setOnClickListener(this);
        Mobile_phone.setOnClickListener(this);
        WeChat.setOnClickListener(this);
        QQ.setOnClickListener(this);
        Contact_address.setOnClickListener(this);

        textview_name = (TextView) findViewById(R.id.textview_name);
        textview_Gender = (TextView) findViewById(R.id.textview_Gender);
        textview_Date_of_birth = (TextView) findViewById(R.id.textview_Date_of_birth);
        textview_Phone = (TextView) findViewById(R.id.textview_Phone);
        textview_WeChat = (TextView) findViewById(R.id.textview_WeChat);
        textview_Contact_address = (TextView) findViewById(R.id.textview_Contact_address);
        textview_qq = (TextView) findViewById(R.id.textview_qq);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //更改头像
            case R.id.Head_portrait:
                showTipPop();
                break;

            //修改名称
            case R.id.Name:
                Intent intent = new Intent(UserActivity.this, ModifyTheNameActivity.class);
                startActivityForResult(intent, 0);
                break;

            //选择性别
            case R.id.Gender:
                showName();

                break;

            //出生日期
            case R.id.Date_of_birth:
                showDateDialog();
                break;

            //手机
            case R.id.Mobile_phone:
                Intent intentphone = new Intent(UserActivity.this, PhoneActivity.class);
                startActivityForResult(intentphone, 1);
                break;

            //微信
            case R.id.WeChat:
                Intent intentwechat = new Intent(UserActivity.this, WeChatActivity.class);
                startActivityForResult(intentwechat, 2);
                break;

            //QQ
            case R.id.QQ:
                Intent intentqq = new Intent(UserActivity.this, QQActivity.class);
                startActivityForResult(intentqq, 3);
                break;

            //添加联系地址
            case R.id.Contact_address:
                Intent intentaddress = new Intent(UserActivity.this, ContactAddressActivity.class);
                startActivityForResult(intentaddress, 4);
                break;


        }
    }

    private void showDateDialog() {
//        final AlertDialog dialog = new AlertDialog.Builder(UserActivity.this)
//                .create();
//        dialog.show();
//        Window window = dialog.getWindow();
//        // 设置布局
//        window.setContentView(R.layout.datepicker_layout);
//        // 设置宽高
//        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
//        // 设置弹出的动画效果
//        window.setWindowAnimations(R.style.AnimBottom);


        View views = View.inflate(this, R.layout.datepicker_layout, null);

        window = new PopupWindow(views, ActionBar.LayoutParams.MATCH_PARENT,
                ViewPager.LayoutParams.WRAP_CONTENT,
                true);
        window.setAnimationStyle(R.style.style_dialog);
//        views.getBackground().setAlpha(140);
        window.setBackgroundDrawable(new BitmapDrawable());
        //出现位置
        window.showAtLocation(Head_portrait, Gravity.BOTTOM, 0, 0);
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
                String str = String.format(Locale.CHINA, "%4d年%2d月%2d日", year.getCurrentItem() + 1950, month.getCurrentItem() + 1, day.getCurrentItem() + 1);
//                Toast.makeText(UserActivity.this, str, Toast.LENGTH_LONG).show();
                textview_Date_of_birth.setText(str);
//                dialog.cancel();
                window.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                dialog.cancel();
                window.dismiss();
            }
        });
//        LinearLayout cancelLayout = (LinearLayout) window.findViewById(R.id.view_none);
//        cancelLayout.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                dialog.cancel();
//                return false;
//            }
//        });

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


    private void showName() {

        View view = View.inflate(this, R.layout.name_popuwindow, null);

        window = new PopupWindow(view, ActionBar.LayoutParams.MATCH_PARENT,
                ViewPager.LayoutParams.WRAP_CONTENT,
                true);
        window.setAnimationStyle(R.style.style_dialog);
        view.getBackground().setAlpha(140);
        window.setBackgroundDrawable(new BitmapDrawable());
        //出现位置
        window.showAtLocation(Head_portrait, Gravity.BOTTOM, 0, 0);
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
        //  男
        final TextView textview_boy = view.findViewById(R.id.textview_boy);
        textview_boy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                textview_Gender.setText(textview_boy.getText().toString().trim());
                window.dismiss();
            }
        });

        //  女
        final TextView textview_girl = view.findViewById(R.id.textview_girl);
        textview_girl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                textview_Gender.setText(textview_girl.getText().toString().trim());
                window.dismiss();
            }
        });

    }

    private void showTipPop() {

        View view = View.inflate(this, R.layout.user_popiuwindow, null);

        window = new PopupWindow(view, ActionBar.LayoutParams.MATCH_PARENT,
                ViewPager.LayoutParams.WRAP_CONTENT,
                true);
        window.setAnimationStyle(R.style.style_dialog);
        view.getBackground().setAlpha(140);
        window.setBackgroundDrawable(new BitmapDrawable());
        //出现位置
        window.showAtLocation(Head_portrait, Gravity.BOTTOM, 0, 0);
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

    // 从本地相册选取图片作为头像
    private void choseHeadImageFromGallery() {
        // 激活系统图库，选择一张图片
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
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
            if (data!=null&&hasSdcard()) {
                crop(Uri.fromFile(tempFile));
            } else {
                Toast.makeText(UserActivity.this, "未找到存储卡，无法存储照片！",Toast.LENGTH_SHORT).show();
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
                imageview_head.setImageBitmap(photo);
                //popupwindow消失,系统背景颜色改变
                window.dismiss();
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

            if (data != null) {
                if (requestCode == 0) {
                    //用户名回传的数据
                    Bundle bundle_name = data.getExtras();
                    String name = bundle_name.getString("name");
                    textview_name.setText(name);
                } else if (requestCode == 1) {
                    //手机回传的数据
                    Bundle bundle_phone = data.getExtras();
                    String phone = bundle_phone.getString("phone");
                    textview_Phone.setText(phone);
                } else if (requestCode == 2) {
                    //微信回传的数据
                    Bundle bundle_wechat = data.getExtras();
                    String wechat = bundle_wechat.getString("wechat");
                    textview_WeChat.setText(wechat);

                } else if (requestCode == 3) {
                    //QQ回传的数据
                    Bundle bundle_QQ = data.getExtras();
                    String QQ = bundle_QQ.getString("QQ");
                    textview_qq.setText(QQ);
                } else if (requestCode == 4) {
                    //联系地址回传的数据
                    Bundle bundle_contactaddress = data.getExtras();
                    String contactaddress = bundle_contactaddress.getString("contactaddress");
                    textview_Contact_address.setText(contactaddress);
                }
            }

        }
    }



//    private void uploadPic(Bitmap bitmap) {
//        // 上传至服务器
//        // ... 可以在这里把Bitmap转换成file，然后得到file的url，做文件上传操作
//        // 注意这里得到的图片已经是圆形图片了
//        // bitmap是没有做个圆形处理的，但已经被裁剪了
//        final String imagePath = ImageUtils.savePhoto(bitmap, Environment
//                .getExternalStorageDirectory().getAbsolutePath(), String
//                .valueOf(System.currentTimeMillis()));
//        if (imagePath != null) {
////            String url = "http://my.cntv.cn/intf/napi/api.php" + "?client="
////                    + "cbox_mobile" + "&method=" + "user.alterUserFace"
////                    + "&userid=" + mUserManager.getUserId();
//            String url = "http://my.cntv.cn/intf/napi/api.php";
//            Log.e("TAG", "-----verifycode----" + "开始");
//            OkHttpClient okHttpClient = new OkHttpClient.Builder().cookieJar(new CookieJar() {
//                List<Cookie> cooKies = new ArrayList<>();
//
//                @Override
//                public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
//                    cooKies = cookies;
//                    Log.e("TAG", "-----verifycode----" + "进行中");
//                    for (Cookie cookie : cooKies) {
//                        Log.e("TAG", "-----verifycode----" + cookie.value());
//                        if ("verifycode".equals(cookie.name())) {
//                            verifycode = cookie.value();
//                        }
//                    }
//                }
//
//                @Override
//                public List<Cookie> loadForRequest(HttpUrl url) {
//                    return cooKies;
//                }
//            }).build();
//            Log.e("TAG", "-----verifycode----" + "结束");
//            File file = new File(imagePath);
//            MultipartBody.Builder builder = new MultipartBody.Builder();
//            builder.setType(MultipartBody.FORM);
//            builder.addFormDataPart("client", "ipanda_mobile");
//            builder.addFormDataPart("method", "user.alterUserFace");
//            builder.addFormDataPart("userid", userManagers.getUserId());
//            builder.addFormDataPart("facefile", file.getName(), RequestBody.create(MediaType.parse("image/png"), file));
//            RequestBody body = builder.build();
////            Request request = new Request.Builder().url(url).build();
//            Request request = new Request.Builder().url(url).addHeader("Referer", "iPanda.Android")
//                    .addHeader("User-Agent", "CNTV_APP_CLIENT_CBOX_MOBILE")
//                    .addHeader("Cookie", "verifycode=" + userManagers.getVerifycode()).post(body).build();
//
//            okHttpClient.newCall(request).enqueue(new Callback() {
//                @Override
//                public void onFailure(Call call, IOException e) {
//                    Log.e("TAG", userManagers.getUserId() + "----------上传头像--------失败" + verifycode);
//                }
//
//                @Override
//                public void onResponse(Call call, Response response) throws IOException {
//                    String string = response.body().string();
//                    try {
//                        JSONObject jsonObject = new JSONObject(string);
//                        int code = jsonObject.getInt("code");
//                        if (code == 0) {
//                            String error = jsonObject.getString("error");
//                            Log.e("TAG", userManagers.getUserId() + "----------上传头像--------成功" + error);
//                        } else if (code == -100) {
//                            String error = jsonObject.getString("error");
//                            Log.e("TAG", "--------error-----" + error);
//                            Log.e("TAG", userManagers.getUserId() + "----------上传头像--------失败" + userManagers.getVerifycode());
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//
//                    }
//                    Log.e("TAG", "----------上传头像--------" + string + userManagers.getUserId() + "----" + imagePath);
//                }
//            });
//        }
//    }


    @Override
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) and run LayoutCreator again
    }
}
