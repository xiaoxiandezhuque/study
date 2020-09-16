package com.xh.study.util;


//进制转换
public class MyStringUtil {
    private static char[] _16 = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String bytes_String16(byte[] b, int size) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(_16[b[i] >> 4 & 0xf])
                    .append(_16[b[i] & 0xf]);
        }
        return sb.toString();
    }

    //小写
    public static String bytes_String16(byte b) {

        return String.format("%02x", b);
    }

    public static byte[] hexStr2bytes(String hexStr) {

        if (hexStr.length() % 2 != 0) {//长度为单数
            hexStr = "0" + hexStr;//前面补0
        }
        char[] chars = hexStr.toCharArray();
        int len = chars.length / 2;
        byte[] bytes = new byte[len];
        for (int i = 0; i < len; i++) {
            int x = i * 2;
            bytes[i] = (byte) Integer.parseInt(String.valueOf(new char[]{chars[x], chars[x + 1]}), 16);
        }
        return bytes;
    }

    public static int byteToInt(byte b) {
        return b & 0xff;
    }

    public static int byteToInt(byte[] b) {
        return ((b[1] & 0xff) << 8) + (b[0] & 0xff);
    }
}
