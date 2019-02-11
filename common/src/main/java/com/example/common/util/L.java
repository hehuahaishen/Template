package com.example.common.util;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Log统一管理类
 */
public class L {

    private L() {
		/* cannot be instantiated ：不能被实例化*/
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /** 是否需要打印bug，可以在application的onCreate函数里面初始化 */
    public static boolean isDebug = true;
    private static final String TAG = "shen";


    /*---------------------------------------------------------------*/
    /*--------------------------- 默认tag ---------------------------*/
    /*---------------------------------------------------------------*/
    public static void i(String msg) {
        if (isDebug)
            Log.i(TAG, msg);
    }

    public static void d(String msg) {
        if (isDebug)
            Log.d(TAG, msg);
    }

    public static void e(String msg)
    {
        if (isDebug)
            Log.e(TAG, msg);
    }

    public static void v(String msg)
    {
        if (isDebug)
            Log.v(TAG, msg);
    }

    /*---------------------------------------------------------------*/
    /*--------------------------- 自定义tag --------------------------*/
    /*---------------------------------------------------------------*/
    public static void i(String tag, String msg)
    {
        if (isDebug)
            Log.i(tag, msg);
    }

    public static void d(String tag, String msg)
    {
        if (isDebug)
            Log.d(tag, msg);
    }

    public static void e(String tag, String msg)
    {
        if (isDebug)
            Log.e(tag, msg);
    }

    public static void v(String tag, String msg)
    {
        if (isDebug)
            Log.v(tag, msg);
    }


    /*---------------------------------------------------------------*/
    /*---------------- 默认tag + 打印消息 + 异常 ----------------------*/
    /*---------------------------------------------------------------*/
    public static void i(String msg, Throwable t) {
        if (isDebug)
            Log.i(TAG, msg + ":\n" + throwableInfo(t));
    }

    public static void d(String msg, Throwable t) {
        if (isDebug)
            Log.d(TAG, msg + ":\n" + throwableInfo(t));
    }

    public static void e(String msg, Throwable t) {
        if (isDebug)
            Log.e(TAG, msg + ":\n" + throwableInfo(t));
    }

    public static void v(String msg, Throwable t) {
        if (isDebug)
            Log.v(TAG, msg + ":\n" + throwableInfo(t));
    }

    /**
     * 打印这个错误出现在哪个地方
     * @param t
     * @return
     */
    public static String throwableInfo(Throwable t){
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        t.printStackTrace(printWriter);
        Throwable cause = t.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.flush();
        printWriter.close();
        String result = writer.toString();

        return result;
    }

    /*---------------------------------------------------------------*/
    /*------------------------ 打印json -----------------------------*/
    /*---------------------------------------------------------------*/
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");


    public static void printJson(String msg){
        printJson(TAG, msg, "");
    }

    public static void printJson(String msg, String headString){
        printJson(TAG, msg, headString);
    }

    public static void printJsonAll(String msg){
        printJsonAll(TAG, msg, "");
    }

    public static void printJsonAll(String msg, String headString){
        printJsonAll(TAG, msg, headString);
    }


    /**
     * 打印json   -- 缺点：数据如果过长无法全部打印
     * @param tag
     * @param msg                   打印的jsond
     * @param headString            头部消息
     */
    public static void printJson(String tag, String msg, String headString) {

        String message;
        String tempMessage = "";

        try {
            if (msg.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(msg);
                // 最重要的方法，就一行，返回格式化的json字符串，其中的数字4是缩进字符数
                message = jsonObject.toString(4);
            } else if (msg.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(msg);
                message = jsonArray.toString(4);
            } else {
                message = msg;
            }
        } catch (JSONException e) {
            message = msg;
        }

        message = headString + LINE_SEPARATOR + message;
        String[] lines = message.split(LINE_SEPARATOR);

        tempMessage = "=\n╔═══════════════════════════════════════════════════════════════════════════════════════\n";
        for (String line : lines) {
            // i(tag, "║ " + line);
            tempMessage += "║ " + line + "\n";
        }

        tempMessage += "╚═══════════════════════════════════════════════════════════════════════════════════════";
        i(tag, tempMessage);
    }


    /**
     * 打印json   -- 缺点：数据前带时间
     * @param tag
     * @param msg                   打印的jsond
     * @param headString            头部消息
     */
    public static void printJsonAll(String tag, String msg, String headString) {

        String message;

        try {
            if (msg.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(msg);
                message = jsonObject.toString(4);//最重要的方法，就一行，返回格式化的json字符串，其中的数字4是缩进字符数
            } else if (msg.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(msg);
                message = jsonArray.toString(4);
            } else {
                message = msg;
            }
        } catch (JSONException e) {
            message = msg;
        }
        printLine(tag, true);

        message = headString + LINE_SEPARATOR + message;
        String[] lines = message.split(LINE_SEPARATOR);
        for (String line : lines) {
            i(tag, "║ " + line);
        }

        printLine(tag, false);
    }


    public static void printLine(String tag, boolean isTop) {
        if (isTop) {
            i(tag, "=\n╔═══════════════════════════════════════════════════════════════════════════════════════");
        } else {
            i(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
        }
    }
}