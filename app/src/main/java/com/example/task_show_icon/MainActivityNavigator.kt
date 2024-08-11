package com.example.task_show_icon

import androidx.fragment.app.Fragment

interface MainActivityNavigator {
    fun onNavigate(fragment: Fragment, isInitial: Boolean = false)
}

fun Fragment.navigateTo(fragment: Fragment) {
    (context as MainActivityNavigator).onNavigate(fragment)
}