package com.example.acbacommons.ACBAEditText.validators

interface ValidatorListener {
    fun isRequiredForValidation(): Boolean
    fun isValid(): Boolean
    fun showError()
}