package com.example.acbacommons.ACBAEditText.layouts

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import com.example.acbacommons.ACBAEditText.validators.ValidatorEnum
import com.example.acbacommons.ACBAEditText.validators.ValidatorListener
import com.example.acbacommons.R
import kotlin.properties.Delegates

class AcbaEditText : AppCompatEditText, ValidatorListener {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        makeView(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        makeView(context, attrs)
    }

    private var mIsRequiredForValidation by Delegates.notNull<Boolean>()
    private lateinit var mValidatorEnum: ValidatorEnum
    private var inputLayoutACBA: InputLayoutAcba? = null
    private val mTextWatcher by lazy {
        object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                inputLayoutACBA?.error = null
                removeListener()
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        }
    }

    private fun removeListener() {
        removeTextChangedListener(mTextWatcher)
    }

    private fun makeView(context: Context, attrs: AttributeSet) {
        val incomingValues = context.obtainStyledAttributes(attrs, R.styleable.AcbaEditText)
        mValidatorEnum =
            ValidatorEnum.values()[incomingValues.getInt(R.styleable.AcbaEditText_validator, 0)]
        mIsRequiredForValidation = mValidatorEnum != ValidatorEnum.NONE
        incomingValues.recycle()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        inputLayoutACBA = getInputLayout(this)
    }

    private fun getInputLayout(view: View): InputLayoutAcba? {
        return if (view.parent is InputLayoutAcba) {
            view.parent as InputLayoutAcba
        } else {
            return getInputLayout(view.parent as View)
        }
    }

    override fun isRequiredForValidation() = mIsRequiredForValidation

    override fun isValid(): Boolean {
        if (!mValidatorEnum.isMatch(text))
            showError()
        return mValidatorEnum.isMatch(text)
    }

    override fun showError() {
        inputLayoutACBA?.let { inputLayout ->
            inputLayout.error = mValidatorEnum.errorMessage
            removeTextChangedListener(mTextWatcher)
            addTextChangedListener(mTextWatcher)
        }
    }
}