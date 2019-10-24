package se.sleepyduckstudio.liveeventlib

class Event<T : Any>(val value: T) {
    var isHandled: Boolean = false

    fun handle(): T {
        isHandled = true
        return value
    }
}