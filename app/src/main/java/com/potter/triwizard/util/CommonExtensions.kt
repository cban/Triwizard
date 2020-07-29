package com.potter.triwizard.util

import android.view.View

fun List<String>.concat() = this.joinToString(" | ") { it }

fun View.show() : View {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
    return this
}
fun View.hide() : View {
    if (visibility != View.INVISIBLE) {
        visibility = View.INVISIBLE
    }
    return this
}
fun View.remove() : View {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
    return this
}
inline fun View.showIf(condition: () -> Boolean) : View {
    if (visibility != View.VISIBLE ) {
        visibility = View.VISIBLE
    }
    return this
}
inline fun View.hideIf(predicate: () -> Boolean) : View {
    if (visibility != View.INVISIBLE) {
        visibility = View.INVISIBLE
    }
    return this
}
