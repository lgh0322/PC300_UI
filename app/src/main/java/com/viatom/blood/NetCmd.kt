package com.viatom.blood

import android.util.Log
import com.viatom.blood.utils.PathUtil
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.lang.Exception

object NetCmd {

    val client = OkHttpClient();

    interface OnDownloadListener {
        /**
         * 开始下载
         */
        fun onDownloadStart()

        /**
         * 下载成功
         * @param filePath 文件下载的路径
         */
        fun onDownloadSuccess(filePath: String?)

        fun onDownloadSuccessBytes(byteArray: ByteArray?)

        /**
         * @param progress 下载进度
         */
        fun onDownloading(progress: Int)

        /**
         * 下载失败
         */
        fun onDownloadFailed()
    }


    fun getFile(url: String, fileName: String, listener: OnDownloadListener?) {
        val absoluteFilePath: String =PathUtil.getPathX(fileName);
        val file = File(absoluteFilePath)
        val request: Request = Request.Builder().url(url).tag("appUpdate").build()
        listener?.onDownloadStart()
        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: Call, e: java.io.IOException) {
                listener?.onDownloadFailed()
            }
            override fun onResponse(call: Call, response: Response) {
                if (200 == response.code) {
                    var fileOutputStream: FileOutputStream? = null
                    var inputStream: InputStream? = null
                    try {
                        val total = response.body!!.contentLength()
                        var sum: Long = 0
                        inputStream = response.body!!.byteStream()
                        fileOutputStream = FileOutputStream(file)
                        val buffer = ByteArray(1024 * 1024)
                        var len = 0
                        while (inputStream.read(buffer).also { len = it } != -1) {
                            fileOutputStream.write(buffer, 0, len)
                            sum += len.toLong()
                            val progress = (sum * 1.0f / total * 100).toInt()
                            listener?.onDownloading(progress)
                        }
                        fileOutputStream.flush()
                        listener?.onDownloadSuccess(absoluteFilePath)
                    } catch (e: IOException) {
                        listener?.onDownloadFailed()
                    } finally {
                        inputStream?.close()
                        fileOutputStream?.close()
                    }
                } else {
                    listener?.onDownloadFailed()
                }
            }

        }


        )
    }




    val url ="http://192.168.6.114"
    @Throws(IOException::class)
    fun getInfo(): ByteArray? {
        val request: Request = Request.Builder().url(url).build()
        client.newCall(request)
            .execute()
            .use { response ->
                response.body?.bytes()?.let {
                    Log.e("newMember", it.size.toString())
                    return it
                }
            }
        return null
    }

}