package se.sleepyduckstudio.liveevent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_live_event.*
import se.sleepyduckstudio.liveeventlib.Event

class LiveEventActivity : AppCompatActivity() {

    private val eventViewModel: EventViewModel by lazy { ViewModelProviders.of(this)[EventViewModel::class.java] }

    private val data = (1..5).map { "Data $it" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_event)

        // Observe each LiveData twice to showcse that events can be handled by the first observer that consumes the event
        eventViewModel.string.observe(::getLifecycle) { text ->
            if (text != null) {
                textView.append("$text\n")
            }
        }

        eventViewModel.string.observe(::getLifecycle) { text ->
            if (text != null) {
                textView.append("$text\n")
            }
        }

        eventViewModel.event.observe(::getLifecycle) { event ->
            if (event != null && !event.isHandled) {
                textView.append("${event.consume()}\n")
            }
        }

        eventViewModel.event.observe(::getLifecycle) { event ->
            if (event != null && !event.isHandled) {
                textView.append("${event.consume()}\n")
            }
        }

        eventViewModel.liveEvent.observe(
            this,
            Observer { event ->
                if (event != null && !event.isHandled) {
                    textView.append("${event.consume()}\n")
                }
            }
        )

        eventViewModel.liveEvent.observe(
            this,
            Observer { event ->
                if (event != null && !event.isHandled) {
                    textView.append("${event.consume()}\n")
                }
            }
        )

        sendDataButton1.setOnClickListener {
            textView.text = ""
            data.forEach { eventViewModel.string.value = it }
        }

        sendDataButton2.setOnClickListener {
            textView.text = ""
            data.forEach { eventViewModel.event.value = Event(it) }
        }

        sendDataButton3.setOnClickListener {
            textView.text = ""
            data.forEach { eventViewModel.liveEvent.postEvent(it) }
        }
    }
}
