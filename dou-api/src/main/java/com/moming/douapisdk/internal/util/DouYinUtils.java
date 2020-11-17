package com.moming.douapisdk.internal.util;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;

/**
 * @author tianzong
 * @date 2020/7/21
 */
public class DouYinUtils {
    public DouYinUtils() {
    }

    public static String signRequest(RequestParametersHolder requestHolder, String secret) {
        Map<String, String> params = requestHolder.getProtocolParams();
        String[] keys = params.keySet().toArray(new String[0]);
        Arrays.sort(keys);

        StringBuilder query = new StringBuilder();
        query.append(secret);
        for (String key: keys) {
            String value = params.get(key);
            if (StringUtils.areNotEmpty(key, value)) {
                query.append(key).append(value);
            }
        }
        query.append(secret);

        return md5(query.toString());
    }



    public static String md5(String txt) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(txt.getBytes());
            byte[] bytes = md.digest();
            int temp;
            StringBuilder builder = new StringBuilder();
            for (byte b : bytes) {
                temp = b;
                if (temp < 0) temp += 256;
                if (temp < 16) builder.append("0");
                builder.append(Integer.toHexString(temp));
            }
            return builder.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("MD5加密错误");
        }
    }
}
