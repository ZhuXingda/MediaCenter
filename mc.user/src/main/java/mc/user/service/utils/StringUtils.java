package mc.user.service.utils;

public class StringUtils {
    public static boolean isNullOrWhiteSpace(String s){
        return s == null || s.trim().isEmpty();
    }
}
