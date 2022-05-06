package com.example.acbacommons.ACBAButton

import android.content.Context
import android.os.SystemClock
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatButton
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
    private var lastTimeClicked = 0L
    private var disableDoubleClick by Delegates.notNull<Boolean>()
    private var duration by Delegates.notNull<Int>()


    private fun init(context: Context, attrs: AttributeSet) {

        val incomingValues = context.obtainStyledAttributes(attrs, R.styleable.AcbaButton)
        disableDoubleClick = incomingValues.getBoolean(R.styleable.AcbaButton_doubleClick, false)
        duration = incomingValues.getInt(R.styleable.AcbaButton_duration, 600)
    }

    override fun setOnClickListener(l: OnClickListener?) {
        val myListener = object  : OnClickListener{
            override fun onClick(p0: View?) {
                if(disableDoubleClick){

                }else{
                    lastTimeClicked = SystemClock.elapsedRealtime()
                }
                l?.onClick(p0)
            }
        }
        super.setOnClickListener(myListener)
    }
}