package com.example.percentloading

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
class LoadingVIew: View {
    //代码创建
    constructor(context: Context):super(context){}
    //xlm创建
    constructor(context: Context, attrs:AttributeSet):super(context, attrs){
        //解析参数sttrs
        var typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.LoadingVIew)
        //取数据
        CircleFgColor = typedArray.getColor(R.styleable.LoadingVIew_CircleFgColor,Color.CYAN)
        CircleBgColor = typedArray.getColor(R.styleable.LoadingVIew_CircleBgColor,Color.CYAN)
    }

    //圆心坐标
    var cx = 0f;
    var cy = 0f;
    var radius = 0f
    val paintWidth = 50f
    //定义进度因子
    var progress = 0f
        set(value) {
            field = value
            invalidate()
        }
    //定义背景画笔颜色
    var CircleFgColor = Color.CYAN
        set(value){
            field = value
            CircleFgPaint.color = value
        }
    var CircleBgColor = Color.MAGENTA
        set(value){
            field = value
            CircleBgPaint.color = value
        }

    //定义背景圆环的画笔
    var CircleBgPaint = Paint().apply {
        color = CircleBgColor
        style = Paint.Style.STROKE
        //画笔的宽度
        strokeWidth = paintWidth
    }
    //定义前景画笔
    var CircleFgPaint = Paint().apply {
        color = CircleFgColor
        style = Paint.Style.STROKE
        strokeWidth = paintWidth
    }
    //定义文本画笔
    var TextPaint = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.FILL
        textSize = 100f
    }
    //文本需要移动的距离
    var space = (TextPaint.fontMetrics.descent - TextPaint.fontMetrics.ascent)/2 - TextPaint.fontMetrics.descent



    //获取各种尺寸
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        cx = width/2f
        cy = height/2f
        radius = Math.min(width,height)/2f - paintWidth
    }

    //绘制图形
    override fun onDraw(canvas: Canvas?) {
        canvas?.drawCircle(cx,cy,radius,CircleBgPaint)
        canvas?.drawArc( paintWidth, paintWidth,width.toFloat()-paintWidth,
            height.toFloat() - paintWidth,-90f,360f*progress,false,CircleFgPaint)
        //文本内容
        var text = "${(progress*100).toInt()}%"
        //绘制文本
        canvas?.drawText(text,cx-TextPaint.textSize/2f-20f,cy+ space,TextPaint)
    }
}