package mc.cas.center.utils;

import org.springframework.util.Assert;

import java.util.*;

public class Base64Util {
    private static final char[] encodeChars = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            '+', '_'};

    private static final HashMap<Character, Integer> encodeCharsMap;

    private static final int[] orderChart1 = {0, 47, 1, 46, 2, 45, 3, 44, 4, 43, 5, 42, 6, 41, 7, 40, 8, 39, 9, 38, 10, 37, 11, 36, 12, 35,
            13, 34, 14, 33, 15, 32, 16, 31, 17, 30, 18, 29, 19, 28, 20, 27, 21, 26, 22, 25, 23, 24};

    private static final int[] orderChart2 = {14, 42, 33, 39, 20, 11, 38, 0, 32, 29, 27, 17, 2, 40, 34, 5, 1, 35, 37, 43, 18, 19, 10, 24, 31,
            15, 28, 46, 25, 4, 44, 6, 41, 7, 9, 36, 3, 21, 45, 13, 47, 16, 8, 26, 22, 12, 23, 30};


    static {
        encodeCharsMap = new HashMap<>();
        for (int i = 0; i < encodeChars.length; i++) {
            encodeCharsMap.put(encodeChars[i], i);
        }
    }

    public static String encode(String ori){
        Assert.isTrue(ori.length()<49, "origin string length over 49");
        char[]exp = new char[48];
        char[]ord = new char[48];
        Random ran = new Random();
        //填充随机字符到48位
        for (int i = 0; i < 48; i++) {
            if(i == ori.length()){
                exp[i] = '|';
            } else if(i > ori.length()){
                exp[i] = (char)(ran.nextInt(255)+1);
            }else {
                exp[i] = ori.charAt(i);
            }
        }
        for (int i = 0; i < 48; i++) {
            ord[i] = exp[orderChart1[i]];
        }
        for (int i = 0; i < 48; i++) {
            exp[i] = ord[orderChart2[i]];
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 48; ) {
            //3个字符为一组
            int a = (exp[i++] & 0xff) | (exp[i++] & 0xff) << 8 | (exp[i++] & 0xff) << 16;
            //以6位转int类型，范围0-63
            sb.append(encodeChars[a & 0x3f])
                .append(encodeChars[(a >> 6) & 0x3f])
                .append(encodeChars[(a >> 12) & 0x3f])
                .append(encodeChars[(a >> 18) & 0x3f]);
        }
        //最后得到64位的字符串
        return sb.toString();
    }

    public static String decode(String encoded){
        char[] enc = encoded.toCharArray();
        char[] exp = new char[48];
        char[] ori = new char[48];
        int j = 0;
        for (int i = 0; i < 64; ) {
            Integer c1 = encodeCharsMap.get(enc[i++]);
            Integer c2 = encodeCharsMap.get(enc[i++]);
            Integer c3 = encodeCharsMap.get(enc[i++]);
            Integer c4 = encodeCharsMap.get(enc[i++]);
            int a = c1 | c2 << 6 | c3 << 12 | c4 << 18;
            exp[j++] = (char)(a & 0xff);
            exp[j++] = (char)(a >> 8 & 0xff);
            exp[j++] = (char)(a >> 16 & 0xff);
        }
        for (int i = 0; i < 48; i++) {
            ori[orderChart2[i]] = exp[i];
        }
        for (int i = 0; i < 48; i++) {
            exp[orderChart1[i]] = ori[i];
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 48; i++) {
            if(exp[i] == '|'){
                break;
            }
            builder.append(exp[i]);
        }
        return builder.toString();
    }

    /*public static void main(String[] args) {
        Random ran = new Random();
        Set<Integer> set = new HashSet<>();
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < 48; i++) {
            int r = ran.nextInt(48);
            if(set.contains(r)){
                i--;
            }else {
                set.add(r);
                list.add(r);
            }
        }
        System.out.println(Arrays.toString(list.toArray()));

        List<Integer> list1 = new LinkedList<>();
        list1.add(0);
        for (int i = 1; i < 24; i++) {
            list1.add(48-i);
            list1.add(i);
        }
        list1.add(24);
        System.out.println(Arrays.toString(list1.toArray()));
        System.out.println(list1.size());
        String s = "sadfasdfsdafsdfasdfasdfasd";
        String encode = encode(s);
        String decode = decode(encode);
        s.equals(decode);
    }*/
}
