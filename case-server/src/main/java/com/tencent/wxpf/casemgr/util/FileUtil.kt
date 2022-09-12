package com.tencent.wxpf.casemgr.util

import com.tencent.wxpf.casemgr.constants.enums.StatusCode
import com.tencent.wxpf.casemgr.entity.exception.CaseServerException
import com.tencent.wxpf.casemgr.extension.makeParentAndNewFile
import com.tencent.wxpf.casemgr.extension.readAll
import java.io.*
import java.nio.charset.Charset
import java.util.*
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import java.util.zip.ZipOutputStream

/**
 * Created by jeeffzheng on 2019/9/29.
 */
object FileUtil {

    /**
     * 解压文件
     * @param zipPath 要解压的目标文件
     * @param descDir 指定解压目录
     * @return 解压结果：成功，失败
     */
    @JvmStatic
    fun decompressZip(zipPath: String?, descDir: String): Boolean {
        val zipFile = File(zipPath)
        var flag = false
        val pathFile = File(descDir)
        pathFile.mkdirs()
        try {
            val zip = ZipFile(zipFile, Charset.forName("gbk"))
            val entries: Enumeration<*> = zip.entries()
            while (entries.hasMoreElements()) {
                val entry = entries.nextElement() as ZipEntry
                val zipEntryName = entry.name
                val inStream = zip.getInputStream(entry)
                //指定解压后的文件夹+当前zip文件的名称
                val outPath = (descDir + zipEntryName).replace("/", File.separator)
                //判断路径是否存在,不存在则创建文件路径
                val file = File(outPath.substring(0, outPath.lastIndexOf(File.separator)))
                file.mkdirs()
                //判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
                if (File(outPath).isDirectory) {
                    continue
                }
                //保存文件路径信息（可利用md5.zip名称的唯一性，来判断是否已经解压）
                System.err.println("当前zip解压之后的路径为：$outPath")
                val outStream: OutputStream = FileOutputStream(outPath)
                val buf1 = ByteArray(2048)
                var len: Int
                while (inStream.read(buf1).also { len = it } > 0) {
                    outStream.write(buf1, 0, len)
                }
                inStream.close()
                outStream.close()
            }
            flag = true
            //必须关闭，要不然这个zip文件一直被占用着，要删删不掉，改名也不可以，移动也不行，整多了，系统还崩了。
            zip.close()
        } catch (e: IOException) {
            throw CaseServerException("解压文件失败：" + e.message, StatusCode.FILE_IMPORT_ERROR)
        }
        return flag
    }

    /**
     * 压缩文件
     * @param sourcePath 要压缩的文件夹
     * @param destPath 压缩文件
     * @return 压缩文件
     */
    @JvmStatic
    fun compressZip(sourcePath: String?, destPath: String?) {
        val resourcesFile = File(sourcePath)
        var outputStream: FileOutputStream? = null
        try {
            outputStream = FileOutputStream(destPath)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        val out = ZipOutputStream(BufferedOutputStream(outputStream))
        try {
            createCompressedFile(out, resourcesFile, "")
            out.close()
        } catch (e: Exception) {
            e.printStackTrace()
            throw CaseServerException("压缩文件失败：${e.message}", StatusCode.FILE_EXPORT_ERROR)
        }
    }

    @Throws(Exception::class)
    fun createCompressedFile(out: ZipOutputStream, file: File, dir: String) {
        var dir = dir
        if (file.isDirectory) {
            //得到文件列表信息
            val files = file.listFiles()
            //将文件夹添加到下一级打包目录
            out.putNextEntry(ZipEntry("$dir/"))
            dir = if (dir.isEmpty()) "" else "$dir/"
            //循环将文件夹中的文件打包
            for (i in files.indices) {
                createCompressedFile(out, files[i], dir + files[i].name)
            }
        } else {
            //文件输入流
            val fis = FileInputStream(file)
            out.putNextEntry(ZipEntry(dir))
            //进行写操作
            var j = 0
            val buffer = ByteArray(1024)
            while (fis.read(buffer).also { j = it } > 0) {
                out.write(buffer, 0, j)
            }
            //关闭输入流
            fis.close()
        }
    }

    /** 读取解析json文件 */
    @JvmStatic
    fun readJsonFile(filePath: String): String {
        var jsonStr = ""
        val fileName = "content.json"
        val jsonFile = (filePath + fileName).replace("/", File.separator)
        return try {
            val file = File(jsonFile)
            val fileReader = FileReader(jsonFile)
            val reader: Reader = InputStreamReader(FileInputStream(file), "utf-8")
            var ch = 0
            val sb = StringBuffer()
            while (reader.read().also { ch = it } != -1) {
                sb.append(ch.toChar())
            }
            fileReader.close()
            reader.close()
            jsonStr = sb.toString()
            jsonStr
        } catch (e: IOException) {
            e.printStackTrace()
            throw CaseServerException("读取json文件失败：" + e.message, StatusCode.FILE_IMPORT_ERROR)
        }
    }

    @JvmStatic
    fun readFromFile(fullPath: String) = File(fullPath).readAll()

    /**
     * ## 将字符串写入到文件中
     */
    @JvmStatic
    fun writeToFile(fullPath: String, content: String) {
        File(fullPath)
            .apply { makeParentAndNewFile() }
            .outputStream()
            .write(content.toByteArray())
    }
}
