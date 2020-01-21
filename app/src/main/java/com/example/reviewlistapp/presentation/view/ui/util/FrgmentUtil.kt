package com.example.reviewlistapp.presentation.view.ui.util

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

object FrgmentUtil  {

    fun AppCompatActivity.replaceFragment(fragment: Fragment,
                                          tag: String,
                                          allowStateLoss: Boolean = false,
                                          @IdRes containerViewId: Int) {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        if (!supportFragmentManager.isStateSaved) {
            ft.replace(containerViewId, fragment, tag)
            ft.commit()
        } else if (allowStateLoss) {
            ft.commitAllowingStateLoss()
        }
    }
}