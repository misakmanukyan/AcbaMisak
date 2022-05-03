package com.example.acbacommons.validators

interface ValidatorListener {
    fun isRequiredForValidation(): Boolean
    fun isValid(): Boolean
    fun showError()
}