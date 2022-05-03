package com.example.acbacommons

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.example.acbacommons.validators.ValidatorListener

class AcbaEditText : AppCompatEditText , ValidatorListener {
    private var mType = 0

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        makeView(context,attrs)
    }
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        makeView(context,attrs)
    }
    private fun makeView(context: Context, attrs: AttributeSet) {
        val incoming = context.obtainStyledAttributes(attrs, R.styleable.AcbaEditText)
        mType = incoming.getInteger(R.styleable.AcbaEditText_email, 0)
        incoming.recycle()
    }

    override fun isRequiredForValidation(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isValid(): Boolean {
        TODO("Not yet implemented")
    }

    override fun showError() {
        TODO("Not yet implemented")
    }
}