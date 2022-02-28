package com.fourio.twynapp.ui.customGuageView

import android.R.attr
import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat

import com.fourio.twynapp.R
import android.R.attr.angle
import android.graphics.*


class CustomGuageView(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    private val paint: Paint = Paint()
    private val paintProgress: Paint = Paint()
    private var angle: Float = 0f

    private var rectangle: RectF? = null
    private var margin: Float

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CustomGuageView,
            0, 0
        ).apply {

            try {
                angle = getFloat(R.styleable.CustomGuageView_angle, 0f)
            } finally {
                recycle()
            }
        }
        paint.isAntiAlias = true
        paint.color = ContextCompat.getColor(context, R.color.dark_blu_2)
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = resources.getDimensionPixelSize(R.dimen._5sdp).toFloat()
        paintProgress.isAntiAlias = true
        paintProgress.color = ContextCompat.getColor(context, R.color.light_blue)
        paintProgress.style = Paint.Style.STROKE
        paintProgress.strokeWidth = resources.getDimensionPixelSize(R.dimen._5sdp).toFloat()
        margin =
            resources.getDimensionPixelSize(com.fourio.twynapp.R.dimen._3sdp)
                .toFloat() // margin should be >= strokeWidth / 2 (otherwise the arc is cut)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (rectangle == null) {
            rectangle =
                RectF(0f + margin, 0f + margin, width.toFloat() - margin, height.toFloat() - margin)
        }
        canvas.drawArc(rectangle!!, 180f, 180f, false, paint)
        canvas.drawArc(rectangle!!, 180f, angle, false, paintProgress)
    }

    fun setAngle(angle_: Float) {
       angle = angle_
        invalidate()
        requestLayout()
    }

}