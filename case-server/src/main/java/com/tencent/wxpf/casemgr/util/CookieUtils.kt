package com.tencent.wxpf.casemgr.util

import org.apache.commons.lang3.StringUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.UnsupportedEncodingException
import java.net.URLDecoder
import java.net.URLEncoder
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by jeeffzheng on 2021/4/22.
 */
object CookieUtils {

    val logger: Logger = LoggerFactory.getLogger(CookieUtils::class.java)

    /**
     * 得到Cookie的值, 不编码
     *
     * @param request
     * @param cookieName
     * @return
     */
    fun getCookieValue(request: HttpServletRequest, cookieName: String?): String? {
        return getCookieValue(request, cookieName, false)
    }

    /**
     * 得到Cookie的值,
     *
     * @param request
     * @param cookieName
     * @return
     */
    fun getCookieValue(request: HttpServletRequest, cookieName: String?, isDecoder: Boolean): String? {
        val cookieList = request.cookies
        if (cookieList == null || cookieName == null) {
            return null
        }
        var retValue: String? = null
        try {
            for (i in cookieList.indices) {
                if (cookieList[i].name == cookieName) {
                    retValue = if (isDecoder) {
                        URLDecoder.decode(cookieList[i].value, "UTF-8")
                    } else {
                        cookieList[i].value
                    }
                    break
                }
            }
        } catch (e: UnsupportedEncodingException) {
            logger.error("Cookie Decode Error.", e)
        }
        return retValue
    }

    /**
     * 得到Cookie的值,
     *
     * @param request
     * @param cookieName
     * @return
     */
    fun getCookieValue(request: HttpServletRequest, cookieName: String?, encodeString: String?): String? {
        val cookieList = request.cookies
        if (cookieList == null || cookieName == null) {
            return null
        }
        var retValue: String? = null
        try {
            for (i in cookieList.indices) {
                if (cookieList[i].name == cookieName) {
                    retValue = URLDecoder.decode(cookieList[i].value, encodeString)
                    break
                }
            }
        } catch (e: UnsupportedEncodingException) {
            logger.error("Cookie Decode Error.", e)
        }
        return retValue
    }

    /**
     * 生成cookie，并指定编码
     * @param request 请求
     * @param response 响应
     * @param cookieName name
     * @param cookieValue value
     * @param encodeString 编码
     */
    fun setCookie(request: HttpServletRequest?,
                  response: HttpServletResponse,
                  cookieName: String?,
                  cookieValue: String?,
                  encodeString: String?) {
        setCookie(request, response, cookieName, cookieValue, null, encodeString, null)
    }

    /**
     * 生成cookie，并指定生存时间
     * @param request 请求
     * @param response 响应
     * @param cookieName name
     * @param cookieValue value
     * @param cookieMaxAge 生存时间
     */
    fun setCookie(request: HttpServletRequest?,
                  response: HttpServletResponse,
                  cookieName: String?,
                  cookieValue: String?,
                  cookieMaxAge: Int?) {
        setCookie(request, response, cookieName, cookieValue, cookieMaxAge, null, null)
    }

    /**
     * 设置cookie，不指定httpOnly属性
     */
    fun setCookie(request: HttpServletRequest?,
                  response: HttpServletResponse,
                  cookieName: String?,
                  cookieValue: String?,
                  cookieMaxAge: Int?,
                  encodeString: String?) {
        setCookie(request, response, cookieName, cookieValue, cookieMaxAge, encodeString, null)
    }

    /**
     * 设置Cookie的值，并使其在指定时间内生效
     *
     * @param cookieMaxAge
     * cookie生效的最大秒数
     */
    @JvmStatic
    fun setCookie(request: HttpServletRequest?,
                  response: HttpServletResponse,
                  cookieName: String?,
                  cookieValue: String?,
                  cookieMaxAge: Int?,
                  encodeString: String?,
                  httpOnly: Boolean?) {
        var cookieValue = cookieValue
        var encodeString = encodeString
        try {
            if (StringUtils.isBlank(encodeString)) {
                encodeString = "utf-8"
            }
            cookieValue = if (cookieValue == null) {
                ""
            } else {
                URLEncoder.encode(cookieValue, encodeString)
            }
            val cookie = Cookie(cookieName, cookieValue)
            if (cookieMaxAge != null && cookieMaxAge > 0) cookie.maxAge = cookieMaxAge
            cookie.path = "/"
            if (httpOnly != null) {
                cookie.isHttpOnly = httpOnly
            }
            response.addCookie(cookie)
        } catch (e: Exception) {
            logger.error("Cookie Encode Error.", e)
        }
    }

    /**
     * 得到cookie的域名
     */
    private fun getDomainName(request: HttpServletRequest): String? {
        var domainName: String? = null
        var serverName = request.requestURL.toString()
        if (serverName == null || serverName == "") {
            domainName = ""
        } else {
            serverName = serverName.toLowerCase()
            serverName = serverName.substring(7)
            val end = serverName.indexOf("/")
            serverName = serverName.substring(0, end)
            val domains = serverName.split("\\.".toRegex()).toTypedArray()
            val len = domains.size
            domainName = if (len > 3) {
                // www.xxx.com.cn
                domains[len - 3] + "." + domains[len - 2] + "." + domains[len - 1]
            } else if (len <= 3 && len > 1) {
                // xxx.com or xxx.cn
                domains[len - 2] + "." + domains[len - 1]
            } else {
                serverName
            }
        }
        if (domainName != null && domainName.indexOf(":") > 0) {
            val ary = domainName.split("\\:".toRegex()).toTypedArray()
            domainName = ary[0]
        }
        return domainName
    }
}
