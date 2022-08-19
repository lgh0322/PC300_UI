package com.vaca.pc300.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import com.vaca.pc300.R
import com.vaca.pc300.utils.Er2WaveUtil

import java.text.SimpleDateFormat

class ReportXWaveView : View {
    companion object{
        var co=74.283167f
        var cod=1f;
    }

    var canvas: Canvas? = null
    lateinit var waveData: DoubleArray
    var waveTime: String = ""
    var timeTemp = 0L
    var isStart = true
    var totalHight = 0f
    var lineSize = 431;
    var hhh = 3;
    var totalHightNumber = 0


    private val x1Paint = Paint()
    private val x2Paint = Paint()

    private val wavePaint = Paint()
    private val timePaint = Paint()
    private val linePaint = Paint()

    constructor(context: Context?, time: Long, shortArray: ShortArray) : super(context) {
        this.timeTemp = time
        waveTime = SimpleDateFormat("MM-dd HH:mm:ss").format(time)

        val ss = shortArray.size
        waveData = DoubleArray(ss) {
            Er2WaveUtil.byteTomV4x(shortArray[it])
        }
        if (ss % lineSize == 0) {
            totalHightNumber = shortArray.size / lineSize
            totalHight = shortArray.size / lineSize * hhh * co
        } else {
            totalHightNumber = shortArray.size / lineSize + 1
            totalHight = (shortArray.size / lineSize + 1) * hhh * co
        }


        init()
    }

    constructor(context: Context?, time: Long, shortArray:DoubleArray) : super(context) {
        this.timeTemp = time
        waveTime = SimpleDateFormat("MM-dd HH:mm:ss").format(time)

        val ss = shortArray.size
        waveData =shortArray
        if (ss % lineSize == 0) {
            totalHightNumber = shortArray.size / lineSize
            totalHight = shortArray.size / lineSize * hhh * co
        } else {
            totalHightNumber = shortArray.size / lineSize + 1
            totalHight = (shortArray.size / lineSize + 1) * hhh * co
        }


        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init()
    }

    override fun onMeasure(width: Int, height: Int) {
        setMeasuredDimension(getPixel(R.dimen.dp_360), totalHight.toInt())
    }

    private fun init() {
        x1Paint.apply {
            color = getColor(R.color.colorStateIndicator_02)
            style = Paint.Style.STROKE
            strokeWidth = 1f
        }
        x2Paint.apply {
            color = getColor(R.color.wavebg2)
            style = Paint.Style.STROKE
            strokeWidth = 1f
        }
        wavePaint.apply {
            color = getColor(R.color.reportWave)
            style = Paint.Style.STROKE
            isAntiAlias = true
            strokeWidth = 2.0f
        }

        timePaint.apply {
            color = getColor(R.color.report_wave_time)
            textSize = 24f
            style = Paint.Style.FILL_AND_STROKE
            isAntiAlias = true
        }

        linePaint.apply {
            color = getColor(R.color.report_wave_hint_line)
            style = Paint.Style.STROKE
            strokeWidth = 3.0f
        }

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        this.canvas = canvas
        drawWave(canvas)
    }


    private fun drawWave(canvas: Canvas) {
        canvas.drawColor(getColor(R.color.white))
        Log.e("gaga",width.toString())

        val xy = 0.1f * co
        var nn = 0f


        var km = 0
        do {
            nn = km.toFloat() * xy
            canvas.drawLine(
                nn,
                0f,
                nn,
                height.toFloat(),
                x2Paint
            )
            km++
        } while (nn <= width)

        km = 0
        do {
            nn = km * xy
            canvas.drawLine(
                0f,
                nn,
                width.toFloat(),
                nn,
                x2Paint
            )
            km++
        } while (nn <= totalHight)


        km = 0
        do {
            nn = km * xy
            canvas.drawLine(
                nn,
                0f,
                nn,
                height.toFloat(),
                x1Paint
            )
            km += 5
        } while (nn <= width)


        km = 0
        do {
            nn = km * xy
            canvas.drawLine(
                0f,
                nn,
                width.toFloat(),
                nn,
                x1Paint
            )
            km += 5
        } while (nn <= totalHight)

        val nv = width.toFloat() / lineSize

        for (k in 0 until totalHightNumber) {
            var baseH=0f;
            for (j in 0 until lineSize) {
                val index = k * lineSize + j
                if (index < waveData.size - 1) {
                    baseH = k * co * hhh + co * hhh / 2f
                    val a = baseH - waveData[index].toFloat() * co
                    val a2 = baseH - waveData[index + 1].toFloat() * co
                    canvas.drawLine(
                        j * nv,
                        a*cod,
                        (j + 1) * nv,
                        a2*cod, wavePaint
                    )
                }

            }
            waveTime = SimpleDateFormat("MM-dd HH:mm:ss").format(timeTemp)
//            canvas.drawText(waveTime, 20f, baseH +co * hhh / 2f- 15f, timePaint)
        }




        val p = Path()
        val baseY = 1.5f * co
        val x1 = 25f
        val x2 = 30f
        p.moveTo(0f, baseY)
        p.lineTo(x1, baseY)
        p.lineTo(x1, baseY - co*cod)
        p.lineTo(x1 + x2, baseY - co*cod)
        p.lineTo(x1 + x2, baseY)
        p.lineTo(x1 * 2 + x2, baseY)
        canvas.drawPath(p, linePaint)
        canvas.drawText("1mV", x1 * 2 + x2, baseY + 35f, timePaint)



    }

    private fun getPixel(resource_id: Int): Int {
        return resources.getDimensionPixelSize(resource_id)
    }

    private fun getColor(resource_id: Int): Int {
        return ContextCompat.getColor(context, resource_id)
    }
}