package com.moming.douapisdk.internal.util;



import com.moming.douapisdk.Constants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Pattern;

/**
 * @author tianzong
 * @date 2020/7/21
 */
public class StringUtils {
    /**
     * An empty immutable <code>String</code> array.
     */
    public static final String[] EMPTY_STRING_ARRAY = new String[0];
    private static final TimeZone TZ_GMT8 = TimeZone.getTimeZone(Constants.DATE_TIMEZONE);
    private static final Pattern PATTERN_CIDR = Pattern.compile("^(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})/(\\d{1,2})$");
    private static final String QUOT = "&quot;";
    private static final String AMP = "&amp;";
    private static final String APOS = "&apos;";
    private static final String GT = "&gt;";
    private static final String LT = "&lt;";

    private StringUtils() {}


    /**
     * 检查指定的字符串是否为空。
     * <ul>
     * <li>SysUtils.isEmpty(null) = true</li>
     * <li>SysUtils.isEmpty("") = true</li>
     * <li>SysUtils.isEmpty("   ") = true</li>
     * <li>SysUtils.isEmpty("abc") = false</li>
     * </ul>
     *
     * @param value 待检查的字符串
     * @return true/false
     */
    public static boolean isEmpty(String value) {
        int strLen;
        if (value == null || (strLen = value.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((!Character.isWhitespace(value.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    /**
     *  字符串不为空
     *
     * @param value
     * @return
     */
    public static boolean isNotEmpty(String value) {
       return  ! isEmpty(value);
    }

    /**
     * 对日期进行字符串格式化，采用yyyy-MM-dd HH:mm:ss的格式。
     */
    public static String formatDateTime(Date date) {
        DateFormat format = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
        format.setTimeZone(TZ_GMT8);
        return format.format(date);
    }

    /**
     * 对日期进行字符串格式化，采用指定的格式。
     */
    public static String formatDateTime(Date date, String pattern) {
        DateFormat format = new SimpleDateFormat(pattern);
        format.setTimeZone(TZ_GMT8);
        return format.format(date);
    }

    /**
     * 检查指定的字符串列表是否不为空。
     */
    public static boolean areNotEmpty(String... values) {
        boolean result = true;
        if (values == null || values.length == 0) {
            result = false;
        } else {
            for (String value : values) {
                result &= !isEmpty(value);
            }
        }
        return result;
    }

    /**
     * 格式化localDateTime
     * @param value 需要格式化的值
     * @return 时间格式字符串
     */
    public static String formatLocalDateTime(LocalDateTime value) {

        return value.format(DateTimeFormatter.ofPattern(Constants.DATE_TIME_FORMAT));

    }
}
