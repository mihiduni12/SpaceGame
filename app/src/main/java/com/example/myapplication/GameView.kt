package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View

class GameView(var c: Context, var gameTask: GameTask) : View(c) {
    private var myPaint: Paint? = null
    private var speed = 1
    private var time = 0
    private var score = 0
    private var myufoPosition = 0
    private val otherufo = ArrayList<HashMap<String, Any>>()

    var viewWidth = 0
    var viewHeight = 0



    init {
        myPaint = Paint()
    }



    //when the view needs to be drawn.
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        viewWidth = width
        viewHeight = height

        if (time % 700 < 10 + speed) {
            val map = HashMap<String, Any>()
            map["lane"] = (0..2).random()
            map["startTime"] = time
            otherufo.add(map)
        }
        time = time + 10 + speed
        val ufoWidth = viewWidth / 5
        val ufoHeight = ufoWidth + 10
        myPaint!!.style = Paint.Style.FILL

        val d = resources.getDrawable(R.drawable.ufo, null)
        d.setBounds(
            myufoPosition * viewWidth / 3 + viewWidth / 15 + 25,
            viewHeight - 2 - ufoHeight,
            myufoPosition * viewWidth / 3 + viewWidth / 15 + ufoWidth - 25,
            viewHeight - 2
        )
        d.draw(canvas)

        myPaint!!.color = Color.GREEN
        for (i in otherufo.indices) {
            try {
                val ufoX = otherufo[i]["lane"] as Int * viewWidth / 3 + viewWidth / 15
                var ufoY = time - otherufo[i]["startTime"] as Int

                val d2 = resources.getDrawable(R.drawable.ast, null)
                d2.setBounds(
                    ufoX + 25, ufoY - ufoHeight, ufoX + ufoWidth - 25, ufoY
                )
                d2.draw(canvas)

                if (otherufo[i]["lane"] as Int == myufoPosition) {
                    if (ufoY > viewHeight - 2 - ufoHeight && ufoY < viewHeight - 2)
                        gameTask.closeGame(score)
                }

                if (ufoY > viewHeight + ufoHeight) {
                    otherufo.removeAt(i)
                    score++
                    speed = 1 + Math.abs(score / 8)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        myPaint!!.color = Color.WHITE
        myPaint!!.textSize = 40f
        canvas.drawText("Score : $score", 80f, 80f, myPaint!!)
        canvas.drawText("Speed : $speed", 380f, 80f, myPaint!!)
        invalidate()
    }

    // when a touch event occurs on the view.
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event!!.action) {
            MotionEvent.ACTION_DOWN -> {
                val x1 = event.x
                if (x1 < viewWidth / 2) {
                    if (myufoPosition > 0) {
                        myufoPosition--
                    }
                }
                if (x1 > viewWidth / 2) {
                    if (myufoPosition < 2) {
                        myufoPosition++
                    }
                }
                invalidate()
            }
            MotionEvent.ACTION_UP -> {
            }
        }
        return true
    }


}
