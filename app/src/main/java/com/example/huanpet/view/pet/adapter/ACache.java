package com.example.huanpet.view.pet.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;


import com.example.huanpet.utils.util.EntityToMap;
import com.example.huanpet.utils.util.Md5Encrypt;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 
 * @描述 : 可以缓存普通的字符串，JsonObject、 JsonArray、Bitmap、Drawable、序列化的java对象，和 byte数据�??
 * @类名 : ACache
 * @作�?? : Android - yhq
 * @版本 : v1.0
 * @日期 : 2016�??4�??6�??
 */
public class ACache {

	public static final String IMG = "img";
	public static final String TXT = "txt";
	public static final String FILE = "file";
	public static final String LIST = "list";
	public static final String JSONOBJECT = "JSONObject";
	public static final String PARSEARRAY = "parseArray";
	public static final String JSON = "json";
	public static final String ENTITY = "entity";
	// 首页缓存
	public static final String MAIN_LIST = "main";

	public static final int TIME_HOUR = 60 * 60;
	public static final int TIME_DAY = TIME_HOUR * 24;
	public static final int MAX_SIZE = 1000 * 1000 * 500; // 50 mb
	public static final int MAX_COUNT = Integer.MAX_VALUE; // 不限制存放数据的数量
	public static Map<String, ACache> mInstanceMap = new HashMap<String, ACache>();
	public ACacheManager mCache;
	public static ACache cache;
	public static final File FILE_PATH = new File(Environment
			.getExternalStorageDirectory().getPath()
			+ File.separator
			+ "HuanHuanCache");

	public static final String SEP1 = "#";
	public static final String SEP2 = "|";

	public static ACache get(Context ctx) {
		return get(ctx, "ACache");
	}

	/**
	 * 
	 * @描述 : 初始�??
	 * @方法名称 : getInstance---->ACache.java
	 * @作�?? : Android - yhq
	 * @创建日期 : 2016�??4�??7�?? 上午10:14:38
	 */
	public static ACache getInstance() {
		if (cache == null) {
			cache = new ACache(FILE_PATH, MAX_SIZE, MAX_COUNT);
		}
		return cache;
	}

	public static ACache get(Context ctx, String cacheName) {
		File f = new File(ctx.getCacheDir(), cacheName);
		return get(f, MAX_SIZE, MAX_COUNT);
	}

	public static ACache get(File cacheDir) {
		return get(cacheDir, MAX_SIZE, MAX_COUNT);
	}

	public static <T> String getMD5FileName(T t, String url) {
		return Md5Encrypt.md5((MapToString(EntityToMap.ConvertObjToMap(t))
				+ SEP2 + url), "UTF-8");
	}

	public static String getMD5FileName(String url) {
		return Md5Encrypt.md5(url, "UTF-8");
	}

	public static String getMD5FileName(Map<String, Object> params, String url) {
		return Md5Encrypt.md5((MapToString(params) + SEP2 + url), "UTF-8");
	}

	/**
	 * Map转换String
	 * 
	 * @param map
	 *            :�??要转换的Map
	 * @return String转换后的字符�??
	 */
	public static String MapToString(Map<?, ?> map) {
		StringBuffer sb = new StringBuffer();
		// 遍历map
		for (Object obj : map.keySet()) {
			if (obj == null) {
				continue;
			}
			Object key = obj;
			Object value = map.get(key);
			if (value instanceof List<?>) {
				sb.append(key.toString() + SEP1 + ListToString((List<?>) value));
				sb.append(SEP2);
			} else if (value instanceof Map<?, ?>) {
				sb.append(key.toString() + SEP1
						+ MapToString((Map<?, ?>) value));
				sb.append(SEP2);
			} else {
				sb.append(key.toString() + SEP1 + value.toString());
				sb.append(SEP2);
			}
		}
		return "M" + sb.toString();
	}

	/**
	 * List转换String
	 * 
	 * @param list
	 *            :�??要转换的List
	 * @return String转换后的字符�??
	 */
	public static String ListToString(List<?> list) {
		StringBuffer sb = new StringBuffer();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) == null || list.get(i) == "") {
					continue;
				}
				// 如果值是list类型则调用自�??
				if (list.get(i) instanceof List) {
					sb.append(ListToString((List<?>) list.get(i)));
					sb.append(SEP1);
				} else if (list.get(i) instanceof Map) {
					sb.append(MapToString((Map<?, ?>) list.get(i)));
					sb.append(SEP1);
				} else {
					sb.append(list.get(i));
					sb.append(SEP1);
				}
			}
		}
		return "L" + sb.toString();
	}

	public static ACache get(Context ctx, long max_zise, int max_count) {
		File f = new File(ctx.getCacheDir(), "ACache");
		return get(f, max_zise, max_count);
	}

	public static ACache get(File cacheDir, long max_zise, int max_count) {
		ACache manager = mInstanceMap.get(cacheDir.getAbsoluteFile() + myPid());
		if (manager == null) {
			manager = new ACache(cacheDir, max_zise, max_count);
			mInstanceMap.put(cacheDir.getAbsolutePath() + myPid(), manager);
		}
		return manager;
	}

	public static String myPid() {
		return "_" + android.os.Process.myPid();
	}

	public ACache(File cacheDir, long max_size, int max_count) {
		if (!cacheDir.exists() && !cacheDir.mkdirs()) {
			throw new RuntimeException("can't make dirs in "
					+ cacheDir.getAbsolutePath());
		}
		mCache = new ACacheManager(cacheDir, max_size, max_count);
	}

	/**
	 * Provides a means to save a cached file before the data are available.
	 * Since writing about the file is complete, and its close method is called,
	 * its contents will be registered in the cache. Example of use:
	 * 
	 * ACache cache = new ACache(this) try { OutputStream stream =
	 * cache.put("myFileName") stream.write("some bytes".getBytes()); // now
	 * update cache! stream.close(); } catch(FileNotFoundException e){
	 * e.printStackTrace() }
	 */
	public class xFileOutputStream extends FileOutputStream {
		File file;

		public xFileOutputStream(File file) throws FileNotFoundException {
			super(file);
			this.file = file;
		}

		public void close() throws IOException {
			super.close();
			mCache.put(file);
		}
	}

	// =======================================
	// ============ String数据 读写 ==============
	// =======================================
	/**
	 * 保存 String数据 �?? 缓存�??
	 * 
	 * @param key
	 *            保存的key
	 * @param value
	 *            保存的String数据
	 */
	public void put(String key, String value) {
		File file = mCache.newFile(key);
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(file), 1024);
			out.write(value);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.flush();
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			mCache.put(file);
		}
	}

	/**
	 * 保存 String数据 �?? 缓存�??
	 * 
	 * @param key
	 *            保存的key
	 * @param value
	 *            保存的String数据
	 * @param saveTime
	 *            保存的时间，单位：秒
	 */
	public void put(String key, String value, int saveTime) {
		put(key, Utils.newStringWithDateInfo(saveTime, value));
	}

	/**
	 * 读取 String数据
	 * 
	 * @param key
	 * @return String 数据
	 */
	public String getAsString(String key) {
		File file = mCache.get(key);
		if (!file.exists())
			return null;
		boolean removeFile = false;
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(file));
			String readString = "";
			String currentLine;
			while ((currentLine = in.readLine()) != null) {
				readString += currentLine;
			}
			if (!Utils.isDue(readString)) {
				return Utils.clearDateInfo(readString);
			} else {
				removeFile = true;
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (removeFile)
				remove(key);
		}
	}

	// =======================================
	// ============= JSONObject 数据 读写 ==============
	// =======================================
	/**
	 * 保存 JSONObject数据 �?? 缓存�??
	 * 
	 * @param key
	 *            保存的key
	 * @param value
	 *            保存的JSON数据
	 */
	public void put(String key, JSONObject value) {
		put(key, value.toString());
	}

	/**
	 * 保存 JSONObject数据 �?? 缓存�??
	 * 
	 * @param key
	 *            保存的key
	 * @param value
	 *            保存的JSONObject数据
	 * @param saveTime
	 *            保存的时间，单位：秒
	 */
	public void put(String key, JSONObject value, int saveTime) {
		put(key, value.toString(), saveTime);
	}

	/**
	 * 读取JSONObject数据
	 * 
	 * @param key
	 * @return JSONObject数据
	 */
	public JSONObject getAsJSONObject(String key) {
		String JSONString = getAsString(key);
		try {
			JSONObject obj = new JSONObject(JSONString);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// =======================================
	// ============ JSONArray 数据 读写 =============
	// =======================================
	/**
	 * 保存 JSONArray数据 �?? 缓存�??
	 * 
	 * @param key
	 *            保存的key
	 * @param value
	 *            保存的JSONArray数据
	 */
	public void put(String key, JSONArray value) {
		put(key, value.toString());
	}

	/**
	 * 保存 JSONArray数据 �?? 缓存�??
	 * 
	 * @param key
	 *            保存的key
	 * @param value
	 *            保存的JSONArray数据
	 * @param saveTime
	 *            保存的时间，单位：秒
	 */
	public void put(String key, JSONArray value, int saveTime) {
		put(key, value.toString(), saveTime);
	}

	/**
	 * 读取JSONArray数据
	 * 
	 * @param key
	 * @return JSONArray数据
	 */
	public JSONArray getAsJSONArray(String key) {
		String JSONString = getAsString(key);
		try {
			JSONArray obj = new JSONArray(JSONString);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// =======================================
	// ============== byte 数据 读写 =============
	// =======================================
	/**
	 * 保存 byte数据 �?? 缓存�??
	 * 
	 * @param key
	 *            保存的key
	 * @param value
	 *            保存的数�??
	 */
	public void put(String key, byte[] value) {
		File file = mCache.newFile(key);
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(file);
			out.write(value);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.flush();
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			mCache.put(file);
		}
	}

	/**
	 * Cache for a stream
	 * 
	 * @param key
	 *            the file name.
	 * @return OutputStream stream for writing data.
	 * @throws FileNotFoundException
	 *             if the file can not be created.
	 */
	public OutputStream put(String key) throws FileNotFoundException {
		return new xFileOutputStream(mCache.newFile(key));
	}

	/**
	 * 
	 * @param key
	 *            the file name.
	 * @return (InputStream or null) stream previously saved in cache.
	 * @throws FileNotFoundException
	 *             if the file can not be opened
	 */
	public InputStream get(String key) throws FileNotFoundException {
		File file = mCache.get(key);
		if (!file.exists())
			return null;
		return new FileInputStream(file);
	}

	/**
	 * 保存 byte数据 �?? 缓存�??
	 * 
	 * @param key
	 *            保存的key
	 * @param value
	 *            保存的数�??
	 * @param saveTime
	 *            保存的时间，单位：秒
	 */
	public void put(String key, byte[] value, int saveTime) {
		put(key, Utils.newByteArrayWithDateInfo(saveTime, value));
	}

	/**
	 * 获取 byte 数据
	 * 
	 * @param key
	 * @return byte 数据
	 */
	public byte[] getAsBinary(String key) {
		RandomAccessFile RAFile = null;
		boolean removeFile = false;
		try {
			File file = mCache.get(key);
			if (!file.exists())
				return null;
			RAFile = new RandomAccessFile(file, "r");
			byte[] byteArray = new byte[(int) RAFile.length()];
			RAFile.read(byteArray);
			if (!Utils.isDue(byteArray)) {
				return Utils.clearDateInfo(byteArray);
			} else {
				removeFile = true;
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (RAFile != null) {
				try {
					RAFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (removeFile)
				remove(key);
		}
	}

	// =======================================
	// ============= 序列�?? 数据 读写 ===============
	// =======================================
	/**
	 * 保存 Serializable数据 �?? 缓存�??
	 * 
	 * @param key
	 *            保存的key
	 * @param value
	 *            保存的value
	 */
	public void put(String key, Serializable value) {
		put(key, value, -1);
	}

	/**
	 * 保存 Serializable数据�?? 缓存�??
	 * 
	 * @param key
	 *            保存的key
	 * @param value
	 *            保存的value
	 * @param saveTime
	 *            保存的时间，单位：秒
	 */
	public void put(String key, Serializable value, int saveTime) {
		ByteArrayOutputStream baos = null;
		ObjectOutputStream oos = null;
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(value);
			byte[] data = baos.toByteArray();
			if (saveTime != -1) {
				put(key, data, saveTime);
			} else {
				put(key, data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
			}
		}
	}

	/**
	 * 读取 Serializable数据
	 * 
	 * @param key
	 * @return Serializable 数据
	 */
	public Object getAsObject(String key) {
		byte[] data = getAsBinary(key);
		if (data != null) {
			ByteArrayInputStream bais = null;
			ObjectInputStream ois = null;
			try {
				bais = new ByteArrayInputStream(data);
				ois = new ObjectInputStream(bais);
				Object reObject = ois.readObject();
				return reObject;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			} finally {
				try {
					if (bais != null)
						bais.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					if (ois != null)
						ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;

	}

	// =======================================
	// ============== bitmap 数据 读写 =============
	// =======================================
	/**
	 * 保存 bitmap �?? 缓存�??
	 * 
	 * @param key
	 *            保存的key
	 * @param value
	 *            保存的bitmap数据
	 */
	public void put(String key, Bitmap value) {
		put(key, Utils.Bitmap2Bytes(value));
	}

	/**
	 * 保存 bitmap �?? 缓存�??
	 * 
	 * @param key
	 *            保存的key
	 * @param value
	 *            保存�?? bitmap 数据
	 * @param saveTime
	 *            保存的时间，单位：秒
	 */
	public void put(String key, Bitmap value, int saveTime) {
		put(key, Utils.Bitmap2Bytes(value), saveTime);
	}

	/**
	 * 读取 bitmap 数据
	 * 
	 * @param key
	 * @return bitmap 数据
	 */
	public Bitmap getAsBitmap(String key) {
		if (getAsBinary(key) == null) {
			return null;
		}
		return Utils.Bytes2Bimap(getAsBinary(key));
	}

	// =======================================
	// ============= drawable 数据 读写 =============
	// =======================================
	/**
	 * 保存 drawable �?? 缓存�??
	 * 
	 * @param key
	 *            保存的key
	 * @param value
	 *            保存的drawable数据
	 */
	public void put(String key, Drawable value) {
		put(key, Utils.drawable2Bitmap(value));
	}

	/**
	 * 保存 drawable �?? 缓存�??
	 * 
	 * @param key
	 *            保存的key
	 * @param value
	 *            保存�?? drawable 数据
	 * @param saveTime
	 *            保存的时间，单位：秒
	 */
	public void put(String key, Drawable value, int saveTime) {
		put(key, Utils.drawable2Bitmap(value), saveTime);
	}

	/**
	 * 读取 Drawable 数据
	 * 
	 * @param key
	 * @return Drawable 数据
	 */
	public Drawable getAsDrawable(String key) {
		if (getAsBinary(key) == null) {
			return null;
		}
		return Utils.bitmap2Drawable(Utils.Bytes2Bimap(getAsBinary(key)));
	}

	/**
	 * 获取缓存文件
	 * 
	 * @param key
	 * @return value 缓存的文�??
	 */
	public File file(String key) {
		File f = mCache.newFile(key);
		if (f.exists())
			return f;
		return null;
	}

	/**
	 * 移除某个key
	 * 
	 * @param key
	 * @return 是否移除成功
	 */
	public boolean remove(String key) {
		return mCache.remove(key);
	}

	/**
	 * 清除�??有数�??
	 */
	public void clear() {
		mCache.clear();
	}

	/**
	 * @title 缓存管理�??
	 * @author 杨福海（michael�?? www.yangfuhai.com
	 * @version 1.0
	 */
	public class ACacheManager {
		public final AtomicLong cacheSize;
		public final AtomicInteger cacheCount;
		public final long sizeLimit;
		public final int countLimit;
		public final Map<File, Long> lastUsageDates = Collections
				.synchronizedMap(new HashMap<File, Long>());
		protected File cacheDir;

		public ACacheManager(File cacheDir, long sizeLimit, int countLimit) {
			this.cacheDir = cacheDir;
			this.sizeLimit = sizeLimit;
			this.countLimit = countLimit;
			cacheSize = new AtomicLong();
			cacheCount = new AtomicInteger();
			calculateCacheSizeAndCacheCount();
		}

		/**
		 * 计算 cacheSize和cacheCount
		 */
		public void calculateCacheSizeAndCacheCount() {
			new Thread(new Runnable() {
				@Override
				public void run() {
					int size = 0;
					int count = 0;
					File[] cachedFiles = cacheDir.listFiles();
					if (cachedFiles != null) {
						for (File cachedFile : cachedFiles) {
							size += calculateSize(cachedFile);
							count += 1;
							lastUsageDates.put(cachedFile,
									cachedFile.lastModified());
						}
						cacheSize.set(size);
						cacheCount.set(count);
					}
				}
			}).start();
		}

		public void put(File file) {
			int curCacheCount = cacheCount.get();
			while (curCacheCount + 1 > countLimit) {
				long freedSize = removeNext();
				cacheSize.addAndGet(-freedSize);

				curCacheCount = cacheCount.addAndGet(-1);
			}
			cacheCount.addAndGet(1);

			long valueSize = calculateSize(file);
			long curCacheSize = cacheSize.get();
			while (curCacheSize + valueSize > sizeLimit) {
				long freedSize = removeNext();
				curCacheSize = cacheSize.addAndGet(-freedSize);
			}
			cacheSize.addAndGet(valueSize);

			Long currentTime = System.currentTimeMillis();
			file.setLastModified(currentTime);
			lastUsageDates.put(file, currentTime);
		}

		public File get(String key) {
			File file = newFile(key);
			Long currentTime = System.currentTimeMillis();
			file.setLastModified(currentTime);
			lastUsageDates.put(file, currentTime);

			return file;
		}

		public File newFile(String key) {
			return new File(cacheDir, key.hashCode() + "");
		}

		public boolean remove(String key) {
			File image = get(key);
			return image.delete();
		}

		public void clear() {
			lastUsageDates.clear();
			cacheSize.set(0);
			File[] files = cacheDir.listFiles();
			if (files != null) {
				for (File f : files) {
					f.delete();
				}
			}
		}

		/**
		 * 移除旧的文件
		 * 
		 * @return
		 */
		public long removeNext() {
			if (lastUsageDates.isEmpty()) {
				return 0;
			}

			Long oldestUsage = null;
			File mostLongUsedFile = null;
			Set<Entry<File, Long>> entries = lastUsageDates.entrySet();
			synchronized (lastUsageDates) {
				for (Entry<File, Long> entry : entries) {
					if (mostLongUsedFile == null) {
						mostLongUsedFile = entry.getKey();
						oldestUsage = entry.getValue();
					} else {
						Long lastValueUsage = entry.getValue();
						if (lastValueUsage < oldestUsage) {
							oldestUsage = lastValueUsage;
							mostLongUsedFile = entry.getKey();
						}
					}
				}
			}

			long fileSize = calculateSize(mostLongUsedFile);
			if (mostLongUsedFile.delete()) {
				lastUsageDates.remove(mostLongUsedFile);
			}
			return fileSize;
		}

		public long calculateSize(File file) {
			return file.length();
		}
	}

	/**
	 * @title 时间计算工具�??
	 * @author 杨福海（michael�?? www.yangfuhai.com
	 * @version 1.0
	 */
	public static class Utils {

		/**
		 * 判断缓存的String数据是否到期
		 * 
		 * @param str
		 * @return true：到期了 false：还没有到期
		 */
		public static boolean isDue(String str) {
			return isDue(str.getBytes());
		}

		/**
		 * 判断缓存的byte数据是否到期
		 * 
		 * @param data
		 * @return true：到期了 false：还没有到期
		 */
		public static boolean isDue(byte[] data) {
			String[] strs = getDateInfoFromDate(data);
			if (strs != null && strs.length == 2) {
				String saveTimeStr = strs[0];
				while (saveTimeStr.startsWith("0")) {
					saveTimeStr = saveTimeStr
							.substring(1, saveTimeStr.length());
				}
				long saveTime = Long.valueOf(saveTimeStr);
				long deleteAfter = Long.valueOf(strs[1]);
				if (System.currentTimeMillis() > saveTime + deleteAfter * 1000) {
					return true;
				}
			}
			return false;
		}

		public static String newStringWithDateInfo(int second, String strInfo) {
			return createDateInfo(second) + strInfo;
		}

		public static byte[] newByteArrayWithDateInfo(int second, byte[] data2) {
			byte[] data1 = createDateInfo(second).getBytes();
			byte[] retdata = new byte[data1.length + data2.length];
			System.arraycopy(data1, 0, retdata, 0, data1.length);
			System.arraycopy(data2, 0, retdata, data1.length, data2.length);
			return retdata;
		}

		public static String clearDateInfo(String strInfo) {
			if (strInfo != null && hasDateInfo(strInfo.getBytes())) {
				strInfo = strInfo.substring(strInfo.indexOf(mSeparator) + 1,
						strInfo.length());
			}
			return strInfo;
		}

		public static byte[] clearDateInfo(byte[] data) {
			if (hasDateInfo(data)) {
				return copyOfRange(data, indexOf(data, mSeparator) + 1,
						data.length);
			}
			return data;
		}

		public static boolean hasDateInfo(byte[] data) {
			return data != null && data.length > 15 && data[13] == '-'
					&& indexOf(data, mSeparator) > 14;
		}

		public static String[] getDateInfoFromDate(byte[] data) {
			if (hasDateInfo(data)) {
				String saveDate = new String(copyOfRange(data, 0, 13));
				String deleteAfter = new String(copyOfRange(data, 14,
						indexOf(data, mSeparator)));
				return new String[] { saveDate, deleteAfter };
			}
			return null;
		}

		public static int indexOf(byte[] data, char c) {
			for (int i = 0; i < data.length; i++) {
				if (data[i] == c) {
					return i;
				}
			}
			return -1;
		}

		public static byte[] copyOfRange(byte[] original, int from, int to) {
			int newLength = to - from;
			if (newLength < 0)
				throw new IllegalArgumentException(from + " > " + to);
			byte[] copy = new byte[newLength];
			System.arraycopy(original, from, copy, 0,
					Math.min(original.length - from, newLength));
			return copy;
		}

		public static final char mSeparator = ' ';

		public static String createDateInfo(int second) {
			String currentTime = System.currentTimeMillis() + "";
			while (currentTime.length() < 13) {
				currentTime = "0" + currentTime;
			}
			return currentTime + "-" + second + mSeparator;
		}

		/*
		 * Bitmap �?? byte[]
		 */
		public static byte[] Bitmap2Bytes(Bitmap bm) {
			if (bm == null) {
				return null;
			}
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
			return baos.toByteArray();
		}

		/*
		 * byte[] �?? Bitmap
		 */
		public static Bitmap Bytes2Bimap(byte[] b) {
			if (b.length == 0) {
				return null;
			}
			return BitmapFactory.decodeByteArray(b, 0, b.length);
		}

		/*
		 * Drawable �?? Bitmap
		 */
		public static Bitmap drawable2Bitmap(Drawable drawable) {
			if (drawable == null) {
				return null;
			}
			// �?? drawable 的长�??
			int w = drawable.getIntrinsicWidth();
			int h = drawable.getIntrinsicHeight();
			// �?? drawable 的颜色格�??
			Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
					: Bitmap.Config.RGB_565;
			// 建立对应 bitmap
			Bitmap bitmap = Bitmap.createBitmap(w, h, config);
			// 建立对应 bitmap 的画�??
			Canvas canvas = new Canvas(bitmap);
			drawable.setBounds(0, 0, w, h);
			// �?? drawable 内容画到画布�??
			drawable.draw(canvas);
			return bitmap;
		}

		/*
		 * Bitmap �?? Drawable
		 */
		@SuppressWarnings("deprecation")
		public static Drawable bitmap2Drawable(Bitmap bm) {
			if (bm == null) {
				return null;
			}
			BitmapDrawable bd = new BitmapDrawable(bm);
			bd.setTargetDensity(bm.getDensity());
			return new BitmapDrawable(bm);
		}
	}

	static {
		if (!ACache.FILE_PATH.exists()) {
			ACache.FILE_PATH.mkdirs();
		}
	}
}
