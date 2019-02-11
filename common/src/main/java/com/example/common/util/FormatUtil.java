package com.example.common.util;

import android.support.annotation.NonNull;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by shen on 9/13 0013.
 */
public class FormatUtil {

    /**
     * 根据传递进来的 src 源数据(String)， 和截取的位数number，截取小数点<p>
     *     如： float2DecimalFormat(2，3) ==> 2.000
     *
     * @param src       要转换的数据(float)
     * @param number   小数点位数(1~7位;超过默认是2位)
     * @return
     */
    public static String double2DecimalFormat(double src, int number){
        DecimalFormat df = getDecimalFormat(number);
        return df.format(src);
    }

    /**
     * 根据传递进来的 src 源数据(String)， 和截取的位数number，截取小数点<p>
     *     如： float2DecimalFormat(2，3) ==> 2.000
     *
     * @param src       要转换的数据(float)
     * @param number   小数点位数(1~7位;超过默认是2位)
     * @return
     */
    public static String float2DecimalFormat(float src, int number){
        DecimalFormat df = getDecimalFormat(number);
        return df.format(src);
    }


    /**
     * 根据传递进来的 src 源数据(String)， 和截取的位数number，截取小数点<p>
     *     如： str2DecimalFormat(2，3) ==> 2.000
     *
     * @param src       要转换的数据(String)
     * @param number   小数点位数(1~7位;超过默认是2位)
     * @return
     */
    public static String str2DecimalFormat(String src, int number){
        if(StringUtils.isEmpty(src)){
            return "";
        }

        DecimalFormat df = getDecimalFormat(number);
        return df.format(Float.valueOf(src));
    }

    @NonNull
    private static DecimalFormat getDecimalFormat(int number) {

        // 0是特殊模式字符，没有数字填充时，默认填写0。
        // 0  数字 是 阿拉伯数字
        // #  数字 是 阿拉伯数字，如果不存在则显示为空
        // .  数字 是 小数分隔符或货币小数分隔符

        String n = "";
        switch (number){
            case 0: n = "#0"; break;
            case 1: n = "#0.0"; break;
            case 2: n = "#0.00"; break;
            case 3: n = "#0.000"; break;
            case 4: n = "#0.0000"; break;
            case 5: n = "#0.00000"; break;
            case 6: n = "#0.000000"; break;
            case 7: n = "#0.0000000"; break;
            default: n = "#0.00"; break;
        }
        return new DecimalFormat(n);
    }


    /**
     * 如 10000 ==> 10,000
     *
     * @param d1
     * @return
     */
    public static String getFormatDoubleTwoDecimalPlaces(Double d1, int digit) {
        String df = "#,##0.";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(df);
        for (int i = 0; i < digit; i++) {
            stringBuilder.append("0");
        }
        DecimalFormat decimalFormat = new DecimalFormat(stringBuilder.toString());// 格式化设置
        return decimalFormat.format(d1);
    }

    /**
     * long补零,返回的是String <p>
     *     如： long2NumberFormat(2，3) ==> 002
     * @param src          要补零的(int)
     * @param number       总位数
     * @return
     */
    public static String long2NumberFormat(long src, int number){

        long m = src;
        NumberFormat nf = NumberFormat.getInstance();       // 得到一个NumberFormat的实例
        nf.setGroupingUsed(false);                           // 设置是否使用分组
        nf.setMaximumIntegerDigits(number);                  // 设置最大整数位数
        nf.setMinimumIntegerDigits(number);                  // 设置最小整数位数

        String data = nf.format(m);

        return data;
    }


    /**
     * String 转换成long再补零,返回的是String<p>
     *     如： str2NumberFormat(2，3) ==> 002
     * @param src          要补零的(int)
     * @param number       总位数
     * @return
     */
    public static String str2NumberFormat(String src, int number){

        NumberFormat nf = NumberFormat.getInstance();   // 得到一个NumberFormat的实例
        nf.setGroupingUsed(false);                      // 设置是否使用分组
        nf.setMaximumIntegerDigits(number);                  // 设置最大整数位数
        nf.setMinimumIntegerDigits(number);                  // 设置最小整数位数

        String data = nf.format(Long.valueOf(src));

        return data;
    }



    /**
     * 字符串 转 float
     * @param string
     * @return
     */
    public static float str2Float(String string){
        float temp = 0;
        try {
            temp = StringUtils.isEmpty(string) ? 0 : Float.valueOf(string.trim());
        }catch (NumberFormatException e){
            // LogUtils.i("str2Float -- error:" , e);
        }
        return temp;
    }

    /**
     * 字符串 转 long
     * @param string
     * @return
     */
    public static long str2Long(String string){

        while (string.length() > 0 && string.substring(0, 1).equals("0")){
            string = string.substring(1, string.length());
        }
        return StringUtils.isEmpty(string)?  0  : Long.valueOf(string);
    }

    /**
     * 字符串 转 int
     * @param string
     * @return
     */
    public static int str2Int(String string){
        return StringUtils.isEmpty(string)? 0 : Integer.valueOf(string);
    }



    /**
     * 两个浮点相加 -- 返回"字符串"格式是带两个小数点
     *
     * @param float1
     * @param float2
     * @return
     */
    public static String strFloatAdd(String float1, String float2){
        float floatTemp = str2Float(float1) + str2Float(float2);
        String strTemp = float2DecimalFormat(floatTemp, 2);
        return strTemp;
    }

    /**
     * double 乘法,确保精度不丢失
     *
     * @param d1
     * @param d2
     * @return
     */
    public static double doubleAdd(Double d1, Double d2) {
        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        return bd1.multiply(bd2).doubleValue();
    }
}
