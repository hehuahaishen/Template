package com.example.common.util;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class StringUtils {

    public static boolean isNotEmpty(String str) {
        return !TextUtils.isEmpty(str) && !str.trim().equals("");
    }

    public static boolean isEmpty(String str) {
        return TextUtils.isEmpty(str) || str.trim().equals("");
    }



    /**
     * 在"资源字符串"中，删除对应的"文件路径"
     *
     * @param parent        资源字符串
     * @param sub           要删除的"文件路径"
     * @return
     */
    public static String deleteSubStr(String parent, String sub) {
        if (StringUtils.isNotEmpty(parent) && StringUtils.isNotEmpty(sub)) {

            if (parent.contains(sub + ",")) {                   // 如果是最后一个        包含
                return parent.replace(sub + ",", "");

            } else if (parent.contains("," + sub)) {            // 如果不是最后一个      包含
                return parent.replace("," + sub, "");

            } else if (parent.equals(sub.trim())){              // 如果只有一个
                return parent.replace(sub, "");
            }

        }
        return parent;
    }

    /**
     * 判断字符串是否是纯数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

    /**
     * 验证邮箱的正则表达式
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        boolean flag = false;
        try {
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }


}
