package se.sleepyduckstudio.liveeventlib

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer


class MutableLiveEvent<T : Any> : LiveEvent<T>() {

    fun postEvent(value: T) {
        events = events.filter { event -> !event.isHandled }.plus(Event(value))
        events.forEach { event -> currentEvent.value = event }
    }
}

sealed class LiveEvent<T : Any>() {
    protected var events: List<Event<T>> = listOf()
    protected val currentEvent: MutableLiveData<Event<T>> = MutableLiveData()

    @MainThread
    fun observe(owner: LifecycleOwner, observer: Observer<in Event<T>>) =
        currentEvent.observe(owner, observer)
}
