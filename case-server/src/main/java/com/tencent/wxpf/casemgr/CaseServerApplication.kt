package com.tencent.wxpf.casemgr

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.SpringApplication

/**
 * ## 启动类
 *
 * @author jeeffzheng
 * @date 2021-4-21
 */
@SpringBootApplication
open class CaseServerApplication

fun main(args: Array<String>) {
    SpringApplication.run(CaseServerApplication::class.java, *args)
}
