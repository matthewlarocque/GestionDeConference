
package com.meeting.manager.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil
{

    static final String		defaultDayFormat	= "yyyy-MM-dd";

    /**
     * @param now
     * @return 返回格式为 'yyyy-MM-dd' 的字符串
     */
    public static String getFormatedDayStr(Date now)
    {
        DateFormat df = new SimpleDateFormat(defaultDayFormat);
        String str = (now == null ? null : df.format(now));
        return str;
    }

    /**
     * 返回当前时间，格式为“yyyy-mm-dd”
     *
     * @return String
     */
    public static String getSimpleFormatedDateNow()
    {
        Date now = new Date();
        return getFormatedDayStr(now);
    }


}