package com.example.acbacommons.button

import android.content.Context
import android.os.SystemClock
import android.util.AttributeSet
import com.example.acbacommons.R
import com.google.android.material.button.MaterialButton
import kotlin.properties.Delegates


class AcbaButton: MaterialButton {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }
    private var lastClickedTime = 0L
    private var disableDoubleClick by Delegates.notNull<Boolean>()
    private var delayDuration by Delegates.notNull<Int>()


    private fun init(context: Context, attrs: AttributeSet) {

        val incomingValues = context.obtainStyledAttributes(attrs, R.styleable.AcbaButton)
        disableDoubleClick = incomingValues.getBoolean(R.styleable.AcbaButton_doubleClick, false)
        delayDuration = incomingValues.getInt(R.styleable.AcbaButton_duration, 600)
    }

    override fun setOnClickListener(l: OnClickListener?) {
        val myListener = OnClickListener { view ->
            if(disableDoubleClick){
                if(SystemClock.elapsedRealtime() - lastClickedTime > delayDuration) {
                    lastClickedTime = SystemClock.elapsedRealtime()
                    l?.onClick(view)
                }
            }else{
               l?.onClick(view)
            }
        }
        super.setOnClickListener(myListener)
    }
}