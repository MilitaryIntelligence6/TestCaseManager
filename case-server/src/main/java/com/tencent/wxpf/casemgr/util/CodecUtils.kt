package com.tencent.wxpf.casemgr.util

import org.apache.commons.codec.digest.DigestUtils
import org.apache.commons.lang3.StringUtils
import java.util.UUID

/**
 * Created by jeeffzheng on 2021/4/22.
 */
object CodecUtils {

    @JvmStatic
    fun md5Hex(data: String, salt: String): String {
        var salt = salt
        if (StringUtils.isBlank(salt)) {
            salt = data.hashCode().toString() + ""
        }
        return DigestUtils.md5Hex(salt + DigestUtils.md5Hex(data))
    }

    @JvmStatic
    fun shaHex(data: String, salt: String): String {
        var salt = salt
        if (StringUtils.isBlank(salt)) {
            salt = data.hashCode().toString() + ""
        }
        return DigestUtils.sha512Hex(salt + DigestUtils.sha512Hex(data))
    }

    @JvmStatic
    fun generateSalt(): String {
        return StringUtils.replace(UUID.randomUUID().toString(), "-", "")
    }
}
