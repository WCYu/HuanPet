package com.example.huanpet.view.pet.adapter;

import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.widget.ImageView;


import com.example.huanpet.R;
import com.example.huanpet.utils.util.AppUtils;
import com.nostra13.universalimageloader.cache.disc.DiskCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;

import java.io.File;

/*
 *  String imageUri = "http://site.com/image.png"; // 网络图片  
 String imageUri = "file:///mnt/sdcard/image.png"; //SD卡图�???  
 String imageUri = "content://media/external/audio/albumart/13"; // 媒体文件�???  
 String imageUri = "assets://image.png"; // assets  
 String imageUri = "drawable://" + R.drawable.image; //  drawable文件   
 */

/**
 * 
 * @描述 : 网络访问缓存
 * @类名 : HttpCacheUtils
 * @作�?? : Android - yhq
 * @版本 : v1.0
 * @日期 : 2016�???4�???7�???
 */
public class HttpCacheUtils {
	public static DisplayImageOptions getImageOptions(int resId) {
		Options decodeOptions = new Options();
		decodeOptions.inSampleSize = 2;
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.showImageOnLoading(resId)
				// 设置图片在下载期间显示的图片
				.showImageForEmptyUri(resId)
				// 设置图片Uri为空或是错误的时候显示的图片
				.showImageOnFail(resId)
				// 设置图片加载/解码过程中错误时候显示的图片
				.cacheOnDisc(true).decodingOptions(decodeOptions)
				.bitmapConfig(Config.RGB_565)// 设置下载的图片是否缓存在SD卡中
				.build();// 构建完成
		return options;
	}

	/**
	 * 
	 * @描述 : 设置缓存图片
	 * @方法名称 : loadImage---->HttpCacheUtils.java
	 * @作�?? : Android - yhq
	 * @创建日期 : 2016�???4�???7�??? 上午11:36:16
	 * @param url
	 * @param view
	 */
	public static void loadImage(String url, ImageView view) {
		try {
			if (url.indexOf("http://") != -1 || url.indexOf("https://") != -1) {
				ImageLoader.getInstance().displayImage(url, view,
						getImageOptions(R.drawable.default_image));
			} else {
				ImageLoader.getInstance().displayImage(
						AppUtils.HTTP_IMAGE_URL + url, view,
						getImageOptions(R.drawable.default_image));
			}
		} catch (Exception e) {
			Picasso.with(AppUtils.getAppContext())
					.load(R.drawable.default_image).into(view);
		}
	}

	/**
	 * 
	 * @描述 : 加载全路径图�???
	 * @方法名称 : loadImage---->HttpCacheUtils.java
	 * @作�?? : Android - yhq
	 * @创建日期 : 2016�???4�???7�??? 上午11:36:16
	 * @param url
	 * @param view
	 */
	public static void loadImageAllPathHttp(String url, ImageView view) {
		try {
			if (url.indexOf("http://") != -1 || url.indexOf("https://") != -1) {
				ImageLoader.getInstance().displayImage(url, view,
						getImageOptions(R.drawable.default_image));
			} else {
				ImageLoader.getInstance().displayImage(url, view,
						getImageOptions(R.drawable.default_image));
			}
		} catch (Exception e) {
			Picasso.with(AppUtils.getAppContext())
					.load(R.drawable.default_image).into(view);
		}
	}

	/**
	 * 
	 * @描述 : 设置缓存图片
	 * @方法名称 : loadImage---->HttpCacheUtils.java
	 * @作�?? : Android - yhq
	 * @创建日期 : 2016�???4�???7�??? 上午11:36:16
	 * @param url
	 * @param view
	 */
	public static void loadImagePet(String url, ImageView view) {
		try {
			if (url.indexOf("http://") != -1 || url.indexOf("https://") != -1) {
				ImageLoader.getInstance().displayImage(url, view,
						getImageOptions(R.drawable.pet_default));
			} else {
				ImageLoader.getInstance().displayImage(
						AppUtils.HTTP_IMAGE_URL + url, view,
						getImageOptions(R.drawable.pet_default));
			}
		} catch (Exception e) {
			Picasso.with(AppUtils.getAppContext()).load(R.drawable.pet_default)
					.into(view);
		}
	}

	/**
	 * 
	 * @描述 : 设置缓存图片
	 * @方法名称 : loadImage---->HttpCacheUtils.java
	 * @作�?? : Android - yhq
	 * @创建日期 : 2016�???4�???7�??? 上午11:36:16
	 * @param url
	 * @param view
	 */
	public static void loadFilePetImage(String url, ImageView view) {
		try {
			if (url.indexOf("http://") != -1 || url.indexOf("https://") != -1) {
				ImageLoader.getInstance().displayImage(url, view,
						getImageOptions(R.drawable.pet_default));
			} else {
				ImageLoader.getInstance().displayImage("file://" + url, view,
						getImageOptions(R.drawable.pet_default));
			}
		} catch (Exception e) {
			Picasso.with(AppUtils.getAppContext()).load(R.drawable.pet_default)
					.into(view);
		}
	}

	/**
	 * 
	 * @描述 : 设置缓存图片
	 * @方法名称 : loadImage---->HttpCacheUtils.java
	 * @作�?? : Android - yhq
	 * @创建日期 : 2016�???4�???7�??? 上午11:36:16
	 * @param url
	 * @param view
	 */
	@SuppressWarnings("deprecation")
	public static void loadImageUser(String url, ImageView view) {
		try {
			if (url.indexOf("http://") != -1 || url.indexOf("https://") != -1) {
				ImageLoader.getInstance().displayImage(url, view,
						getImageOptions(R.drawable.user_defaults));
			} else {
				ImageLoader.getInstance().displayImage(
						AppUtils.HTTP_IMAGE_URL + url, view,
						getImageOptions(R.drawable.user_defaults));
			}
		} catch (Exception e) {
			Picasso.with(AppUtils.getAppContext())
					.load(R.drawable.user_defaults).into(view);
		}
	}

	/**
	 * 获取缓存大小
	 * 
	 * @return
	 */
	public static String getCacheSize() {
		DiskCache diskCache = ImageLoader.getInstance().getDiscCache();
		File file = diskCache.getDirectory();
		return FileSizeUtil.getAutoFileOrFilesSize(file.getPath());
	}

	/**
	 * 清理缓存
	 */
	public static void clearCache() {
		ImageLoader.getInstance().getDiscCache().clear();
		ImageLoader.getInstance().getMemoryCache().clear();
		ACache.getInstance().clear();
	}

}
