package me.androiddev.logintestapp.common

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

var TextInputEditText.parentError: String
    get() = "someMessage"
    set(value) {
        (this.parent.parent as TextInputLayout).error = value
    }

fun Activity.closeSoftKeyboard() {
    val currentFocusView: View? = this.currentFocus
    if (currentFocusView != null) {
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocusView.windowToken, 0)
    }
}