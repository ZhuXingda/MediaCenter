package mh.cas.center.utils;

import java.util.Arrays;
import java.util.Random;

public class Base64Util {
    private static final char[] encodeChars = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            '+', '_'};


    public static String encode(String ori){
        char[]cov  = new char[48];
        if(ori.length()<48){
            int margin = 48/ori.length();
            Random ran = new Random();
            for (int i = 0; i < 48; i++) {
                if(i%margin==0&&i/margin<ori.length()){
                    cov[i] = ori.charAt(i/margin);
                }else {
                    cov[i] = (char) (ran.nextInt(256)+1);
                }
            }
        }else {
            cov = ori.substring(0,48).toCharArray();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 48; i+=3) {
            //3个字符为一组
            int a = (cov[i] & 0xff) | (cov[i+1] & 0xff) << 8 | (cov[i+2] & 0xff) << 16;
            //以6位转int类型，范围0-63
            sb.append(encodeChars[a & 0x3f]);
            sb.append(encodeChars[(a >> 6) & 0x3f]);
            sb.append(encodeChars[(a >> 12) & 0x3f]);
            sb.append(encodeChars[(a >> 18) & 0x3f]);
        }
        return sb.toString();
    }
}
