package se.sleepyduckstudio.liveevent

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import se.sleepyduckstudio.liveeventlib.Event
import se.sleepyduckstudio.liveeventlib.MutableLiveEvent


class EventViewModel : ViewModel() {

    val string: MutableLiveData<String> = MutableLiveData()
    val event: MutableLiveData<Event<String>> = MutableLiveData()
    val liveEvent: MutableLiveEvent<String> = MutableLiveEvent()
}