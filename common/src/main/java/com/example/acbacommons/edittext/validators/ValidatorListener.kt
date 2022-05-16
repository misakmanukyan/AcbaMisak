package com.example.acbacommons.edittext.validators

interface ValidatorListener {
    fun isRequiredForValidation(): Boolean
    fun isValid(): Boolean
    fun showError()
}