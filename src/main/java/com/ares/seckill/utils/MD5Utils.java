package com.ares.seckill.utils;


import org.apache.commons.codec.digest.DigestUtils;

public final class MD5Utils {

    private static final String salt = "xwcxwj1111";

    public static String md5(String str){
        return DigestUtils.md5Hex(str);
    }

    /**
     * 前端传向后端
     * @param pass 密码
     * @return
     */
    public static String inputPassToFormPass(String pass){
        String str = "" + salt.charAt(0) + salt.charAt(2) + pass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }


    /**
     * 前端传向后端
     * @param pass 密码
     * @return
     */
    public static String formPassToDbPass(String pass,String salt){
        String str = "" + salt.charAt(0) + salt.charAt(2) + pass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    /**
     * 前端传向后端
     * @param pass 密码
     * @return
     */
    public static String inputPassToDbPass(String pass,String salt){
        return formPassToDbPass(inputPassToFormPass(pass),salt);
    }

    public static void main(String[] args) {
        MD5Utils md5Utils = new MD5Utils();
        System.out.println(md5Utils.inputPassToFormPass("123434"));
        System.out.println(md5Utils.formPassToDbPass("678bf156112440915548b74b59362f90","xwcxwj1111"));
        System.out.println(md5Utils.inputPassToDbPass("123434","xwcxwj1111"));
    }
}
