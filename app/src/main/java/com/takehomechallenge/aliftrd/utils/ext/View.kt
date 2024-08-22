package com.takehomechallenge.aliftrd.utils.ext

import android.view.View

fun View.gone() {
    visibility = View.GONE
}

fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.show() {
    visibility = View.VISIBLE
}

infix fun View.click(click: () -> Unit) {
    setOnClickListener {
        click()
    }
}