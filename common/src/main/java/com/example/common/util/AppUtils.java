package com.example.common.util;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import java.lang.reflect.Method;

/**
 * 跟App相关的辅助类
 *
 */
public class AppUtils {

    private AppUtils() {
		/** cannot be instantiated : 不能被实例化 */
        throw new UnsupportedOperationException("cannot be instantiated");

    }

    /**
     * 获取设备id(AndroidId)
     * @return
     */
    public static String getAndroidId(ContentResolver contentResolver){
        String id = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID);
        return id;
    }
    /**
     * 获取应用程序名称
     */
    public static String getAppName(Context context) {
        try {
            //1,包管理者对象packageManager
            PackageManager packageManager = context.getPackageManager();
            //2,从包的管理者对象中,获取指定包名的基本信息(版本名称,版本号),传0代表获取基本信息
            //getPackageName()得到本应用的包名
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * [获取应用程序版本名称信息]<p>
     *
     * 1,包管理者对象packageManager——<br>
     * 2,从包的管理者对象中,获取指定包名的基本信息(版本名称,版本号)——"PackageInfo"——<br>
     * getPackageName()得到本应用的包名<br>
     *
     * @param context
     * @return 当前应用的版本名称
     */
    public static String getVersionName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;

        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 返回版本号<p>
     * 1,包管理者对象packageManager——<br>
     * 2,从包的管理者对象中,获取指定包名的基本信息(版本名称,版本号)——"PackageInfo"——<br>
     * getPackageName()得到本应用的包名<br>
     *
     * @param context
     * @return
     * 			非0 则代表获取成功
     */
    public static int getVersionCode(Context context) {
        //1,包管理者对象packageManager
        PackageManager packageManager = context.getPackageManager();
        //2,从包的管理者对象中,获取指定包名的基本信息(版本名称,版本号),传0代表获取基本信息
        //getPackageName()得到本应用的包名
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            //3,获取版本名称
            return packageInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    /*-------------------------------------------------------------------*/
    /**
     * 获取设备号
     * @return
     */
    public static String getDeviceNum() {
        return android.os.Build.MODEL;
    }


    /**
     * 获取设备序列号
     *
     * @return
     */
    public static String getSerialNumber(){
        String serial = null;
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get =c.getMethod("get", String.class);
            serial = (String)get.invoke(c, "ro.serialno");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return serial;
    }


    /**
     * 优点：
     *      1.根据不同的手机设备返回IMEI，MEID或者ESN码，唯一性良好 。
     * 不足：
     *      1.非手机：如平板电脑，像这样设备没有通话的硬件功能，系统中也就没有TELEPHONY_SERVICE，自然也就无法获得DEVICE_ID;
     *      2.权限问题：获取DEVICE_ID需要READ_PHONE_STATE权限；
     *      3.厂商定制系统中的Bug：少数手机设备上，由于该实现有漏洞，会返回垃圾，如:00000000或者****
     * @return
     */
    public static String getDeviceId(Context context){
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String IMEI = tm.getDeviceId();
        return IMEI;
    }


    /**
     * The WLAN MAC Address string
     *  是另一个唯一ID。但是你需要为你的工程加入android.permission.ACCESS_WIFI_STATE 权限，否则这个地址会为null。
     *  WifiManager wm = (WifiManager)getSystemService(Context.WIFI_SERVICE);
     *  String m_szWLANMAC = wm.getConnectionInfo().getMacAddress();
     *  Returns: 00:11:22:33:44:55 (这不是一个真实的地址。而且这个地址能轻易地被伪造。).WLan不必打开，就可读取些值。
     *
     * @return
     */
    public static String getWLAN_MAC(Context context){
        @SuppressLint("WifiManagerLeak")
        WifiManager wm = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        String m_szWLANMAC = wm.getConnectionInfo().getMacAddress();
        return m_szWLANMAC;
    }

    /**
     * The BT MAC Address string
     *  只在有蓝牙的设备上运行。并且要加入android.permission.BLUETOOTH 权限.
     *
     * @return
     */
    public static String getBT_MAC(){
        BluetoothAdapter m_BluetoothAdapter = null; // Local Bluetooth adapter
        m_BluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        String m_szBTMAC = m_BluetoothAdapter.getAddress();
        return m_szBTMAC;
    }
}
