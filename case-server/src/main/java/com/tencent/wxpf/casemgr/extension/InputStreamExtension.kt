package com.tencent.wxpf.casemgr.extension

import java.io.File
import java.io.InputStream

/**
 * @FileName InputStreamExtension
 * @author JeeffZheng
 * @version 1.0.0
 * @Description TODO
 * @CreateTime 2022年09月08日 19:46:00
 */
fun InputStream.readAll() = this.buffered().reader().readText()

fun InputStream.copyTo(destPath: String) {
    File(destPath)
        .apply { makeParentAndNewFile() }
        .outputStream()
        .buffered()
        .use(this::copyTo)
}
