package com.tencent.wxpf.casemgr.util

import java.lang.Exception
import java.text.ParseException
import kotlin.jvm.JvmOverloads
import java.text.SimpleDateFormat
import java.util.*

/**
 * ## 时间类
 *
 * @author jeeffzheng
 * @date 2020/11/26
 */
object TimeUtil {

    const val DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss"

    @JvmOverloads
    fun toString(date: Date?, pattern: String? = DEFAULT_PATTERN): String? {
        return if (date == null) {
            null
        } else SimpleDateFormat(pattern).format(date)
    }

    /**
     * 时间字符串转换为Date类型
     *
     * @param timeStr 时间字符串
     * @param regex 表达式
     * @return Date类
     */
    fun transferStrToDateAnyRegex(timeStr: String?, regex: String?): Date? {
        try {
            return SimpleDateFormat(regex).parse(timeStr)
        } catch (e: ParseException) {
            // 对于没有日期的用例，这里会爆出太多的error，选择不显示了
            // LOGGER.error("[日期类转换错误]str={}, regex={}, error={}", timeStr, regex, e.getMessage());
            // e.printStackTrace();
        }
        return null
    }

    /**
     * 时间字符串转换为Date类型
     *
     * @param timeStr 时间字符串
     * @return Date类
     */
    @JvmStatic
    fun transferStrToDateInSecond(timeStr: String?): Date? {
        return transferStrToDateAnyRegex(timeStr, DEFAULT_PATTERN)
    }

    /**
     * 与原始时间1970-01-01 00:00:00 进行比较
     *
     * @param compareDate 比较日期
     * @return false 当前日期大于1970-01-01
     */
    @JvmStatic
    fun compareToOriginalDate(compareDate: Date?): Boolean {
        try {
            // 被比较日期默认为1970
            val compareTime = "1971-01-01 00:00:00"
            val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            return compareDate(df.parse(compareTime), compareDate)
        } catch (e: Exception) {
            // LOGGER.error("[日期解析出错]" );
        }
        return false
    }

    /**
     * 比较两个日期
     *
     *
     * @param comparedDate 被比较数，相当于减法的被减数
     * @param compareDate 比较数，相当于减法中的减数
     * @return true,1号为大于等于2号位
     * false,1号位小于2号位
     */
    fun compareDate(comparedDate: Date, compareDate: Date?): Boolean {
        return comparedDate >= compareDate
    }
}
