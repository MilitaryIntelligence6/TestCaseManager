package com.tencent.wxpf.casemgr.extension

import java.io.File

/**
 * @ClassName FileExtension
 * @author JeeffZheng
 * @version 1.0.0
 * @Description TODO
 * @CreateTime 2022年09月08日 19:58:00
 */
fun File.makeParentAndNewFile() {
    if (!exists()) {
        this.parentFile?.mkdirs()
        this.createNewFile()
    }
}

fun File.readAll() = this.inputStream().readAll()

fun File.copyTo(destPath: String) = this.inputStream().copyTo(destPath)
