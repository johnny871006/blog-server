package com.johnny.blogserver.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    public static String code(String str){

        // MD5加密
        // @param str 要加密的字符串
        // @return    加密後的字符串
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[]byteDigest = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for(int offset = 0; offset < byteDigest.length; offset++){
                i = byteDigest[offset];
                if(i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            // 32位加密
            return buf.toString();
            // 16位加密
            // return buf.toString().substring(8,24)
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            return null;
        }
    }
}
