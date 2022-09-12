package com.tencent.wxpf.casemgr.extension

/**
 * @ClassName OriginArrayAddition
 * @author JeeffZheng
 * @version 1.0.0
 * @Description TODO
 * @CreateTime 2022年08月16日 19:05:00
 */
fun FloatArray.valueString(): String {
    val builder = StringBuilder("floatArray {")
    this.forEachIndexed { index: Int, f: Float ->
        if (index != this.size) {
            builder.append("$f, ")
        } else {
            builder.append(f)
        }
    }
    builder.append("}")
    return builder.toString()
}


