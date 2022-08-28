package com.viatom.blood.net

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

object NetCmd {

    val client = OkHttpClient();
    var url ="http://192.168.6.114"
    fun getFileUrl(name:String):String{
        return url+"/"+name;
    }

    interface OnDownloadListener {
        /**
         * 开始下载
         */
        fun onDownloadStart()

        /**
         * 下载成功
         * @param filePath 文件下载的路径
         */
        fun onDownloadSuccess(index: Int)

        fun onDownloadSuccessBytes(byteArray: ByteArray?)

        /**
         * @param progress 下载进度
         */
        fun onDownloading(index:Int,progress: Int)

        /**
         * 下载失败
         */
        fun onDownloadFailed()
    }


    fun getFile(url: String, fileName: String, listener: OnDownloadListener?, total:Int=0,index:Int=0) {
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

                        var sum: Long = 0
                        inputStream = response.body!!.byteStream()
                        fileOutputStream = FileOutputStream(file)
                        val buffer = ByteArray(1024 * 1024)
                        var len = 0
                        while (inputStream.read(buffer).also { len = it } != -1) {
                            fileOutputStream.write(buffer, 0, len)
                            sum += len.toLong()
                            val progress = (sum * 100.0f / total ).toInt()
                            Log.e("fuck","aaaa   ${sum}    ${total}")
                            listener?.onDownloading(index,progress)
                        }
                        fileOutputStream.flush()
                        listener?.onDownloadSuccess(index)
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