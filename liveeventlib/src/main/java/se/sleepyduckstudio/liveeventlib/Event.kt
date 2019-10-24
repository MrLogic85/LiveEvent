package se.sleepyduckstudio.liveeventlib

class Event<T : Any>(val value: T) {
    var isHandled: Boolean = false

    fun consume(): T {
        isHandled = true
        return value
    }
}