package me.androiddev.logintestapp.bindingadapter

import androidx.core.widget.doOnTextChanged
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


@BindingAdapter("set_clear_parent_error")
fun setClearError(editText: TextInputEditText, arg1: String) {

    editText.doOnTextChanged { _, _, _, _ ->
//        (editText.parent.parent as TextInputLayout).error = null
        (editText.parent.parent as TextInputLayout).isErrorEnabled = false
    }

}