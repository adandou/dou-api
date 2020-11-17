package com.moming.douapisdk.internal.util;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tianzong
 * @date 2020/7/21
 */

public class DouYinHashMap extends HashMap<String, String> {
    private static final long serialVersionUID = -7294799271234236668L;


    /**
     * Constructs an empty <tt>HashMap</tt> with the default initial capacity
     * (16) and the default load factor (0.75).
     */
    public DouYinHashMap() {
        super();
    }

    /**
     * Constructs a new <tt>HashMap</tt> with the same mappings as the
     * specified <tt>Map</tt>.  The <tt>HashMap</tt> is created with
     * default load factor (0.75) and an initial capacity sufficient to
     * hold the mappings in the specified <tt>Map</tt>.
     *
     * @param m the map whose mappings are to be placed in this map
     * @throws NullPointerException if the specified map is null
     */
    public DouYinHashMap(Map<? extends String, ? extends String> m) {
        super(m);
    }

    public String put(String key, Object value) {
        String strValue;

        if (value == null) {
            strValue = null;
        } else if (value instanceof String) {
            strValue = (String) value;
        } else if (value instanceof Integer) {
            strValue = ((Integer) value).toString();
        } else if (value instanceof Long) {
            strValue = ((Long) value).toString();
        } else if (value instanceof Float) {
            strValue = ((Float) value).toString();
        } else if (value instanceof Double) {
            strValue = ((Double) value).toString();
        } else if (value instanceof Boolean) {
            strValue = ((Boolean) value).toString();
        } else if (value instanceof Date) {
            strValue = StringUtils.formatDateTime((Date) value);
        } else if (value instanceof LocalDateTime) {
            strValue = StringUtils.formatLocalDateTime((LocalDateTime) value);
        } else {
            strValue = value.toString();
        }

        return this.put(key, strValue);
    }

    @Override
    public String put(String key, String value) {
        if (StringUtils.areNotEmpty(key, value)) {
            return super.put(key, value);
        } else {
            return null;
        }
    }
}
