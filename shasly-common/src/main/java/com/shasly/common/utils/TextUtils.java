package com.shasly.common.utils;

public class TextUtils {

    /**
     * 判null
     * @param data
     * @return
     */
    public static boolean empty(String data) {
        if (data == null || data.trim().length() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 生成随机字符串
     * @param len
     * @return
     */
    public static String getString(int len) {
        String res = "" ;
        for (int i = 0; i < len; i++) {
            //A-Z
            char n1 = (char) ((int) (Math.random() * 100) % 26 + 65);
            //a-z
            char n2 = (char) ((int) (Math.random() * 100) % 26 + 97);
            //0-9
            char n3 = (char) ((int) (Math.random() * 10) + 48);

            char[] arr = {n1, n2, n3};

            int c = (int) (Math.random() * 10) % 3 ;
            res += arr[c] ;
        }

        return res ;
    }

}
