package com.ln.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Environment;
import android.telephony.TelephonyManager;

import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * 通用工具方法类
 */
public class MTools {

    /**
     * 是否存在SD卡
     *
     * @return
     */
    public static boolean isSDCardExists() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * SD卡根目录
     *
     * @return
     */
    public static String getSDPath() {
        if (!isSDCardExists()) {
            return null;
        }
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }


    public static String getDate(String pattern) {
        DateFormat format = new SimpleDateFormat(pattern);
        String time = format.format(new Date());
        return time;

    }


    public static String getDate() {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String time = format.format(date);
        return time;
    }

    /**
     * 把系统时间分割为年，月，日
     *
     * @return返回年月日数组
     */
    public static int[] splitSystemDate() {
        Calendar c = Calendar.getInstance();
        int[] date = new int[]{c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), c.get(Calendar.HOUR_OF_DAY)};
        return date;

    }

    /**
     * 获取手机系统信息标识等
     *
     * @param context
     * @return 手机唯一识别号
     */
    public static String getDeviceInfo(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String imei = tm.getDeviceId();
        return imei;

    }

    /**
     * 拼接的时间字符串进行分割
     *
     * @param date
     * @return
     */
    public static String[] DateSplit(String date) {
        String sDate[] = date.split("\\|");
        return sDate;

    }

    public static String getRandomString(int length) {
        StringBuffer buffer = new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        int range = buffer.length();
        for (int i = 0; i < length; i++) {
            sb.append(buffer.charAt(random.nextInt(range)));
        }
        return sb.toString();
    }

    /*
     * 时间以点分割
     */
    public static String[] pointSplit(String str) {

        String sli[] = str.split("\\.");

        return sli;
    }

    /**
     * unix时间戳转换成特定格式字符串日期
     *
     * @param timeStr unix时间
     * @return 转换后的日期
     */
    @SuppressLint("SimpleDateFormat")
    public static String unixTime2Date(String timeStr) {
        Long timestamp = Long.parseLong(timeStr);
        String date = new SimpleDateFormat("yyyy.MM.dd HH:mm").format(new Date(timestamp));
        return date;
    }

    /**
     * 判断json串是否是正确的格式
     *
     * @param json
     * @return
     */
    public static boolean isGoodJson(String json) {

        try {
            new JsonParser().parse(json);
            return true;
        } catch (JsonParseException e) {
            System.out.println("bad json: " + json);
            return false;
        }
    }

    /**
     * 比较时间相差分钟数
     *
     * @param pattern 日期格式
     * @return string日期
     * @throws ParseException
     */
    public static long CompaerMill(String shiftTime, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);// 日期字符串格式
        String strCurrentDate = sdf.format(new Date());
        try {
            Date date1 = sdf.parse(shiftTime);// 按照格式解析字符串，返回Date对象
            Date date2 = sdf.parse(strCurrentDate);
            long seconds = (date1.getTime() - date2.getTime()) / 1000;// 得出两个时间的毫秒差，再除以1000得到相差秒数
            long minutes = seconds / 60;// 将秒数换算成分钟数
            System.out.println(minutes);
            return minutes;
        } catch (Exception e) {
        }
        return 0;

    }

    /**
     * 保留两位小数
     *
     * @param str
     * @return
     */
    public static String getString2Float(String str) {
        Double d = Double.valueOf(str);
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return decimalFormat.format(d);
    }

    // 获取当前版本的版本号
    public static String getVersion(Context mContext) {
        try {
            PackageManager packageManager = mContext.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(mContext.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return "版本号未知";
        }
    }

    /**
     * 产生随机数判断是偶数还是奇数
     *
     * @param n 生成一个1-n的整数
     * @return true 为偶数，false为奇数
     */
    public static boolean random(int n) {
        Random random = new Random();
        int num = random.nextInt(n) + 1;
        if (num % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }
}
