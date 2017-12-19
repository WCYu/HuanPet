package com.example.huanpet.view.pet.View;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.widget.ImageView;


import com.example.huanpet.R;
import com.example.huanpet.utils.util.AppUtils;
import com.example.huanpet.utils.util.CJSON;
import com.example.huanpet.utils.util.TableUtils;
import com.example.huanpet.utils.util.UserInfo;
import com.example.huanpet.view.pet.ImageUtils;
import com.example.huanpet.view.pet.adapter.ACache;
import com.example.huanpet.view.pet.adapter.HttpCacheUtils;
import com.example.huanpet.view.pet.adapter.ImageUtilsss;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.StringCallback;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Sam on 2016/1/9.
 */
@SuppressLint("AppCompatCustomView")
public class PhotoCircleView extends ImageView {
	private int mBorderThickness = 0;
	private Context mContext;
	private int defaultColor = Color.parseColor("#FDFDFD");
	// 如果只有其中一个有值，则只画一个圆形边框
	private int mBorderOutsideColor = 0;// 图片的外边界
	private int mBorderInsideColor = 0;// 图片的内边界
	// 控件默认长、宽
	private int defaultWidth = 0;
	private int defaultHeight = 0;


	public PhotoCircleView(Context context) {
		super(context);
		mContext = context;
		setScaleType(ScaleType.FIT_XY);
	}

	public PhotoCircleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		setCustomAttributes(attrs);
		setScaleType(ScaleType.FIT_XY);
	}

	public PhotoCircleView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
		setCustomAttributes(attrs);
		setScaleType(ScaleType.FIT_XY);
	}

	/**
	 * 带缓存的生成图片
	 * 
	 * @param url
	 */
	public void setImageHttpDefaultUrl(String url) {
		if (url == null) {
			Picasso.with(mContext).load(R.drawable.default_image).into(this);
			return;
		}
		HttpCacheUtils.loadImage(url, this);
	}

	/**
	 * 带缓存的生成图片
	 * 
	 * @param url
	 */
	public void setImageHttpUserUrl(String url) {
		if (url == null) {
			Picasso.with(mContext).load(R.drawable.user_defaults).into(this);
			return;
		}
		HttpCacheUtils.loadImageUser(url, this);
	}

	/**
	 * 带缓存的生成图片
	 * 
	 * @param url
	 */
	public void setImageHttpPetUrl(String url) {
		if (url == null) {
			Picasso.with(mContext).load(R.drawable.pet_default).into(this);
			return;
		}
		HttpCacheUtils.loadImagePet(url, this);
	}

	/**
	 * 带缓存的生成图片
	 * 
	 * @param url
	 */
	public void setImageHttpUrlByTag(String url) {
		HttpCacheUtils.loadImageUser(url, this);
		new HttpTask().execute(url);
	}

	public void setImageHttpAccount(final Long account) {
		if (getTag() != null) {
			setImageBitmap((Bitmap) getTag());
			return;
		}
		final String pathUrl = AppUtils.REQUESTURL
				+ "user/getUserInfoByPhone.jhtml";
		String str = ACache.getInstance().getAsString(
				ACache.getMD5FileName(pathUrl + account + ACache.IMG));
		if (str != null) {
			setImageHttpUrlByTag(str);
			return;
		}
		Picasso.with(AppUtils.getAppContext()).load(R.drawable.user_defaults)
				.into(this);
		Map<String, Object> param = new HashMap<>();
		param.put(TableUtils.UserInfo.USERPHONE, account);
		OkHttpUtils.post(pathUrl)
				.params(CJSON.DATA, CJSON.toJSONMap(param))
				.execute(new StringCallback() {

					@Override
					public void onResponse(boolean arg0, String arg1,
                                           Request arg2, Response arg3) {
						if (CJSON.getRET(arg1)) {
							final String path = String.valueOf(CJSON
									.parseObject(CJSON.getDESC(arg1),
											UserInfo.class).getUserImage());
							setImageHttpUrlByTag(path);
							ACache.getInstance().put(
									ACache.getMD5FileName(pathUrl + account
											+ ACache.IMG), path,1000*60);
						}
					}
				});
	}

	class HttpTask extends AsyncTask<String, Void, Bitmap> {

		@Override
		protected Bitmap doInBackground(String... params) {
			String urlPath = AppUtils.HTTP_IMAGE_URL + params[0];
			Bitmap bmp = ACache.getInstance().getAsBitmap(
					ACache.getMD5FileName(urlPath));
			if (bmp != null) {
				PhotoCircleView.this.setTag(bmp);
				return bmp;
			}
			InputStream inputStream = null;
			Bitmap bimp = null;
			try {
				URL url = new URL(urlPath); // 服务器地址
				if (url != null) {
					// 打开连接
					HttpURLConnection httpURLConnection = (HttpURLConnection) url
							.openConnection();
					httpURLConnection.setConnectTimeout(10000);// 设置网络连接超时的时间为10秒
					httpURLConnection.setRequestMethod("GET"); // 设置请求方法为GET
					httpURLConnection.setDoInput(true); // 打开输入流
					httpURLConnection
							.setRequestProperty("User-Agent",
									"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0");
					httpURLConnection
							.setRequestProperty("Accept",
									"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
					httpURLConnection.setRequestProperty("Accept-Language",
							"zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
					httpURLConnection.setRequestProperty("Accept-Encoding",
							"gzip, deflate");
					httpURLConnection.setRequestProperty("Connection",
							"keep-alive");
					httpURLConnection.setRequestProperty("Cache-Control",
							"max-age=0");
					httpURLConnection.setRequestProperty("DNT", "1");
					int responseCode = httpURLConnection.getResponseCode(); // 获取服务器响应值

					if (responseCode == HttpURLConnection.HTTP_OK) { // 正常连接
						inputStream = httpURLConnection.getInputStream(); // 获取输入流
						bimp = ImageUtilsss.inputStreamToBitmap(inputStream);
						PhotoCircleView.this.setTag(bimp);
						ACache.getInstance().put(
								ACache.getMD5FileName(urlPath), bimp);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return bimp;
		}

	}

	private void setCustomAttributes(AttributeSet attrs) {
		// 设置图片充满
		// 边界的宽度
		mBorderThickness = getResources().getDimensionPixelSize(R.dimen.dp1_3);
		// 外边界的颜色
		mBorderOutsideColor = defaultColor;
		// 内边界的颜色
		mBorderInsideColor = defaultColor;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		try {
			Drawable drawable = getDrawable();
			if (drawable == null) {
				return;
			}

			if (getWidth() == 0 || getHeight() == 0) {
				return;
			}
			this.measure(0, 0);
			if (drawable.getClass() == NinePatchDrawable.class)
				return;
			Bitmap b = ((BitmapDrawable) drawable).getBitmap();
			Bitmap bitmap = b.copy(Config.ARGB_8888, true);
			if (defaultWidth == 0) {
				defaultWidth = getWidth();
			}
			if (defaultHeight == 0) {
				defaultHeight = getHeight();
			}
			// 保证重新读取图片后不会因为图片大小而改变控件宽、高的大小（针对宽、高为wrap_content布局的imageview，但会导致margin无效）
			// if (defaultWidth != 0 && defaultHeight != 0) {
			// LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
			// defaultWidth, defaultHeight);
			// setLayoutParams(params);
			// }

			int radius = 0;
			if (mBorderInsideColor != defaultColor
					&& mBorderOutsideColor != defaultColor) {// 定义画两个边框，分别为外圆边框和内圆边框
				radius = (defaultWidth < defaultHeight ? defaultWidth
						: defaultHeight) / 2 - 2 * mBorderThickness;
				// 画内圆
				drawCircleBorder(canvas, radius + mBorderThickness / 2,
						mBorderInsideColor);
				// 画外圆
				drawCircleBorder(canvas, radius + mBorderThickness
						+ mBorderThickness / 2, mBorderOutsideColor);
			} else if (mBorderOutsideColor == defaultColor) {// 定义画一个边框
				radius = (defaultWidth < defaultHeight ? defaultWidth
						: defaultHeight) / 2 - mBorderThickness;
				drawCircleBorder(canvas, radius + mBorderThickness / 2,
						mBorderInsideColor);
			} else if (mBorderInsideColor == defaultColor
					&& mBorderOutsideColor != defaultColor) {// 定义画一个边框
				radius = (defaultWidth < defaultHeight ? defaultWidth
						: defaultHeight) / 2 - mBorderThickness;
				drawCircleBorder(canvas, radius + mBorderThickness / 2,
						mBorderOutsideColor);
			} else {// 没有边框
				radius = (defaultWidth < defaultHeight ? defaultWidth
						: defaultHeight) / 2;
			}
			Bitmap roundBitmap = getCroppedRoundBitmap(bitmap, radius);
			canvas.drawBitmap(roundBitmap, defaultWidth / 2 - radius,
					defaultHeight / 2 - radius, null);
		} catch (Exception e) {
		}
	}

	/**
	 * 获取裁剪后的圆形图片
	 * 
	 * @param radius
	 *            半径
	 */
	public Bitmap getCroppedRoundBitmap(Bitmap bmp, int radius) {
		Bitmap scaledSrcBmp;
		int diameter = radius * 2;

		// 为了防止宽高不相等，造成圆形图片变形，因此截取长方形中处于中间位置最大的正方形图片
		int bmpWidth = bmp.getWidth();
		int bmpHeight = bmp.getHeight();
		int squareWidth = 0, squareHeight = 0;
		int x = 0, y = 0;
		Bitmap squareBitmap;
		if (bmpHeight > bmpWidth) {// 高大于宽
			squareWidth = squareHeight = bmpWidth;
			x = 0;
			y = (bmpHeight - bmpWidth) / 2;
			// 截取正方形图片
			squareBitmap = Bitmap.createBitmap(bmp, x, y, squareWidth,
					squareHeight);
		} else if (bmpHeight < bmpWidth) {// 宽大于高
			squareWidth = squareHeight = bmpHeight;
			x = (bmpWidth - bmpHeight) / 2;
			y = 0;
			squareBitmap = Bitmap.createBitmap(bmp, x, y, squareWidth,
					squareHeight);
		} else {
			squareBitmap = bmp;
		}

		if (squareBitmap.getWidth() != diameter
				|| squareBitmap.getHeight() != diameter) {
			scaledSrcBmp = Bitmap.createScaledBitmap(squareBitmap, diameter,
					diameter, true);

		} else {
			scaledSrcBmp = squareBitmap;
		}
		Bitmap output = Bitmap.createBitmap(scaledSrcBmp.getWidth(),
				scaledSrcBmp.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		Paint paint = new Paint();
		Rect rect = new Rect(0, 0, scaledSrcBmp.getWidth(),
				scaledSrcBmp.getHeight());

		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);
		paint.setDither(true);
		canvas.drawARGB(0, 0, 0, 0);
		canvas.drawCircle(scaledSrcBmp.getWidth() / 2,
				scaledSrcBmp.getHeight() / 2, scaledSrcBmp.getWidth() / 2,
				paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(scaledSrcBmp, rect, rect, paint);
		// bitmap回收(recycle导致在布局文件XML看不到效果)
		// bmp.recycle();
		// squareBitmap.recycle();
		// scaledSrcBmp.recycle();
		bmp = null;
		squareBitmap = null;
		scaledSrcBmp = null;
		return output;
	}

	/**
	 * 边缘画圆
	 */
	private void drawCircleBorder(Canvas canvas, int radius, int color) {
		Paint paint = new Paint();
		/* 去锯齿 */
		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);
		paint.setDither(true);
		paint.setColor(color);
		/* 设置paint的 style 为STROKE：空心 */
		paint.setStyle(Paint.Style.STROKE);
		/* 设置paint的外框宽度 */
		paint.setStrokeWidth(mBorderThickness);
		canvas.drawCircle(defaultWidth / 2, defaultHeight / 2, radius, paint);
	}
}
