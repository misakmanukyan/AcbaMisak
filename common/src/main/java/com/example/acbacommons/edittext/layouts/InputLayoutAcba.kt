package com.example.acbacommons.edittext.layouts

import android.content.Context
import android.util.AttributeSet
import com.example.acbacommons.R
import com.google.android.material.textfield.TextInputLayout

class InputLayoutAcba: TextInputLayout {

    constructor(context: Context) : super(context)
    constructor(
        context: Context,
        attrs: AttributeSet
    ) : super(context, attrs) {
        init(attrs)
    }

    constructor(
        context: Context,
        attrs: AttributeSet,
        defStyleAttr: Int,
    ) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet) {
        val incomingValues = context.obtainStyledAttributes(attrs, R.styleable.InputLayoutAcba)
        incomingValues.recycle()
    }}