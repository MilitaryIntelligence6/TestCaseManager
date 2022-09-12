package com.tencent.wxpf.casemgr.util

/**
 * Created by jeeffzheng on 2021/4/2.
 */
object BitBaseUtil {
    @JvmStatic
    fun mergeLong(high32: Long, low32: Long): Long {
        return high32 shl 32 or low32
    }

    @JvmStatic
    fun getHigh32(v: Long): Long {
        return v shr 32
    }

    @JvmStatic
    fun getLow32(v: Long): Long {
        return v and 0xffffffffL
    }
}
