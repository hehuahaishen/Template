package com.example.common.util;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.example.common.app.BaseApp;


/**
 * Resource -- 获取资源id   或是 获取资源的值
 */
public class ResUtils {

	/*-----------------------  获取资源id --------------------------*/
	public static int getLayoutResIDByName(Context context, String name) {
		return context.getResources().getIdentifier(name, "layout",
				context.getPackageName());
	}

	public static int getIdResIDByName(Context context, String name) {
		return context.getResources().getIdentifier(name, "id",
				context.getPackageName());
	}

	// ResUtil.getStringResIDByName(this, "app_name")
	public static int getStringResIDByName(Context context, String name) {
		return context.getResources().getIdentifier(name, "string",
				context.getPackageName());
	}

	public static int getColorResIDByName(Context context, String name) {
		return context.getResources().getIdentifier(name, "color",
				context.getPackageName());
	}

	public static int getDrawableResIDByName(Context context, String name) {
		return context.getResources().getIdentifier(name, "drawable",
				context.getPackageName());
	}

	public static int getMipmapResIDByName(Context context, String name) {
		return context.getResources().getIdentifier(name, "mipmap",
				context.getPackageName());
	}

	public static int getRawResIDByName(Context context, String name) {
		return context.getResources().getIdentifier(name, "raw",
				context.getPackageName());
	}


	/*-----------------------  获取资源的值 --------------------------*/
	public static String getString(int resId) {
		return BaseApp.getContext().getResources().getString(resId);
	}

	public static String getString(int resId, Object... format) {
		return String.format(BaseApp.getContext().getResources().getString(resId), format);
	}


	public static String getString(Context context, int resId) {
		return context.getResources().getString(resId);
	}

	public static String getString(Context context, int resId, Object... format) {
		return String.format(context.getResources().getString(resId), format);
	}


	public static int getColor(Context context, int colorId) {
		return ContextCompat.getColor(context, colorId);
	}

	public static int getColor(int colorId) {
		return ContextCompat.getColor(BaseApp.getContext(), colorId);
	}
}
