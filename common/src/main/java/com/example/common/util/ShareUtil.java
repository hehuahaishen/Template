package com.example.common.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * describe: SharedPreferences封装类
 */
public class ShareUtil {
    public static final String NAME = "config";


    /**
     * 存储String类型的值
     *
     * @param mContext this
     * @param key      key值
     * @param value    要存储的String值
     */
    public static void putString(Context mContext, String key, String value) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(key, value).commit();
    }

    /**
     * 获取String类型的值
     *
     * @param mContext this
     * @param key      key
     * @param defValue 默认值
     * @return
     */
    public static String getString(Context mContext, String key, String defValue) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, defValue);
    }


    /**
     * 存储Int类型的值
     *
     * @param mContext this
     * @param key      key
     * @param value    要存储的Int值
     */
    public static void putInt(Context mContext, String key, int value) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt(key, value).commit();
    }


    /**
     * 获取Int类型的值
     *
     * @param mContext this
     * @param key      key
     * @param defValue 默认值
     * @return
     */
    public static int getInt(Context mContext, String key, int defValue) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, defValue);
    }


    /**
     * 存储Boolean类型的值
     *
     * @param mContext this
     * @param key      key
     * @param value    要存储Boolean值
     */
    public static void putBoolean(Context mContext, String key, boolean value) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(key, value).commit();
    }

    /**
     * 获取Boolean类型的值
     *
     * @param mContext this
     * @param key      key
     * @param defValue 默认值
     * @return
     */
    public static boolean getBoolean(Context mContext, String key, Boolean defValue) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, defValue);
    }

    //删除 单个 key
    public static void deleShare(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().remove(key).commit();
    }

    //删除全部 key
    public static void deleAll(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();
    }



    /*---------------------  bean 以类的方式保存或提取 ------------------------*/
    public static void setBean(Context context, Object object, String key) {
        //获取编辑器
        SharedPreferences sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();

        if (object instanceof Serializable) { // object 必须实现Serializable接口，否则会出问题
            ObjectOutputStream oos = null;
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                oos = new ObjectOutputStream(baos);
                oos.writeObject(object);
                @SuppressLint({"NewApi", "LocalSuppress"})
                String string64 = new String(Base64.encode(baos.toByteArray(), 0));
                edit.putString(key, string64).commit();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    if(oos != null)
                        oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Object getBean(Context context, String key) {
        //获取编辑器
        SharedPreferences sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);

        Object obj = null;
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        try {
            String base64 = sharedPreferences.getString(key, "");
            if (base64.equals("")) {
                return null;
            }
            @SuppressLint({"NewApi", "LocalSuppress"})
            byte[] base64Bytes = Base64.decode(base64.getBytes(), 1);
            bais = new ByteArrayInputStream(base64Bytes);
            ois = new ObjectInputStream(bais);
            obj = ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(bais != null)
                    bais.close();
                if(ois != null)
                    ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return obj;

    }
}
